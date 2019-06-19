import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BookingWithTarif;
import pages.Cart;
import pages.HomePage;
import pages.TimetablePage;
import utils.Converter;

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
        HomePage homePage = open(webUrl, HomePage.class);
        String routeDescr = homePage.chooseFromCity()
                .chooseToCity()
                .chooseTomorrow()
                .chooseTransport()
                .search()
                .waitRoutes()
                .setDirectRoutes()
                .scrollDown()
                .getRouteDetails();
        String price = timetable.buyRoute();
        String cartPrice = cart.getPrice();
        assertEquals(price, cartPrice);
        cart.buyTicket(routeDescr);
        price = Converter.spaceToNbsp(price);
        String priceWithoutPenny = StringUtils.substringBefore(price, ",");
        String bookingDescr = booking.checkPrice(price).getDescription();
        assertEquals(routeDescr, bookingDescr);
        booking.checkFirstTariff(priceWithoutPenny)//баг на фронте? Не везде есть копейки
            .changeToSecondTariff()
            .nextPage()
            .setDocs();

        Thread.sleep(5000);
    }

    @Test
    public void Test1(){
        open("https://stage.movista.ru/cart?uid=001d929e-9e0e-4766-b1f4-f105722b8c98");
        Cart cart = new Cart();
        String price = cart.getPrice();
        price = Converter.spaceToNbsp(price);
        System.out.println(price);
//        cart.buyTicket();
        new BookingWithTarif().checkPrice(price);
    }

}
