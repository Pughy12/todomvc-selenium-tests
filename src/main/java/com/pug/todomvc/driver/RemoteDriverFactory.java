package com.pug.todomvc.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mike on 25/03/2018.
 */
class RemoteDriverFactory {

    private static URL GRID_URL = null;

    static {
        try {
            GRID_URL = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    static WebDriver createInstance(String browserName) {

        switch (browserName) {
            case "chrome":
                return new RemoteWebDriver(GRID_URL, new ChromeOptions());
            case "firefox":
                return new RemoteWebDriver(GRID_URL, new FirefoxOptions());
            case "ie":
                return new RemoteWebDriver(GRID_URL, new InternetExplorerOptions());
            default:
                return new RemoteWebDriver(GRID_URL, new ChromeOptions());
        }
    }
}
