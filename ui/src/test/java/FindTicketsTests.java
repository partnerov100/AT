import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utils.Converter;
import utils.PostgreSQL;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

@Report
@Listeners({TextReport.class})
public class FindTicketsTests extends Config {

    @Test
    public void homePageTest() throws InterruptedException {
        addCookie();
        CartPage cart = new CartPage();
        TimetablePage timetable = new TimetablePage();
        TariffPage booking = new TariffPage();
        PrepaidPage prepaidPage = new PrepaidPage();
        SberbankPage sberPage = new SberbankPage();
        HomePage homePage = open(webUrl, HomePage.class);
        String routeDescr = homePage.setBaseInformation()
            .setDirectRoutes()
            .getRouteDetails();
        String price = timetable.buyRoute();
        String routeInfo = timetable.getRouteInfo();
        String cartPrice = cart.getPrice();
        assertEquals(price, cartPrice);
        cart.buyTicket(routeDescr);
        price = Converter.spaceToNbsp(price);
        String priceWithoutPenny = Converter.hidePennyAndRub(price);
        booking.checkPrice(price).checkDescription(routeDescr);
        booking.checkFirstTariff(priceWithoutPenny);//баг на фронте? Не везде есть копейки на деве
        String secondPrice = booking.changeToSecondTariff();
        booking.nextPage()
            .confirmPhone()
            .setDocs()
            .submit();
        prepaidPage.checkPrice(secondPrice);
        routeInfo = prepaidPage.toPay(routeInfo);
        sberPage.checkPrice(secondPrice).setCardAndPay();
        SucessfulOrderPage orderPage = new SucessfulOrderPage();

        orderPage.checkCongratsText().checkDownload();
        Thread.sleep(15000);
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
