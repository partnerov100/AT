package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TimetablePage {
    private int k = 0;
    private SelenideElement loader = $(By.xpath("//div[@role='PageLoaderComponent']"));
    private ElementsCollection ourRoutes = $$(By.xpath("//div[contains(@class, 'route_can-buy')]"));
    private ElementsCollection allRoutes = $$(By.xpath("//div[@class='timetable-route__container']"));
    private SelenideElement transfer = $(By.xpath("//button[.='Пересадки']"));
    private SelenideElement directCheckbox = transfer.find(By.xpath("//label[.='Прямой']"));
    private SelenideElement transferReady = transfer.find(By.xpath("//button[.='Готово']"));
    private SelenideElement scrollLoader = $(By.xpath("//div[@class='loader-horizontal']"));
    private ElementsCollection routesByTitle = $$(By.xpath("//div[@title='SCAT']"));
//    private SelenideElement routeByTitle = $(By.xpath("//div[@title='SCAT']["+k+"]"));
    private SelenideElement routeName = $(By.xpath("//div[text()[contains(.,'DV-755, Boeing 737, Регулярный')]]"));
    private SelenideElement buyTicket = $(By.xpath("//div[text()[contains(.,'Купить маршрут')]]"));
    private SelenideElement hideDetails = $(By.xpath("//div[text()[contains(.,'Купить маршрут')]]"));
    //*[.='"Купить маршрут"']   //span[.='Скрыть детали']


    public TimetablePage waitRoutes(){
        loader.waitWhile(Condition.enabled, 30000);
        ourRoutes.last().waitUntil(Condition.enabled, 10000);
        return this;
    }

    public TimetablePage setDirectRoutes(){
        transfer.click();
        directCheckbox.click();
        transferReady.click();
        return this;
    }

    public TimetablePage scrollDown(){
        while(scrollLoader.isDisplayed()){
            try{
            scrollLoader.scrollTo();

                Thread.sleep(150);
            } catch (InterruptedException ignored) {
//                e.printStackTrace();
            }
            if(!scrollLoader.isDisplayed()){
                break;
            }
        }

        return this;
    }
//    route-trip-name
//    DV-755, Boeing 737, Регулярный
    public void findRoute(){
        for(int i =1; i<routesByTitle.size()+1; i++){
            SelenideElement routeByTitle = $(By.xpath("//div[@title='SCAT']["+i+"]"));
//            routesByTitle.get(i)
            routeByTitle
                    .scrollTo()
                    .click();
            if(routeName.exists()){
                i=routesByTitle.size()+1;
            }
            else {
                hideDetails.click();
            }
        }
        buyTicket.click();
    }


}
