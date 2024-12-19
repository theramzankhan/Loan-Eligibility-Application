package com.project.Loan.Eligibility.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Loan.Eligibility.Application.entity.Loan;
import com.project.Loan.Eligibility.Application.entity.LoanResponse;
import com.project.Loan.Eligibility.Application.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/save-data")
	public ResponseEntity<String> fetchAndSaveData() {
		try {
		loanService.fetchAndSaveData();
		return new ResponseEntity<>("Loan data saved to database successfully!", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace(); // Logs the exception for debugging
			String errorMessage = "Issue: Data couldn't saved to the database." + e.getMessage();
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/eligibility")
	public ResponseEntity<?> checkEligibility(@RequestBody Loan loan) { // ? Generic ResponseEntity (<?>): The use of <?> allows the method to return different types of response objects (LoanResponse on success and String on failure).
		try {
//			LoanResponse loanResponse = loanService.calculateEligibility(loan);
//	        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
			return new ResponseEntity<>(loanService.calculateEligibility(loan), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			String errorMessage = "Issue: Some problem while processing the request.";
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
