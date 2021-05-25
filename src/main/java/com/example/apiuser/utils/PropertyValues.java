package com.example.apiuser.utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Get property or send an exception
 */
public class PropertyValues {

        InputStream inputStream;

        public Properties getPropValues(String propFileName) throws IOException {
            Properties prop = new Properties();

            try {

                inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }

            } catch (Exception e) {//TODO Search for a better exception
                throw e;
            } finally {
                inputStream.close();
            }
            return prop;
        }

}
