package ru.tsarev.library_project.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.tsarev.library_project.model.Person;

@Component
public class PersonDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Person> index() {
		return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
	}

	public Person show(int id) {
		return jdbcTemplate.query("SELECT * FROM Person WHERE personId=?", new Object[] { id },
				new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
	}

	public Person findPersonBook(int id) {
		return jdbcTemplate.query(
				"SELECT Person.personId, Person.name, Person.yearOfBirth FROM Person JOIN Book ON Person.personId=Book.personId WHERE Book.bookId=?",
				new Object[] { id }, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
	}

	public void save(Person person) {
		jdbcTemplate.update("INSERT INTO Person(name, yearOfBirth) VALUES(?, ?)", person.getName(),
				person.getYearOfBirth());
	}

	public void update(int id, Person updatedPerson) {
		jdbcTemplate.update("UPDATE Person SET name=?, yearOfBirth=? WHERE personId=?", updatedPerson.getName(),
				updatedPerson.getYearOfBirth(), id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Person WHERE personId=?", id);
	}

}
