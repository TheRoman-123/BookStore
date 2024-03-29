package com.bookstore.dto.auth;

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
public class AuthenticationRequest {

    @NotBlank(message = "Please provide username or email")
    @Size(max = 254, message = "Provided username or email too long")
    private String emailOrUsername;

    @NotBlank(message = "Please provide a password")
    @Size(min = 8, max = 50, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$)[0-9A-Za-z@#$%^&+=]{8,}$",
            message = "Password must have at least 1 number, 1 letter Uppercase and Lowercase and 1 special character"
    )
    private String password;
}
