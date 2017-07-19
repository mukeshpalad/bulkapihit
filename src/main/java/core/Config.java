package core;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Config {
	
	public static String MarketplaceUrl="";
	public static String AuthToken="";
	public static String ResourceURI="";
	public static int BatchSize;
	public static int WaitInSeconds;
	
	/**
	 * Method to load all variables in property file
	 */
	public static void loadPropertiesfile()
	{	
		try {
			File propertyfile = new File(System.getProperty("user.dir")+"//Resources//config.properties");
			FileReader filereader = new FileReader(propertyfile);
			
			Properties prop = new Properties();
			prop.load(filereader);
			
			MarketplaceUrl = prop.getProperty("MarketplaceUrl");
			AuthToken = prop.getProperty("AuthToken");
			ResourceURI = prop.getProperty("ResourceURI");
			BatchSize = Integer.parseInt(prop.getProperty("BatchSize"));
			WaitInSeconds = Integer.parseInt(prop.getProperty("WaitInSeconds"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	



}
