package com.pug.todomvc.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * A base page object which should be extended to get more functionality for page object classes.
 *
 * This class sets up the PageFactory which allows us to do the following in our PageObjects:
 *
 * @FindBy(css = "button#submit")
 * private WebElement submitButton;
 *
 * You can then use the submitButton without worrying about a NPE, the PageFactory will initialise the elements
 * once the page object class is instantiated.
 */
public class PageObject {

    private WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver driver() {
        return this.driver;
    }


}
