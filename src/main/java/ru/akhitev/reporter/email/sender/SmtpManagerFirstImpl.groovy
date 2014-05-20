/*
 * ru.akhitev.reporter is a library for encryption.
 * Copyright (c) 2014 Aleksei Khitevi (Хитёв Алексей Юрьевич).
 *
 * This file is part of ru.akhitev.encrypter
 *
 * ru.akhitev.reporter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * ru.akhitev.encrypter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package ru.akhitev.reporter.email.sender

import org.apache.log4j.Logger
import ru.akhitev.reporter.Reporter;

import javax.mail.MessagingException
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * ISmtpManager implementation
 *
 * @author Aleksei Khitev (alexkhitev@gmail.com)
 */
class SmtpManagerFirstImpl implements ISmtpManager {
    /**
     * It used for connecting with SMTP server and for sending emails
     */
    JavaMailSender mailSender
    /**
     * The method used for setting connection properties
     * @param host Host adress
     * @param port Server port
     * @param username Username
     * @param password Password
     */
    void setMailSender(String host, int port, String username, String password) {
        mailSender = new JavaMailSenderImpl()
        mailSender.host=host
        mailSender.port=port
        mailSender.protocol="smtp"
        mailSender.username=username
        mailSender.password=password
    }
    /**
     * The method used for sending email
     * @param body Email's body
     * @param to Adress (or adresses,  separated by kommas)
     * @param from Sender's adress
     * @param subject Email's subject
     */
    void sendMessage(String body, String to, String from, String subject) {
        MimeMessage mime = mailSender.createMimeMessage();
        MimeMessageHelper helper=null;
        try {
            helper = new MimeMessageHelper(mime, true, "UTF-8")
        } catch (MessagingException e) {
            e.printStackTrace();
            Reporter.logger.error(e)
        }
        try {
            helper.to=new InternetAddress(to)
            helper.from=new InternetAddress(from)
            helper.subject=subject
            helper.setText(body,true)
        } catch (MessagingException e) {
            e.printStackTrace()
            Reporter.logger.error(e)
        }
        mailSender.send(mime);
    }

    void sendMessage(String body, String to, String from, String subject, List<File> attachments){
        MimeMessage mime = mailSender.createMimeMessage();
        MimeMessageHelper helper=null;
        try {
            helper = new MimeMessageHelper(mime, true, "UTF-8")
        } catch (MessagingException e) {
            e.printStackTrace();
            Reporter.logger.error(e)
        }
        try {
            helper.to=new InternetAddress(to)
            helper.from=new InternetAddress(from)
            helper.subject=subject
            helper.setText(body,true)
            for(File attachment : attachments){
                helper.addAttachment(attachment.name, attachment)
            }
        } catch (MessagingException e) {
            e.printStackTrace()
            Reporter.logger.error(e)
        }
        mailSender.send(mime);
    }
}