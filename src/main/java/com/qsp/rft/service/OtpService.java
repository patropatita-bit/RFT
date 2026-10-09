package com.qsp.rft.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.HexFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OtpService {

    private static final Duration VALID_FOR = Duration.ofMinutes(5);
    private static final Duration RESEND_GAP = Duration.ofSeconds(30);
    private static final int MAX_WRONG_TRIES = 3;

    private static class Entry {
        String hash;
        Instant expiresAt;
        Instant sentAt;
        int wrongTries;
    }

    private final Map<String, Entry> store = new ConcurrentHashMap<>();
    private final SecureRandom random = new SecureRandom();

    public void send(String phone) {
        Entry old = store.get(phone);
        if (old != null && Instant.now().isBefore(old.sentAt.plus(RESEND_GAP))) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS,
                    "Please wait 30 seconds before asking for a new OTP");
        }

        String otp = String.format("%06d", random.nextInt(1_000_000));

        Entry e = new Entry();
        e.hash = hash(phone, otp);
        e.sentAt = Instant.now();
        e.expiresAt = e.sentAt.plus(VALID_FOR);
        store.put(phone, e);

        // TESTING ONLY: later this line is replaced by a real SMS service.
        System.out.println("OTP for " + phone + " is " + otp);
    }

    public void verify(String phone, String otp) {
        Entry e = store.get(phone);
        if (e == null) {
            throw bad("Please request an OTP first");
        }
        if (Instant.now().isAfter(e.expiresAt)) {
            store.remove(phone);
            throw bad("OTP expired. Please request a new one");
        }
        if (e.wrongTries >= MAX_WRONG_TRIES) {
            store.remove(phone);
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS,
                    "Too many wrong tries. Please request a new OTP");
        }
        boolean match = MessageDigest.isEqual(
                hash(phone, otp).getBytes(StandardCharsets.UTF_8),
                e.hash.getBytes(StandardCharsets.UTF_8));
        if (!match) {
            e.wrongTries++;
            throw bad("Wrong OTP");
        }
        store.remove(phone);   // an OTP can be used only once
    }

    private ResponseStatusException bad(String message) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }

    private String hash(String phone, String otp) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest((phone + ":" + otp).getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException(ex);
        }
    }
}