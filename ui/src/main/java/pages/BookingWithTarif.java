package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.Converter;
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
    private static SelenideElement generalPrice = $(By.xpath("//div[contains(@class, 'general-price')]"));
    private SelenideElement nextBtn = $(By.xpath("//button[.='Далее']"));


    public BookingWithTarif checkPrice(String price) {
        SelenideElement hideDetails = $(By.xpath("//*[@id='pay-header__info']//*[text()[contains(.,'" + price + "')]]"));
        hideDetails.waitUntil(Condition.visible, 40000);
        return this;
    }

    public String getDescription() {
        return routeDescription.getText();
    }

    public Documents nextPage(){
        nextBtn.scrollTo().click();
        return new Documents();
    }

    @Step("Выбор другого тарифа с проверкой изменения цены")
    public String changeToSecondTariff() {
        String secondPrice = $(By.xpath(headerPrice+"[2]")).getText();
        footers.get(2).$x(choseBtn).click();
        String totalPrice = generalPrice.getText().replace("Итого: ", "");
        CustomAssert.assertEquals(secondPrice, totalPrice);
        return getFullPrice(totalPrice);
    }

    public BookingWithTarif checkFirstTariff(String price) {
        $(By.xpath("//div[@class='avia-rate-header'][1]//div[text()[contains(.,'"+price+"')]]"))
                .shouldBe(Condition.enabled);
        $(By.xpath("//div[contains(@class, 'footer-container')][1]//div[contains(@class, 'selected-label')]"))
                .shouldBe(Condition.enabled);
        return this;
    }

    private String getFullPrice(String price) {
        String newPrice = price.replace(" ₽", "");
        newPrice = Converter.spaceToNbsp(newPrice);
        SelenideElement mainPrice = $(By.xpath("//div[@class='pay-header__info__inner']//*[text()[contains(.,'"+newPrice+"')]]"));
        mainPrice.scrollIntoView("{block: \"end\"}");
        return mainPrice.getText();
    }




}
