/*
 * ru.akhitev.reporter. Программа для отправки по почте отчетов о работе в формате HTML
 * Copyright (c) 2014 Хитёв Алексей
 *
 * Этот файл - часть ru.akhitev.reporter. Вы можете перераспространять ее и/или изменять ее на условиях
 * Стандартной общественной лицензии GNU в том виде, в каком она была опубликована Фондом свободного программного
 * обеспечения; либо версии 3 лицензии, либо (по вашему выбору) любой более поздней версии.
 * Эта программа распространяется в надежде, что она будет полезной, но БЕЗО ВСЯКИХ ГАРАНТИЙ; даже без неявной гарантии
 * ТОВАРНОГО ВИДА или ПРИГОДНОСТИ ДЛЯ ОПРЕДЕЛЕННЫХ ЦЕЛЕЙ. Подробнее см. в Стандартной общественной лицензии GNU.
 * Вы должны были получить копию Стандартной общественной лицензии GNU вместе с этой программой. Если это не так, см.
 * <http://www.gnug/licenses/>.
 */

package ru.akhitev.reporter.email.sender

import org.junit.Before
import org.junit.Test
import ru.akhitev.reporter.email.report.ReportManager
import ru.akhitev.reporter.email.report.ReportManagerImplOne

/**
 * Тест для обработчика отправки письма
 *
 * @author Хитёв Алексей Юрьевич (alexkhitev@gmail.com)
 */
class SmtpManagerTest {
    /**
     * Обработчика отправки письма
     */
    SmtpManager smtpManager
    /**
     * Создатель тела письма
     */
    ReportManager reportManager
    /**
     * Инициализация
     */
    @Before
    void init(){
        smtpManager = new SmtpManagerImplOne()
        smtpManager.setMailSender("smtp.mail.ru", 2525, "akhitev.testing@mail.ru", "Omega0987")
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
    void sendMessageTest(){
        smtpManager.sendMessage("123", "alexkhitev@gmail.com", "akhitev.testing@mail.ru", "Test sending from ru.akhitev.reporter.email.sender.SmtpManagerTest")
    }
}
