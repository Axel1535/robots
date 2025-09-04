package fr.ensibs.hello;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.JSONObject;

import fr.ensibs.io.JsonLoader;
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
        // open the hello.json resource
        try (InputStream in = Main.class.getResourceAsStream("/hello.json")) {
            // load the JSON object
            JsonLoader loader = new JsonLoader();
            JSONObject obj = loader.load(in);
            // display the message
            System.out.println(obj.getString("message"));
        }
    }
}
