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

package ru.akhitev.reporter.email.sender

import org.junit.Before
import org.junit.Test
import ru.akhitev.reporter.email.report.IReportManager
import ru.akhitev.reporter.email.report.ReportManagerFirstImpl

/**
 * Email sender's test
 *
 * @author Aleksei Khitev (alexkhitev@gmail.com)
 */
class SmtpManagerTest {
    /**
     * Smtp manager
     */
    ISmtpManager smtpManager
    /**
     * Report manager
     */
    IReportManager reportManager
    /**
     * initialising
     */
    @Before
    void init(){
        smtpManager = new SmtpManagerFirstImpl()
        smtpManager.setMailSender("smtp.mail.ru", 2525, "akhitev.testing@mail.ru", "Omega0987")
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
    void sendMessageTest(){
        smtpManager.sendMessage("123", "alexkhitev@gmail.com", "akhitev.testing@mail.ru", "Test sending from ru.akhitev.reporter.email.sender.SmtpManagerTest")
    }

    @Test
    void sendMessageWithFiles(){
        smtpManager.sendMessage("123", "alexkhitev@gmail.com", "akhitev.testing@mail.ru", "Test sending from ru.akhitev.reporter.email.sender.SmtpManagerTest",[new File("1.txt"),new File("2.txt"),new File("3.txt")])
    }
}
