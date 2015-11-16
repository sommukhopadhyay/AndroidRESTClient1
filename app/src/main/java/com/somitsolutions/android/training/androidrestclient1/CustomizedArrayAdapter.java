package com.somitsolutions.android.training.androidrestclient1;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomizedArrayAdapter extends ArrayAdapter<DataModel> {
	
	private Context mContext;
	List<DataModel> mDataList;
	
	public CustomizedArrayAdapter(Context context, int resource,
			List<DataModel> objects) {
		super(context, R.layout.display, objects);
		// TODO Auto-generated constructor stub
		mContext = context;
		mDataList = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		 if (convertView == null) {
		        // This a new view we inflate the new layout
		        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		        convertView = inflater.inflate(R.layout.display, parent, false);
		    }
		 
		 TextView id = (TextView)convertView.findViewById(R.id.textViewId);
		 TextView title = (TextView)convertView.findViewById(R.id.textViewTitle);
		 
		 DataModel temp = mDataList.get(position);
		
		id.setText(Integer.toString(temp.getId()));
		title.setText(temp.getTitle());
		
		return convertView;
	}
}
