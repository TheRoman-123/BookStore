package com.bookstore.dto.auth;

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

    @NotBlank(message = "Username can't be empty")
    @Pattern(regexp = "^[_a-zA-Z][_a-zA-Z0-9]{2,50}$", message = "Provide valid username")
    private String username;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Provide valid email")
    @Size(min = 6, max = 254, message = "Email too long")
    private String email;

    @NotBlank(message = "Please provide a password")
    @Size(min = 8, max = 254, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_@#$%^&+=])(?=\\S+$)[-_0-9A-Za-z@#$%^&+=]{8,254}$",
            message = "Password must have at least 1 number, 1 letter Uppercase and Lowercase and 1 special character"
    )
    private String password;
}
