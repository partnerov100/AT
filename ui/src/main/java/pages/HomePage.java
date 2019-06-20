package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.Assert;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import utils.DatePicker;

import static com.codeborne.selenide.Selenide.$;

@Report
@Listeners({TextReport.class})
public class HomePage {
    private String fDay = DatePicker.getDate();
    private SelenideElement searchForm = $(By.xpath("//div[@class='search-form']"));
    private SelenideElement dropdownDialog = $(By.xpath("//div[@class='ui-dropdown-dialog']"));
    private SelenideElement fromCity = $(By.xpath("//button[contains(@class, 'ignore-from')]"));
    private SelenideElement toCity = $(By.xpath("//button[contains(@class, 'ignore-to')]"));
    private SelenideElement fromDate = $(By.xpath("//button[contains(@class, 'rangeFrom')]"));
    private SelenideElement transport = $(By.xpath("(//button[contains(@class, 'item__button')])[last()]"));
    private SelenideElement cityList = $(By.xpath("//div[contains(@class, 'menu-list')]"));
    private SelenideElement input = $(By.xpath("//input"));
    private SelenideElement popularCity = $(By.xpath("//div[text()[contains(.,'Популярные города')]]"));
    private SelenideElement loader = $(By.xpath("//div[text()[contains(.,'Загрузка...')]]"));
    private SelenideElement focusedItem = $(By.xpath("//div[contains(@class, 'option--isFocused')]"));
    private SelenideElement dayToday = $(By.xpath("//div[contains(@class, 'Day--today')]"));
    private SelenideElement futureDay = $(By.xpath("//div[contains(@aria-label, '"+fDay+"')]"));
    private SelenideElement busButton = $(By.xpath("//div[.='Автобус']/parent::button"));
    private SelenideElement trainButton = $(By.xpath("//div[.='Поезд']/parent::button"));
    private SelenideElement airplainButton = $(By.xpath("//div[.='Самолёт']/parent::button"));
    private SelenideElement chooseTransportBtn = $(By.xpath("//button[.='Готово']"));
    private SelenideElement cookieBtn = $(By.xpath("//button[.='Я согласен(-на)']"));
    private SelenideElement searchBtn = $(By.xpath("//button[@id='searchSubmit']"));



    public HomePage chooseFromCity() {
        fromCity.click();
        input.setValue("Москва");
        loader.waitWhile(Condition.disabled, 5000);
        Assert.assertEquals(focusedItem.getText(), "Москва\n" +"Россия");
        focusedItem.click();
        return this;
    }

    public HomePage chooseToCity() {
        toCity.click();
        input.setValue("Нур-Султан");
        loader.waitWhile(Condition.disabled, 5000);
        Assert.assertEquals(focusedItem.getText(), "Нур-Султан\n" +"Акмолинская область");
        focusedItem.click();
        return this;
    }

    public HomePage chooseTomorrow()  {
        fromDate.click();
        futureDay.click();
        return this;
    }

    public HomePage chooseTransport() {
        transport.click();
        airplainButton.click();
        chooseTransportBtn.click();
        return this;
    }

    public TimetablePage search() {
        cookieBtn.click();
        searchBtn.click();
        return new TimetablePage();
    }

}
