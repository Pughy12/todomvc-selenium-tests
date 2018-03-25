package com.pug.todomvc.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retries failed tests up to a max number then fails and moves on.
 *
 * This is used either by setting retryAnalyzer on Test annotations or by setting a listener for AnnotationTransformer
 * and then it'll be on all tests by default
 *
 * This class must be kept public (even if IntelliJ says it can be package local) otherwise TestNG won't be able to
 * find it.
 */
public class Retry implements IRetryAnalyzer {

    private int counter;
    private static final int MAX_RETRIES = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (counter < MAX_RETRIES) {
            System.out.println("Test failed, retrying...");
            counter++;
            return true;
        }

        System.out.println("Retried max times, failing and moving on...");
        return false;
    }
}
