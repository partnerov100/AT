import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utils.Converter;
import utils.PostgreSQL;

import static com.codeborne.selenide.Selenide.open;

@Report
@Listeners({TextReport.class})
public class BuyTicketsTests extends Config {

    @Test
    public void homePageTest() throws InterruptedException {
        addCookie();
        CartPage cart = new CartPage();
        TimetablePage timetable = new TimetablePage();
        TariffPage tariffPage = new TariffPage();
        PrepaidPage prepaidPage = new PrepaidPage();
        SberbankPage sberPage = new SberbankPage();
        SucessfulOrderPage finalPage = new SucessfulOrderPage();
        //начало теста
        HomePage homePage = open(webUrl, HomePage.class);
        String routeDescr = homePage.setBaseInformation()
            .setDirectRoutes()
            .getRouteDetails();
        String price = timetable.buyRoute();
        String routeInfo = timetable.getRouteInfo();
        cart.checkPrice(price).buyTicket(routeDescr);
        price = Converter.spaceToNbsp(price);
        String priceWithoutPenny = Converter.hidePennyAndRub(price);//выпиливаем копейки из цены
        tariffPage.checkPrice(price).checkDescription(routeDescr);
        tariffPage.checkFirstTariff(priceWithoutPenny);//баг на фронте? Не везде есть копейки на деве
        String secondPrice = tariffPage.changeToSecondTariff();
        tariffPage.nextPage().confirmPhone().setDocs().submit();
        prepaidPage.checkPrice(secondPrice);
        routeInfo = prepaidPage.toPay(routeInfo);
        sberPage.checkPrice(secondPrice).setCardAndPay();
        finalPage.checkCongratsText()
            .checkRouteInfo(routeInfo)
            .checkDownload();
    }

    @Test(enabled = false)
    public void Test1() throws InterruptedException {

        open("http://web-tmp.dev-k8s.movista.ru/");
        System.out.println(PostgreSQL.getCode());
//        Converter.hidePenny("5 923,78 ₽");
//        new TariffPage().nextPage();
//        new DocumentsPage().setDocs();
        Thread.sleep(5000);
    }

}