package com.yme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

	private AlertDialog alertDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// this should be called before super.onCreate
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this will make the app fullscreen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		overridePendingTransition(R.anim.transition_right_in, R.anim.transition_left_out);
	}
	
	@Override
	protected void onStop() {
		if (alertDialog != null)
			alertDialog.dismiss();

		super.onStop();
	}
	
	public void msbox(String str,String str2)
	{		
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);                      
	    dlgAlert.setMessage(str2);
	    dlgAlert.setTitle(str);              
	    dlgAlert.setPositiveButton("OK", null);
	    dlgAlert.setCancelable(true);

	    dlgAlert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int whichButton) {
	        	alertDialog = null;
	        }
	    });

	    alertDialog = dlgAlert.create();
	    alertDialog.show();
	}
	
	public void onOkButtonTap(View view)
	{
		msbox("Title", "Hello");
		
		TextView v = (TextView)findViewById(R.id.textView1);
		v.setText("Text changed");
	}
	
	public void onNewTabTap(View view)
	{
		Intent intent = new Intent(this, SecondActivity.class);
//		intent.putExtra("position", position);
		this.startActivity(intent);
	}
	
}

