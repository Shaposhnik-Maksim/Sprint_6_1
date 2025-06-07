import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MainTest extends BaseTest {
    private final int questionIndex;
    private final String expectedAnswer;

    private static final List<String> EXPECTED_ANSWERS = Arrays.asList(
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    );

    public MainTest(int questionIndex, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {0, EXPECTED_ANSWERS.get(0)},
                {1, EXPECTED_ANSWERS.get(1)},
                {2, EXPECTED_ANSWERS.get(2)},
                {3, EXPECTED_ANSWERS.get(3)},
                {4, EXPECTED_ANSWERS.get(4)},
                {5, EXPECTED_ANSWERS.get(5)},
                {6, EXPECTED_ANSWERS.get(6)},
                {7, EXPECTED_ANSWERS.get(7)}
        };
    }

    @Test
    public void testFaqFunctionality() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickQuestion(questionIndex);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPage.getAnswerLocator(questionIndex)));
        assertTrue("Ответ на вопрос должен быть виден", mainPage.isAnswerVisible(questionIndex));
        String actualAnswer = mainPage.getAnswerText(questionIndex);
        assertEquals("Текст ответа не соответствует ожидаемому", expectedAnswer, actualAnswer);
    }
}