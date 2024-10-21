package ru.tsarev.library_project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Person")
public class Person {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "full_name")
	@NotEmpty(message = "Данное поле не должно пыть пустым!")
	@Size(min = 3, max = 50, message = "Имя должно содержать от 3 до 50 символов!")
	private String fullName;

	@Column(name = "year_of_birth")
	@Min(value = 1925, message = "Некорректный год рождения!")
	@Max(value = 2014, message = "Некорректный год рождения!")
	private int yearOfBirth;

	@OneToMany(mappedBy = "owner")
	private List<Book> books;

	public Person() {
	}

	public Person(String fullName, int yearOfBirth) {
		this.fullName = fullName;
		this.yearOfBirth = yearOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
