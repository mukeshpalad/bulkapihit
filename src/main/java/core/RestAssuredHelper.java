package core;

/**
 * Created by mukeshpal on 08/06/17.
 */

import java.util.HashMap;

import org.apache.log4j.lf5.LogLevel;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;


	public class RestAssuredHelper {


		public static HashMap<String, String> executeRest(String baseUrl, String method, String resource, 
				String body, HashMap<String, String> headers){

			HashMap<String, String> hm = new HashMap<>();

			String URL="";
			Response response=null;
			URL = baseUrl + resource;

			RestAssured.useRelaxedHTTPSValidation();
			//System.out.println("URL="+URL);

			Log.Message("Connecting to " + URL, LogLevel.INFO);
			Log.Message("Method  " + method, LogLevel.INFO);
			if (method=="POST"){
				response = RestAssured.given().headers(headers).body(body).post(URL);
			}else if(method=="PUT"){
				response = RestAssured.given().headers(headers).body(body).put(URL);
			}else if(method=="DELETE"){
				response = RestAssured.given().headers(headers).body(body).delete(URL);
			}else if(method=="GET"){
				response = RestAssured.given().headers(headers).body(body).get(URL);
			}else if(method=="PATCH"){
				response = RestAssured.given().headers(headers).body(body).patch(URL);
			}else{
				Log.Message("Method not supported" + method, LogLevel.ERROR);
				//System.exit(1);
			}

			hm.put("StatusCode", response.statusCode()+"");
			hm.put("StatusLine", response.getStatusLine()+"");
			hm.put("ResponseBody", response.getBody().asString());
			hm.put("ResponseHeaders", response.getHeaders().toString());

			if (response.statusCode()==200 || response.statusCode()==201)
				Log.Message("Successfully got the respone....." + response.getBody().asString(), LogLevel.INFO);
			else
				Log.Message("Error in getting the respone....." + response.getBody().asString(), LogLevel.ERROR);

			return hm;
		}


}
