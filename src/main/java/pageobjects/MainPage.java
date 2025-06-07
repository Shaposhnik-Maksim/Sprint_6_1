package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver;
    private static final By COOKIE_BUTTON = By.className("App_CookieButton__3cvqF");
    private static final By ORDER_BUTTON_TOP = By.className("Button_Button__ra12g");
    private static final By ORDER_BUTTON_BOTTOM = By.xpath("//div[contains(@class, 'Home_ThirdPart__LSTEE')]//button[text()='Заказать']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getAnswerLocator(int index) {
        return By.id("accordion__panel-" + index);
    }

    public void clickQuestion(int index) {
        By questionLocator = By.id("accordion__heading-" + index);
        WebElement element = driver.findElement(questionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public boolean isAnswerVisible(int index) {
        return driver.findElement(getAnswerLocator(index)).isDisplayed();
    }

    public String getAnswerText(int index) {
        return driver.findElement(getAnswerLocator(index)).getText().trim();
    }

    public void clickCookieButton() {
        driver.findElement(COOKIE_BUTTON).click();
    }

    public void clickOrderButtonTop() {
        driver.findElement(ORDER_BUTTON_TOP).click();
    }

    public void goToAndClickBottomOrderButton() {
        clickCookieButton();
        WebElement element = driver.findElement(ORDER_BUTTON_BOTTOM);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }
}