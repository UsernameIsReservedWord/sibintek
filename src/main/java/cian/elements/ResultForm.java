package cian.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

public class ResultForm extends HtmlElement {

    @FindBy(xpath = "//div[contains(@class, \"main-container\")]")
    private List<WebElement> resultList;

    public List<WebElement> getResultList() {
        return resultList;
    }
}
