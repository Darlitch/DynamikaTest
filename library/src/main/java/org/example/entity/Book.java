package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(
            name = "book_seq",
            sequenceName = "book_id_seq",
            allocationSize = 50
    )
    private Long id;

    @NotBlank(message = "Название обязательно")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "Автор обязателен")
    @Column(name = "author", nullable = false)
    private String author;

    @NotBlank(message = "ISBN обязателен")
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;
}
