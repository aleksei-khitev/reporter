package ru.akhitev.reporter.email.sender;

import org.apache.log4j.Logger;

interface SmtpManager{
    void setMailSender(String host, int port, String username, String password)
    void sendMessage(String body, String to, String from, String subject)
    void setLogger(Logger logger)
}