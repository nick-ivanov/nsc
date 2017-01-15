package nnsignalcoder;

import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public class NSCPropertyHelper {
    final static String PROPERTY_FILEPATH = "src/nnsignalcoder/application.properties";

    static String getProperty(String property) {
        try {
            FileReader reader = new FileReader(PROPERTY_FILEPATH);
            Properties propertiesObj = new Properties();
            propertiesObj.load(reader);
            return propertiesObj.getProperty(property);
        } catch (Exception ex) {
            System.out.println("FATAL ERROR: " + ex.getMessage());
            System.exit(1);
        }

        return null;
    }

    static String getPropertyWithSpaces(String property, String delimiter) {
        try {
            FileReader reader = new FileReader(PROPERTY_FILEPATH);
            Properties propertiesObj = new Properties();
            propertiesObj.load(reader);
            return propertiesObj.getProperty(property).replaceAll(delimiter, "");
        } catch (Exception ex) {
            System.out.println("FATAL ERROR: " + ex.getMessage());
            System.exit(1);
        }

        return null;
    }

    static Set<Entry<Object,Object>> getPropertySet() {
        try {
            FileReader reader = new FileReader(PROPERTY_FILEPATH);
            Properties propertiesObj = new Properties();
            propertiesObj.load(reader);
            return propertiesObj.entrySet();
        } catch (Exception ex) {
            System.out.println("FATAL ERROR: " + ex.getMessage());
            System.exit(1);
        }

        return null;
    }
}