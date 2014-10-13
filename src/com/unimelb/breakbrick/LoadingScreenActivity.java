package com.unimelb.breakbrick;

import org.lightcouch.CouchDbClientAndroid;
import org.lightcouch.CouchDbProperties;

import com.google.gson.JsonObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingScreenActivity extends Activity implements Runnable
{

	private ProgressDialog progressDialog;
	private Thread thread;
	private Handler handler;
	private int lvl, col = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
     
        lvl = getIntent().getExtras().getInt("level");
    	System.out.println("<<<<<<<<<<<<<<< + " + lvl);        
		progressDialog = new ProgressDialog(LoadingScreenActivity.this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setTitle("Loading...");
		progressDialog.setMessage("Loading new level, please wait...");
		progressDialog.setCancelable(false);
		progressDialog.setIndeterminate(false);
		progressDialog.setMax(100);
		progressDialog.setProgress(0);
		progressDialog.show();
		
        handler = new Handler();
        thread = new Thread(this, "ProgressDialogThread");
        thread.start();
    }

	int counter = 0;
    
	@Override
	public void run() 
	{
		CouchDbProperties properties = new CouchDbProperties()
		  .setDbName("brickbreak")
		  .setCreateDbIfNotExist(true)
		  .setProtocol("http")
		  .setHost("115.146.92.221")
		  .setPort(5984)
		  .setMaxConnections(100)
		  .setConnectionTimeout(0);

		CouchDbClientAndroid dbClient4 = new CouchDbClientAndroid(properties);
		JsonObject json = dbClient4.find(JsonObject.class, "gamelevel");
		col = json.getAsJsonObject().get("coloumn").getAsInt();
		
		try 
		{
			synchronized (thread) 
			{
				
				while(counter <= 4)
				{
					thread.wait(850);
					counter++;
					
					handler.post(new Runnable() 
					{
						@Override
						public void run() 
						{
							progressDialog.setProgress(counter*25);
						}
					});
				}
			}
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		handler.post(new Runnable() 
		{
			@Override
			public void run() 
			{
				progressDialog.dismiss();
				Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
				myIntent.putExtra("level", lvl);
				myIntent.putExtra("col", col);
				startActivity(myIntent);
	            finish();
			}
		});
		
		synchronized (thread) 
		{
			thread.interrupt();
		}
	}
}