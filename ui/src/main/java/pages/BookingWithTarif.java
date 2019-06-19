package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.CustomAssert;

import static org.testng.Assert.assertEquals;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BookingWithTarif {

    private static String choseBtn= "//button[.='Выбрать']";
    private static String headerPrice = "(//div[contains(@class, 'header__price')])";
    private SelenideElement routeDescription = $(By.xpath("//div[contains(@class, 'container__description')]"));
    private SelenideElement selectedFooter = $(By.xpath("//div[contains(@class, 'selected-label')]"));
    private ElementsCollection footers = $$(By.xpath("//div[contains(@class, 'footer-container')]"));
    private ElementsCollection pricesHeader = $$(By.xpath(headerPrice));
    private ElementsCollection tarifHeader = $$(By.xpath("//div[@class='avia-rate-header']"));
    private ElementsCollection сhoseBtns = $$(By.xpath(choseBtn));
    private SelenideElement generalPrice = $(By.xpath("//div[contains(@class, 'general-price')]"));
    private SelenideElement nextBtn = $(By.xpath("//button[.='Далее']"));

    public BookingWithTarif checkPrice(String price) {
        SelenideElement hideDetails = $(By.xpath("//*[@id='pay-header__info']//*[text()[contains(.,'" + price + "')]]"));
        hideDetails.waitUntil(Condition.visible, 30000);
        return this;
    }

    public String getDescription() {
        return routeDescription.getText();
    }

    public void nextPage(){
        nextBtn.scrollTo().click();
    }

    @Step("Выбор другого тарифа с проверкой изменения цены")
    public BookingWithTarif changeToSecondTariff() {
        String secondPrice = $(By.xpath(headerPrice+"[2]")).getText();
        footers.get(2).$x(choseBtn).click();
        String generalPriceTxt = generalPrice.getText().replace("Итого: ", "");
        CustomAssert.assertEquals(secondPrice, generalPriceTxt);
        return this;
    }

    public BookingWithTarif checkFirstTariff(String price) {
        $(By.xpath("//div[@class='avia-rate-header'][1]//div[text()[contains(.,'"+price+"')]]"))
                .shouldBe(Condition.enabled);
        $(By.xpath("//div[contains(@class, 'footer-container')][1]//div[contains(@class, 'selected-label')]"))
                .shouldBe(Condition.enabled);
        return this;
    }


}
