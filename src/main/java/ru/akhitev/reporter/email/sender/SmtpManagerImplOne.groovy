package ru.akhitev.reporter.email.sender

import org.apache.log4j.Logger;

import javax.mail.MessagingException
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Реализация класса SmtpManager.
 *
 * @author Хитёв Алексей Юрьевич (alexkhitev@gmail.com)
 */
class SmtpManagerImplOne implements SmtpManager {
    /**
     * Обслуживает соединение с smtp сервером и осуществляет отправку
     */
    JavaMailSender mailSender
    /**
     * Журнал log4j
     */
    Logger logger
    /**
     * Задача журнала log4j
     * @param logger
     */
    void setLogger(Logger logger){
        this.logger=logger
    }
    /**
     * Задача параметров соединения с сервером
     * @param host Адрес узла
     * @param port Порт сервера smtp
     * @param username Имя пользователя
     * @param password Пароль
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
     * Отправка письма
     * @param body Тело письма
     * @param to Адрес получателя (получателей через запятую)
     * @param from Адрес отправителя
     * @param subject Тема письма
     */
    void sendMessage(String body, String to, String from, String subject) {
        MimeMessage mime = mailSender.createMimeMessage();
        MimeMessageHelper helper=null;
        try {
            helper = new MimeMessageHelper(mime, true, "UTF-8")
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error(e)
        }
        try {
            helper.to=new InternetAddress(to)
            helper.from=new InternetAddress(from)
            helper.subject=subject
            helper.setText(body,true)
        } catch (MessagingException e) {
            e.printStackTrace()
            logger.error(e)
        }
        mailSender.send(mime);
    }

}