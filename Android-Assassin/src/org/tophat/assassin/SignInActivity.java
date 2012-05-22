package org.tophat.assassin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.android.*;
import com.facebook.android.Facebook.*;

/* Handle all signing in work */
public class SignInActivity extends Activity {
	
	private int authToken = 1;     //Facebook store their own auth token

	
	private static final String FACEBOOK_APP_ID = "385044381546657";
	
	Facebook facebook = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
		
        setContentView(R.layout.login);
    }
    
    @Override
    public void onStart()
    {
		super.onStart();
    }
    
    public void onResume()
    {
		super.onResume();
		
		if(isSignedIn()) //If the player already has an auth token
		{		
			Intent signInIntent = new Intent(this, AssassinMenu.class);
			startActivity(signInIntent);
		}
		//Show login options and wait for input
    }
    
    public boolean isSignedIn()
    {
		if(authToken != 0 || (facebook != null && facebook.isSessionValid()))
		{
			return true;
		}
		return false;
    }
    
    public boolean signIn()
    {
    	/*Sign in the player using the TopHat system and set authToken */
		authToken = 1;
		
		return true;
    }

	public void facebookButtonHandler(View v)
	{
		if(facebookLogin())
		{
			Intent signInIntent = new Intent(this, AssassinActivity.class);
			startActivity(signInIntent);
		}
		else{
			//Error
		}
	}

	/**
	 * The alternate to Facebook signin system.
	 * @param v
	 */
	public void vanillaLoginHandler(View v)
	{
		AssassinActivity.parser.sendString("jsontest");
	}

    public boolean facebookLogin()
    {
		if(facebook == null)
		{
			facebook = new Facebook(FACEBOOK_APP_ID);
		}
		
		/* Authorize facebook with SSO or showing a webview */
		facebook.authorize(this, new DialogListener() {
			@Override
			public void onComplete(Bundle values) {}
	   
			@Override
			public void onFacebookError(FacebookError error) {}
			
			@Override
			public void onError(DialogError e) {}
			
			@Override
			public void onCancel() {}
		});
		
		return facebook.isSessionValid();
	}
    
    public int getAuthToken(){
        return authToken;
    }

	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if(facebook != null)
		{
			facebook.authorizeCallback(requestCode, resultCode, data);
		}
	}
}
