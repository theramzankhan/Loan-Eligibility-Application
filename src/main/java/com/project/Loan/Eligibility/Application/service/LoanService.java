package com.project.Loan.Eligibility.Application.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.Loan.Eligibility.Application.entity.Loan;
import com.project.Loan.Eligibility.Application.entity.LoanResponse;
import com.project.Loan.Eligibility.Application.repository.LoanRepository;

@Service
public class LoanService {
	
	private final String url = "https://prepstripe.com/loan_task_payloads.json";
	
	@Autowired
	private LoanRepository loanRepository;
	
	public void fetchAndSaveData() {
		
//		throw new RuntimeException("Simulated Exception: Something went wrong while saving data."); intentionally wrote to test the exception message
		
		RestTemplate restTemplate = new RestTemplate();
		JsonNode loanDetails = restTemplate.getForObject(url, JsonNode.class);
		
		loanDetails.forEach(entry -> {
			Loan data = new Loan();
			data.setUserId(entry.get("userId").asText());
			data.setMonthlyIncome(entry.get("monthlyIncome").asInt());
			data.setExistingLoanObligations(entry.get("existingLoanObligations").asInt());
			data.setCreditScore(entry.get("creditScore").asInt());
			data.setRequestedLoanAmount(entry.get("requestedLoanAmount").asInt());
			
			loanRepository.save(data);
		});
	}
	
	public LoanResponse calculateEligibility(Loan loan) {
		
		int monthlyIncome = loan.getMonthlyIncome();
		int existingLons = loan.getExistingLoanObligations();
		int creditScore = loan.getCreditScore();
		int requestedAmount = loan.getRequestedLoanAmount();
		
		LoanResponse response = new LoanResponse();
		
		if(monthlyIncome < 30000) {
			response.setEligible(false);
			response.setRejectionReason("Monthly income is below the required minimum.");
			return response;
		}
		
		if(existingLons > monthlyIncome * 0.4) {
			response.setEligible(false);
			response.setRejectionReason("Existing loan obligations exceed 40% of monthly income.");
			return response;
		}
		
		if(creditScore < 700) {
			response.setEligible(false);
			response.setRejectionReason("Credit score is below 700.");
			return response;
		}
		
		int maxLoanAmount = 10 * monthlyIncome;
		if(requestedAmount > maxLoanAmount) {
			response.setEligible(false);
			response.setRejectionReason("Requested loan amount exceeds the maximum allowed limit.");
			return response;
		}
		
		Map<String, Integer> emiBreakDown = Map.of(
				"8%", calculateEMI(requestedAmount, 8, 12),
				"10%", calculateEMI(requestedAmount, 10, 12),
				"12%", calculateEMI(requestedAmount, 12, 12)
		);
		
		response.setEligible(true);
		response.setApprovedLoanAmount(requestedAmount);
		response.setEmiBreakdown(emiBreakDown);
		
		return response;
	}
	
	private int calculateEMI(int principal, double rate, int months) {
		double monthlyRate = rate / (12 * 100);
		return (int) ((principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months)));
	}

}
