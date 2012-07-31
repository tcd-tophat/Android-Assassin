package org.tophat.assassin.mapping;

import java.util.ArrayList;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A Class in order to map the game mappings onto.
 * @author Kevin
 *
 */
public class GameList implements Parcelable{
	
	private Integer maxPerpage;
	/**
	 * @return the maxPerpage
	 */
	public Integer getMaxPerpage() {
		return maxPerpage;
	}

	/**
	 * @param maxPerpage the maxPerpage to set
	 */
	public void setMaxPerpage(Integer maxPerpage) {
		this.maxPerpage = maxPerpage;
	}

	/**
	 * @return the pagination_offset
	 */
	public Integer getPaginationOffset() {
		return paginationOffset;
	}

	/**
	 * @param pagination_offset the pagination_offset to set
	 */
	public void setPaginationOffset(Integer paginationOffset) {
		this.paginationOffset = paginationOffset;
	}

	/**
	 * @return the games
	 */
	public ArrayList<Game> getGames() {
		return games;
	}

	/**
	 * @param games the games to set
	 */
	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}

	private Integer paginationOffset;
	private ArrayList<Game> games;
	
	public GameList()
	{
		this.setGames(new ArrayList<Game>());
		this.setMaxPerpage(null);
		this.setPaginationOffset(null);
	}
	
	public GameList(Map<String, Object> gamelist)
	{
		this.setGames(new ArrayList<Game>());
		
		if (gamelist.containsKey("pagination_offset"))
			this.setPaginationOffset((Integer)gamelist.get("pagination_offset"));
		
		if (gamelist.containsKey("max_perpage"))
			this.setMaxPerpage((Integer)gamelist.get("max_perpage"));
		
		if (gamelist.containsKey("games"))
		{
			ArrayList<Map<String, Object>> games = (ArrayList<Map<String, Object>>) gamelist.get("games");
			
			for(Map<String, Object> game : games)
			{
				this.games.add(new Game(game));
			}
		}
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
}
