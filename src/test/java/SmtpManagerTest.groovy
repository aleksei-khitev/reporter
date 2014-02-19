import org.junit.Before
import org.junit.Test
import ru.akhitev.reporter.email.report.ReportManager
import ru.akhitev.reporter.email.report.ReportManagerImplOne
import ru.akhitev.reporter.email.sender.SmtpManager
import ru.akhitev.reporter.email.sender.SmtpManagerImplOne

/**
 * Created by hitev on 19.02.14.
 */
class SmtpManagerTest {
    SmtpManager smtpManager
    ReportManager reportManager
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
    @Test
    void sendMessageTest(){
        smtpManager.sendMessage("123", "alexkhitev@gmail.com", "akhitev.testing@mail.ru", "Test sending from SmtpManagerTest")
    }
}
