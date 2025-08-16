package com.library_management.LibraryManagement.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestDTO {
    private String Username;
    private String Email;
    private String Password;
}
