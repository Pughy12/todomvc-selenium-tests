package com.pug.todomvc.retry;

import org.testng.IAnnotationTransformer3;
import org.testng.annotations.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Used for transforming TestNG annotations (so you don't have to remember to set each one manually)
 *
 * Set automatically in this class:
 *   - retryAnalyzer = Retry.class
 */
public class AnnotationTransformer implements IAnnotationTransformer3 {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(Retry.class);
    }

    @Override
    public void transform(IListenersAnnotation annotation, Class testClass) {

    }

    @Override
    public void transform(IConfigurationAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

    }

    @Override
    public void transform(IDataProviderAnnotation annotation, Method method) {

    }

    @Override
    public void transform(IFactoryAnnotation annotation, Method method) {

    }
}
