package org.tophat.assassin;

import android.app.Activity;
import android.os.Bundle;

public class AssassinActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
	public void onStart(){
		super.onStart();

		Intent signInIntent = new Intent(this, SignInActivity.class);
		startActivity(signInIntent);
	}
}
