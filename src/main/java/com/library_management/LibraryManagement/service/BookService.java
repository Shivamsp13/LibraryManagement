package com.library_management.LibraryManagement.service;

import com.library_management.LibraryManagement.DTO.BookDTO;
import com.library_management.LibraryManagement.entity.Book;
import com.library_management.LibraryManagement.repository.BookRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRespository bookrespository;

    public List<Book> getAllBooks() {
        return bookrespository.findAll();
    }

    public Book getBookById(Long id) {
        Book book=bookrespository.findById(id).
                orElseThrow(()->new RuntimeException("Book Not Found"));

        return book;
    }

    public Book addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setQuantity(bookDTO.getQuantity());
        book.setIsAvailable(bookDTO.isAvailable());

        return bookrespository.save(book);
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        Book oldBook=bookrespository.findById(id).
                orElseThrow(()->new RuntimeException("Book id is not available"));

        oldBook.setTitle(bookDTO.getTitle());
        oldBook.setAuthor(bookDTO.getAuthor());
        oldBook.setIsbn(bookDTO.getIsbn());
        oldBook.setQuantity(bookDTO.getQuantity());
        oldBook.setIsAvailable(bookDTO.isAvailable());

        return bookrespository.save(oldBook);
    }

    public void deleteBookById(Long id) {
        bookrespository.deleteById(id);
    }
}
