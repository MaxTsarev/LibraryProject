package ru.tsarev.library_project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.tsarev.library_project.model.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
	Optional<Person> findByFullName(String fullName);
}
