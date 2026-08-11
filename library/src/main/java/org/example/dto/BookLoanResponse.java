package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BookLoanResponse {
    private String fullName;
    private LocalDate birthDate;

    private String title;
    private String author;
    private String isbn;

    private LocalDateTime takenAt;
}
