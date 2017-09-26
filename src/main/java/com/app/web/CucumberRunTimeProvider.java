package com.app.web;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.servlet.RequestScoped;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import java.util.Arrays;

/**
 * Created by Yuvaraj on 14/09/2017.
 */
public class CucumberRunTimeProvider implements Provider<Runtime> {

    @Inject
    private CucumberArgsParser cucumberArgsParser;

    @Override
    public Runtime get() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        RuntimeOptions runtimeOptions = new RuntimeOptions(Arrays.asList(cucumberArgsParser.getParsedNoTagsArguments()));
        MultiLoader resourceLoader = new MultiLoader(classLoader);
        ResourceLoaderClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
        Runtime runtime = new Runtime(resourceLoader, classFinder, classLoader, runtimeOptions);
        return runtime;
    }
}
