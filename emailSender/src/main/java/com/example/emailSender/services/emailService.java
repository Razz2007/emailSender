package com.example.emailSender.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class emailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void basicMail(){
        try {
        String addressMail="Uourema@hma.com";

        String subject="Este es un correo de prueba";

        String bodyMail="Correo prueba"; 
        emailSender( addressMail,subject, bodyMail);
    }catch(Exception e) {
            
        }
    }
    public void advancedEmail(String addressMail) {
        try {
            // destinatario
            // String addressMail = "cjcs.cadenasarasty8@gmail.com";
            // Asunto
            String subject = "Este es un correo de prueba";
            // Cuerpo Correo
            String bodyMail = ""
    + "<!DOCTYPE html>\n"
    + "<html lang=\"es\">\n"
    + "<head>\n"
    + "    <meta charset=\"UTF-8\">\n"
    + "    <title>Correo de Prueba</title>\n"
    + "    <style>\n"
    + "        body {\n"
    + "            font-family: Arial, sans-serif;\n"
    + "            background-color: #f4f4f4;\n"
    + "            margin: 0;\n"
    + "            padding: 0;\n"
    + "        }\n"
    + "        .correo-container {\n"
    + "            background-color: #ffffff;\n"
    + "            max-width: 600px;\n"
    + "            margin: 40px auto;\n"
    + "            padding: 30px;\n"
    + "            border-radius: 8px;\n"
    + "            box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);\n"
    + "        }\n"
    + "        h2 {\n"
    + "            color: #333333;\n"
    + "        }\n"
    + "        p {\n"
    + "            font-size: 16px;\n"
    + "            color: #555555;\n"
    + "        }\n"
    + "        .btn {\n"
    + "            display: inline-block;\n"
    + "            margin-top: 20px;\n"
    + "            padding: 12px 20px;\n"
    + "            background-color: #007BFF;\n"
    + "            color: white;\n"
    + "            text-decoration: none;\n"
    + "            border-radius: 5px;\n"
    + "        }\n"
    + "        .footer {\n"
    + "            margin-top: 30px;\n"
    + "            font-size: 12px;\n"
    + "            color: #aaaaaa;\n"
    + "            text-align: center;\n"
    + "        }\n"
    + "    </style>\n"
    + "</head>\n"
    + "<body>\n"
    + "    <div class=\"correo-container\">\n"
    + "        <h2>¡Hola, Juan!</h2>\n"
    + "        <p>Este es un correo de prueba enviado desde nuestra aplicación de Spring Boot.</p>\n"
    + "        <p>Haz clic en el botón a continuación para visitar nuestro sitio:</p>\n"
    + "        <a href=\"https://www.ejemplo.com\" class=\"btn\">Ir al sitio</a>\n"
    + "        <div class=\"footer\">\n"
    + "            © 2025 Tu Empresa. Todos los derechos reservados.\n"
    + "        </div>\n"
    + "    </div>\n"
    + "</body>\n"
    + "</html>";

            emailSender(addressMail, subject, bodyMail);
        } catch (Exception e) {

        }
    }
    public void sendNewAccountEmail(String addressMail){
        try {
    String subject = "Bienvenido a nuestra plataforma";
    String bodyMail = "<html>..."
        + "<h2>¡Hola!</h2>"
        + "<p>Tu cuenta ha sido creada exitosamente.</p>"
        + "<p>Gracias por unirte a nosotros.</p>"
        + "</html>";
    emailSender(addressMail, subject, bodyMail);
        }catch (Exception e){

        }
    
}

public void sendForgotPasswordEmail(String addressMail, String resetLink) throws MessagingException {
    String subject = "Restablece tu contraseña";
    String bodyMail = "<html>..."
        + "<h2>¿Olvidaste tu contraseña?</h2>"
        + "<p>Puedes restablecerla haciendo clic en el siguiente enlace:</p>"
        + "<a href=\"" + resetLink + "\" class=\"btn\">Restablecer contraseña</a>"
        + "</html>";
    emailSender(addressMail, subject, bodyMail);
}

public void sendActivationEmail(String addressMail, String code) throws MessagingException {
    String subject = "Activa tu cuenta";
    String bodyMail = "<html>..."
        + "<h2>Activación de cuenta</h2>"
        + "<p>Tu código de activación es: <strong>" + code + "</strong></p>"
        + "</html>";
    emailSender(addressMail, subject, bodyMail);
}

public void sendPasswordChangedNotification(String addressMail) throws MessagingException {
    String subject = "Contraseña cambiada";
    String bodyMail = "<html>..."
        + "<h2>Tu contraseña ha sido cambiada</h2>"
        + "<p>Este mensaje es para notificarte que tu contraseña fue modificada recientemente.</p>"
        + "</html>";
    emailSender(addressMail, subject, bodyMail);
}

public void sendLowStockNotification(String addressMail, String productName, int currentStock) throws MessagingException {
    String subject = "Producto con bajo stock";
    String bodyMail = "<html>..."
        + "<h2>Stock bajo: " + productName + "</h2>"
        + "<p>Actualmente solo quedan " + currentStock + " unidades disponibles.</p>"
        + "</html>";
    emailSender(addressMail, subject, bodyMail);
}

public void sendPurchaseNotification(String addressMail, List<String> productos) throws MessagingException {
    String subject = "Compra confirmada";
    StringBuilder listaHTML = new StringBuilder();
    listaHTML.append("<ul>");
    for (String producto : productos) {
        listaHTML.append("<li>").append(producto).append("</li>");
    }
    listaHTML.append("</ul>");

    String bodyMail = "<html>..."
        + "<h2>Gracias por tu compra</h2>"
        + "<p>Has comprado los siguientes productos:</p>"
        + listaHTML.toString()
        + "</html>";
    emailSender(addressMail, subject, bodyMail);
}


    public boolean emailSender(String addressMail, String subject, String bodyMail) throws MessagingException{

        try {
            // Create a MimeMessage object
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message, true);
            helper.setTo(addressMail);
            helper.setSubject(subject);
            helper.setText(bodyMail, true); // Set the email body and enable HTML
            javaMailSender.send(message);;
             return true; // Set the email body and enable HTML
            // Set the sender's email address
    
        }catch (Exception e) {
            System.out.println(e.getMessage());
            }
            return false; // If an exception occurs, return false
        }
    }