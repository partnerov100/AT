import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utils.Converter;

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;
import static com.codeborne.selenide.Selenide.open;

@Report
@Listeners({TextReport.class})
public class FindTicketsTests extends Config {

    @Test
    public void homePageTest() throws InterruptedException {
        Cart cart = new Cart();
        TimetablePage timetable = new TimetablePage();
        BookingWithTarif booking = new BookingWithTarif();
        PrepaidPage prepaidPage = new PrepaidPage();
        SberbankPage sberPage = new SberbankPage();
        HomePage homePage = open(webUrl, HomePage.class);
        String routeDescr = homePage.setBaseInformation()
            .setDirectRoutes().getRouteDetails();
        String price = timetable.buyRoute();
        String routeInfo = timetable.getRouteInfo();
        String cartPrice = cart.getPrice();
        assertEquals(price, cartPrice);
        cart.buyTicket(routeDescr);
        price = Converter.spaceToNbsp(price);
        String priceWithoutPenny = Converter.hidePenny(price);
        String bookingDescr = booking.checkPrice(price).getDescription();
        assertEquals(routeDescr, bookingDescr);
        booking.checkFirstTariff(priceWithoutPenny);//баг на фронте? Не везде есть копейки
        String secondPrice = booking.changeToSecondTariff();
        booking.nextPage()
            .confirmPhone()
            .setDocs()
            .submit();
        prepaidPage.checkPrice(secondPrice);
        prepaidPage.toPay(routeInfo);
        sberPage.checkPrice(secondPrice).setCardAndPay();
        SucessfulOrder orderPage = new SucessfulOrder();
        orderPage.checkCongratsText();
        Thread.sleep(15000);
    }

    @Test
    public void Test1() throws InterruptedException, SQLException {

        open("http://web-tmp.dev-k8s.movista.ru/booking?uid=16bcb455-2e65-46d3-9765-c1e5535f7b2a");
        Converter.hidePenny("5 923,78 ₽");
//        new BookingWithTarif().nextPage();
//        new Documents().setDocs();
        Thread.sleep(5000);
    }

}
