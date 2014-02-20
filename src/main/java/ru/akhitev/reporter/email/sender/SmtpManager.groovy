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