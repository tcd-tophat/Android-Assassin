package org.tophat.assassin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.android.*;
import com.facebook.android.Facebook.*;

/* Handle all signing in work */
public class SignInActivity extends Activity {
	
	private int authToken = 0;

	Facebook facebook = new Facebook("APP_ID");
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.main);
		//Facebook Login
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
=======
        setContentView(R.layout.login);
>>>>>>> 0b4f814f3c137603b017f8876a3f40e89d4b688a
    }
    
    @Override
    public void onStart(){
    	super.onStart();
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	if(isSignedIn()){		//If the player already has an auth token
    		Intent signInIntent = new Intent(this, AssassinActivity.class);
    		startActivity(signInIntent);
    	}
    	else{
    		if(signIn()){
    			//Player has been signed in
    		}
    		else{
    			//Handle login errors
    		}
    	}
    }
    
    public boolean isSignedIn(){
    	if(authToken != 0 || facebook.isSessionValid()){
    		return true;
    	}
    	return false;
    }
    
    public boolean signIn(){
    /*Sign in the player and set suthToken */
    	authToken = 1;
    	return true;
    }

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
		
		facebook.authorizeCallback(requestCode, resultCode, data);
	}
}
