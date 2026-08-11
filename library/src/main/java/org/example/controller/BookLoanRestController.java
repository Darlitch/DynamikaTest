package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.BookLoanResponse;
import org.example.service.BookLoanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/loans")
public class BookLoanRestController {
    private final BookLoanService bookLoanService;

    @GetMapping
    public List<BookLoanResponse> getAll() {
        return bookLoanService.getAllWithDetails();
    }
}