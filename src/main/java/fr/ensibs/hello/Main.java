package fr.ensibs.hello;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fr.ensibs.io.PropertiesLoader;

/**
 * Simple application intended to experiment VCS and build tools  
 * 
 * @author Pascale Launay
 */
public class Main 
{
    /**
     * The application entry point
     * 
     * @param args NONE
     * @throws IOException if an error occurs while accessing the program resources
     */
    public static void main(String[] args) throws IOException 
    {
        // open the hello.properties resource
        try (InputStream in = Main.class.getResourceAsStream("/hello.properties")) {
            // load the properties
            PropertiesLoader loader = new PropertiesLoader();
            Properties props = loader.load(in);
            // display the message
            System.out.println(props.getProperty("message"));
        }
    }
}
