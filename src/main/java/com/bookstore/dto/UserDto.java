package com.bookstore.dto;

import com.bookstore.entity.User;
import com.bookstore.entity.security.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.bookstore.entity.User}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {
    @Positive(message = "Provide positive id")
    private Long id;

    @Pattern(message = "Provide valid username", regexp = "[a-zA-Z][-A-Za-z_0-9]{1,50}")
    @NotBlank(message = "Provide username")
    private String username;


    @NotBlank(message = "Provide password")
    private String password;

    private String firstname;

    private String lastname;

    @Email(message = "Provide valid email")
    @NotBlank(message = "Provide email")
    private String email;

    private String phone;

    boolean enabled;

    private Role role;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhone(phone);
        user.setPassword(password);
        return user;
    }
}
