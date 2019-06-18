package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BookingWithTarif {

    private SelenideElement routeDescription = $(By.xpath("//div[contains(@class, 'container__description')]"));
    private SelenideElement selectedFooter = $(By.xpath("//div[contains(@class, 'selected-label')]"));
    private ElementsCollection footers = $$(By.xpath("//div[contains(@class, 'footer-container')]"));
    private ElementsCollection pricesHeader = $$(By.xpath("//div[contains(@class, 'header__price')]"));
    private ElementsCollection tarifHeader = $$(By.xpath("//div[@class='avia-rate-header']"));


    public BookingWithTarif checkPrice(String price) {
        SelenideElement hideDetails = $(By.xpath("//*[@id='pay-header__info']//*[text()[contains(.,'" + price + "')]]"));
        hideDetails.shouldBe(Condition.visible);
        return this;
    }

    public String getDescription() {
        return routeDescription.getText();
    }

    public void checkChoosenTariff(String price) {

        $(By.xpath("//div[@class='avia-rate-header'][1]//div[text()[contains(.,'"+price+"')]]")).shouldBe(Condition.enabled);

    }


}
