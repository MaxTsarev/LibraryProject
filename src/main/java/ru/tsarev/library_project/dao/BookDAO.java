package ru.tsarev.library_project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.tsarev.library_project.model.Book;

@Component
public class BookDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Book> index() {
		return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
	}

	public Optional<Book> show(String title) {
		return jdbcTemplate.query("SELECT * FROM Book WHERE title=?", new Object[] { title },
				new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
	}

	public Book show(int id) {
		return jdbcTemplate.query("SELECT * FROM Book WHERE bookId=?", new Object[] { id },
				new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
	}

	public void save(Book book) {
		jdbcTemplate.update("INSERT INTO Book(title, author, yearOfWriting) VALUES(?, ?, ?)", book.getTitle(),
				book.getAuthor(), book.getYearOfWriting());
	}

	public void update(int id, Book updatedBook) {
		jdbcTemplate.update("UPDATE Book SET title=?, author=?, yearOfWriting=? WHERE bookId=?", updatedBook.getTitle(),
				updatedBook.getAuthor(), updatedBook.getYearOfWriting(), id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Book WHERE bookId=?", id);
	}

	public void addBookPerson(int personId, int bookId) {
		jdbcTemplate.update("UPDATE Book SET personId=? WHERE bookId=?", personId, bookId);
	}

	public List<Book> getBooksOfPerson(int id) {
		return jdbcTemplate.query("SELECT * FROM Book WHERE personId=?", new Object[] { id },
				new BeanPropertyRowMapper<>(Book.class));
	}

	public void release(int id) {
		jdbcTemplate.update("UPDATE Book SET personId=NULL WHERE bookId=?", id);
	}

}
