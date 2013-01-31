package com.yme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MySecondViewArrayAdapter extends ArrayAdapter<String> {
	
	private final Context context;
	private final String[] values;

	public MySecondViewArrayAdapter(Context context, String[] values) {
		super(context, R.layout.rowlayout_second_activity, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.rowlayout_second_activity, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		
		// icon
		
		textView.setText(values[position]);
		

		return rowView;
	}

}
