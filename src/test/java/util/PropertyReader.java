package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyReader {
	public static FileInputStream oFis;
	public static String propertyValue = null;
	
	public static String readDataFromPropertyFile(String fileName, String propertyKey)
	{
		File oFile = new File("./data/"+fileName+".properties");
		try {
			oFis = new FileInputStream(oFile);
			Properties property = new Properties();
			property.load(oFis);
			propertyValue = property.getProperty(propertyKey);
			oFis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertyValue;
	}
}
