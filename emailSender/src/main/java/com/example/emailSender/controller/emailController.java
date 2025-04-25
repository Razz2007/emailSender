package com.example.emailSender.controller;

import com.example.emailSender.services.emailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class emailController {

    @Autowired
    private emailService emailService;

    @GetMapping("/basicEmail")
    public String sendBasicEmail() {
        emailService.basicMail();
        return "Basic email sent successfully!";
    }

    @GetMapping("/advancedEmail/{email}")
    public String advancedEmail(@PathVariable String email) {
        emailService.advancedEmail(email);
        return "Advanced email sent successfully!";
    }

    @GetMapping("/newAccount/{email}")
    public String sendNewAccountEmail(@PathVariable String email){
        emailService.sendNewAccountEmail(email);
        return "New account email sent successfully!";
    }

    @GetMapping("/forgotPassword")
    public String sendForgotPassword(@RequestParam String email, @RequestParam String resetLink) throws MessagingException {
        emailService.sendForgotPasswordEmail(email, resetLink);
        return "Forgot password email sent successfully!";
    }

    @GetMapping("/activateAccount")
    public String sendActivationEmail(@RequestParam String email, @RequestParam String code) throws MessagingException {
        emailService.sendActivationEmail(email, code);
        return "Activation email sent successfully!";
    }

    @GetMapping("/passwordChanged")
    public String sendPasswordChangedEmail(@RequestParam String email) throws MessagingException {
        emailService.sendPasswordChangedNotification(email);
        return "Password changed notification sent successfully!";
    }

    @GetMapping("/lowStock")
    public String sendLowStockEmail(@RequestParam String email,
                                    @RequestParam String product,
                                    @RequestParam int stock) throws MessagingException {
        emailService.sendLowStockNotification(email, product, stock);
        return "Low stock notification sent successfully!";
    }

    @PostMapping("/purchaseNotification")
    public String sendPurchaseNotification(@RequestParam String email,
                                           @RequestBody List<String> productos) throws MessagingException {
        emailService.sendPurchaseNotification(email, productos);
        return "Purchase notification sent successfully!";
    }
}
