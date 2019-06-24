package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.CustomAssert;

import static com.codeborne.selenide.Selenide.$;

public class PrepaidPage {
    private SelenideElement pay = $(By.xpath("//button[.='Оплатить']"));
    private SelenideElement routeInfo = $(By.xpath("//div[text()[contains(.,'Информация о рейсе:')]]"));
    private SelenideElement price = $(By.xpath("//div[contains(@class, 'result-price__price')]"));
//    class="loader-horizontal__wrapper ui-button__inner-loader loader-horizontal__wrapper_center loader-horizontal__wrapper_xxxs"

    public PrepaidPage checkPrice(String cartPrice){
        routeInfo.waitUntil(Condition.visible, 20000);
        String strPrice = price.getText();
        CustomAssert.assertEquals(cartPrice, strPrice);
        return this;
    }

    public void toPay(String earlierRouteInfo){
        String getRouteInfo = routeInfo.getText().replace("Информация о рейсе: ", "")
                .replace("-", "→");
        CustomAssert.assertEquals(earlierRouteInfo, getRouteInfo);
        pay.click();
    }
}
