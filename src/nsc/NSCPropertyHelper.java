package nsc;

import java.util.*;
import java.io.*;

public class NSCPropertyHelper {
    private final static String PROPERTY_FILEPATH = "src/nsc/application.properties";
    private final static int ERROR_EXIT_CODE = 1;

    static String getProperty(String property) {
        try {
            FileReader reader = new FileReader(PROPERTY_FILEPATH);
            Properties propertiesObj = new Properties();
            propertiesObj.load(reader);
            return propertiesObj.getProperty(property);
        } catch (Exception ex) {
            System.err.println("FATAL ERROR: " + ex.getMessage());
            System.exit(ERROR_EXIT_CODE);
        }

        return null;
    }
}