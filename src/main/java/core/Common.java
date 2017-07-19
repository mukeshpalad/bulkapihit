package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.lf5.LogLevel;

/**
 * Created by mukeshpal on 08/06/17.
 */
public class Common {
	
	public static String readFile(String source) 
	{
		String content="";
		try
		{
			File file = new File(System.getProperty("user.dir")+source);
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String line;
			while ((line = br.readLine()) != null) 
			{
			  content = content+line;
			}
			br.close();
			fileReader.close();

		} catch (IOException e) {
			Log.Message( "Error while reading file: " + e.getMessage(), LogLevel.ERROR);
		}
		
		return content;
	}
	
	public static File createFile(String source) throws IOException 
	{
		File file  = new File(System.getProperty("user.dir")+source);
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.close();
		return file;
	}
	
	public static void writeOnFile(File file, String textToWrite)
	{
		try {
			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.append(textToWrite+"\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String getCurrentTimeStamp()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
