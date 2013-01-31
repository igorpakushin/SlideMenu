package com.yme;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SecondActivity extends Activity {

	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		listView = (ListView) findViewById(R.id.mylist);
		String[] values = new String[] { 
				"Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
				"Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
				"Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
				"Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
				"Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
				"Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
				"Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
				"Lala", "Android", "iPhone", "Lala", "Android", "iPhone",
		};
		
		MySecondViewArrayAdapter myadapter = new MySecondViewArrayAdapter(this, values);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item, android.R.id.text1, values);
		
		listView.setAdapter(myadapter);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.transition_left_in, R.anim.transition_right_out);
	}

}
