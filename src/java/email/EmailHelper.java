/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Yahir
 */
public class EmailHelper implements Runnable {
    
    private final String FROM = "intercambios.escom@gmail.com";
    private final String FROMNAME = "Intercambios escom";
    private final String SMTP_USERNAME = "intercambios.escom@gmail.com"; 
    private final String SMTP_PASSWORD = "intercambiosescom1";
    private final String HOST = "smtp.gmail.com";
    private final int PORT = 587;
    
    private String to;
    private String subject;
    private String body;

    public EmailHelper(String TO, String SUBJECT, String BODY) {
        this.to = TO;
        this.subject = SUBJECT;
        this.body = BODY;
    }

    @Override
    public void run() {
        try {
            this.sendMail();
        } catch (Exception ex) {
            Logger.getLogger(EmailHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMail() throws Exception {
        // Create a Properties object to contain connection configuration information.
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", PORT); 
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.auth", "true");
    	Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM,FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setContent(body,"text/html");
           
        // Send the message.
        try(Transport transport = session.getTransport()) {
            System.out.println("Enviando...");
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email enviado a " + to);
        }
        catch (Exception ex) {
            System.out.println("El email no se envió");
            System.out.println("Error message: " + ex.getMessage());
        }
    }
    
}
