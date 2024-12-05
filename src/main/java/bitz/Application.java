package bitz;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Application {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String filename = "application.properties";
        Properties properties = new Properties();

        ClassLoader loader = Application.class.getClassLoader();
        InputStream stream = loader.getResourceAsStream(filename);
        try {
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties");
        }

        System.out.println("Foo: " + properties.getProperty("foo"));
    }
}
