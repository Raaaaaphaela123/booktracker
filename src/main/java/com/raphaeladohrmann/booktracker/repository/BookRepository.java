package com.raphaeladohrmann.booktracker.repository;

import com.raphaeladohrmann.booktracker.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * JpaRepository bietet uns alle Standard-Operationen:
 * save(), findAll(), findById(), deleteById() usw.
 * Wir müssen hier keinen einzigen SQL-Befehl selbst schreiben,
 * außer wir brauchen individuelle Abfragen.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
