package org.tophat.assassin;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.tophat.assassin.networking.APICommunicator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class VanillaLogin extends Activity 
{
	
	public static APICommunicator apic;
	
	public static Context c;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vanillalogin);
    }
    
	@Override
	public void onStart()
	{
		super.onStart();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
	}
	
	public void loginButtonHandler( View v )
	{	
		new CommandProcessTask().execute();
	}
	
	 private class CommandProcessTask extends AsyncTask<String, Integer, String> 
	 {
	     protected String doInBackground(String... urls) 
	     {
	    	 String response = AssassinActivity.apic.jsontest();
	    	 
	    	 if ( response == null )
	    	 {
	    		 return AssassinActivity.apic.getApiError();
	    	 }
	    	 
	    	 return "Successfully communicated with Server";
	     }

	     protected void onPostExecute(String result)
	     {
				AssassinActivity.showNotification(result);
	     }
	 }
}
