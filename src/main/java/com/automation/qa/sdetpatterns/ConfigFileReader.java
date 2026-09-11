package com.automation.qa.sdetpatterns;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ============================================================================
 * INTERVIEW TOPIC: Properties File Configuration Reader for Test Frameworks
 * ============================================================================
 * Why it is asked in QA / SDET interviews:
 * 1. "How do you manage test environment variables (URL, browser, timeouts)?"
 * 2. "How do you read a properties file in Java using classpath resources?"
 * 3. Evaluates proper use of `java.util.Properties` and `try-with-resources`.
 */
public class ConfigFileReader {

    private final Properties properties = new Properties();

    public ConfigFileReader(String propertyFileName) {
        // Load properties from the classpath (src/main/resources)
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertyFileName)) {
            if (input == null) {
                throw new RuntimeException("Property file not found on classpath: " + propertyFileName);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load property file: " + propertyFileName, e);
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property key '" + key + "' not specified in configuration.");
        }
        return value.trim();
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue).trim();
    }

    public int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) return defaultValue;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) return defaultValue;
        return Boolean.parseBoolean(value.trim());
    }

    public static void main(String[] args) {
        ConfigFileReader config = new ConfigFileReader("config.properties");

        System.out.println("=== Framework Configuration Readout ===");
        System.out.println("Application URL:     " + config.getProperty("app.url"));
        System.out.println("Environment:         " + config.getProperty("app.environment"));
        System.out.println("Browser:             " + config.getProperty("browser"));
        System.out.println("Headless Mode:       " + config.getBooleanProperty("headless", false));
        System.out.println("Implicit Wait (s):   " + config.getIntProperty("implicit.wait.seconds", 5));
        System.out.println("Explicit Wait (s):   " + config.getIntProperty("explicit.wait.seconds", 15));
        System.out.println("Max Retry Attempts:  " + config.getIntProperty("retry.max.attempts", 2));
    }
}
