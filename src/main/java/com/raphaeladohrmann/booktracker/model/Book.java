package com.raphaeladohrmann.booktracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                // Generiert Getter, Setter, toString, equals/hashCode
@NoArgsConstructor   // Generiert leeren Konstruktor
@AllArgsConstructor  // Generiert Konstruktor mit allen Feldern
@Entity              // Markiert die Klasse als Datenbank-Tabelle
@Table(name = "books") // ist optional  und benennt die Tabelle explizit
public class Book {

    @Id // Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Datenbank zählt ID automatisch hoch
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int rating;      // 1-5 Sterne
    private String status;   // z.B. "Gelesen", "Geplant"

}
