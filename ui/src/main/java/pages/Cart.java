package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.Props;

import static com.codeborne.selenide.Selenide.$;

public class Cart {

    private SelenideElement hideDetails = $(By.xpath("//div[text()[contains(.,'Скрыть детали')]]"));
    private SelenideElement showDetails = $(By.xpath("//div[text()[contains(.,'Показать детали')]]"));
    private SelenideElement buyTicket = $(By.xpath("//div[text()[contains(.,'Купить билет')]]"));
    private SelenideElement price = $(By.xpath("//div[contains(@class, 'container__price')]"));

    public Cart buyTicket(String routeInfo){
        showDetails.click();
        $(By.xpath("//div[text()[contains(.,'"+ routeInfo +"')]]")).shouldBe(Condition.visible);
        hideDetails.click();
        buyTicket.click();
        return this;
    }

    public String getPrice() {
        return price.getText();
    }

}
