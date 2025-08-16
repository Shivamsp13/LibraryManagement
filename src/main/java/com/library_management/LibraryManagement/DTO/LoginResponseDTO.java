package com.library_management.LibraryManagement.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class LoginResponseDTO {
    private String Token;
    private String Username;
    private Set<String> Roles;

}
