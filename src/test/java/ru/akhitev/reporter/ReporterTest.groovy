package ru.akhitev.reporter

import org.junit.Before
import org.junit.Test

/**
 * Тест работы приложения в целом
 *
 * @author Хитёв Алексей Юрьевич (alexkhitev@gmail.com)
 */
class ReporterTest {
    Reporter reporter
    /**
     * Инициализация
     */
    @Before
    void init(){
        reporter = new Reporter()
        reporter.setProperties("Общий тест", "Ошибки №1", "Ошибки №2","smtp.mail.ru", 2525, "akhitev.testing@mail.ru", "Omega0987", "alexkhitev@gmail.com", "Test sending from ru.akhitev.reporter.ReporterTest")
        reporter.addToMessage("1.1 Тест","1.2 Тест","system")
        reporter.addToMessage("3.1 Тест","3.2 Тест","other")
    }
    /**
     * Тест
     */
    @Test
    void GeneralTest(){
        reporter.sendReport()
    }
}
