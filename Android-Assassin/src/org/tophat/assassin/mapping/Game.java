package org.tophat.assassin.mapping;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A Class in order to map the game mappings onto.
 * @author Kevin
 *
 */
public class Game implements Parcelable {
	
	private User creator;
	private String time;
	private Integer id;
	private String name;
	private GameType gameType;
	
	public Game()
	{
		this.setCreator(null);
		this.setTime(null);
		this.setId(null);
		this.setName(null);
		this.setGameType(null);
	}
	
	private void setGameType(GameType gametype) {
		this.gameType = gametype;
	}

	public Game(Map<String, Object> game)
	{
		if (game.containsKey("id"))
			this.setId((Integer)game.get("id"));
		
		if (game.containsKey("time"))
			this.setTime((String)game.get("time"));
		
		if (game.containsKey("name"))
			this.setName((String)game.get("name"));
		
		if (game.containsKey("creator"))
			this.setCreator(new User((Map<String, Object>)game.get("creator")));
		
		if (game.containsKey("game_type"))
			this.setGameType(new GameType((Map<String, Object>)game.get("game_type")));
	}
	
	/**
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}


	/**
	 * @param creator the creator to set
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}


	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}


	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the gameType
	 */
	public GameType getGameType() {
		return gameType;
	}

	// Parcelling part
	public Game(Parcel in){
		ArrayList<String> data = in.readArrayList(ArrayList.class.getClassLoader());
		
		this.setName(data.get(0));
		this.setTime(data.get(1));
		this.setId(Integer.parseInt(data.get(2)));
		
		Parcelable[] objs = in.readParcelableArray(Parcelable.class.getClassLoader());
		
		this.setCreator((User)objs[0]);
		this.setGameType((GameType)objs[1]);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		ArrayList<String> data = dest.createStringArrayList ();
		
		data.add(this.getName());
		data.add(this.getTime());
		data.add(this.getId().toString());
		
		dest.writeStringList(data);
		
		Parcelable[] objs = new Parcelable[2];
		
		objs[0] = this.getCreator();
		objs[1] = this.getGameType();
		
		dest.writeParcelableArray(objs, 0);
	}
}
