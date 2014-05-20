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

package ru.akhitev.reporter.email.report;

import org.antlr.stringtemplate.StringTemplate
import org.antlr.stringtemplate.StringTemplateGroup
import org.apache.log4j.Logger
/**
 *IReportManager implementation
 *
 * @author Aleksei Khitev (alexkhitev@gmail.com)
 */
class ReportManagerFirstImpl implements IReportManager {
    /**
     * System messages list
     */
    def systemErrorList = []
    /**
     * Another messages list
     */
    def stringErrorsList = []
    /**
     * Are there any messages in the lists
     */
    boolean hasError = false
    /**
     * Templates group
     */
    StringTemplateGroup group = new StringTemplateGroup("group", "templates");
    /**
     * The method used for adding message to system section
     * @param firstCol First column text
     * @param secondCol Second column text
     */
    void addToSystemErrorList(String firstCol, String secondCol) {
        systemErrorList.add("<td><b>${firstCol}</b></td><td>${secondCol}</td>")
        hasError=true
    }
    /**
     * The method used for adding message to another section
     * @param firstCol First column text
     * @param secondCol Second column text
     */
    void addToStringErrorList(String firstCol, String secondCol) {
        stringErrorsList.add("<td><b>${firstCol}</b></td><td>${secondCol}</td>")
        hasError=true
    }
    /**
     * The method used for email's body forming
     * @param title Main title at the template
     * @param firstHeader System section title
     * @param secondHeader Another section title
     * @return Email's body
     */
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
    /**
     * The method used for forming table from message's lists
     * @param firstHeader System section title
     * @param secondHeader Another section title
     * @return tables'
     */
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
    /**
     * Did messages add to the report
     * @return
     */
    boolean havingErrors() {
        hasError
    }
}