package com.library_management.LibraryManagement.repository;

import com.library_management.LibraryManagement.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRecordRepository extends JpaRepository<IssueRecord,Long> {

}
