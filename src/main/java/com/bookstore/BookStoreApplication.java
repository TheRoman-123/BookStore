package com.bookstore;

import com.bookstore.config.SecurityUtility;
import com.bookstore.dto.UserDto;
import com.bookstore.entity.security.Role;
import com.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.bookstore.config.SecurityUtility.passwordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class BookStoreApplication implements CommandLineRunner {

    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Override
    public void run(String... args) {

        UserDto user1 = new UserDto();
        user1.setFirstname("Test");
        user1.setLastname("Test");
        user1.setUsername("test");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("Bca1234$"));
        user1.setEmail("ewerdfsd@gmail.com");
        user1.setRole(Role.USER);

        try {
            userService.createUser(user1);
        } catch (Exception ignored) {}

        UserDto user2 = new UserDto();
        user2.setFirstname("Admin");
        user2.setLastname("Admin");
        user2.setUsername("admin");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("Abc1234$"));
        user2.setEmail("efgfdfgbgggbggcx@gmail.com");
        user2.setRole(Role.ADMIN);

        try {
            userService.createUser(user2);
        } catch (Exception ignored) {}

        UserDto user = UserDto.builder()
                .username("roman")
                .email("roma.tcheloweck@gmail.com")
                .password(passwordEncoder().encode("Abc1234$"))
                .firstname("Roman")
                .lastname("Rudi")
                .phone("+37360147036")
                .enabled(true)
                .role(Role.ADMIN)
                .build();

        try {
            userService.createUser(user);
        } catch (Exception ignored) {}
    }
}
