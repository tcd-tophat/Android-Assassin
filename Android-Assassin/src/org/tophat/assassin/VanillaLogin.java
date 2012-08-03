package org.tophat.assassin;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.tophat.android.networking.APICommunicator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class VanillaLogin extends Activity 
{
	
	public static APICommunicator apic;
	
	public static Context c;
	
	public VanillaLogin vanilla;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vanillalogin);
        
        vanilla = this;
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
		new CommandProcessTask().execute(new String[]{((EditText)findViewById(R.id.email)).getText().toString(),((EditText)findViewById(R.id.password)).getText().toString()});
	}
	
	 private class CommandProcessTask extends AsyncTask<String, Integer, String> 
	 {
	     protected String doInBackground(String... deets) 
	     {
	    	 boolean response = AssassinActivity.apic.login(deets[0], deets[1]);
	    	 
	    	 if ( response == false )
	    	 {
	    		 return AssassinActivity.apic.getApiError();
	    	 }
	    	 
	    	 return "success";
	     }

	     protected void onPostExecute(String result)
	     {
	    	 if (!result.equals("success"))
	    	 {
				AssassinActivity.showNotification(result);
	    	 }
	    	 else
	    	 {
				Intent menu = new Intent(vanilla, GameMenu.class);
				startActivityForResult(menu, Constants.MENU_ACTIVITY);
	    	 }
	     }
	 }
}
