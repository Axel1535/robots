package fr.ensibs.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loader used to load properties 
 */
public class PropertiesLoader 
{
    /**
     * Load properties from an input stream that contains properties in the form "key=value"
     * 
     * @param in the input stream 
     * @return the loaded properties
     * @throws IOException if an error occurs while accessing the input stream
     */
    public Properties load(InputStream in) throws IOException
    {
        Properties props = new Properties();
        props.load(in);
        return props;
    }
}
