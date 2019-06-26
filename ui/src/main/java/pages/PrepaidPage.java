package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.CustomAssert;

import static com.codeborne.selenide.Selenide.$;

public class PrepaidPage {
    private SelenideElement pay = $(By.xpath("//button[.='Оплатить']"));
    private SelenideElement routeInfo = $(By.xpath("//div[text()[contains(.,'Информация о рейсе:')]]"));
    private SelenideElement price = $(By.xpath("//div[contains(@class, 'result-price__price')]"));

    @Step("Проверка цены")
    public PrepaidPage checkPrice(String cartPrice){
        routeInfo.waitUntil(Condition.visible, 60000);
        price.waitUntil(Condition.enabled, 10000);
        String strPrice = price.getText();
        CustomAssert.assertEquals(strPrice, cartPrice);
        return this;
    }

    @Step("Проверка дескрипшена")
    public String toPay(String earlierRouteInfo){
        String getRouteInfo = routeInfo.getText().replace("Информация о рейсе: ", "")
                .replace("-", "→");
        CustomAssert.assertEquals(getRouteInfo, earlierRouteInfo);
        pay.click();
        return getRouteInfo;
    }
}
