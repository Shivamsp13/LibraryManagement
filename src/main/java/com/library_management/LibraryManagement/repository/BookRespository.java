package com.library_management.LibraryManagement.repository;

import com.library_management.LibraryManagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

public interface BookRespository extends JpaRepository<Book, Long> {

}
