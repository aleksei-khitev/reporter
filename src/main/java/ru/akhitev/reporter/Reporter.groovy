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

package ru.akhitev.reporter

import ru.akhitev.reporter.email.report.IReportManager
import ru.akhitev.reporter.email.report.ReportManagerFirstImpl
import ru.akhitev.reporter.email.sender.ISmtpManager
import ru.akhitev.reporter.email.sender.SmtpManagerFirstImpl;
import org.apache.log4j.Logger

/**
 * The main class
 *
 * @author Aleksei Khitev (alexkhitev@gmail.com)
 */
class Reporter{
    /**
     * Email Send Manager
     */
    ISmtpManager smtpManager
    /**
     * Report Body Manager
     */
    IReportManager reportManager
    /**
     * Property's map
     */
    def properties = [:]
    /**
     * Logger
     */
    static Logger logger;
    /**
     * Constructor with logger
     * @param logger Logger log4j
     */
    Reporter(Logger logger){
        smtpManager = new SmtpManagerFirstImpl()
        reportManager = new ReportManagerFirstImpl()
        this.logger = logger;
        parseProprtiesFile("dat${File.separator}reporter.properties")
    }
    /**
     * Constructor without logger
     */
    Reporter(){
        smtpManager = new SmtpManagerFirstImpl()
        reportManager = new ReportManagerFirstImpl()
        parseProprtiesFile("dat${File.separator}reporter.properties")
    }
    /**
     * Constructor with property's file path
     * @param propFilePath Property's file for property map
     */
    Reporter(String propFilePath){
        smtpManager = new SmtpManagerFirstImpl()
        reportManager = new ReportManagerFirstImpl()
        parseProprtiesFile(propFilePath)
    }
    /**
     *  Constructor with property's file path and logger
     * @param propFilePath Property's file for property map
     * @param logger Logger log4j
     */
    Reporter(String propFilePath, Logger logger){
        smtpManager = new SmtpManagerFirstImpl()
        reportManager = new ReportManagerFirstImpl()
        this.logger = logger;
        parseProprtiesFile(propFilePath)
    }
    /**
     * The method used for setting properties from property's file
     * @param propFilePath Property's file for property map
     */
    void parseProprtiesFile(String propFilePath){
        Properties props = new Properties()
        File propsFile = new File(propFilePath)
        if(propsFile.exists()){
            props.load(new StringReader(propsFile.getText("UTF-8")))
            properties['title']=props.getProperty('title')
            properties['firstHeader']=props.getProperty('firstHeader')
            properties['secondHeader']=props.getProperty('secondHeader')
            properties['host']=props.getProperty('host')
            properties['port']=props.getProperty('port').toInteger()
            properties['username']=props.getProperty('username')
            properties['password']=props.getProperty('password')
            properties['to']=props.getProperty('to')
            properties['subject']=props.getProperty('subject')
        }
    }
    /**
     * The method used for setting properties by hand
     * @param title Main title at the template
     * @param firstHeader System section title
     * @param secondHeader Another section title
     * @param host SMTP server adress
     * @param port SMTP server port
     * @param username Username for SMTP connection
     * @param password Password for SMTP connection
     * @param to Adress (or adresses,  separated by kommas)
     * @param subject Email's subject
     */
    void setProperties(String title, String firstHeader, String secondHeader,String host, int port, String username, String password, String to, String subject){
        properties['title']=title
        properties['firstHeader']=firstHeader
        properties['secondHeader']=secondHeader
        properties['host']=host
        properties['port']=port
        properties['username']=username
        properties['password']=password
        properties['to']=to
        properties['subject']=subject
    }
    /**
     * The method used for adding message to message list
     * @param firstCol First column text
     * @param secondCol Second column text
     * @param type Type of message (system or another)
     */
    void addToMessage(String firstCol, String secondCol, String type){
        if(type.equals("system")){
            reportManager.addToSystemErrorList(firstCol,secondCol)
        }else{
            reportManager.addToStringErrorList(firstCol,secondCol)
        }
    }
    /**
     * The method used for sending report
     */
    void sendReport(){
        smtpManager.setMailSender(properties['host'],properties['port'],properties['username'],properties['password'])
        smtpManager.sendMessage(reportManager.createReport(properties['title'],properties['firstHeader'], properties['secondHeader']), properties['to'], properties['username'], properties['subject'])
    }
}