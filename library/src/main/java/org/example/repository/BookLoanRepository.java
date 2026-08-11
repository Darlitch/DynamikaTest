package org.example.repository;

import org.example.entity.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
}
