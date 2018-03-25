package com.pug.todomvc.driver;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * A class to replicate before and after methods, but a bit more configurable (can edit configuration methods too)
 */
public class RemoteWebDriverListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            final String browserName = method.getTestMethod().getXmlTest().getParameter("browserName");
            WebDriver driver = RemoteDriverFactory.createInstance(browserName);
            RemoteDriverManager.setWebDriver(driver);

            driver.get("http://todomvc.com/examples/angularjs/#/");
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = RemoteDriverManager.getDriver();
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
