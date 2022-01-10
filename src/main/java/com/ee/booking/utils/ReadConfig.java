package com.ee.booking.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    Properties pro;

    public ReadConfig() {
        File src = new File("src/main/resources/resources.properties");

        try {
            FileInputStream fileInputStream = new FileInputStream(src);
            pro = new Properties();
            pro.load(fileInputStream);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }

    public String getApplicationURL() {
        String url = pro.getProperty("baseURL");
        return url;
    }

    public String getUsername() {
        String username = pro.getProperty("username");
        return username;
    }

    public String getPassword() {
        String password = pro.getProperty("password");
        return password;
    }

    public String getBrowser() {
        String browser = pro.getProperty("browser");
        return browser;
    }

    public String getVersion() {
        String version = pro.getProperty("version");
        return version;
    }
}
