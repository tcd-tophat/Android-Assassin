package org.tophat.assassin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AssassinActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
    	Intent signInIntent = new Intent(this, SignInActivity.class);
    	startActivity(signInIntent);
    }
	public void onStart(){
		super.onStart();

		Intent signInIntent = new Intent(this, SignInActivity.class);
		startActivity(signInIntent);
	}
}
