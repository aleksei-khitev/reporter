package ru.akhitev.reporter.email.sender;

import org.apache.log4j.Logger;

/**
 * Интерфейс для работы с почтой по протоколу SMTP
 *
 * @author Хитёв Алексей Юрьевич (alexkhitev@gmail.com)
 */
interface SmtpManager{
    /**
     * Задача параметров соединения с сервером
     * @param host Адрес узла
     * @param port Порт сервера smtp
     * @param username Имя пользователя
     * @param password Пароль
     */
    void setMailSender(String host, int port, String username, String password)
    /**
     * Отправка письма
     * @param body Тело письма
     * @param to Адрес получателя (получателей через запятую)
     * @param from Адрес отправителя
     * @param subject Тема письма
     */
    void sendMessage(String body, String to, String from, String subject)
    /**
     * Задача журнала log4j для сообщений об ошибках работы
     * @param logger Журнал log4j
     */
    void setLogger(Logger logger)
}