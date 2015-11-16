package com.somitsolutions.android.training.androidrestclient1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends Activity implements View.OnClickListener{
	Button mButtonMakeARestCall;
	Button mButtonMakeAnotherRestCall;
	ProgressDialog mProgressDialog;
	Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		mButtonMakeARestCall = (Button)findViewById(R.id.button1);
		mButtonMakeAnotherRestCall = (Button)findViewById(R.id.button2);
		
		mButtonMakeARestCall.setOnClickListener(this);
		mButtonMakeAnotherRestCall.setOnClickListener(this);
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setTitle("Wait....");
		mProgressDialog.setMessage("Downloading in progress....");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.equals(mButtonMakeARestCall)){
			HashMap<Object, Object> data = new HashMap<Object, Object>();
			data.put("userId", 1);
			HTTPAsyncTask httpAsyncTaskMakeARESTCall = new HTTPAsyncTask(mContext,new CallBack(){

				@Override
				public void onProgress() {
				// TODO Auto-generated method stub
				mProgressDialog.show();
				}
				 
				@Override
				public void onResult(String result) {
				JSONObject jsonObject;
				// TODO Auto-generated method stub
				mProgressDialog.dismiss();
				try {
					Log.i("Result", result);
					JSONArray jsonPostsArray = new JSONArray(result);
					//Log.i("This is result...",jsonPostsArray.toString());
					/*for (int i = 0; i <5; i++){
						JSONObject jsonPost = jsonPostsArray.getJSONObject(i);
						Toast.makeText(getApplicationContext(), jsonPost.getInt("id") + " - " + jsonPost.getString("title"), Toast.LENGTH_SHORT).show();
					}*/
					
					Intent intentDisplay = new Intent(getApplicationContext(), DisplayActivity.class);
					intentDisplay.putExtra("jsonArray", jsonPostsArray.toString());
					startActivity(intentDisplay);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}
				 
				@Override
				public void onCancel() {
				// TODO Auto-generated method stub
					
				}
			},data , null, "GET");
			httpAsyncTaskMakeARESTCall.execute("http://jsonplaceholder.typicode.com/posts");

		}
		if(v.equals(mButtonMakeAnotherRestCall)){
			
		}
	}
}
