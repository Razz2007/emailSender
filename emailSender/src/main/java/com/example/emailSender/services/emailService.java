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

    public void basicMail(String addressMail) {
        try {

            String subject = "Correo de prueba básico";
            String bodyMail = "<html><body><h2 style='color: #007BFF;'>Hola!</h2><p>Este es un correo básico de prueba.</p></body></html>";
            emailSender(addressMail, subject, bodyMail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void advancedEmail(String addressMail) {
        try {
            String subject = "Correo Avanzado con Diseño";
            String bodyMail = generateStyledTemplate("¡Hola, Juan!", "Este es un correo de prueba enviado desde nuestra aplicación.", "Visítanos", "https://www.ejemplo.com");
            emailSender(addressMail, subject, bodyMail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendNewAccountEmail(String addressMail, String username) throws MessagingException {
        String subject = "Bienvenido a nuestra plataforma";
        String content = "Tu cuenta ha sido creada exitosamente.<br>Gracias por unirte a nosotros.";
        String bodyMail = generateStyledTemplate("¡Hola, " + username + "!", content, null, null);
        emailSender(addressMail, subject, bodyMail);
    }

    public void sendForgotPasswordEmail(String addressMail, String resetLink) throws MessagingException {
        String subject = "Restablece tu contraseña";
        String content = "Haz clic en el siguiente botón para restablecer tu contraseña.";
        String bodyMail = generateStyledTemplate("¿Olvidaste tu contraseña?", content, "Restablecer", resetLink);
        emailSender(addressMail, subject, bodyMail);
    }

    public void sendActivationEmail(String addressMail, String code) throws MessagingException {
        String subject = "Activa tu cuenta";
        String content = "Tu código de activación es: <strong style='font-size: 20px;'>" + code + "</strong>";
        String bodyMail = generateStyledTemplate("Activación de cuenta", content, null, null);
        emailSender(addressMail, subject, bodyMail);
    }

    public void sendPasswordChangedNotification(String addressMail) throws MessagingException {
        String subject = "Contraseña cambiada";
        String content = "Este mensaje es para notificarte que tu contraseña fue modificada recientemente.";
        String bodyMail = generateStyledTemplate("Tu contraseña ha sido cambiada", content, null, null);
        emailSender(addressMail, subject, bodyMail);
    }

    public void sendLowStockNotification(String addressMail, String productName, int currentStock) throws MessagingException {
        String subject = "Producto con bajo stock";
        String content = "El producto <strong>" + productName + "</strong> está bajo en inventario. Solo quedan <strong>" + currentStock + "</strong> unidades.";
        String bodyMail = generateStyledTemplate("¡Alerta de stock!", content, null, null);
        emailSender(addressMail, subject, bodyMail);
    }

    public void sendPurchaseNotification(String addressMail, List<String> productos) throws MessagingException {
        String subject = "Compra confirmada";
        StringBuilder listaHTML = new StringBuilder("<ul style='padding-left: 20px;'>");
        for (String producto : productos) {
            listaHTML.append("<li>").append(producto).append("</li>");
        }
        listaHTML.append("</ul>");
        String content = "Has comprado los siguientes productos:" + listaHTML;
        String bodyMail = generateStyledTemplate("Gracias por tu compra", content, null, null);
        emailSender(addressMail, subject, bodyMail);
    }

    public boolean emailSender(String addressMail, String subject, String bodyMail) throws MessagingException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(addressMail);
            helper.setSubject(subject);
            helper.setText(bodyMail, true);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private String generateStyledTemplate(String titulo, String contenido, String textoBoton, String urlBoton) {
        String botonHTML = "";
        if (textoBoton != null && urlBoton != null) {
            botonHTML = "<a href=\"" + urlBoton + "\" style=\"display:inline-block;padding:12px 25px;background-color:#007BFF;color:white;text-decoration:none;border-radius:5px;margin-top:20px;\">" + textoBoton + "</a>";
        }

        return "<!DOCTYPE html><html lang=\"es\"><head><meta charset=\"UTF-8\"><style>" +
                "body { background-color: #f4f4f4; font-family: 'Segoe UI', sans-serif; margin: 0; padding: 0; }" +
                ".container { max-width: 600px; margin: 40px auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }" +
                "h2 { color: #333; } p { color: #555; line-height: 1.6; }" +
                ".footer { margin-top: 30px; font-size: 12px; color: #aaa; text-align: center; }" +
                "</style></head><body><div class=\"container\">" +
                "<h2>" + titulo + "</h2>" +
                "<p>" + contenido + "</p>" +
                botonHTML +
                "<div class=\"footer\">© 2025 Tu Empresa. Todos los derechos reservados.</div>" +
                "</div></body></html>";
    }
}
