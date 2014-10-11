package com.unimelb.breakbrick;

import java.util.HashMap;
import java.util.Map;

import org.lightcouch.CouchDbClientAndroid;
import org.lightcouch.CouchDbProperties;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.Window;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class MainActivity extends Activity {
	private WorldView worldView;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
//		CouchDbProperties properties = new CouchDbProperties()
//		  .setDbName("brickbreak")
//		  .setCreateDbIfNotExist(true)
//		  .setProtocol("http")
//		  .setHost("115.146.92.221")
//		  .setPort(5984)
//		  .setMaxConnections(100)
//		  .setConnectionTimeout(0);
//
//		CouchDbClientAndroid dbClient3 = new CouchDbClientAndroid(properties);
//		JsonObject json = new JsonObject();
//		json.addProperty("_id", "gamelevel");
//		json.addProperty("row", "1");
//		json.addProperty("coloumn", "2");
//		dbClient3.save(json); 

		
	//	worldView = (WorldView) findViewById(R.id.worldview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("no", null).show();
    } 
}
