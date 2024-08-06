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
import ru.tsarev.library_project.model.Book;
import ru.tsarev.library_project.model.Person;
import ru.tsarev.library_project.util.BookValidator;

@Controller
@RequestMapping("/books")
public class BookController {

	private final BookDAO bookDAO;
	private final PersonDAO personDAO;
	private final BookValidator bookValidator;

	@Autowired
	public BookController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
		this.bookDAO = bookDAO;
		this.personDAO = personDAO;
		this.bookValidator = bookValidator;
	}

	// список всех книг
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("books", bookDAO.index());
		return "books/indexBook";
	}

	// конкретная книга
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model, @ModelAttribute("man") Person person) {
		model.addAttribute("book", bookDAO.show(id));
		model.addAttribute("person", personDAO.findPersonBook(id));
		model.addAttribute("people", personDAO.index());
		return "books/showBook";
	}

	// сохрание новой книги
	@GetMapping("/new")
	public String saveNewPerson(@ModelAttribute("book") Book book) {
		return "books/newBook";
	}

	@PostMapping()
	public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		bookValidator.validate(book, bindingResult);
		if (bindingResult.hasErrors())
			return "books/newBook";
		bookDAO.save(book);
		return "redirect:/books";
	}

	// изменение данных о книге
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("book", bookDAO.show(id));
		return "books/editBook";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
			@PathVariable("id") int id) {
		bookValidator.validate(book, bindingResult);
		if (bindingResult.hasErrors())
			return "books/editBook";
		bookDAO.update(id, book);
		return "redirect:/books";
	}

	// назначить книгу человеку
	@PatchMapping("/{id}/add")
	public String appoint(@ModelAttribute("man") Person person, BindingResult bindingResult,
			@PathVariable("id") int bookId) {
		if (bindingResult.hasErrors())
			return "books/editBook";
		bookDAO.addBookPerson(person.getPersonId(), bookId);
		return String.format("redirect:/books/%d", bookId);
	}

	// освободить книгу
	@PatchMapping("/del/{id}")
	public String release(@PathVariable("id") int id) {
		bookDAO.release(id);
		return String.format("redirect:/books/%d", id);
	}

	// удаление данных о книге
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		bookDAO.delete(id);
		return "redirect:/books";
	}

}
