package org.tophat.assassin;

import org.tophat.assassin.mapping.Game;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class JoinGame extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.gamescreen);
        

        //Game g = getIntent().getExtras().getString("game");
        
        ((TextView)findViewById(R.id.game_name_placeholder)).setText(""); 
    }
}
