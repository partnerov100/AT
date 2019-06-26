package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CustomAssert;

import java.lang.invoke.MethodHandles;

import static com.codeborne.selenide.Selenide.$;

public class SucessfulOrderPage {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private String downloadText = "Скачать маршрутные квитанции";
    private SelenideElement downloadReceiptsBtn = $(By.xpath("//button[text()[contains(.,'"+downloadText+"')]]"));
    private SelenideElement routeInfo = $(By.xpath("//div[text()[contains(.,'Информация о рейсе:')]]"));
    private SelenideElement congrats = $(By.xpath("//div[contains(@class, 'thanks__title')]"));
    private SelenideElement download = $(By.xpath("//button[.='Скачать']"));

    public SucessfulOrderPage checkCongratsText(){
        String thanksText = "Поздравляем с успешной покупкой билетов!";
        congrats.waitUntil(Condition.enabled, 60000);
        CustomAssert.assertEquals(congrats.getText(), thanksText);
        return this;
    }

    public void checkDownload() {
        downloadReceiptsBtn.click();
        logger.info(WebDriverRunner.getWebDriver().getCurrentUrl());
        download.click();
    }

    public SucessfulOrderPage checkRouteInfo(String earlierRouteInfo) {
        String routeInfoText = routeInfo.getText().replace("Информация о рейсе: ", "")
                .replace("-", "→");;
        CustomAssert.assertEquals(routeInfoText, earlierRouteInfo);
        return this;
    }

}
