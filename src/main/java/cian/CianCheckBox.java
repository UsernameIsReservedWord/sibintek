package cian;

import org.openqa.selenium.WebElement;

public class CianCheckBox {

    private WebElement webElement;

    public CianCheckBox(WebElement webElement) {
        this.webElement = webElement;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public boolean isSelected() {
        return webElement.getAttribute("class")
                .contains("filters-radio-active");
    }

    public void select() {
        if (!isSelected()) {
            webElement.click();
        }
    }

    public String getText() {
        return webElement.getText();
    }
}
