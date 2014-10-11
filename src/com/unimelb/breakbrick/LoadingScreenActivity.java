package com.unimelb.breakbrick;

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
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
     
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