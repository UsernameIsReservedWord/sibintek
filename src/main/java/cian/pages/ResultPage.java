package cian.pages;

import cian.elements.ResultForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import static Utils.ElementHelper.waitElement;

public class ResultPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//div[contains(@class, \"card\")][1]")
    private ResultForm resultForm;

    public ResultPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new HtmlElementDecorator(this.webDriver), this);
    }

    public int getTotalSortedOffers() {
        waitElement(webDriver, resultForm);
        String totalOffers = resultForm.getTotalOffers().getText();
        return Integer.valueOf(totalOffers.substring(0, totalOffers.indexOf(" ")));
    }
}
