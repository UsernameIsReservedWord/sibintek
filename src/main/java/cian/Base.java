package cian;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Base {

    private WebDriver webDriver;
    private WebDriverProperties webDriverProperties = new WebDriverProperties();
    protected final String URL = webDriverProperties.getTarget();

    protected WebDriver getDriver() {
        DesiredCapabilities capabilities;
        switch (webDriverProperties.getBrowser()) {
            case "Firefox":
                capabilities = DesiredCapabilities.firefox();
                break;
            case "Chrome":
                capabilities = DesiredCapabilities.chrome();
                break;
            default:
                throw new IllegalArgumentException(webDriverProperties.getBrowser());
        }
        webDriver = new RemoteWebDriver(webDriverProperties.getServer(), capabilities);
        return webDriver;
    }
}
