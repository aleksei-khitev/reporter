package ru.akhitev.reporter

import ru.akhitev.reporter.email.report.ReportManager
import ru.akhitev.reporter.email.report.ReportManagerImplOne
import ru.akhitev.reporter.email.sender.SmtpManager
import ru.akhitev.reporter.email.sender.SmtpManagerImplOne;
import org.apache.log4j.Logger

/**
 * Главный класс
 *
 * @author Хитёв Алексей Юрьевич (alexkhitev@gmail.com)
 */
class Reporter{
    /**
     * Обработчик отправки письма
     */
    SmtpManager smtpManager
    /**
     * Создатель тела письма
     */
    ReportManager reportManager
    /**
     * Карта свойств
     */
    def properties = [:]
    /**
     * Конструктор с журналом
     * @param logger Журнал log4j
     */
    Reporter(Logger logger){
        smtpManager = new SmtpManagerImplOne()
        reportManager = new ReportManagerImplOne()
        smtpManager.setLogger(logger)
        reportManager.setLogger(logger)
        parseProprtiesFile("dat${File.separator}reporter.properties")
    }
    /**
     * Констурктор без журнала
     */
    Reporter(){
        smtpManager = new SmtpManagerImplOne()
        reportManager = new ReportManagerImplOne()
        parseProprtiesFile("dat${File.separator}reporter.properties")
    }
    /**
     * Конструктор с путем к файлу свойств
     * @param propFilePath Файл с данными для карты свойств
     */
    Reporter(String propFilePath){
        smtpManager = new SmtpManagerImplOne()
        reportManager = new ReportManagerImplOne()
        parseProprtiesFile(propFilePath)
    }
    /**
     *  Конструктор с журналом и путем к файлу свойств
     * @param propFilePath Файл с данными для карты свойств
     * @param logger Журнал log4j
     */
    Reporter(String propFilePath, Logger logger){
        smtpManager = new SmtpManagerImplOne()
        reportManager = new ReportManagerImplOne()
        smtpManager.setLogger(logger)
        reportManager.setLogger(logger)
        parseProprtiesFile(propFilePath)
    }
    /**
     * Метод заполнения карты свойств из файла
     * @param propFilePath Файл с данными для карты свойств
     */
    void parseProprtiesFile(String propFilePath){
        Properties props = new Properties()
        File propsFile = new File(propFilePath)
        if(propsFile.exists()){
            props.load(propsFile.newDataInputStream())
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
     * Заполнение карты свойств в ручную
     * @param title Основной заголовок в шаблоне
     * @param firstHeader Заголовок секции системных сообщений
     * @param secondHeader Заголовок секции сообщений обработки
     * @param host Адрес сервера smtp
     * @param port Порт сервера smtp
     * @param username Имя пользователя для подключения к серверу smtp
     * @param password Пароль для подключения к серверу smtp
     * @param to Адрес получателя (получателей через запятую)
     * @param subject Тема письма
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
     * Добавление сообщения в список сообщений
     * @param firstCol Наполнение первой ячейки таблицы
     * @param secondCol Наполнение второй ячейки таблицы
     * @param type Тип (system или что либо другое)
     */
    void addToMessage(String firstCol, String secondCol, String type){
        if(type.equals("system")){
            reportManager.addToSystemErrorList(firstCol,secondCol)
        }else{
            reportManager.addToStringErrorList(firstCol,secondCol)
        }
    }
    /**
     * Отправка отчета
     */
    void sendReport(){
        smtpManager.setMailSender(properties['host'],properties['port'],properties['username'],properties['password'])
        smtpManager.sendMessage(reportManager.createReport(properties['title'],properties['firstHeader'], properties['secondHeader']), properties['to'], properties['username'], properties['subject'])
    }
}