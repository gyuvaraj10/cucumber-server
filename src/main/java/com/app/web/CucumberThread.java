package com.app.web;

/**
 * Created by Yuvaraj on 14/09/2017.
 */
public class CucumberThread {

    private static Thread thread;

    public static Thread getCurrentThread() {
        if(thread == null) {
            thread = Thread.currentThread();
        }
        return thread;
    }

    public static ClassLoader getClassLoader() {
        return getCurrentThread().getContextClassLoader();
    }
}
