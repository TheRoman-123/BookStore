package com.bookstore.controller;

import com.bookstore.utility.MailConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

//    private final UserService userService;

    private final MailConstructor mailConstructor;

    private final JavaMailSender mailSender;

//    @PostMapping("/newUser")
//    public ResponseEntity<String> newUser(
//            @RequestBody HashMap<String, String> mapper
//    ) {
//        String username = mapper.get("username");
//        String userEmail = mapper.get("email");
//
//        if (userService.findByUsername(username) != null) {
//            return new ResponseEntity<>("usernameExists", HttpStatus.BAD_REQUEST);
//        }
//        if (userService.findByEmail(userEmail) != null) {
//            return new ResponseEntity<>("emailExists", HttpStatus.BAD_REQUEST);
//        }
//
//        User user = new User();
//        user.setUsername(username);
//        user.setEmail(userEmail);
//        String password = SecurityUtility.randomPassword();
//        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
//        user.setPassword(encryptedPassword);
//
//        user.setRole(Role.USER);
//        userService.createUser(user);
//
//        SimpleMailMessage email = mailConstructor.constructNewUserEmail(user, password);
//        mailSender.send(email);
//
//        return new ResponseEntity<>("User added Successfully!", HttpStatus.OK);
//    }

//    @PostMapping("/forgetPassword")
//    public ResponseEntity<String> forgetPassword(
//            @RequestBody HashMap<String, String> mapper,
//            HttpServletRequest request
//    ) {
//        User user = userService.findByEmail(mapper.get("email"));
//
//        if (user == null) {
//            return new ResponseEntity<>("Email not found", HttpStatus.BAD_REQUEST);
//        }
//
//        String password = SecurityUtility.randomPassword();
//        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
//        user.setPassword(encryptedPassword);
//        userService.save(user);
//
//        SimpleMailMessage newEmail = mailConstructor.constructNewUserEmail(user, password);
//        mailSender.send(newEmail);
//
//        return new ResponseEntity<>("Email sent!", HttpStatus.OK);
//    }
//
//    @GetMapping("/getCurrentUser")
//    public User getUser(Principal principal) {
//        String username = principal.getName();
//        User user = new User();
//        if (username != null) {
//            user = userService.findByUsername(username);
//        }
//        return user;
//    }
//
//    //    @PostMapping(value = "/updateUserInfo")
//    @PutMapping(value = "/updateUserInfo")
//    public ResponseEntity<String> updateUser(
//            @RequestBody HashMap<String, Object> mapper
//    ) throws Exception {
//
//        int id = (int) mapper.get("id");
//        String username = (String) mapper.get("username");
//        String email = (String) mapper.get("email");
//        String firstName = (String) mapper.get("firstName");
//        String lastName = (String) mapper.get("lastName");
//        String password = (String) mapper.get("currentPassword");
//        String newPassword = (String) mapper.get("newPassword");
//
//        User user = userService.findById(id);
//
//        if (user == null) {
//            throw new Exception("User not found");
//        }
//        if (userService.findByUsername(username) != null) {
//            if (userService.findByUsername(username).getId() != user.getId()) {
//                return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
//            }
//        }
//
//        if (userService.findByEmail(email) != null) {
//            if (userService.findByEmail(email).getId() != user.getId()) {
//                return new ResponseEntity<>("Email not found", HttpStatus.BAD_REQUEST);
//            }
//        }
//
//        PasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
//        String dbPassword = user.getPassword();
//        if (password != null)
//            if (passwordEncoder.matches(password, dbPassword)) {
//                if (newPassword != null && !newPassword.isEmpty()) {
//                    user.setPassword(passwordEncoder.encode(newPassword));
//                }
//                user.setEmail(email);
//            } else {
//                return new ResponseEntity<>("Incorrect current Password", HttpStatus.BAD_REQUEST);
//            }
//
//        user.setUsername(username);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//
//        userService.save(user);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
