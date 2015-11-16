package com.somitsolutions.android.training.androidrestclient1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import android.util.Log;

public class HttpUtility { 

	private final static HttpClient mHhttpclient = new DefaultHttpClient();

public static String GET(String url){
       InputStream inputStream = null;
       String result = "";
       try {

           // make GET request to the given URL
           HttpResponse httpResponse = mHhttpclient.execute(new HttpGet(url));

           // receive response as inputStream
           inputStream = httpResponse.getEntity().getContent();

           // convert inputstream to string
           if(inputStream != null){
               result = convertInputStreamToString(inputStream);
           }

           else
               result = "Did not work!";

       } catch (Exception e) {
           Log.d("InputStream", e.getLocalizedMessage());
       }

       return result;
   }



public static String POST(String url, HashMap<Object, Object> mParams){
	InputStream inputStream = null;
	String result = "";
	try{
		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity((List<? extends NameValuePair>) mParams, "UTF-8"));
		HttpResponse httpResponse = mHhttpclient.execute(post);
		// receive response as inputStream
		
		inputStream = httpResponse.getEntity().getContent();
		 
		// convert inputstream to string
		if(inputStream != null){
		result = convertInputStreamToString(inputStream);
		//inputStream.close();
		}
		else
		result = "Did not work!";
		} 
	catch (Exception e) {
		Log.d("InputStream", e.getLocalizedMessage());
		}
	
	return result;
 
}

public static String POST(String url, JSONObject obj){

       InputStream inputStream = null;
       String result = "";

       try{
           HttpPost post = new HttpPost(url);
           post.setHeader("Content-type", "application/json");

           StringEntity se = new StringEntity(obj.toString()); 
           se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
           post.setEntity(se);

           HttpResponse httpResponse = mHhttpclient.execute(post);

           // receive response as inputStream
           inputStream = httpResponse.getEntity().getContent();

           // convert inputstream to string
           if(inputStream != null){
               result = convertInputStreamToString(inputStream);
           }

           else
               result = "Did not work!";

       } catch (Exception e) {
           Log.d("InputStream", e.getLocalizedMessage());
       }
       Log.i("JSONPOSTEND", "End of JSON data post methos...");
       
       return result;
   }

   public static String convertInputStreamToString(InputStream inputStream) throws IOException{
       BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
       String line = "";
       String result = "";
       while((line = bufferedReader.readLine()) != null)
           result += line;

       inputStream.close();
       return result;

   }
}


