package cian.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class FilterForm extends HtmlElement {

    @FindBy(xpath = "(//button[contains(@class, \"components-container-oeSzBHpB\")])[1]")
    public Button action;

    @FindBy(xpath = "(//button[contains(@class, \"components-container-oeSzBHpB\")])[2]")
    public Button type;

    @FindBy(xpath = "(//button[contains(@class, \"components-container-oeSzBHpB\")])[3]")
    public Button category;

    @FindBy(xpath = "(//button[contains(@class, \"components-container-oeSzBHpB\")])[4]")
    public Button quarters;

    @FindBy(xpath = "//input[contains(@class, \"filters-field-price-text-input\")]")
    public TextInput price;

    @FindBy(id = "geo-suggest-input")
    public TextInput location;

    @FindBy(xpath = "//button[contains(@class, \"filters-field-button\")]")
    public Button button;

    @FindBy(xpath = "//div[contains(@class, \"geosuggest_widget-group-container\")]")
    public HtmlElement locationContainer;

    public CheckBox typeCheckbox(String place) {
        String xpath = "//span[text()=\"%s\"]/preceding-sibling::input";
        return new CheckBox(findElement(By.xpath(String.format(xpath, place))));
    }

    public CheckBox categoryCheckbox(String category) {
        String xpath = "//span[text()=\"%s\"]/ancestor::label";
        return new CheckBox(findElement(By.xpath(String.format(xpath, category))));
    }

    public CheckBox quarterCheckbox(String quarter) {
        String xpath = "//label[text()=\"%s\"]";
        return new CheckBox(findElement(By.xpath(String.format(xpath, quarter))));
    }

}
