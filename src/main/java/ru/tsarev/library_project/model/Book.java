package ru.tsarev.library_project.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

	private int bookId;

	@NotEmpty(message = "Данное поле не должно пыть пустым!")
	@Size(min = 2, max = 50, message = "Название книги должно содержать от 2 до 50 символов!")
	private String title;

	@NotEmpty(message = "Данное поле не должно пыть пустым!")
	@Size(min = 3, max = 50, message = "Имя автора должно содержать от 3 до 50 символов!")
	private String author;

	@Min(value = 0, message = "Некорректный год написания книги!")
	@Max(value = 2025, message = "Некорректный год написания книги!")
	private int yearOfWriting;

	public Book(int bookId, String title, String author, int yearOfWriting) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.yearOfWriting = yearOfWriting;
	}

	public Book() {
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYearOfWriting() {
		return yearOfWriting;
	}

	public void setYearOfWriting(int yearOfWriting) {
		this.yearOfWriting = yearOfWriting;
	}

}
