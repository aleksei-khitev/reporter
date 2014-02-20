/*
 * ru.akhitev.reporter. Программа для отправки по почте отчетов о работе в формате HTML
 * Copyright (c) 2014 Хитёв Алексей
 *
 * Этот файл - часть ru.akhitev.reporter. Вы можете перераспространять ее и/или изменять ее на условиях
 * Стандартной общественной лицензии GNU в том виде, в каком она была опубликована Фондом свободного программного
 * обеспечения; либо версии 3 лицензии, либо (по вашему выбору) любой более поздней версии.
 * Эта программа распространяется в надежде, что она будет полезной, но БЕЗО ВСЯКИХ ГАРАНТИЙ; даже без неявной гарантии
 * ТОВАРНОГО ВИДА или ПРИГОДНОСТИ ДЛЯ ОПРЕДЕЛЕННЫХ ЦЕЛЕЙ. Подробнее см. в Стандартной общественной лицензии GNU.
 * Вы должны были получить копию Стандартной общественной лицензии GNU вместе с этой программой. Если это не так, см.
 * <http://www.gnug/licenses/>.
 */

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