import org.junit.Before
import org.junit.Test
import ru.akhitev.reporter.Reporter

/**
 * Created by hitev on 19.02.14.
 */
class ReporterTest {
    Reporter reporter
    @Before
    void init(){
        reporter = new Reporter()
        reporter.setProperties("Общий тест", "Ошибки №1", "Ошибки №2","smtp.mail.ru", 2525, "akhitev.testing@mail.ru", "Omega0987", "alexkhitev@gmail.com", "Test sending from ReporterTest")
        reporter.addToMessage("1.1 Тест","1.2 Тест","system")
        reporter.addToMessage("3.1 Тест","3.2 Тест","other")
    }
    @Test
    void GeneralTest(){
        reporter.sendReport()
    }
}
