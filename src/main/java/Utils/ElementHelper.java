package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementHelper {

    public static WebElement waitElement(WebDriver driver, String xpath) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public static WebElement waitElement(WebDriver driver, WebElement webElement) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(webElement));
    }
}
