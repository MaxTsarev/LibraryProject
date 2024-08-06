package ru.tsarev.library_project.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

	private int personId;

	@NotEmpty(message = "Данное поле не должно пыть пустым!")
	@Size(min = 3, max = 50, message = "Имя должно содержать от 3 до 50 символов!")
	private String name;

	@Min(value = 1925, message = "Некорректный год рождения!")
	@Max(value = 2014, message = "Некорректный год рождения!")
	private int yearOfBirth;

	public Person() {
	}

	public Person(int personId, String name, int yearOfBirth) {
		this.personId = personId;
		this.name = name;
		this.yearOfBirth = yearOfBirth;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Person guest = (Person) obj;
		return personId == guest.personId && (name == guest.name || (name != null && name.equals(guest.getName())))
				&& yearOfBirth == guest.yearOfBirth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + personId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + yearOfBirth;
		return result;
	}

	@Override
	public String toString() {
		return "value: " + personId + ", " + name + ", " + yearOfBirth;
	}

}
