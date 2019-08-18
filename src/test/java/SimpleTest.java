import cian.Base;
import cian.pages.MainPage;
import cian.pages.ResultPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SimpleTest extends Base {

    private WebDriver driver = getDriver();

    @BeforeTest
    public void getPage() {
        driver.get(URL);
    }

    @AfterTest
    public void after() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test
    public void searchTest() {
        MainPage mainPage = new MainPage(driver);
        ResultPage resultPage = mainPage.setAction("Купить")
                .setType("Квартира")
                .setCategory("Вторичка")
                .quarterDeselectAll()
                .setQuarter("6-комнатная +")
                .setQuarter("5-комнатная")
                .setLocation("Тыва")
                .setPriceLimit("5000000")
                .submitSearch();
        assertEquals(resultPage.getCountResultRaw(), 1);
    }
}
