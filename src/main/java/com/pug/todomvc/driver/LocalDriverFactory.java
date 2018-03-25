package com.pug.todomvc.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Local WebDriver creation for IE Firefox and Chrome.
 *
 * Wouldn't be used if we're using a Grid because all you need for that is a
 * RemoteWebDriver instance and capabilities passed into it.
 */
class LocalDriverFactory {

    private static final String FIREFOX = "firefox";
    private static final String INTERNET_EXPLORER = "ie";
    private static final String CHROME = "chrome";

    static WebDriver createInstance(String browserName) {

        switch (browserName) {
            case FIREFOX:
                return buildFirefox();
            case INTERNET_EXPLORER:
                return buildInternetExplorer();
            case CHROME:
            default: // Chrome can be the default since it's the best browser
                return buildChrome();
        }
    }

    /**
     * Build a local ChromeDriver instance with particular configurations
     * @return
     */
    private static WebDriver buildChrome() {
        System.setProperty("webdriver.chrome.driver", "src//main//resources//drivers//chromedriver");

        final ChromeOptions options = new ChromeOptions();

        // Silence ChromeDriver
        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
        builder.withSilent(true);
        ChromeDriverService service = builder.build();

        // Headless
        options.addArguments("--headless");

        // Starts Chrome and returns configured WebDriver
        return new ChromeDriver(service);
    }

    /**
     * Build a local InternetExplorerDriver instance with particular configurations
     * @return
     */
    private static WebDriver buildInternetExplorer() {
        System.setProperty("webdriver.ie.driver", "");
        return new InternetExplorerDriver();
    }

    /**
     * Builds a local FirefoxDriver instance with particular configurations
     * @return
     */
    private static WebDriver buildFirefox() {
        System.setProperty("webdriver.gecko.driver", "src//main//resources//drivers//geckodriver");
        return new FirefoxDriver();
    }
}
