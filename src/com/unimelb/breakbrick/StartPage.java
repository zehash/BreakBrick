package com.unimelb.breakbrick;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartPage extends Activity{
	
	final Context context = this;
	final CharSequence[] items={"Easy","Medium","Hard"};
	AlertDialog levelDialog;
	private EditText result;
	private String level, col;
	
//	public boolean isNetworkAvailable() {
//	    ConnectivityManager connectivityManager 
//	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//	}
//	
//	public void createDialog() {
//		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
//		alertDialog.setTitle("Alert Dialog");
//		alertDialog.setMessage("Please check your internet connectivity");
//		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int which) {
//				Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
//			}
//		});
//		alertDialog.show();
//	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		if (!isNetworkAvailable()) {
//			createDialog();
//		}
		setContentView(R.layout.startpage);
        
		
//		Button newGame = (Button) findViewById(R.id.button1);
//        newGame.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
//                startActivityForResult(myIntent, 0);
//               }
//        });
        
        
        Button newGame = (Button) findViewById(R.id.button1);
        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	final Intent myIntent = new Intent(view.getContext(), MainActivity.class);
            	
				myIntent.putExtra("level", 1);
				myIntent.putExtra("col", 1);
            	
			    AlertDialog.Builder builder = new AlertDialog.Builder(context);
		        builder.setTitle("Select The Difficulty Level");

		        builder.setSingleChoiceItems(items,-1,
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int item) {

		                        switch (item) {
		                        case 0:
		                            WorldView.mode=0;
		                            startActivityForResult(myIntent, 0);
		                            break;
		                        case 1:
		                        	WorldView.mode=1;
		                            startActivityForResult(myIntent, 0);
		                            break;
		                        case 2:
		                        	WorldView.mode=2;
		                            startActivityForResult(myIntent, 0);
		                            break;

		                        }
		                        levelDialog.dismiss();
		                    }
		                });
		        levelDialog = builder.create();
		        levelDialog.show();
		      
		     
		}
            
        }); 
        
        
        
//        Button resumeGame = (Button) findViewById(R.id.button2);
//        resumeGame.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				WorldView.state = "RUNNING";			
	
//				Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
//	            startActivity(myIntent);
//	            finish();

//			}
        	
//        });
        	
    
        Button highScore = (Button) findViewById(R.id.button3);
        highScore.setOnClickListener(new View.OnClickListener() {
        	 public void onClick(View view) {
                 Intent myIntent = new Intent(getApplicationContext(), HighScore.class);
                 startActivity(myIntent);
                 finish();
                }
        });
        
        
        Button loadLevel = (Button) findViewById(R.id.button4);
        loadLevel.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(getApplicationContext(), LoadGameLevel.class);
				myIntent.putExtra("level", 2);
				startActivity(myIntent);
                finish();
			}
        	
        });
      
        Button exit = (Button) findViewById(R.id.button5);
        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                System.exit(0);
            }

        });
        
 }
}
