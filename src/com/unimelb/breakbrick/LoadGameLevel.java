package com.unimelb.breakbrick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoadGameLevel  extends Activity{
	private int lvl;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameload);
        //lvl = intent.getIntExtra("level", 2);
          lvl = getIntent().getExtras().getInt("level");
        System.out.println("hihihih " + lvl);
        
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
            	myIntent.putExtra("level", lvl);
            	startActivity(myIntent);
                finish();
            }
        });
	}

	
}
