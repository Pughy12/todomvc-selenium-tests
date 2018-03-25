package com.pug.todomvc.driver;

import org.openqa.selenium.WebDriver;

/**
 * Created by Mike on 24/03/2018.
 */
public class RemoteDriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }
}
