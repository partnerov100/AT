import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

import static com.codeborne.selenide.Selenide.open;

@Report
@Listeners({TextReport.class})
public class FindTicketsTests extends Config {

    @Test
    public void homePageTest() throws InterruptedException {

        HomePage homePage = open(webUrl, HomePage.class);
        homePage.chooseFromCity()
                .chooseToCity()
                .chooseTomorrow()
                .chooseTransport()
                .search()
                .waitRoutes()
                .setDirectRoutes();

        Thread.sleep(5000);

    }

}
