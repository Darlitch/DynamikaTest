package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.BookLoanService;
import org.example.service.BookService;
import org.example.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/loans")
public class BookLoanController {

    private final BookLoanService bookLoanService;
    private final BookService bookService;
    private final ClientService clientService;


    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("books", bookService.getAll());
        model.addAttribute("clients", clientService.getAll());
        return "loans/form";
    }


    @PostMapping("/create")
    public String create(@RequestParam Long clientId, @RequestParam Long bookId) {
        bookLoanService.create(clientId, bookId);
        return "redirect:/loans/create";
    }
}