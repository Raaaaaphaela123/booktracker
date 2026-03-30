package com.raphaeladohrmann.booktracker.controller;

import com.raphaeladohrmann.booktracker.model.Book;
import com.raphaeladohrmann.booktracker.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Sagt Spring, dass jede Methode ihre Rückgabewerte direkt als JSON in den HTTP-Antwortkörper (Response Body) schreibt, statt eine HTML-Seite zu suchen.
@RequestMapping("/api/books") // Definiert den Basis-Pfad für alle Endpunkte in dieser Klasse. Alle Anfragen an http://localhost:8080/api/books landen hier.
public class BookController {

    // Das Feld ist final -> Es MUSS über den Konstruktor gesetzt werden.
    private final BookService bookService;

    /*
     * Constructor Injection:
     * Spring sieht diesen Konstruktor und weiß: "Um einen BookController zu bauen,
     * brauche ich einen BookService." Spring sucht diesen Service im Kontext
     * und reicht ihn hier hinein.
     *
     * Hinweis: Seit Spring 4.3 ist das @Autowired am Konstruktor optional,
     * wenn es nur einen Konstruktor gibt.
     *
     * Alternative: @RequiredArgsContructor Annotation über der Klasse (Lombok)
     */
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /*
     * HTTP GET: Wird aufgerufen, wenn man die URL im Browser oder via Tool öffnet.
     * Erwartet keine Daten, sondern "holt" Informationen ab.
     * @return Eine Liste aller Bücher, die Spring automatisch in ein JSON-Array umwandelt.
     */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    /*
     * HTTP POST: Wird genutzt, um neue Daten zum Server zu schicken.
     * @RequestBody: Das Herzstück der REST-API. Spring nimmt das ankommende JSON-Paket
     * aus der Anfrage und versucht, es automatisch in ein Java-Objekt (Book) zu "mappen".
     * @param book Das vom Client (z.B. Postman/React) gesendete Buch-Objekt.
     * @return Eine Bestätigungsnachricht als einfacher Text.
     */
    @PostMapping
    public String createBook(@RequestBody Book book) {
        // Die Daten werden an den Service zur (temporären) Speicherung weitergegeben
        bookService.add(book);

        // Rückgabe einer Erfolgsmeldung an den Client
        return "Buch erfolgreich empfangen: " + book.getTitle();
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Erfolg: Buch mit ID " + id + " wurde aus der Datenbank gelöscht.";
    }
}
