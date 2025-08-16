package com.library_management.LibraryManagement.service;

import com.library_management.LibraryManagement.entity.Book;
import com.library_management.LibraryManagement.entity.IssueRecord;
import com.library_management.LibraryManagement.entity.User;
import com.library_management.LibraryManagement.repository.BookRespository;
import com.library_management.LibraryManagement.repository.IssueRecordRepository;
import com.library_management.LibraryManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.time.LocalDate;

@Service
public class IssueRecordService {

    @Autowired
    private IssueRecordRepository issuerecordrepository;

    @Autowired
    private BookRespository bookrespository;

    @Autowired
    private UserRepository userrepository;

    public IssueRecord issueTheBook(Long bookID) {
        Book book=bookrespository.findById(bookID)
                .orElseThrow(()->new RuntimeException("bookID is not valid"));

        if(book.getQuantity()<=0 || !book.getIsAvailable()) {
            throw new RuntimeException("Book is not available");
        }

        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userrepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("username not found"));

        IssueRecord issuerecord;
        issuerecord = new IssueRecord();
        issuerecord.setIssueDate(LocalDate.now());
        issuerecord.setDueDate(LocalDate.now().plusDays(14));
        issuerecord.setIsReturned(false);
        issuerecord.setUser(user);
        issuerecord.setBook(book);

        book.setQuantity(book.getQuantity()-1);
        if(book.getQuantity()==0){
            book.setIsAvailable(false);
        }

        bookrespository.save(book);
        return issuerecordrepository.save(issuerecord);
    }

    public IssueRecord returnTheBook(Long issueRecordId) {
        IssueRecord issuerecord=issuerecordrepository.findById(issueRecordId)
                .orElseThrow(()-> new RuntimeException("Book ID not Available"));

        if(issuerecord.getIsReturned()){
            throw new RuntimeException("Book Is Already Returned");
        }

        Book book=issuerecord.getBook();
        book.setQuantity(book.getQuantity()+1);
        book.setIsAvailable(true);
        bookrespository.save(book);

        issuerecord.setReturnDate(LocalDate.now());
        issuerecord.setIsReturned(true);
        return issuerecordrepository.save(issuerecord);
    }
}
