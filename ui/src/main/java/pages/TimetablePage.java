package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Props;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TimetablePage {
    private static String routeTitleStr = Props.getData("routeTitle");
    private static String routeNameStr = Props.getData("routeFullName");
    private static final Logger logger = LoggerFactory.getLogger(TimetablePage.class.getName());
    private SelenideElement loader = $(By.xpath("//div[@role='PageLoaderComponent']"));
    private ElementsCollection ourRoutes = $$(By.xpath("//div[contains(@class, 'route_can-buy')]"));
    private ElementsCollection allRoutes = $$(By.xpath("//div[@class='timetable-route__container']"));
    private SelenideElement transfer = $(By.xpath("//button[.='Пересадки']"));
    private SelenideElement directCheckbox = transfer.find(By.xpath("//label[.='Прямой']//input"));
    private SelenideElement oneTransferChkbox = transfer.find(By.xpath("//label[.='1 пересадка']//input"));
    private SelenideElement transferReady = transfer.find(By.xpath("//button[.='Готово']"));
    private SelenideElement scrollLoader = $(By.xpath("//div[@class='loader-horizontal']"));
    private ElementsCollection routesByTitle = $$(By.xpath("//div[@title='"+routeTitleStr+"']"));
    private SelenideElement routeName = $(By.xpath("//div[text()[contains(.,'"+ routeNameStr +"')]]"));
    private SelenideElement buyTicket = $(By.xpath("//div[text()[contains(.,'Купить маршрут')]]"));
    private SelenideElement hideDetails = $(By.xpath("//span[text()[contains(.,'Скрыть детали')]]"));
    private SelenideElement route = $(By.xpath("//div[@title='Air Astana'][1]"));


    public TimetablePage waitRoutes(){
        loader.waitWhile(Condition.enabled, 30000);
        ourRoutes.last().waitUntil(Condition.enabled, 10000);
        return this;
    }

    /**
     * Оставляем только прямые маршруты
     */
    public TimetablePage setDirectRoutes() throws ElementNotFound {
        transfer.click();
        oneTransferChkbox.click();
        oneTransferChkbox.shouldNotBe(Condition.checked);
        directCheckbox.shouldBe(Condition.checked);
        transferReady.click();
        try{
            loader.waitUntil(Condition.enabled, 2000);
            loader.waitWhile(Condition.enabled, 5000);
        } catch (ElementNotFound e) {
            logger.error(e.toString());
        }
        return this;
    }

    /**
     * Скролл списка пока не исчезнет loader
     */
    public TimetablePage scrollDown() throws InterruptedException {
        while(scrollLoader.isDisplayed()){
            try{
            scrollLoader.scrollTo();
            Thread.sleep(150);
            } catch (ElementNotFound e) {
                logger.error(e.toString());
            }
            if(!scrollLoader.isDisplayed()){
                break;
            }
        }
        return this;
    }

    public Cart findRoute(){
        for(int i =0; i<routesByTitle.size(); i++){
            routesByTitle.get(i)
                .scrollIntoView("{block: \"end\"}")
                .click();
            if(routeName.exists()) {
                i = routesByTitle.size();
            } else {
                hideDetails
                    .scrollIntoView("{block: \"end\"}")
                    .click();
            }
        }
        buyTicket
                .scrollTo()
                .click();
        return new Cart();
    }


}
