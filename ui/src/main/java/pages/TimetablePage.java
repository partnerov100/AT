package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TimetablePage {

    private SelenideElement loader = $(By.xpath("//div[@role='PageLoaderComponent']"));
    private ElementsCollection route = $$(By.xpath("//div[contains(@class, 'route_can-buy')]"));
    private SelenideElement transfer = $(By.xpath("//button[.='Пересадки']"));
    private SelenideElement directCheckbox = transfer.find(By.xpath("//label[.='Прямой']"));
    private SelenideElement transferReady = transfer.find(By.xpath("//button[.='Готово']"));

    public TimetablePage waitRoutes(){
        loader.waitWhile(Condition.enabled, 30000);
        route.last().waitUntil(Condition.enabled, 10000);
        System.out.println(route.size());
        return this;
    }

    public TimetablePage setDirectRoutes(){
        transfer.click();
        directCheckbox.click();
        transferReady.click();
        return this;
    }

}
