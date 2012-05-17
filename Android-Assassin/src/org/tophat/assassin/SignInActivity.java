package org.tophat.assassin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* Handle all signing in work */
public class SignInActivity extends Activity {
	
	private int authToken = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
    	if(authToken != 0){
    		return true;
    	}
    	return false;
    }
    
    public boolean signIn(){
    /*Sign in the player and set suthToken */
    	authToken = 1;
    	return true;
    }
}
