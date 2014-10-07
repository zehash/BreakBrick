package com.unimelb.breakbrick;


	import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
	 
	public class HighScore extends Activity {
	
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.highscore);
	 
	//        TextView txtName = (TextView) findViewById(R.id.txtName);
	        Button btnClose = (Button) findViewById(R.id.btnClose);
	 
//	        Intent i = getIntent();

	 //       String name = i.getStringExtra("name");
	  	 
	//        txtName.setText(name);
	 
       btnClose.setOnClickListener(new View.OnClickListener() {
	 
	            public void onClick(View arg0) {
	            	Intent myIntent = new Intent(getApplicationContext(), StartPage.class);
	                startActivity(myIntent);
	                finish();
	            }
	        });
	 
	    }
	}

