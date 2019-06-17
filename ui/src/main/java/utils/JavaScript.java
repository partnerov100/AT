package utils;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;

public class JavaScript {

    public static SelenideElement scrollToElement(SelenideElement element) {
        ((JavascriptExecutor) WebDriverRunner.getWebDriver())
                .executeScript("arguments[0].scrollIntoView(true);", element.toWebElement());
        return element;
    }
}
