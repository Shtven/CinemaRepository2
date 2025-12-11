package com.shtven.cinema.services;

import com.google.zxing.WriterException;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class EmailService {

    @Value("${sendgrid.api.key}")
    private String sendgridApiKey;

    @Value("${mail.from}")
    private String fromEmail;

    private final QrCodeService qrCodeService;

    public EmailService(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    private void sendEmail(String to, String subject, String htmlBody) throws MessagingException {
        Email from = new Email(fromEmail);
        Email toEmail = new Email(to);
        Content content = new Content("text/html", htmlBody);
        Mail mail = new Mail(from, subject, toEmail, content);

        SendGrid sg = new SendGrid(sendgridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            int status = response.getStatusCode();
            if (status >= 400) {
                throw new MessagingException(
                        "Error enviando correo via SendGrid. Status: "
                                + status + " Body: " + response.getBody()
                );
            }
        } catch (IOException e) {
            throw new MessagingException("Error de IO al enviar correo via SendGrid", e);
        }
    }

    public void loadHtmlTemplatePurchaseAndSend(String pelicula,
                                                String sala,
                                                String asientos,
                                                String folio,
                                                String total,
                                                String email)
            throws IOException, MessagingException, WriterException {

        ClassPathResource resource =
                new ClassPathResource("templates/email/Purchase.html");

        String html;
        try (var in = resource.getInputStream()) {
            html = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }

        html = html.replace("{{PELICULA}}", pelicula);
        html = html.replace("{{SALA}}", sala);
        html = html.replace("{{ASIENTOS}}", asientos);
        html = html.replace("{{FOLIO}}", folio);
        html = html.replace("{{TOTAL}}", total);

        String qrText = folio;
        byte[] qrBytes = qrCodeService.generateQrPng(qrText, 120, 120);
        String qrBase64 = Base64.getEncoder().encodeToString(qrBytes);

        html = html.replace("{{QR_BASE64}}", qrBase64);

        sendEmail(email, "Confirmación de compra", html);
    }
}
