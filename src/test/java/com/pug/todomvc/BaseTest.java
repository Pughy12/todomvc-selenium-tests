package com.pug.todomvc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * A base class which test classes can extend to get things needed to run tests
 *
 * I'd definitely change the way this class works with more time. For example, having the system properties
 * initialised here is not great, but I didn't have much time to implement something better.
 * Also adding more functionality like being able to override a browser or start url per test would be nice.
 *
 * I tried using Firefox with geckodriver but I ran into some issues. Most notably was with Selenium's Actions class.
 * Calling actions.doubleClick(element).perform() threw an error and apparently it's because geckodriver doesn't support
 * the Actions API yet (see here: https://github.com/mozilla/geckodriver/issues/159).
 */
public class BaseTest {

    /** The driver instance which will be used to pass to PageObjects to carry out actions in tests */
    private WebDriver driver;

    private static final String DRIVER_FOLDER = "src/main/resources/drivers/";
    private static final String CHROMEDRIVER_PROPERTY_NAME = "webdriver.chrome.driver";

    // Initialise system properties for driver locations
    static {
        System.setProperty(CHROMEDRIVER_PROPERTY_NAME, DRIVER_FOLDER + "chromedriver");
    }

    /**
     * Set up method, run before each test method. Starts the browser and navigates to the starting url.
     */
    @BeforeMethod
    public void setUp() {
        this.driver = new ChromeDriver();
        this.driver.get("http://todomvc.com/examples/angularjs/#/");
    }

    /**
     * Tear down method, runs after each test method. Closes the browser
     */
    @AfterMethod
    public void tearDown() {
        this.driver.close();
        this.driver.quit();
        this.driver = null;
    }

    /**
     * Get the driver to be used in tests to pass through to page objects
     *
     * @return The instance of the driver
     */
    WebDriver driver() {
        return driver;
    }
}
