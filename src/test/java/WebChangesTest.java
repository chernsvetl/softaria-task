import org.example.WebChanges;
import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WebChangesTest {

    @Test
    void generatedEmailTextTest() {

        Hashtable<String, String> yesterdayData = new Hashtable<>();
        Hashtable<String, String> todayData = new Hashtable<>();

        yesterdayData.put("https://www.google.com/page1", "<p>info1</p>");
        yesterdayData.put("https://www.google.com/page2", "<p>info2</p>");

        todayData.put("https://www.google.com/page2", "<p>upd2</p>");
        todayData.put("https://www.google.com/page3", "<p>info3</p>");

        String expectedEmailText = "Здравствуйте, дорогая и.о. секретаря\n\n" +
                "За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n" +
                "Исчезли следующие страницы:\n" +
                "https://www.google.com/page1\n" +
                "Появились следующие новые страницы:\n" +
                "https://www.google.com/page3\n" +
                "Изменились следующие страницы:\n" +
                "https://www.google.com/page2\n\n" +
                "С уважением,\n" +
                "автоматизированная система\n" +
                "мониторинга.\n"
                ;

        String actualEmailText= WebChanges.generateEmailText(yesterdayData, todayData);
        assertEquals(expectedEmailText, actualEmailText);
    }
}
