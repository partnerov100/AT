package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.Props;

import static com.codeborne.selenide.Selenide.$;

public class Cart {

    private SelenideElement hideDetails = $(By.xpath("//div[text()[contains(.,'Скрыть детали')]]"));
    private SelenideElement showDetails = $(By.xpath("//div[text()[contains(.,'Показать детали')]]"));
    private static String routeNameStr = Props.getData("routeFullName");
    private SelenideElement routeName = $(By.xpath("//div[text()[contains(.,'"+ routeNameStr +"')]]"));
    private SelenideElement buyTicket = $(By.xpath("//div[text()[contains(.,'Купить билет')]]"));

    public void buyTicket(){
        showDetails.click();
        routeName.shouldBe(Condition.visible);
        hideDetails.click();
        buyTicket.click();

    }

}
