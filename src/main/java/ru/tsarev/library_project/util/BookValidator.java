package ru.tsarev.library_project.util;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.tsarev.library_project.dao.BookDAO;
import ru.tsarev.library_project.model.Book;

@Component
public class BookValidator implements Validator{
	
	private final BookDAO bookDAO;
	
	@Autowired
	public BookValidator(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Book book = (Book) target;
		if(bookDAO.show(book.getTitle()).isPresent()) {
			errors.rejectValue("title", "", "Название книги не должно повторяться!");
		}
		
	}

}
