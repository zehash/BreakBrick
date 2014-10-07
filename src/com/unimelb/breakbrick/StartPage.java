package com.unimelb.breakbrick;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class StartPage extends Activity{
	
	final Context context = this;
	final CharSequence[] items={"Easy","Medium","Hard"};
	int imputSelection = 0;
	AlertDialog levelDialog;
	private EditText result;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
            	
			    AlertDialog.Builder builder = new AlertDialog.Builder(context);
		        builder.setTitle("Select The Difficulty Level");

		        builder.setSingleChoiceItems(items,-1,
		                new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int item) {

		                        switch (item) {
		                        case 0:
		                            imputSelection=0;
		                            startActivityForResult(myIntent, 0);
		                            break;
		                        case 1:
		                            imputSelection=1;
		                            startActivityForResult(myIntent, 0);
		                            break;
		                        case 2:
		                            imputSelection=2;
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
        
        
        
        Button resumeGame = (Button) findViewById(R.id.button2);
        resumeGame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        	
       
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

			@Override
			public void onClick(View v) {
				
				
				
			}
        	
        });
        
        
        Button resumeGame = (Button) findViewById(R.id.button2);
        resumeGame.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				// get prompts.xml view
				LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.prompts, null);
 
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
 
				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);
 
				final EditText userInput = (EditText) promptsView
						.findViewById(R.id.editTextDialogUserInput);
 
				// set dialog message
				alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("OK",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
						// get user input and set it to result
						// edit text
						result.setText(userInput.getText());
					    }
					  })
					.setNegativeButton("Cancel",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					    }
					  });
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
 
			}
		});
        
        
        Button highScore = (Button) findViewById(R.id.button3);
        highScore.setOnClickListener(new View.OnClickListener() {
        	 public void onClick(View view) {
                 Intent myIntent = new Intent(getApplicationContext(), HighScore.class);
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
