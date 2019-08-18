import Utils.XlsxReader;
import cian.Base;
import cian.pages.MainPage;
import cian.pages.ResultPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class SimpleTest extends Base {

    private WebDriver driver;

    @DataProvider(name = "data")
    public Object[] dataParams() {
        List<Map> mapList = new XlsxReader("example.xlsx").getMapList();
        Object[] objects = new Object[mapList.size()];
        for (int i = 0; i < mapList.size(); i++) {
            objects[i] = mapList.get(i);
        }
        return objects;
    }

    @BeforeMethod
    public void getPage() {
        driver = getDriver();
        driver.get(URL);
    }

    @AfterMethod
    public void after() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test(dataProvider = "data")
    public void searchTest(Map<String, String> map) {
        MainPage mainPage = new MainPage(driver);
        ResultPage resultPage = mainPage.setAction(map.get("action"))
                .setType(map.get("type"))
                .setCategory(map.get("category"))
                .quarterDeselectAll()
                .setQuarter(map.get("quarter"))
                .setLocation(map.get("location"))
                .setPriceLimit(map.get("priceLimit"))
                .submitSearch();
        assertEquals(resultPage.getTotalSortedOffers(), 1);
    }
}