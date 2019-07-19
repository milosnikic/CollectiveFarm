/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kicnilec
 */
public class Configuration {
    
    private static Configuration instance;
    private Properties properties;
    
    private Configuration() {
        properties = new Properties();
        String path = "../PoljoprivrednaZadrugaCommonLib/settings.properties";
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            try {
                properties.load(new FileInputStream(f));
            } catch (FileNotFoundException ex) {
//                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
//                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }
    
    public Properties getProperties() {
        return properties;
    }
    
    public String getProperty(String key) {
        return properties.getProperty(key, "n/a");
    }
    
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
    
    public void saveChanges() {
        try {
            properties.store(new FileOutputStream("../PoljoprivrednaZadrugaCommonLib/settings.properties"), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
