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

package ru.akhitev.reporter.email.report

import org.junit.Before
import org.junit.Test

/**
 * Report manager's test
 *
 * @author Aleksei Khitev (alexkhitev@gmail.com)
 */
class ReportManagerTest{
    /**
     * Report Manager
     */
    IReportManager reportManager
    /**
     * Initialising
     */
    @Before
    void init(){
        reportManager=new ReportManagerFirstImpl()
        reportManager.addToSystemErrorList("1.1 Тест","1.2 Тест")
        reportManager.addToSystemErrorList("2.1 Тест","2.2 Тест")
        reportManager.addToSystemErrorList("3.1 Тест","3.2 Тест")
        reportManager.addToStringErrorList("1.1 Тест","1.2 Тест")
        reportManager.addToStringErrorList("2.1 Тест","2.2 Тест")
        reportManager.addToStringErrorList("3.1 Тест","3.2 Тест")
    }
    /**
     * Test
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