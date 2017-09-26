package com.app.web;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.servlet.RequestScoped;
import cucumber.runtime.Runtime;

/**
 * Created by Yuvaraj on 14/09/2017.
 */
public class OtherModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Runtime.class).toProvider(CucumberRunTimeProvider.class);
        bind(CucumberExecutionService.class);
        bind(CucumberArgsParser.class).in(Singleton.class);
    }
}
