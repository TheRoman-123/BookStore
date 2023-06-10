package com.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "First name can't be empty")
    @Pattern(regexp = "[a-zA-Z]{2,50}", message = "Provide valid firstname")
    private String firstName;

    @NotBlank(message = "Last name can't be empty")
    @Pattern(regexp = "[a-zA-Z]{2,50}", message = "Provide valid lastname")
    private String lastName;

    @Email(message = "Provide valid email")
    @Size(min = 6, max = 254, message = "Email too long")
    private String email;

    @NotBlank(message = "Please provide a password")
    @Size(min = 8, max = 50, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$)[0-9A-Za-z@#$%^&+=]{8,}$",
            message = "Password must have at least 1 number, 1 letter Uppercase and Lowercase and 1 special character"
    )
    private String password;
}
