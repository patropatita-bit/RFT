package com.qsp.rft.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.rft.dto.DashboardResponse;
import com.qsp.rft.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardService;
	   @GetMapping
	    public DashboardResponse getDashboard(@RequestParam LocalDate date) {
	        return dashboardService.getDashboard(date);
	    }
} 