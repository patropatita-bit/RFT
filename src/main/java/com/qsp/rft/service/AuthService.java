package com.qsp.rft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.qsp.rft.dto.LoginResponse;
import com.qsp.rft.dto.RegisterRequest;
import com.qsp.rft.entity.User;
import com.qsp.rft.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    public void register(RegisterRequest r) {
        if (userRepository.existsByPhone(r.phone())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This number is already registered. Please log in");
        }
        User u = new User();
        u.setName(r.name().trim());
        u.setRestaurantName(r.restaurantName().trim());
        u.setPhone(r.phone());
        userRepository.save(u);
        otpService.send(r.phone());
    }

    public void requestOtp(String phone) {
        if (!userRepository.existsByPhone(phone)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "This number is not registered. Please register first");
        }
        otpService.send(phone);
    }

    public LoginResponse verify(String phone, String otp) {
        otpService.verify(phone, otp);
        User u = userRepository.findByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new LoginResponse(u.getId(), u.getName(), u.getRestaurantName());
    }
}