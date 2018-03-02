/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api;

/**
 *
 * @author SAJAN
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class AppConfiguration {

    private Properties appProp;
    private String filename = "config.properties";
    private InputStream input = null;

    public static final String CONF_APP_KEY = "app_key";
    public static final String CONF_APP_SECRET = "app_secret";
    public static final String CONF_APP_AUTH = "auth_file";

    public AppConfiguration() {
        appProp = new Properties();
    }

    /**
     *
     * @param filename : give the application configuration name
     * @throws IOException
     */
    public AppConfiguration(String filename) throws IOException {
        appProp = new Properties();
        this.filename = filename;
    }

    /**
     * Loads the <b>Application configuration</b> during StartUp
     *
     * @throws IOException
     */
    public boolean loadConfiguration() {
        try {
            input = new FileInputStream(this.filename);
            //input = getClass().getClassLoader().getResourceAsStream(this.filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + this.filename);
                return false;
            }
            //load a properties file from class path, inside static method
            appProp.load(input);
            displayAllProp();
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void createConf() {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put(CONF_APP_KEY, "check");
        properties.put(CONF_APP_SECRET, "check");
        //properties.put(CONF_APP_AUTH, "test.auth");
        storeProperties(properties);
        displayAllProp();
    }

    public void storeProperties(Map properties) {
        OutputStream output = null;

        try {
            output = new FileOutputStream(this.filename);
            Set<String> keys = properties.keySet();
            for(String key: keys){
                // set the properties value                
                String value = properties.get(key).toString();
                appProp.setProperty(key, value);
            }
            // save properties to project root folder
            appProp.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    
    public Enumeration<?> getAllProperties(){
        //get the property value and print it out
        Enumeration<?> appProps = appProp.propertyNames();
        return appProps;
    }
    
    public String getSpecificProp(String key){
        return appProp.getProperty(key, null);       
    }

    public void displayAllProp() {
        //get the property value and print it out
        Enumeration<?> appProps = appProp.propertyNames();
        while (appProps.hasMoreElements()) {
            String key = (String) appProps.nextElement();
            String value = appProp.getProperty(key);
            System.out.println("Key : " + key + ", Value : " + value);
        }
    }
}

