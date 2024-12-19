package com.project.Loan.Eligibility.Application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Loan {
	
	@Id
	private String userId;
	private int monthlyIncome;
	private int existingLoanObligations;
	private int creditScore;
	private int requestedLoanAmount;
	
	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Loan(String userId, int monthlyIncome, int existingLoanObligations, int creditScore,
			int requestedLoanAmount) {
		super();
		this.userId = userId;
		this.monthlyIncome = monthlyIncome;
		this.existingLoanObligations = existingLoanObligations;
		this.creditScore = creditScore;
		this.requestedLoanAmount = requestedLoanAmount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(int monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public int getExistingLoanObligations() {
		return existingLoanObligations;
	}

	public void setExistingLoanObligations(int existingLoanObligations) {
		this.existingLoanObligations = existingLoanObligations;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	public int getRequestedLoanAmount() {
		return requestedLoanAmount;
	}

	public void setRequestedLoanAmount(int requestedLoanAmount) {
		this.requestedLoanAmount = requestedLoanAmount;
	}

	@Override
	public String toString() {
		return "Loan [userId=" + userId + ", monthlyIncome=" + monthlyIncome + ", existingLoanObligations="
				+ existingLoanObligations + ", creditScore=" + creditScore + ", requestedLoanAmount="
				+ requestedLoanAmount + "]";
	}

}
