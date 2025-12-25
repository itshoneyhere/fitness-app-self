package com.example.notificationService.controller;

import com.example.notificationService.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send")
    public ResponseEntity<Void> sendMail(@RequestParam("t") String message)
    {
        emailService.sendSimpleEmail("honey.gemini01@gmail.com","test mail from diet service",message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sendHtml")
    public ResponseEntity<Void> sendHtmlMail()
    {
        emailService.sendHtmlEmail("honey.gemini01@gmail.com","test mail from diet service",getHtml());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public String getHtml()
    {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Notification</title>
                </head>
                <body style="font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px;">
                
                    <table width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <td align="center">
                                <table width="600" cellpadding="20" cellspacing="0"
                                       style="background-color: #ffffff; border-radius: 6px;">
                
                                    <tr>
                                        <td>
                                            <h2 style="color: #333333; margin-bottom: 10px;">
                                                Hello,
                                            </h2>
                
                                            <p style="color: #555555; font-size: 14px; line-height: 1.6;">
                                                This is a test notification email sent from your
                                                Spring Boot notification service.
                                            </p>
                
                                            <p style="color: #555555; font-size: 14px;">
                                                If you did not request this message, you can safely ignore it.
                                            </p>
                
                                            <hr style="border: none; border-top: 1px solid #dddddd; margin: 20px 0;">
                
                                            <p style="color: #888888; font-size: 12px;">
                                                Regards,<br>
                                                Notification Service
                                            </p>
                                        </td>
                                    </tr>
                
                                </table>
                            </td>
                        </tr>
                    </table>
                
                </body>
                </html>
                
                """;
    }


}
