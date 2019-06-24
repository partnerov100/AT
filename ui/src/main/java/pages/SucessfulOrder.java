package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.CustomAssert;

import static com.codeborne.selenide.Selenide.$;

public class SucessfulOrder {
    private String downloadText = "Скачать маршрутные квитанции";
    private String alreadySendText = "Мы уже отправили всю важную информацию на вашу электронную почту. Но вы всегда сможете скачать квитанции здесь.";
    private SelenideElement downloadBtn = $(By.xpath("//div[text()[contains(.,'"+downloadText+"')]]"));
    private SelenideElement congrats = $(By.xpath("//div[contains(@class, 'thanks__title')]"));

    public SucessfulOrder checkCongratsText(){
        String thanksText = "Поздравляем с успешной покупкой билетов!";
        congrats.waitUntil(Condition.enabled, 30000);
        CustomAssert.assertEquals(congrats.getText(), thanksText);
        return this;
    }

    public void checkDownload(){
        downloadBtn.click();

    }

}
