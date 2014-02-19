package ru.akhitev.reporter.email.report;

import org.antlr.stringtemplate.StringTemplate
import org.antlr.stringtemplate.StringTemplateGroup
import org.apache.log4j.Logger

class ReportManagerImplOne implements ReportManager {
    def systemErrorList = []
    def stringErrorsList = []
    boolean hasError = false
    Logger logger
    StringTemplateGroup group = new StringTemplateGroup("group", "templates");

    void setLogger(Logger logger){
        this.logger=logger
    }

    boolean havingErrors() {
        hasError
    }

    String createReport(String title, String firstHeader, String secondHeader) {
        StringTemplate mainTemplate
        try{
            mainTemplate = group.getInstanceOf("reportTemplate");
        }catch(IllegalArgumentException ex){
            mainTemplate = new StringTemplate()
            mainTemplate.setTemplate('<html><head><meta http-equiv="Content-type" content="text/html; charset=utf-8" /><title></title></head><body><h3>$title$</h3><br/><hr>$date$<br/><hr><br/>$error$</body></html>')
        }
        mainTemplate.setAttribute("title",title)
        mainTemplate.setAttribute("error", generateTable(firstHeader, secondHeader))
        Calendar calendar = Calendar.getInstance()
        mainTemplate.setAttribute("date", calendar.format("дата: dd.MM.yyyy; время: HH.mm; день: EEE"))
        mainTemplate.toString()
    }

    void addToSystemErrorList(String firstCol, String secondCol) {
        systemErrorList.add("<td><b>${firstCol}</b></td><td>${secondCol}</td>")
        hasError=true
    }

    void addToStringErrorList(String firstCol, String secondCol) {
        stringErrorsList.add("<td><b>${firstCol}</b></td><td>${secondCol}</td>")
        hasError=true
    }

    String generateTable(String firstHeader, String secondHeader) {
        String result = ""
        String temp = ""
        if(systemErrorList.size()>0) {
            StringTemplate dbErrorListTemplate
            try{
                dbErrorListTemplate = group.getInstanceOf("DBErrorList");
            }catch(IllegalArgumentException ex){
                dbErrorListTemplate = new StringTemplate()
                dbErrorListTemplate.setTemplate('<tr><th colspan=\'2\' style=\'background-color: DarkRed; color: white\'>$header$</th></tr>$errors$')
            }
            for(String errString : systemErrorList) {
                temp +="<tr>${errString}</tr>"
            }
            dbErrorListTemplate.setAttribute("header",firstHeader)
            dbErrorListTemplate.setAttribute("errors",temp)
            result += dbErrorListTemplate.toString()
        }
        temp = ""
        if(stringErrorsList.size()>0) {
            StringTemplate strErrorListTemplate
            try{
                strErrorListTemplate = group.getInstanceOf("strErrorList");
            }catch(IllegalArgumentException ex){
                strErrorListTemplate = new StringTemplate()
                strErrorListTemplate.setTemplate('<tr><th colspan=\'2\' style=\'background-color: mediumpurple; color: white\'>$header$</th></tr>$errors$')
            }
            for(String errString : stringErrorsList) {
                temp +="<tr>${errString}</tr>"
            }
            strErrorListTemplate.setAttribute("header", secondHeader)
            strErrorListTemplate.setAttribute("errors",temp)
            result += strErrorListTemplate.toString()
        }
        return result
    }
}