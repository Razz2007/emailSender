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
            String bodyMail = generateStyledTemplate(
                "¡Hola!", 
                "Este es un correo básico de prueba.", 
                null, 
                null
            );
            emailSender(addressMail, subject, bodyMail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void advancedEmail(String addressMail) {
        try {
            String subject = "Correo Avanzado con Diseño";
            String bodyMail = generateStyledTemplate(
                "¡Hola, Juan!",
                "Este es un correo de prueba enviado desde nuestra aplicación.",
                "Visítanos",
                "https://www.ejemplo.com"
            );
            emailSender(addressMail, subject, bodyMail);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendNewAccountEmail(String addressMail, String username) throws MessagingException {
        String subject = "Bienvenido a nuestra plataforma";
        String content = "Tu cuenta ha sido creada exitosamente.<br>Gracias por unirte a nosotros.";
        String bodyMail = generateStyledTemplate(
            "¡Hola, " + username + "!", 
            content, 
            null, 
            null
        );
        emailSender(addressMail, subject, bodyMail);
    }

    public void sendForgotPasswordEmail(String addressMail, String resetLink) throws MessagingException {
        String subject = "Restablece tu contraseña";
        String content = "Haz clic en el siguiente botón para restablecer tu contraseña.";
        String bodyMail = generateStyledTemplate(
            "¿Olvidaste tu contraseña?", 
            content, 
            "Restablecer",
            resetLink
        );
        emailSender(addressMail, subject, bodyMail);
    }

    public void sendActivationEmail(String addressMail, String code) throws MessagingException {
        String subject = "Activa tu cuenta";
        String content = "Tu código de activación es: <strong style='font-size: 20px;'>" + code + "</strong>";
        String bodyMail = generateStyledTemplate(
            "Activación de cuenta", 
            content, 
            null, 
            null
        );
        emailSender(addressMail, subject, bodyMail);
    }

    public void sendPasswordChangedNotification(String addressMail) throws MessagingException {
        String subject = "Contraseña cambiada";
        String content = "Este mensaje es para notificarte que tu contraseña fue modificada recientemente.";
        String bodyMail = generateStyledTemplate(
            "Tu contraseña ha sido cambiada",
            content,
            null,
            null
        );
        emailSender(addressMail, subject, bodyMail);
    }

    public void sendLowStockNotification(String addressMail, String productName, int currentStock) throws MessagingException {
        String subject = "Producto con bajo stock";
    
        String tablaHTML = "<table style='width:100%;border-collapse:collapse;'>"
                + "<thead><tr style='background-color:#dc3545;color:white;'>"
                + "<th style='padding:12px;border:1px solid #ddd;'>Producto</th>"
                + "<th style='padding:12px;border:1px solid #ddd;'>Stock Actual</th>"
                + "</tr></thead><tbody>"
                + "<tr>"
                + "<td style='padding:12px;border:1px solid #ddd;text-align:center;'>" + productName + "</td>"
                + "<td style='padding:12px;border:1px solid #ddd;text-align:center;'>" + currentStock + "</td>"
                + "</tr></tbody></table>";
    
        String content = "El siguiente producto está bajo en inventario:" + tablaHTML;
        String bodyMail = generateStyledTemplate(
            "¡Alerta de Stock Bajo!",
            content,
            null,
            null
        );
        emailSender(addressMail, subject, bodyMail);
    }
    
    public void sendPurchaseNotification(String addressMail, List<String> productos) throws MessagingException {
        String subject = "Compra confirmada";
    
        StringBuilder tablaHTML = new StringBuilder();
        tablaHTML.append("<table style='width:100%;border-collapse:collapse;'>")
                 .append("<thead>")
                 .append("<tr style='background-color:#007BFF;color:white;'>")
                 .append("<th style='padding:12px;border:1px solid #ddd;'>Producto</th>")
                 .append("</tr>")
                 .append("</thead><tbody>");
    
        for (String producto : productos) {
            tablaHTML.append("<tr>")
                     .append("<td style='padding:12px;border:1px solid #ddd;text-align:center;'>")
                     .append(producto)
                     .append("</td>")
                     .append("</tr>");
        }
    
        tablaHTML.append("</tbody></table>");
    
        String content = "Gracias por tu compra. Aquí tienes el detalle de tus productos:" + tablaHTML.toString();
        String bodyMail = generateStyledTemplate(
            "Resumen de tu compra",
            content,
            null,
            null
        );
        emailSender(addressMail, subject, bodyMail);
    }

    private boolean emailSender(String addressMail, String subject, String bodyMail) throws MessagingException {
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
            botonHTML = "<a href=\"" + urlBoton + "\" style=\"display:inline-block;padding:14px 28px;background-color:#28a745;color:white;text-decoration:none;font-weight:bold;border-radius:8px;font-size:16px;margin-top:25px;\">"
                      + textoBoton + "</a>";
        }

        return "<!DOCTYPE html><html lang=\"es\"><head><meta charset=\"UTF-8\"><style>" +
                "body { background: linear-gradient(to right, #74ebd5, #acb6e5); font-family: 'Poppins', sans-serif; margin: 0; padding: 0; }" +
                ".container { max-width: 600px; margin: 40px auto; background: #ffffff; padding: 40px; border-radius: 15px; box-shadow: 0 8px 20px rgba(0,0,0,0.15); text-align: center; }" +
                "h2 { color: #333333; font-size: 28px; margin-bottom: 20px; }" +
                "p { color: #555555; font-size: 16px; line-height: 1.8; margin-bottom: 30px; }" +
                ".header-image { width: 100%; border-radius: 15px 15px 0 0; margin-bottom: 20px; }" +
                ".footer { margin-top: 40px; font-size: 13px; color: #999999; }" +
                "</style></head><body><div class=\"container\">" +
                "<img class=\"header-image\" src=\"https://i.pinimg.com/736x/90/85/62/908562a0ecff4871d7f3d01f8dbfe4f9.jpg\" alt=\"Encabezado\">" + // Cambia por tu propia imagen si quieres
                "<h2>" + titulo + "</h2>" +
                "<p>" + contenido + "</p>" +
                botonHTML +
                "<div class=\"footer\">© 2025 Razz2007. Todos los derechos reservados.</div>" +
                "</div></body></html>";
    }
}
