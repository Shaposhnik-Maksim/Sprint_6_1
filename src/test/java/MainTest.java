import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MainTest extends BaseTest {
    private final int questionIndex;
    private final int answerIndex;
    private MainPage mainPage;

    public MainTest(int questionIndex, int answerIndex) {
        this.questionIndex = questionIndex;
        this.answerIndex = answerIndex;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {1, 1},
                {2, 2},
                {3, 3},
                {4, 4},
                {5, 5},
                {6, 6},
                {7, 7}
        };
    }

    @Test
    public void testFaqFunctionality() {
        mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickQuestion(questionIndex);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPage.getAnswerLocator(answerIndex)));

        assertTrue("Ответ на вопрос видно", mainPage.isAnswerVisible(answerIndex));
    }
}