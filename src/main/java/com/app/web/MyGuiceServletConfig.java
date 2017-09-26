package com.app.web;


import com.google.inject.*;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

/**
 * Created by Yuvaraj on 12/09/2017.
 */
public class MyGuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        System.setProperty("guice.injector-source", "com.app.configuration.GuiceModule");
        Injector injector = Guice.createInjector(new ServeletModule(), new OtherModule());
        return injector;
    }
}
