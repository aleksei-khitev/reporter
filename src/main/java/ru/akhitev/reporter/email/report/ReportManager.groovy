package ru.akhitev.reporter.email.report

import org.apache.log4j.Logger;

interface ReportManager{
    String createReport(String title, String firstHeader, String secondHeader)
    void addToSystemErrorList(String firstCol, String secondCol);
    void addToStringErrorList(String firstCol, String secondCol);
    boolean havingErrors()
    void setLogger(Logger logger)
}