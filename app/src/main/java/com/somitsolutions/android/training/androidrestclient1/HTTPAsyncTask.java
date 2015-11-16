package com.somitsolutions.android.training.androidrestclient1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.content.Context;
import android.os.AsyncTask; 
import android.util.Log; 
public class HTTPAsyncTask extends AsyncTask<String, Void, String> { 
	private CallBack mCb; 
	HashMap<Object, Object> mData = null; 
	//List mParams= new ArrayList(); 
	HashMap<Object, Object> mParams = new HashMap<Object, Object>();
	String mTypeOfRequest; 
	String mStrToBeAppended = ""; 
	boolean isPostDataInJSONFormat = false; 
	JSONObject mJSONPostData = null;
	Context mContext = null;
	
	public HTTPAsyncTask(Context context, CallBack c, HashMap<Object, Object> data, JSONObject jsonObj, String request) { 
		mContext = context;
		mCb = c;
		mTypeOfRequest = request;
		mJSONPostData = jsonObj;
		//Log.i("JSONDATA", mJSONPostData.toString());
		if((data != null) && (jsonObj == null)){ 
		mData = data; 
		if(mTypeOfRequest.equalsIgnoreCase("GET")){
			Object key = null;
			Iterator<Object> it = mData.keySet().iterator();
			while(it.hasNext()){ 
				key = it.next(); 
				mParams.put(key, mData.get(key)); 
				} 
			for (int i = 0; i<mParams.size()-1; i++){ 
				mStrToBeAppended+= "?" + key + "=" + mParams.get(key) + "&";
			} //add the last parameter without the "&" 
			mStrToBeAppended+= "?" + key + "=" + mParams.get(key);
		} 
		if(mTypeOfRequest.equalsIgnoreCase("POST")){
			Object key = null;
			isPostDataInJSONFormat = false;
			Iterator<Object> it = mData.keySet().iterator();
			while(it.hasNext()){
				key = it.next();
				mParams.put(key, mData.get(key));
				}
			}
		}
		
		if ((mData == null) && (mJSONPostData != null) && (mTypeOfRequest.equalsIgnoreCase("POST") == true)){
			isPostDataInJSONFormat = true;
		//Log.i("ISJSONDATA",Boolean.toString(isPostDataInJSONFormat) );
		} 
	} 
	
	@Override 
	protected String doInBackground(String... baseUrls) {
	//android.os.Debug.waitForDebugger();
		publishProgress(null);
		if(mTypeOfRequest.equalsIgnoreCase("GET")){
		String finalURL = baseUrls[0]+ mStrToBeAppended;
		return HttpUtility.GET(finalURL); 
		} 
		
		if (mTypeOfRequest.equalsIgnoreCase("POST")){
			if(isPostDataInJSONFormat == false){
				return HttpUtility.POST(baseUrls[0],mParams );
			} 
			if(isPostDataInJSONFormat == true){
				Log.i("JSONDATAPOSTMETHOd","JSON POST method to be called...");
				return HttpUtility.POST(baseUrls[0], mJSONPostData);
			}
		}
		return null;
	} 
	
	// onPostExecute displays the results of the AsyncTask. 
	@Override 
	protected void onPostExecute(String result) {
		mCb.onResult(result);
	} 
	
	@Override 
	protected void onProgressUpdate(Void...voids ) {
	mCb.onProgress(); 
	}
}
