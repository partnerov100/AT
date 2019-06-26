package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import utils.CustomAssert;
import utils.Props;

import static com.codeborne.selenide.Selenide.$;

public class SberbankPage {

    private static String cardNum = Props.getData("cardNumber");
    private static String cardCvv = Props.getData("cardCvv");
    private static String cardDate = Props.getData("cardDate");
    private SelenideElement price = $(By.xpath("//span[@class='sb_order-price__amount']"));
    private SelenideElement cardnumber = $(By.id("cardnumber"));
    private SelenideElement expiry = $(By.id("expiry"));
    private SelenideElement cvc = $(By.id("cvc"));
    private SelenideElement pay = $(By.xpath("//button[@class='sbersafe-pay-button']"));

    public SberbankPage checkPrice(String cartPrice){
        price.waitUntil(Condition.enabled, 20000);
        String sberPrice = price.getText();
        cartPrice = cartPrice.replace(" ₽", "");
        //TODO убрать следущие две строки если цены будут везде с копейками
        cartPrice = StringUtils.substringBefore(cartPrice, ",");
        sberPrice = StringUtils.substringBefore(sberPrice, ",");

        CustomAssert.assertEquals(sberPrice, cartPrice);
        return this;
    }

    public void setCardAndPay(){
        cardnumber.setValue(cardNum);
        cvc.setValue(cardCvv);
        expiry.setValue(cardDate);
        pay.waitUntil(Condition.enabled, 10000).click();

    }

}