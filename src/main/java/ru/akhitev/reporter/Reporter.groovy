package ru.akhitev.reporter

import ru.akhitev.reporter.email.report.ReportManager
import ru.akhitev.reporter.email.report.ReportManagerImplOne
import ru.akhitev.reporter.email.sender.SmtpManager
import ru.akhitev.reporter.email.sender.SmtpManagerImplOne;
import org.apache.log4j.Logger

class Reporter{
    SmtpManager smtpManager
    ReportManager reportManager
    def properties = [:]

    Reporter(Logger logger){
        smtpManager = new SmtpManagerImplOne()
        reportManager = new ReportManagerImplOne()
        smtpManager.setLogger(logger)
        reportManager.setLogger(logger)
    }

    Reporter(){
        smtpManager = new SmtpManagerImplOne()
        reportManager = new ReportManagerImplOne()
    }

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

    void addToMessage(String firstCol, String secondCol, String type){
        if(type.equals("system")){
            reportManager.addToSystemErrorList(firstCol,secondCol)
        }else{
            reportManager.addToStringErrorList(firstCol,secondCol)
        }
    }

    void sendReport(){
        smtpManager.setMailSender(properties['host'],properties['port'],properties['username'],properties['password'])
        smtpManager.sendMessage(reportManager.createReport(properties['title'],properties['firstHeader'], properties['secondHeader']), properties['to'], properties['username'], properties['subject'])
    }
}