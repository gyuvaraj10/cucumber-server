package com.app.web;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

/**
 * Created by Yuvaraj on 12/09/2017.
 */
public class ServeletModule extends ServletModule {

    @Override
    public void configureServlets() {
        bind(RunWithLoadedResourcesServlet.class).in(Singleton.class);
        serve("/loaded").with(RunWithLoadedResourcesServlet.class);
    }

}
