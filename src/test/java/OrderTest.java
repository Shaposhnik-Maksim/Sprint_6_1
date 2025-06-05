
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPage;
import pageobjects.OrderPage;
import pageobjects.RentalPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private MainPage mainPage;
    private OrderPage orderPage;
    private RentalPage rentalPage;

    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String color;
    private final String comment;

    public OrderTest(String name, String surname, String address,
                     String metroStation, String phone, String date,
                     String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Макс", "Максимович", "ул. Автозаводская, 17", "Автозаводская", "+79991111111", "01.01.2025", "black", "Комментарий Тук Тук"},
                {"Мария", "Мариновна", "пр. Садовый, 1", "Планерная", "+79992222222", "23.12.2025", "grey", "Комментарий ТУК ТУК"},
                {"Алексей", "Алексеевич", "пер. Воробья, 1074", "Парк культуры", "+79993333333", "21.12.2024", "black", "Комментарий тук тук"}
        };
    }

    @Test
    public void testHappyPathOrderByTopButton() {
        initializePageObjects();

        mainPage.clickCookieButton();
        mainPage.clickOrderButtonTop();
        assertOrderHeaderVisible();

        orderPage.fillOrderForm(name, surname, address, metroStation, phone);
        assertRentalHeaderVisible();

        rentalPage.fillRentalForm(date, color, comment);
        waitForConfirmButtonAndClick();
        assertOrderConfirmationVisible();
    }

    @Test
    public void testHappyPathOrderByBottomButton() {
        initializePageObjects();
        mainPage.goToAndClickBottomOrderButton();
        assertOrderHeaderVisible();

        orderPage.fillOrderForm(name, surname, address, metroStation, phone);
        assertRentalHeaderVisible();

        rentalPage.fillRentalForm(date, color, comment);
        waitForConfirmButtonAndClick();
        assertOrderConfirmationVisible();
    }

    private void initializePageObjects() {
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        rentalPage = new RentalPage(driver);
    }

    private void assertOrderHeaderVisible() {
        assertTrue("Заголовок заказа отображаеться", orderPage.isOrderHeaderVisible());
    }

    private void assertRentalHeaderVisible() {
        assertTrue("Заголовок аренды отображаеться", rentalPage.isRentalHeaderVisible());
    }

    private void assertOrderConfirmationVisible() {
        assertTrue("Подтверждение заказа отображаеться", rentalPage.isOrderConfirmationVisible());
    }

    private void waitForConfirmButtonAndClick() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(RentalPage.confirmButton));
        rentalPage.clickConfirmButton();
    }
}
