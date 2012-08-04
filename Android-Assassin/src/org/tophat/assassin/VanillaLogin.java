package org.tophat.assassin;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.tophat.android.exceptions.HttpException;
import org.tophat.android.exceptions.NotFound;
import org.tophat.android.exceptions.Unauthorised;
import org.tophat.android.model.ApiTokenMapper;
import org.tophat.android.networking.ApiCommunicator;

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
	
	public static ApiCommunicator apic;
	
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
	     protected String doInBackground(String... details) 
	     {
			ApiTokenMapper atm = new ApiTokenMapper(AssassinActivity.apic);
			 
			try 
			{
				AssassinActivity.apic.setApitoken(atm.getApiToken(details[0], details[1]));
			} 
			catch (NotFound e)
			{
				return "The given user was not found.";
			} 
			catch (Unauthorised e)
			{
				return "The login details given were incorrect.";
			} 
			catch (HttpException e) 
			{
				e.printStackTrace();
				return "Error. Please check logs.";
			}
			 
			return null;
	     }

	     protected void onPostExecute(String error)
	     {
	    	 if (error == null)
	    	 {
				Intent menu = new Intent(vanilla, GameMenu.class);
				startActivityForResult(menu, Constants.MENU_ACTIVITY);
	    	 }
	    	 else
	    	 {
	    		AssassinActivity.showNotification(error);
	    	 }
	     }
	 }
}
