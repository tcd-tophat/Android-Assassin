package org.tophat.assassin;

import org.tophat.android.mapping.Game;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class JoinGame extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.gamescreen);
        
        Bundle b = getIntent().getExtras();
		Game g = b.getParcelable("game");
        
        ((TextView)findViewById(R.id.game_name_placeholder)).setText(g.getName()); 
        ((TextView)findViewById(R.id.game_type_placeholder)).setText(g.getGameType().getName()); 
    }
}
