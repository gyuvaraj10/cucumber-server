package com.app.web;

import com.google.inject.Inject;
import com.google.inject.Provider;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yuvaraj on 14/09/2017.
 */
public class CucumberExecutionService {

    @Inject
    private Provider<Runtime> runtime;

    @Inject
    private CucumberArgsParser cucumberArgsParser;

    private Runtime lruntime;
    private Object freshStats;
    private Field statsField;


    public byte run() throws IOException {
        Runtime runtime1 =runtime.get();
        runtime1.run();
        return runtime1.exitStatus();
    }


    public String run1() {
        try {
            List<String> args = Arrays.asList(cucumberArgsParser.getParsedNoTagsArguments());
            RuntimeOptions runtimeOptions = new RuntimeOptions(args);
            Runtime runtime =runtime(runtimeOptions);
            runtime.run();
            if(runtime.exitStatus()==1) {
                String format = args.stream().filter(x->x.contains("json:")).findFirst().get();
                String path = format.split("json:")[1];
                return FileUtils.readFileToString(new File(path), Charset.defaultCharset());
            }
            return "1";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }


    private Runtime runtime(RuntimeOptions runtimeOptions) throws Exception {
        if(lruntime == null) {
            lruntime = getRuntime(runtimeOptions);
            statsField = lruntime.getClass().getDeclaredField("stats");
            statsField.setAccessible(true);
            Class<?> statsClass = Class.forName("cucumber.runtime.Stats");
            Constructor<?> statsConstructor = statsClass.getDeclaredConstructor(Boolean.TYPE);
            statsConstructor.setAccessible(true);
            freshStats = statsConstructor.newInstance(true);
        } else {
            //Removes the Error
            lruntime.getErrors().clear();
            //sets the Fresh Stats Object
            statsField.set(lruntime, ObjectUtils.cloneIfPossible(freshStats));
            //Creates a new Context Class Loader for Every Thread
            Field field = lruntime.getClass().getDeclaredField("classLoader");
            field.setAccessible(true);
            field.set(lruntime, getNewClassLoader());
        }
        return lruntime;
    }

    public Runtime getRuntime(RuntimeOptions runtimeOptions) throws Exception {
        ClassLoader classLoader = getNewClassLoader();
        MultiLoader resourceLoader = new MultiLoader(classLoader);
        ResourceLoaderClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
        Runtime runtime = new Runtime(resourceLoader, classFinder, classLoader, runtimeOptions);
        return runtime;
    }

    private ClassLoader getNewClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private RuntimeOptions getRunTimeOptions() {
        return new RuntimeOptions(Arrays.asList(cucumberArgsParser.getParsedNoTagsArguments()));
    }
}
