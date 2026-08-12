package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Book;
import org.example.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "books/form";
        }
        bookService.create(book);
        return "redirect:/books";
    }

    @GetMapping
    public String getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model
    ) {
        Page<Book> books = bookService.getAll(
                PageRequest.of(page, size, Sort.by("author", "title", "id"))
        );
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        return "books/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Book book,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "books/form";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }
}
