package com.shtven.cinema.services;

import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private QrCodeService qrCodeService;

    private void sendEmail(String to, String subject, String htmlBody, String qrText)
            throws MessagingException, IOException, WriterException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);


        byte[] qrBytes = qrCodeService.generateQrPng(qrText, 200, 200);


        helper.addInline("qrImage", new ByteArrayResource(qrBytes), "image/png");

        mailSender.send(mimeMessage);
    }

    public void loadHtmlTemplatePurchaseAndSend(String pelicula,
                                           String sala,
                                           String asientos,
                                           String folio,
                                           String total,
                                           String email) throws IOException, MessagingException, WriterException {

        ClassPathResource resource =
                new ClassPathResource("templates/email/Purchase.html");

        String html = Files.readString(resource.getFile().toPath(), StandardCharsets.UTF_8);

        html = html.replace("{{PELÍCULA}}", pelicula);
        html = html.replace("{{SALA}}", sala);
        html = html.replace("{{ASIENTOS}}", asientos);
        html = html.replace("{{FOLIO}}", folio);
        html = html.replace("{{TOTAL}}", total);

        String qrText = folio;

        sendEmail(email, "Confirmación de compra", html, qrText);


    }
}
