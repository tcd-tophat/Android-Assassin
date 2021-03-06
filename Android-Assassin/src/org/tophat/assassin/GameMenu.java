package org.tophat.assassin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.tophat.android.exceptions.HttpException;
import org.tophat.android.exceptions.NoInternetConnection;
import org.tophat.android.exceptions.NotFound;
import org.tophat.android.exceptions.Unauthorised;
import org.tophat.android.mapping.Game;
import org.tophat.android.mapping.GameList;
import org.tophat.android.model.ApiTokenMapper;
import org.tophat.android.model.GameMapper;
import org.tophat.assassin.components.ListItem;
import org.tophat.assassin.components.QViewAdapter;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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
        
        new CommandProcessTask().execute();
    }
    
    public ArrayList<Map> getData()
    {
    	GameMapper gm = new GameMapper(AssassinActivity.apic);
    	
    	this.games = new GameList();
    	
		try {
			this.games = gm.getGameList();
		} catch (Unauthorised e) {	
			AssassinActivity.showNotification("You must authenticate.");
		} catch (NoInternetConnection e) {	
			AssassinActivity.showNotification("You currently do not have an internet connection.");
		} catch (HttpException e) {	
			AssassinActivity.showNotification("Error.");
		}
    	
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
	
	public void loadGames(ArrayList<Map> data)
	{   
		//Setup the titles of the xml content to be set later
		String[] from = {"title", "subTitle"};
		int[] to = {R.id.title, R.id.subTitle};
		
		String[] ids = null;
		
		//Setup the special adapter which works with subtitles.
		QViewAdapter adapter = new QViewAdapter(GameMenu.this, (List<? extends Map<String, ?>>) data, from, to,  ids);
		
		//Set this subtitle adapter works 
		setListAdapter(adapter);
		
		lv = getListView();
		lv.setTextFilterEnabled(true);
		
		lv.setVerticalFadingEdgeEnabled(true);
		
		lv.setOnItemClickListener(
			new OnItemClickListener() 
			{
			      public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			      {
			    	  AssassinActivity.showNotification(games.getGames().get(position).getName()); 
			    	  Intent intent = new Intent(GameMenu.this, JoinGame.class);
			    	  intent.putExtra("game", games.getGames().get(position));
			    	  startActivity(intent); 
			      }
			});
	}
	
	private class CommandProcessTask extends AsyncTask<String, Integer, ArrayList<Map>> 
	{	
		private ProgressDialog dialog;
		
		@Override    
		protected void onPreExecute() 
		{       
		    super.onPreExecute();
		    
		    dialog = ProgressDialog.show(GameMenu.this, "", 
	                "Loading Games. Please wait...", true);
		}
		    
		protected ArrayList<Map> doInBackground(String... details) 
		{
			ArrayList<Map> data = getData();
			
			return data;
		 }

	     protected void onPostExecute(ArrayList<Map> data)
	     {
	    	 loadGames(data);
	    	 this.dialog.cancel();
	     }
	 }
}
