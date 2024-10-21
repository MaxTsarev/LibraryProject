package ru.tsarev.library_project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tsarev.library_project.model.Book;

public interface BooksRepository extends JpaRepository<Book, Integer> {
	List<Book> findByTitleStartingWith(String title);
}
