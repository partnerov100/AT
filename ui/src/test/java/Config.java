import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.Props;

import java.net.MalformedURLException;
import java.net.URL;

public class Config {

    protected final String webUrl = Props.getURLs("webUrl");

    public static String getRunType() {
        String runType = System.getProperty("runType");
        if(runType==null) {
            String type = "local";
            System.setProperty("runType", type);
            runType = type;
        }
        return runType;
    }

    @BeforeClass
    public void setUp() throws MalformedURLException {
        switch (getRunType()) {
            case ("local"):
                Configuration.startMaximized = true;
                Configuration.reportsFolder = "target/allure-results";
                Configuration.driverManagerEnabled = false;
                Configuration.browserVersion = "75.0";
                break;
            case ("server"):
                DesiredCapabilities capabilities = new DesiredCapabilities();
                ChromeOptions options = new ChromeOptions();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("75.0");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("start-maximized");
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
