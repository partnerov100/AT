import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.Props;
import utils.TakeAttachments;

import static com.codeborne.selenide.Selenide.open;
import java.net.MalformedURLException;
import java.net.URL;


@Listeners({TakeAttachments.class})
public class Config {

    final static String webUrl = Props.getEnvData("webUrl");
    private static String cookie = Props.getEnvData("cookie");

    private static String getRunType() {
        String runType = System.getProperty("runType");
        if(runType==null) {
            runType = "local";
            System.setProperty("runType", runType);
        }
        return runType;
    }

    void addCookie(){
      open(webUrl);//"about:blank"
        WebDriverRunner.getWebDriver().manage().deleteCookieNamed("mv_test");
        Cookie ck = new Cookie("mv_test", cookie);
        WebDriverRunner.getWebDriver().manage().addCookie(ck);
    }

    @BeforeClass
    public void setUp() throws MalformedURLException {
        switch (getRunType()) {
            case ("local"):
                Configuration.driverManagerEnabled = false;
                Configuration.startMaximized = true;
                Configuration.reportsFolder = "target/allure-results";
                Configuration.browserVersion = "75.0";
                break;
            case ("server"):
                DesiredCapabilities capabilities = new DesiredCapabilities();
                ChromeOptions options = new ChromeOptions();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("75.0");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("start-maximized");
                Configuration.screenshots = false;
                capabilities.setCapability("screenshots", false);
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.104.151:4444/wd/hub"), capabilities);
                WebDriverRunner.setWebDriver(driver);
                break;
        }
    }

    @BeforeMethod
    public void clearCache() {
        WebDriverRunner.clearBrowserCache();
    }

    @AfterClass
    public void tearDown() {
        WebDriverRunner.getWebDriver().getCurrentUrl();
        WebDriverRunner.getWebDriver().close();
        WebDriverRunner.closeWebDriver();
    }
}
