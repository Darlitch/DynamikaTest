package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Book;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", id));
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book update(Long id, Book book) {
        Book existing = getById(id);
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setIsbn(book.getIsbn());
        return bookRepository.save(existing);
    }
}
