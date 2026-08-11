package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.BookLoanResponse;
import org.example.entity.Book;
import org.example.entity.BookLoan;
import org.example.entity.Client;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.BookLoanRepository;
import org.example.repository.BookRepository;
import org.example.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookLoanService {
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final BookLoanRepository bookLoanRepository;

    public BookLoan create(Long bookId, Long clientId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", bookId));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client", clientId));
        BookLoan bookLoan = new BookLoan(client, book);
        return bookLoanRepository.save(bookLoan);
    }

    public List<BookLoanResponse> getAllWithDetails() {
        return bookLoanRepository.findAllWithDetails();
    }
}
