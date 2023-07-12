package com.example.demo.repository;

import com.example.demo.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface LoanRepo extends JpaRepository<Loans, Long> {
    //@PreAuthorize("hasRole('ROOT')")
    List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);
}
