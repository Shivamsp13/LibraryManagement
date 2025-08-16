package com.library_management.LibraryManagement.controller;

import com.library_management.LibraryManagement.entity.IssueRecord;
import com.library_management.LibraryManagement.service.IssueRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issuerecords")
public class IssueRecordController {

    @Autowired
    private IssueRecordService issuerecordservice;
    @PostMapping("/issuerecords/{bookid}")
    public ResponseEntity<IssueRecord> issueTheBook(@PathVariable Long bookID) {
        return ResponseEntity.ok(issuerecordservice.issueTheBook(bookID));
    }

    @PostMapping("/returthebook/{issuerecordid}")
    public ResponseEntity<IssueRecord> returnTheBook(@PathVariable Long issueRecordId){
        return ResponseEntity.ok(issuerecordservice.returnTheBook(issueRecordId));
    }

}
