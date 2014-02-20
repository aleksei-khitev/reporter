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

package ru.akhitev.reporter

import org.junit.Before
import org.junit.Test

/**
 * Тест работы приложения в целом
 *
 * @author Хитёв Алексей Юрьевич (alexkhitev@gmail.com)
 */
class ReporterTest {
    Reporter reporterOne
    Reporter reporterTwo
    /**
     * Инициализация
     */
    @Before
    void init(){
        reporterOne = new Reporter()
        reporterOne.setProperties("Общий тест", "Ошибки №1", "Ошибки №2","smtp.mail.ru", 2525, "akhitev.testing@mail.ru", "Omega0987", "alexkhitev@gmail.com", "Test sending from ru.akhitev.reporter.ReporterTest")
        reporterOne.addToMessage("1.1 Тест","1.2 Тест","system")
        reporterOne.addToMessage("3.1 Тест","3.2 Тест","other")
    }
    /**
     * Тест
     */
    @Test
    void GeneralTest(){
        reporterOne.sendReport()
    }
    @Test
    void FileWithPropertiesTest(){
        reporterTwo = new Reporter("dat${File.separator}reporter.properties")
        reporterTwo.addToMessage("1.1 Тест","1.2 Тест","system")
        reporterTwo.addToMessage("3.1 Тест","3.2 Тест","other")
        reporterTwo.sendReport()
    }
}
