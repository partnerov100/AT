package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Documents {

    private SelenideElement email = $(By.id("email"));
    private SelenideElement phone = $(By.id("phoneNumber"));
    private String apply = "(//span[text()[contains(.,'Я согласен')]]/ancestor::div[1]/input)";
    private SelenideElement documentType = $(By.xpath("//div[@class='select-field']"));
    private SelenideElement internationalPasp = $(By.xpath("//div[@class]/div[.='Заграничный паспорт РФ']"));
    private SelenideElement serialNumber = $(By.xpath("//input[@name='passengers[0].number']"));
    private SelenideElement passportDate = $(By.xpath("//input[@name='passengers[0].expireDate']"));
    private SelenideElement citizenship = $(By.xpath("//div[text()[contains(.,'Гражданство')]]/following-sibling::div[1]"));
    private SelenideElement lastName = $(By.xpath("//input[@name='passengers[0].lastName']"));
    private SelenideElement firstName = $(By.xpath("//input[@name='passengers[0].firstName']"));
    private SelenideElement middleName = $(By.xpath("//input[@name='passengers[0].middleName']"));
    private SelenideElement birthDay = $(By.xpath("//input[@name='passengers[0].birthDay']"));
    private SelenideElement male = $(By.xpath("//label[@for='passengers[0].sex1']"));
    private SelenideElement female = $(By.xpath("//label[@for='passengers[0].sex2']"));
    private SelenideElement firstApplyChkbox = $(By.xpath(apply+"[1]"));
    private SelenideElement secondApplyChkbox = $(By.xpath(apply+"[2]"));

    public void setDocs(){
        email.setValue("2123f32f@getnada.com");
        documentType.click();
        internationalPasp.click();
        serialNumber.setValue("123456789");
        passportDate.setValue("10102025");
        citizenship.shouldBe(Condition.visible);
        lastName.setValue("Movistov");
        firstName.setValue("Movist");
        middleName.setValue("Movistovich");
        birthDay.setValue("01011900");
        female.click();
        male.click();
        firstApplyChkbox.scrollIntoView(true).click();
        secondApplyChkbox.scrollIntoView(true).click();

    }

}
