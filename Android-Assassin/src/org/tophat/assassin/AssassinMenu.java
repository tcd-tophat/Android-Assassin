package org.tophat.assassin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.tophat.assassin.components.ListItem;
import org.tophat.assassin.components.QViewAdapter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AssassinMenu extends ListActivity {
	
    private List<? extends Map<String, ?>> data;
	private ListView lv;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        String title = "Single List Item";
        String subtitle = "";
        String type = "";
        
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
		
		//lv.setCacheColorHint(Color.WHITE);
		lv.setVerticalFadingEdgeEnabled(true);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    	  //Do Nothing
		      }
		});
    }
    
    public ArrayList<Map> getData()
    {
    	Map<String, Object> games = AssassinActivity.apic.games();
    	
    	ArrayList<Map<String, Object>> gameslist = (ArrayList<Map<String, Object>>) games.get("games");
    	
        ArrayList<Map> data = new ArrayList<Map>();
        
        for ( Map<String, Object> gamemap : gameslist )
        {
        	data.add((Map) new ListItem((String)gamemap.get("name"), (String)((Map)gamemap.get("creator")).get("name")+" - "+(String)((Map)gamemap.get("game_type")).get("name"), ((Integer)gamemap.get("id")).toString()));
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
