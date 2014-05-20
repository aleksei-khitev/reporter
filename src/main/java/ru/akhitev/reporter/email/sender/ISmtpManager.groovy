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

package ru.akhitev.reporter.email.sender;

import org.apache.log4j.Logger;

/**
 * The interface used for working with email by SMTP
 *
 * @author Aleksei Khitev (alexkhitev@gmail.com)
 */
interface ISmtpManager {
    /**
     * The method used for setting server configuration
     * @param host Host
     * @param port SMTP server's port
     * @param username username
     * @param password password
     */
    void setMailSender(String host, int port, String username, String password)
    /**
     * The method used for sending email
     * @param body Email's body
     * @param to Adress (or adresses,  separated by kommas)
     * @param from Sender's adress
     * @param subject Email's subject
     */
    void sendMessage(String body, String to, String from, String subject)

    void sendMessage(String body, String to, String from, String subject, List<File> attachments)
}