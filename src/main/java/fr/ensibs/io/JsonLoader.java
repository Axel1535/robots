package fr.ensibs.io;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loader used to load JSON object 
 */
public class JsonLoader 
{
    /**
     * Load a JSON obejct from an input stream that contains JSON declarations
     * 
     * @param in the input stream 
     * @return the loaded JSON object
     * @throws IOException if an error occurs while accessing the input stream
     */
    public JSONObject load(InputStream in)
    {
        JSONTokener tokener = new JSONTokener(in);
        return new JSONObject(tokener);
    }
}
