package cian;

import Utils.ApplicationProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Base {

    private ApplicationProperties properties = new ApplicationProperties();
    protected final String URL = properties.get("url");
    private WebDriver webDriver;

    protected WebDriver getDriver() {
        if (webDriver != null) {
            return webDriver;
        }
        switch (properties.get("browser")) {
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", properties.get("firefoxdriver"));
                webDriver = new FirefoxDriver();
                break;
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", properties.get("chromedriver"));
                webDriver = new ChromeDriver();
                break;
            default:
                throw new IllegalArgumentException(properties.get("browser"));
        }
        webDriver.manage().timeouts().implicitlyWait(Long.parseLong(properties.get("timeout")), TimeUnit.SECONDS);
        return webDriver;
    }
}
