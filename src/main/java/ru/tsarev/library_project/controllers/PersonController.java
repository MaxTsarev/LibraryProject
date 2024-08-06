package ru.tsarev.library_project.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.tsarev.library_project.dao.BookDAO;
import ru.tsarev.library_project.dao.PersonDAO;
import ru.tsarev.library_project.model.Person;

@Controller
@RequestMapping("/people")
public class PersonController {

	private final PersonDAO personDAO;
	private final BookDAO bookDAO;

	@Autowired
	public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
		this.personDAO = personDAO;
		this.bookDAO = bookDAO;
	}

	// список всех пользователей
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("people", personDAO.index());
		return "people/index";
	}

	// конкретный человек
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", personDAO.show(id));
		model.addAttribute("books", bookDAO.getBooksOfPerson(id));
		return "people/show";
	}

	// сохрание нового пользователя
	@GetMapping("/new")
	public String saveNewPerson(@ModelAttribute("person") Person person) {
		return "people/new";
	}

	@PostMapping()
	public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "people/new";
		personDAO.save(person);
		return "redirect:/people";
	}

	// изменение данных человека
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("person", personDAO.show(id));
		return "people/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
			@PathVariable("id") int id) {
		if (bindingResult.hasErrors())
			return "people/edit";
		personDAO.update(id, person);
		return "redirect:/people";
	}

	// удаление данных о человеке
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		personDAO.delete(id);
		return "redirect:/people";
	}

}
