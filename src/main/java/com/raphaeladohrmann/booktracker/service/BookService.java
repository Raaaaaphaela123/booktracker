package com.raphaeladohrmann.booktracker.service;

import com.raphaeladohrmann.booktracker.model.Book;
import com.raphaeladohrmann.booktracker.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // Konstruktor-Injektion: Spring reicht das Repository hier rein
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Methode zur Ausgabe aller Bücher
    public List<Book> findAll() {
        // Holt jetzt alle Einträge aus der echten Datenbank-Tabelle
        return bookRepository.findAll();
    }

    // Methode zum Hinzufügen eines Buchs
    public Book add(Book book) {
        // Speichert das Objekt dauerhaft in der Datenbank
        return bookRepository.save(book);
    }

    public Book update(Long id, Book updatedBook) {
        // Wir suchen das alte Buch, falls es existiert
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setStatus(updatedBook.getStatus());
                    book.setRating(updatedBook.getRating());
                    return bookRepository.save(book); // Speichert die Änderungen
                })
                .orElseThrow(() -> new RuntimeException("Buch nicht gefunden"));
    }
}
