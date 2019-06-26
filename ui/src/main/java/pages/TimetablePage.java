package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Props;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TimetablePage {
    private static String blockEnd = "{block: \"end\"}";
    private static String routeTitleStr = Props.getData("routeTitle");
    private static String routeNameStr = Props.getData("routeFullName");
    private static String openRouteStr = "//div[contains(@class, 'route--isOpened')]";
    private static final Logger logger = LoggerFactory.getLogger(TimetablePage.class.getName());
    private SelenideElement loader = $(By.xpath("//div[@role='PageLoaderComponent']"));
    private ElementsCollection ourRoutes = $$(By.xpath("//div[contains(@class, 'route_can-buy')]"));
    private ElementsCollection allRoutes = $$(By.xpath("//div[@class='timetable-route__container']"));
    private SelenideElement transfer = $(By.xpath("//button[.='Пересадки']"));
    private SelenideElement directCheckbox = $(By.xpath("//label[.='Прямой']//input"));
    private SelenideElement oneTransferChkbox = $(By.xpath("//label[.='1 пересадка']//input"));
    private SelenideElement transferReady = $(By.xpath("//button[.='Готово']"));
    private SelenideElement scrollLoader = $(By.xpath("//div[@class='loader-horizontal']"));
    private ElementsCollection routesByTitle = $$(By.xpath("//div[@title='"+routeTitleStr+"']"));
    private SelenideElement routeName = $(By.xpath("//div[text()[contains(.,'"+ routeNameStr +"')]]"));
    private SelenideElement buyRoute = $(By.xpath("//div[text()[contains(.,'Купить маршрут')]]"));
    private SelenideElement hideDetails = $(By.xpath("//span[text()[contains(.,'Скрыть детали')]]"));
    private SelenideElement routeDescription = $(By.xpath("//div[contains(@class, 'item__container__description')]"));
    private SelenideElement openPrice = $(By.xpath("//span[contains(@class, 'price--isOpened')]"));
    private SelenideElement openRouteTitle = $(By.xpath(openRouteStr+"//div[contains(@class, 'name__item__title')]"));

    public String getRouteInfo(){
        return openRouteTitle.getText();
    }

    private TimetablePage waitRoutes(){
        loader.waitWhile(Condition.enabled, 90000);
        ourRoutes.last().waitUntil(Condition.enabled, 10000);
        return this;
    }

    @Step("Только прямые маршруты")
    public TimetablePage setDirectRoutes() throws InterruptedException {
        waitRoutes();
        transfer.click();
        oneTransferChkbox.click();
        oneTransferChkbox.shouldNotBe(Condition.checked);
        directCheckbox.shouldBe(Condition.checked);
        transferReady.click();
        try{
            loader.waitUntil(Condition.enabled, 2000);
            loader.waitWhile(Condition.enabled, 5000);
        } catch (ElementNotFound e) {
            logger.warn(e.toString());
        }
        scrollDown();
        return this;
    }

    /**
     * Скролл списка пока не исчезнет loader,
     * периодически loader исчезает и можно словить warning
     */
    public TimetablePage scrollDown() throws InterruptedException {
        while(scrollLoader.isDisplayed()){
            try{
            scrollLoader.scrollTo();
            Thread.sleep(150);
            } catch (ElementNotFound e) {
                logger.warn(e.toString());
            }
            if(!scrollLoader.isDisplayed()){
                break;
            }
        }
        return this;
    }

    public void findRoute(){
        for(int i =0; i<routesByTitle.size(); i++){
            routesByTitle.get(i)
                .scrollIntoView(blockEnd)
                .click();
            if(routeName.exists()) {
                i = routesByTitle.size();
            } else {
                hideDetails
                    .scrollIntoView(blockEnd)
                    .click();
            }
        }
    }

    public String getRouteDetails(){
        routesByTitle.first()
            .scrollIntoView(blockEnd)
            .click();
        return routeDescription.getText();
    }

    private String getPrice() {
        return openPrice.getText();
    }

    /**
     * Купить маршрут и запомнить цену
     */
    public String buyRoute() {
        String price = getPrice().replace("от ", "");
        buyRoute.scrollIntoView(blockEnd).click();
        return price;
    }


}
