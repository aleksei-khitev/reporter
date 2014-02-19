package ru.akhitev.reporter.email.report

import org.junit.Before
import org.junit.Test
import ru.akhitev.reporter.email.report.ReportManager
import ru.akhitev.reporter.email.report.ReportManagerImplOne

/**
 * Тест создателя тела письма
 *
 * @author Хитёв Алексей Юрьевич (alexkhitev@gmail.com)
 */
class ReportManagerTest{
    /**
     * Создатель тела письма
     */
    ReportManager reportManager
    /**
     * Инициализация
     */
    @Before
    void init(){
        reportManager=new ReportManagerImplOne()
        reportManager.addToSystemErrorList("1.1 Тест","1.2 Тест")
        reportManager.addToSystemErrorList("2.1 Тест","2.2 Тест")
        reportManager.addToSystemErrorList("3.1 Тест","3.2 Тест")
        reportManager.addToStringErrorList("1.1 Тест","1.2 Тест")
        reportManager.addToStringErrorList("2.1 Тест","2.2 Тест")
        reportManager.addToStringErrorList("3.1 Тест","3.2 Тест")
    }
    /**
     * Тест
     */
    @Test
    void addToErrorListsAndCreateReport(){
        String report = reportManager.createReport("Общий тест","Ошибки №1", "Ошибки №2")
        assert report != null
        assert report.contains("<html>")
        assert report.contains("th colspan='2' style='background-color: DarkRed; color: white'>")
        assert report.contains("<tr><td><b>1.1 Тест</b></td><td>1.2 Тест</td></tr><tr><td><b>2.1 Тест</b></td><td>2.2 Тест</td></tr><tr><td><b>3.1 Тест</b></td><td>3.2 Тест</td></tr>")
        assert report.contains("<th colspan='2' style='background-color: mediumpurple; color: white'>")
        assert report.contains("<tr><td><b>1.1 Тест</b></td><td>1.2 Тест</td></tr><tr><td><b>2.1 Тест</b></td><td>2.2 Тест</td></tr><tr><td><b>3.1 Тест</b></td><td>3.2 Тест</td></tr>")
    }
}