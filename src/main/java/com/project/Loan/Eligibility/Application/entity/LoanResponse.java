package com.project.Loan.Eligibility.Application.entity;

import java.util.Map;

public class LoanResponse {
	
	private boolean eligible;
	private String rejectionReason;
	private int approvedLoanAmount;
	private Map<String, Integer> emiBreakdown;
	
	public LoanResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanResponse(boolean eligible, String rejectionReason, int approvedLoanAmount,
			Map<String, Integer> emiBreakdown) {
		super();
		this.eligible = eligible;
		this.rejectionReason = rejectionReason;
		this.approvedLoanAmount = approvedLoanAmount;
		this.emiBreakdown = emiBreakdown;
	}

	public boolean isEligible() {
		return eligible;
	}

	public void setEligible(boolean eligible) {
		this.eligible = eligible;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public int getApprovedLoanAmount() {
		return approvedLoanAmount;
	}

	public void setApprovedLoanAmount(int approvedLoanAmount) {
		this.approvedLoanAmount = approvedLoanAmount;
	}

	public Map<String, Integer> getEmiBreakdown() {
		return emiBreakdown;
	}

	public void setEmiBreakdown(Map<String, Integer> emiBreakdown) {
		this.emiBreakdown = emiBreakdown;
	}

	@Override
	public String toString() {
		return "LoanResponse [eligible=" + eligible + ", rejectionReason=" + rejectionReason + ", approvedLoanAmount="
				+ approvedLoanAmount + ", emiBreakdown=" + emiBreakdown + "]";
	}

}
