package com.bookstore.utility;

import com.bookstore.entity.Order;
import com.bookstore.entity.User;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class MailConstructor {

    private final Environment env;

    private final TemplateEngine templateEngine;

    public SimpleMailMessage constructNewUserEmail(User user, String password) {
        String message = "\nPlease use the following credentials to log in and edit your personal information"
                + "\nUsername: " + user.getUsername() + "\nPassword: " + password;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setText(message);
        email.setFrom(env.getProperty("support.email"));

        return email;

    }

    public MimeMessagePreparator constructOrderConfirmationEmail(User user, Order order) {
        Context context = new Context();
        context.setVariable("order", order);
        context.setVariable("user", user);
//        context.setVariable("cartItemList", order.getOrderItemList());
        String text = templateEngine.process("orderConfirmationEmailTemplate", context);

        return mimeMessage -> {
            MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
            email.setTo(user.getEmail());
            email.setSubject("Order Confirmation - " + order.getId());
            email.setText(text, true);
            email.setFrom(new InternetAddress("yourEmail@gmail.com"));
        };
    }
}
