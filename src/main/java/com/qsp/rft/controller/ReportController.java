package com.qsp.rft.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.rft.service.ReportService;

@RestController
@RequestMapping("/api/Reports")
public class ReportController {

	   @Autowired
	    private ReportService reportService;

	    @GetMapping("/profit")
	    public BigDecimal getProfit() {
	        return reportService.getProfit();
	    }
}
