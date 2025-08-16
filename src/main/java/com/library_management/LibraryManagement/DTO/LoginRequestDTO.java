package com.library_management.LibraryManagement.DTO;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class LoginRequestDTO {
    private String Username;
    @Getter
    @Setter
    private String Password;

}
