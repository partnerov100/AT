package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Documents {

    private SelenideElement documentType = $(By.xpath("//div[@class='select-field']"));
    private SelenideElement internationalPasp = $(By.xpath("//div[@class]/div[.='Заграничный паспорт РФ']"));
    private SelenideElement serialNumber = $(By.xpath("//input[@name='passengers[0].number']"));
    private SelenideElement passportDate = $(By.xpath("//input[@name='passengers[0].expireDate']"));


    public void setDocs(){
        documentType.click();
        internationalPasp.click();
        serialNumber.setValue("123456789");
        passportDate.setValue("10102025");
    }

}
