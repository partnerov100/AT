package pages;

import com.codeborne.selenide.Condition;
import org.testng.Assert;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private SelenideElement searchForm = $(By.xpath("//div[@class='search-form']"));
    private SelenideElement dropdownDialog = searchForm.$(By.xpath("//div[@class='ui-dropdown-dialog']"));
    private SelenideElement fromCity = searchForm.find(By.xpath("//button[contains(@class, 'ignore-from')]"));
    private SelenideElement toCity = searchForm.$(By.xpath("//button[contains(@class, 'ignore-to')]"));
    private SelenideElement fromDate = searchForm.$(By.xpath("//button[contains(@class, 'rangeFrom')]"));
    private SelenideElement transport = searchForm.$(By.xpath("(//button[contains(@class, 'item__button')])[last()]"));
    private SelenideElement cityList = searchForm.$(By.xpath("//div[contains(@class, 'menu-list')]"));
    private SelenideElement input = searchForm.$(By.xpath("//input"));
    private SelenideElement popularCity = cityList.$(By.xpath("//div[text()[contains(.,'Популярные города')]]"));
    private SelenideElement loader = cityList.$(By.xpath("//div[text()[contains(.,'Загрузка...')]]"));
    private SelenideElement focusedItem = cityList.$(By.xpath("//div[contains(@class, 'option--isFocused')]"));

    public HomePage chooseFromCity() {
        fromCity.click();
        input.setValue("Москва");
        loader.waitWhile(Condition.disabled, 5000);
        Assert.assertEquals(focusedItem.getText(), "Москва\n" +"Россия");
        focusedItem.click();
        return this;
    }
    public HomePage chooseToCity()  {
        toCity.click();
        input.setValue("Нур-Султан");
        loader.waitWhile(Condition.disabled, 5000);
        Assert.assertEquals(focusedItem.getText(), "Нур-Султан\n" +"Акмолинская область");
        focusedItem.click();
        return this;
    }

}
