package cian.pages;

import cian.CianCheckBox;
import cian.elements.FilterForm;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.util.List;

import static Utils.ElementHelper.waitElement;

public class MainPage {

    private WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new HtmlElementDecorator(webDriver), this);
    }

    @FindBy(xpath = "//div[contains(@class, \"filters-content\")]")
    private FilterForm filterForm;

    public MainPage setAction(String actionType) {
        String actionXpath = "(//div[contains(@class, \"filters-operations-radio\")])[%s]";
        this.filterForm.action.click();
        CianCheckBox element;
        switch (actionType) {
            case "Купить":
                element = new CianCheckBox(waitElement(webDriver, String.format(actionXpath, "1")));
                if (!element.isSelected()
                        && element.getText().equals(actionType)) {
                    element.select();
                }
                break;
            case "Снять":
                element = new CianCheckBox(waitElement(webDriver, String.format(actionXpath, "2")));
                if (!element.isSelected()
                        && element.getText().equals(actionType)) {
                    element.select();
                }
                break;
            case "Посуточно":
                element = new CianCheckBox(waitElement(webDriver, String.format(actionXpath, "3")));
                if (!element.isSelected() && element.getText().equals(actionType)) {
                    element.select();
                }
                break;
            default:
                throw new AssertionError("Чёт не то ввели");
        }
        return this;
    }

    public MainPage setType(String type) {
        filterForm.type.click();
        filterForm.typeCheckbox(type).select();
        return this;
    }

    public MainPage setPriceLimit(String price) {
        filterForm.price.clear();
        filterForm.price.sendKeys(price);
        return this;
    }

    public MainPage setLocation(String location) {
        filterForm.location.getWrappedElement().click();
        waitElement(webDriver, filterForm.locationContainer);
        filterForm.location.sendKeys(Keys.LEFT_CONTROL, "a", Keys.DELETE);
        filterForm.location.sendKeys(location);
        waitElement(webDriver, filterForm.locationContainer)
                .findElements(By.xpath("//div[contains(@class, \"geosuggest_widget-item\")][1]"))
                .get(0).click();
//        filterForm.findElements(By.xpath("//div[contains(@class, \"geosuggest_widget-item\")][1]")).get(0).click();
        return this;
    }

    public MainPage setCategory(String category) {
        filterForm.category.click();
        filterForm.categoryCheckbox(category).select();
        return this;
    }

    public ResultPage submitSearch() {
        filterForm.button.click();
        return new ResultPage(this.webDriver);
    }

    public MainPage setQuarter(String quarter) {
        filterForm.quarters.click();
        filterForm.quarterCheckbox(quarter).select();
        filterForm.quarters.click();
        return this;
    }

    public MainPage quarterDeselectAll() {
        if (!filterForm.quarters.getWrappedElement().getAttribute("class").contains("components-button--opened"))
            filterForm.quarters.click();
        List<WebElement> quarterBlock = waitElement(webDriver, filterForm.findElement(By.xpath(".//div[contains(@class, \"filters-operations-content-check\")]")))
                .findElements(By.xpath(".//span[contains(@class, \"components-container\")]"));
        quarterBlock.stream()
                .filter(x -> x.getAttribute("class").contains("components-checked"))
                .forEach(WebElement::click);
        filterForm.quarters.click();
        return this;
    }
}
