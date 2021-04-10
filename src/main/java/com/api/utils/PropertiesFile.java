package com.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropertiesFile {
	
	private static final Logger LOG = LogManager.getLogger(PropertiesFile.class);
	private static FileInputStream fis;
	private static Properties prop = null;

	public static String getProperty(String property) {		

		try {
			fis = new FileInputStream(new File("config.properties"));
			prop = new Properties();
			prop.load(fis);
		} catch(FileNotFoundException fnfe) {
			LOG.error("Properties File Not Found", fnfe);
		} catch(IOException ioe) {
			LOG.error("IO Exception while loading Properties File", ioe);
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				LOG.error("IO Exception while closing file input stream", e);
			}
		}
		return prop.getProperty(property).trim();
	}
}
