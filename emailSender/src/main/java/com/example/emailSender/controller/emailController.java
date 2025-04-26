package com.example.emailSender.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.emailSender.services.emailService;

import jakarta.mail.MessagingException;

@RestController
public class emailController {

    @Autowired
    private emailService emailService;

    @GetMapping("/basicEmail/{email}")
    public String sendBasicEmail(@PathVariable String email) {
        emailService.basicMail(email);
        return "Email sent successfully!";
    }

        @GetMapping("/advancedEmail/{email}")
    public String advancedEmail(@PathVariable String email) {
        emailService.advancedEmail(email);
        return "mail sent successfully";
    }
        @GetMapping("/newAccount/{email}")
    public String newAccountEmail(@PathVariable String email) throws MessagingException {
        emailService.sendNewAccountEmail(email, "Usuario");
        return "New account email sent successfully!";
    }
        @GetMapping("/forgotPassword/{email}")
    public String forgotPasswordEmail(@PathVariable String email) throws MessagingException {
        emailService.sendForgotPasswordEmail(email, "#");
        return "Forgot password email sent successfully!";
    }

    @GetMapping("/activate/{email}")
    public String activationEmail(@PathVariable String email) throws MessagingException {
        emailService.sendActivationEmail(email, "123456");
        return "Activation email sent successfully!";
    }

    @GetMapping("/passwordChanged/{email}")
    public String passwordChangedEmail(@PathVariable String email) throws MessagingException {
        emailService.sendPasswordChangedNotification(email);
        return "Password changed notification sent successfully!";
    }

    @GetMapping("/lowStock/{email}")
    public String lowStockEmail(@PathVariable String email) throws MessagingException {
        emailService.sendLowStockNotification(email, "Producto Ejemplo", 3);
        return "Low stock notification sent successfully!";
    }

    @GetMapping("/purchase/{email}")
    public String purchaseEmail(@PathVariable String email) throws MessagingException {
        emailService.sendPurchaseNotification(email, Arrays.asList("Producto A", "Producto B", "Producto C"));
        return "Purchase notification email sent successfully!";
    }

}
