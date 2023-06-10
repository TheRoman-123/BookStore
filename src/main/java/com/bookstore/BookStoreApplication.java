package com.bookstore;

import com.bookstore.config.SecurityUtility;
import com.bookstore.entity.User;
import com.bookstore.entity.security.Role;
import com.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class BookStoreApplication implements CommandLineRunner {

    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User user1 = new User();
        user1.setFirstName("Test");
        user1.setLastName("Test");
        user1.setUsername("test");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("Bca1234$"));
        user1.setEmail("ewerdfsd@gmail.com");
        user1.setRole(Role.USER);

        userService.createUser(user1);

        User user2 = new User();
        user2.setFirstName("Admin");
        user2.setLastName("Admin");
        user2.setUsername("admin");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("Abc1234$"));
        user2.setEmail("efgfdfgbgggbggcx@gmail.com");
        user2.setRole(Role.ADMIN);

        userService.createUser(user2);
    }
}
