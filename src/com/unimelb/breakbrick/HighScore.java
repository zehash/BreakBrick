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
	 
	        
	        Button btnClose = (Button) findViewById(R.id.btnClose);
	 
	        
    //	    Intent i = getIntent();

    	    TextView txtName1 = (TextView) findViewById(R.id.tV5);
	        String name1 = "Egemen";
	  	    txtName1.setText(name1);
	  	    
	  	    TextView txtScore1 = (TextView) findViewById(R.id.tV6);
	        String score1 = "1000";
	        txtScore1.setText(score1);
	        
	        TextView txtName2 = (TextView) findViewById(R.id.tV8);
	        String name2 = "Lars";
	  	    txtName2.setText(name2);
	  	    
	  	    TextView txtScore2 = (TextView) findViewById(R.id.tV9);
	        String score2 = "999";
	        txtScore2.setText(score2);
	        
	        TextView txtName3 = (TextView) findViewById(R.id.tV11);
	        String name3 = "Antony";
	  	    txtName3.setText(name3);
	  	    
	  	    TextView txtScore3 = (TextView) findViewById(R.id.tV12);
	        String score3 = "888";
	        txtScore3.setText(score3);
	 
       btnClose.setOnClickListener(new View.OnClickListener() {
	 
	            public void onClick(View arg0) {
	            	Intent myIntent = new Intent(getApplicationContext(), StartPage.class);
	                startActivity(myIntent);
	                finish();
	            }
	        });
	 
	    }
	}

