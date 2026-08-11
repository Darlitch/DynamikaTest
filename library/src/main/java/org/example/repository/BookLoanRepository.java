package org.example.repository;

import org.example.dto.BookLoanResponse;
import org.example.entity.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    @Query("SELECT new org.example.dto.BookLoanResponse(" +
            "c.fullName, " +
            "c.birthDate, " +
            "b.title, " +
            "b.author, " +
            "b.isbn, " +
            "bl.takenAt) " +
           "FROM BookLoan bl " +
           "JOIN bl.book b " +
           "JOIN bl.client c")
    List<BookLoanResponse> findAllWithDetails();
}
