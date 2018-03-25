package com.pug.todomvc.driver;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * A class to replicate before and after methods, but a bit more configurable (can edit configuration methods too)
 */
public class LocalWebDriverListener implements IInvokedMethodListener {

    private static final String BASE_URL = "http://todomvc.com/examples/angularjs/#/";

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            final String browserName = method.getTestMethod().getXmlTest().getParameter("browserName");
            final WebDriver driver = LocalDriverFactory.createInstance(browserName);
            LocalDriverManager.setWebDriver(driver);

            driver.get(BASE_URL);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            final WebDriver driver = LocalDriverManager.getDriver();

            if (driver != null) {
                driver.quit();
            }
        }
    }

}