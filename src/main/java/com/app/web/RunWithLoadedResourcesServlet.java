package com.app.web;

import com.google.inject.Inject;
import com.google.inject.Provider;
import cucumber.runtime.Runtime;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yuvaraj on 12/09/2017.
 */
public class RunWithLoadedResourcesServlet extends HttpServlet {

    @Inject
    private CucumberExecutionService executionService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().println(executionService.run());
        resp.getWriter().println(executionService.run1());
    }
}
