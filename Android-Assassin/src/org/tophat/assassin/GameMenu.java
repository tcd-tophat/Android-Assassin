package org.tophat.assassin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.tophat.assassin.components.ListItem;
import org.tophat.assassin.components.QViewAdapter;
import org.tophat.assassin.mapping.Game;
import org.tophat.assassin.mapping.GameList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class GameMenu extends ListActivity {
	
    private List<? extends Map<String, ?>> data;
	private ListView lv;
	GameList games;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayList<Map> data = getData();
        
		//Setup the titles of the xml content to be set later
		String[] from = {"title", "subTitle"};
		int[] to = {R.id.title, R.id.subTitle};
		
		String[] ids = null;
		
		//Setup the special adapter which works with subtitles.
		QViewAdapter adapter = new QViewAdapter(this, (List<? extends Map<String, ?>>) data, from, to,  ids);
		
		//Set this subtitle adapter works 
		this.setListAdapter(adapter);
		
		this.lv = getListView();
		lv.setTextFilterEnabled(true);
		
		lv.setVerticalFadingEdgeEnabled(true);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    	  AssassinActivity.showNotification(games.getGames().get(position).getName()); 
		    	  Intent intent = new Intent(GameMenu.this, JoinGame.class);
		    	  intent.putExtra("game", games.getGames().get(position));
		    	  startActivity(intent); 
		      }
		});
    }
    
    public ArrayList<Map> getData()
    {
    	games = AssassinActivity.apic.games();
    	
    	ArrayList<Map> data = new ArrayList<Map>();
    	
        for ( Game game : games.getGames() )
        {
        	data.add((Map) new ListItem(game.getName(), game.getCreator().getName()+" - "+ game.getGameType().getName(), game.getId().toString()));
        }
        
        return data;    	
    }
    
	@Override
	public void onStart(){
		super.onStart();
	}
	
	@Override
	public void onResume(){
		super.onResume();
	}
}
