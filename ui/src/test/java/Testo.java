import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

@Report
@Listeners({TextReport.class})
public class Testo {

    @Test
    public void homePageTest() {
        Configuration.baseUrl = "www.movista.ru";
        open("/");
    }
}
