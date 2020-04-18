package org.rw.phonebook.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mailer {
    /*
     * Change these and the SMTP settings below for the mail server.
     * I've just realized the class name is not very smart.
     * It should be Mailer but anyway.
     */
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";

    private Mailer() {
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param recipientEmail TO recipient
     * @param title          title of the message
     * @param message        message to be sent
     *
     * @throws MessagingException if an error occurs while parsing the addresses or sending the message
     */
    public static void send(String recipientEmail, String title, String message) throws MessagingException {
        send(USERNAME, PASSWORD, recipientEmail, "", title, message);
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param username       GMail username
     * @param password       GMail password
     * @param recipientEmail TO recipient
     * @param ccEmail        CC recipient. Can be empty if there is no CC recipient
     * @param title          title of the email
     * @param message        message to be sent
     *
     * @throws MessagingException if the connection ended abruptly or if the message is not a MimeMessage
     */
    public static void send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message) throws MessagingException {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        final MimeMessage msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(username + "@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        if (ccEmail.length() > 0) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
        }

        msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());
        msg.setContent(message, "text/html");

        Transport transport = session.getTransport("smtps");

        transport.connect("smtp.gmail.com", username, password);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }
}
