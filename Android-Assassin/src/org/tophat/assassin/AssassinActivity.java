package org.tophat.assassin;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.tophat.assassin.networking.APICommunicator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AssassinActivity extends Activity 
{
	
	public static APICommunicator apic;
	
	public static Context c;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
     
        //parser = new HTTPParser();
        
        c = this.getApplicationContext();
        
        apic = new APICommunicator(this);
    }
    
	@Override
	public void onStart()
	{
		super.onStart();
		
		Intent signInIntent = new Intent(this, SignInActivity.class);
		startActivity(signInIntent);
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
	}
	
	/**
	 * Makes Toast twice as fast, without burning!
	 * @param data
	 */
	public static void showNotification(String data)
	{
		AssassinActivity.showNotification(data, Toast.LENGTH_SHORT);
	}

	/**
	 * Shows the notification for a custom defined period.
	 * @param data
	 * @param duration
	 */
	public static void showNotification(String data, int duration)
	{
		//Show the toast message in the terminal too.
		System.out.println("[AndroidAssassin Toast] "+data);
		
		if( AssassinActivity.c != null)
		{
			Toast toast;
			toast = Toast.makeText(AssassinActivity.c, data, duration);
			toast.show();
		}
	}
	
	
}
