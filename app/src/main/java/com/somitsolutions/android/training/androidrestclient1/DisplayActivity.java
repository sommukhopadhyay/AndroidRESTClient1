package com.somitsolutions.android.training.androidrestclient1;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DisplayActivity extends ListActivity {
	ArrayList<DataModel> mDisplayArray = new ArrayList<DataModel>();
	ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mListView = getListView();
		
		String displayData = getIntent().getStringExtra("jsonArray");
		
		try {
			JSONArray jsonDisplayData = new JSONArray(displayData);
			
			for (int i = 0; i <jsonDisplayData.length(); i++){
				JSONObject jsonData = jsonDisplayData.getJSONObject(i);
				DataModel dataModelTemp = new DataModel();
				dataModelTemp.setId(jsonData.getInt("id"));
				dataModelTemp.setTitle(jsonData.getString("title"));
				Log.i("Test",dataModelTemp.getTitle());
				mDisplayArray.add(dataModelTemp);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final CustomizedArrayAdapter customArrayAdapter = new CustomizedArrayAdapter(getApplicationContext(), R.layout.display,mDisplayArray);
		setListAdapter(customArrayAdapter);
	}

}
