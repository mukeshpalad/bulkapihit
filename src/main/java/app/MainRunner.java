package app;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.lf5.LogLevel;
import org.json.JSONException;

import core.Config;
import core.Log;


public class MainRunner {
	
	public static void main(String[] args) throws InterruptedException, IOException
	{	
		//BasicConfigurator.configure();
		Config.loadPropertiesfile();
		String inputFilePath= "//Resources//inputUUIDs.json";
		
		try {
			OderMgmtRecalculatePriceRunner.runOrderManagmentPriceCalculateAPI(inputFilePath);
		} catch (JSONException e) {
			
			e.printStackTrace();
		}		
	}

}
