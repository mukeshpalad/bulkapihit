package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.lf5.LogLevel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import core.Common;
import core.Config;
import core.Log;
import core.RestAssuredHelper;

public class OderMgmtRecalculatePriceRunner {
	
	public static ArrayList<String> getAllCEUuidInArrayList(String path) throws JSONException
	{
		String fileContent = Common.readFile(path);
		
		JSONObject object = null;
		JSONArray uuidJsonArray = null;
		
		try
		{
			object = new JSONObject(fileContent);
			uuidJsonArray = object.getJSONArray("data");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		ArrayList<String> uuidList=new ArrayList<String>();
		
		int i=0;
		while (i<uuidJsonArray.length())
		{
			JSONObject jsonObject = uuidJsonArray.getJSONObject(i);
			uuidList.add(jsonObject.get("uuid").toString());
			i++;
		}
		return uuidList;
		
	}
	
	
	
	public static void runOrderManagmentPriceCalculateAPI(String inputFilePath) 
			throws JSONException, InterruptedException, IOException
	{
		ArrayList<String> uuidList = getAllCEUuidInArrayList(inputFilePath);
		
		
		String outputFilePath = "//Output//OrderMgmtRecalculatePrice_"+Common.getCurrentTimeStamp()+".txt";
		
		File file = Common.createFile(outputFilePath);
		
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Content-type", "application/json");
		headers.put("accept", "application/json");
		headers.put("Authorization", "Bearer " + Config.AuthToken);
		
		HashMap<String, String> response = new HashMap<>();
		
		int i=1;
		String body= "[";
		while(!uuidList.isEmpty())
		{
			String uuid = uuidList.get(0);
			body = body+"\""+uuid+"\",";
			uuidList.remove(uuid);
			i++;
			
			if(i==Config.BatchSize)
			{
				if(!uuidList.isEmpty())
					{
					uuid = uuidList.get(0);
					body = body+"\""+uuid+"\"]";
					uuidList.remove(uuid);
					response= RestAssuredHelper.executeRest(Config.MarketplaceUrl, "PATCH", Config.ResourceURI, body, headers);
					Log.Message("API Response Code: "+response.get("StatusCode"), LogLevel.INFO);
					Log.Message("API Response Body: "+response.get("ResponseBody"), LogLevel.INFO);
		
					Common.writeOnFile(file, response.get("ResponseBody").toString());
					TimeUnit.SECONDS.sleep(Config.WaitInSeconds);
					i=1;
					body= "[";
					}
				
			} 
		}
		
		body = body.substring(0, body.length()-1)+"]";
		response= RestAssuredHelper.executeRest(Config.MarketplaceUrl, "PATCH", Config.ResourceURI, body, headers);
		Log.Message("API Response Code: "+response.get("StatusCode"), LogLevel.INFO);
		Log.Message("API Response Body: "+response.get("ResponseBody"), LogLevel.INFO);
	
		Common.writeOnFile(file, response.get("ResponseBody").toString());
		
	}
	
	
	
	

}
