package ec.ups.edu.ppw.autoSpotBackend.business;


import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@RequestScoped
public class ServiceSendMail {
    @Resource(name = "java:jboss/mail/MyMailSession")
    private Session mailSession;


    public void sendEmail(String to, String subject, String body) {
        try {
            Message message = new MimeMessage(mailSession);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Correo enviado correctamente a: " + to);
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
    }
}
