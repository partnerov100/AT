import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Report
@Listeners({TextReport.class})
public class FirstTest {

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        capabilities.setBrowserName("chrome");
//        capabilities.setVersion("75.0");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.104.151:4444/wd/hub"), capabilities);
//        WebDriverRunner.setWebDriver(driver);
        Configuration.startMaximized=true;
    }

    @BeforeMethod
    public void clearCache() {
        WebDriverRunner.clearBrowserCache();
    }

    @Test
    public void homePageTest() throws InterruptedException {
        SelenideElement fromCity = $(By.xpath("(//div[@class='search-form']//button[.='Выберите город'])[1]"));
        SelenideElement toCity = $(By.xpath("(//div[@class='search-form']//button[.='Выберите город'])[2]"));
        Configuration.baseUrl = "https://stage.movista.ru";
        open("/");
        fromCity.click();
//        Thread.sleep(5000);


    }
}
