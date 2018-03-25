package com.pug.todomvc.page_objects;

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
class PageObject {

    private WebDriver driver;

    PageObject(WebDriver driver) {
        if (driver == null) {
            throw new IllegalStateException("WebDriver shouldn't be null but it is");
        }

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver driver() {
        return this.driver;
    }


}
