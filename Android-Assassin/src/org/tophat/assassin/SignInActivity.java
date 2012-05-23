package org.tophat.assassin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/*import com.facebook.android.*;
import com.facebook.android.Facebook.*;*/

/* Handle all signing in work */
public class SignInActivity extends Activity {
	
	private int authToken;     //Facebook store their own auth token
	
	//Facebook facebook = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
		
        setContentView(R.layout.login);
        
        authToken = 0;
        
        //facebook = new Facebook( Constants.FACEBOOK_APP_ID );
    }
    
    @Override
    public void onStart()
    {
		super.onStart();
    }
    
    public void onResume()
    {
		super.onResume();
		
		/*if(isSignedIn()) //If the player already has an auth token
		{		
			Intent signInIntent = new Intent(this, AssassinMenu.class);
			startActivity(signInIntent);
		}*/
		//Show login options and wait for input
    }
    
    public boolean isSignedIn()
    {
		if(authToken != 0 )
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
			Intent signInIntent = new Intent(this, AssassinMenu.class);
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
		AssassinActivity.apic.jsontest();
	}

    public boolean facebookLogin()
    {
		
		/*/* Authorize facebook with SSO or showing a webview 
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
		
		return facebook.isSessionValid();*/
    	
    	return false;
	}
    
    public int getAuthToken(){
        return authToken;
    }

	/*public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if(facebook != null)
		{
			facebook.authorizeCallback(requestCode, resultCode, data);
		}
	}*/
}
