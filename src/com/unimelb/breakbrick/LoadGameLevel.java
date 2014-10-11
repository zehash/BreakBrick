package com.unimelb.breakbrick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoadGameLevel  extends Activity{
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameload);
        
        Button btnBack = (Button) findViewById(R.id.btnBack);
        
        
        btnBack.setOnClickListener(new View.OnClickListener() {
       	 
            public void onClick(View view) {
            	Intent myIntent = new Intent(getApplicationContext(), StartPage.class);
                startActivity(myIntent);
                finish();
            }
        });
        
        Button level1 = (Button) findViewById(R.id.level1);
        
        level1.setOnClickListener(new View.OnClickListener() {
          	 
            public void onClick(View view) {
            	Intent myIntent = new Intent(getApplicationContext(), LoadingScreenActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
	}

	
}
