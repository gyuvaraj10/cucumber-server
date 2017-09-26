package com.app.web;

/**
 * Created by Yuvaraj on 14/09/2017.
 */
public class CucumberArgsParser {

    public String[] getParsedArguments() {
        String[] args = new String[] {
                "--monochrome",
                "--glue","tests",
                "--tags", "@test",
                "../webapps/webapp/features"};
        return args;
    }


    public String[] getParsedNoTagsArguments() {
        String[] args = new String[] {
                "--monochrome",
                "--glue","tests",
                "--format", String.format("json:/tmp/%s.json",System.currentTimeMillis()),
                "../webapps/webapp/features"};
        return args;
    }
}
