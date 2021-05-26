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

        public static Properties getPropValues(String propFileName) throws IOException {
            Properties prop = new Properties();

            try(InputStream inputStream = PropertyValues.class
                    .getClassLoader().getResourceAsStream(propFileName)) {

                prop.load(inputStream);

                inputStream.close();

            } catch (Exception e) {//TODO Search for a better exception
                throw e;
            }
            return prop;
        }

}
