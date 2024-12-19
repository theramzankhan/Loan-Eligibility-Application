package com.project.Loan.Eligibility.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Loan.Eligibility.Application.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, String> {
	
}
