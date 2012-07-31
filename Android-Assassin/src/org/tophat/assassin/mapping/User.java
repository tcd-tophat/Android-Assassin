package org.tophat.assassin.mapping;

import java.util.ArrayList;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * This class is mapping for the user 
 * @author Kevin
 *
 */
public class User implements Parcelable {

	private Integer id;
	private String name;
	private String email;
	private String created;
	private ArrayList<Game> joinedGames; 
	
	
	/**
	 * 
	 */
	public User()
	{
		this.setId(null);
		this.setName(null);
		this.setCreated(null);
		this.setEmail(null);
		this.setJoinedGames(new ArrayList<Game>());
	}
	
	/**
	 * Setup the User object with the details supplied from the JSON from the platform.
	 * @param user
	 */
	public User(Map<String, Object> user)
	{
		this.setId(null);
		this.setName(null);
		this.setCreated(null);
		this.setEmail(null);
		this.setJoinedGames(new ArrayList<Game>());
		
		if (user.containsKey("id"))
			this.setId((Integer)user.get("id"));
		
		if (user.containsKey("created"))
			this.setCreated((String)user.get("created"));
		
		if (user.containsKey("email"))
			this.setEmail((String)user.get("email"));
		
		if (user.containsKey("name"))
			this.setName((String)user.get("name"));
		
		if (user.containsKey("joined_games"))
		{	
			ArrayList<Map<String, Object>> gameslist = (ArrayList<Map<String, Object>>) user.get("joined_games");
			
			for(Map<String, Object> game : gameslist)
			{
				this.joinedGames.add(new Game(game));
			}
		}
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}


	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}


	/**
	 * @return the joined_games
	 */
	public ArrayList<Game> getJoinedGames() {
		return joinedGames;
	}


	/**
	 * @param joined_games the joined_games to set
	 */
	public void setJoinedGames(ArrayList<Game> joinedGames) {
		this.joinedGames = joinedGames;
	}
	
	// Parcelling part
	public User(Parcel in){
		ArrayList<String> data = in.readArrayList(ArrayList.class.getClassLoader());
		
		this.setName(data.get(0));
		this.setEmail(data.get(1));
		this.setId(Integer.parseInt(data.get(2)));
		
		in.readList(this.joinedGames, ArrayList.class.getClassLoader());
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
		data.add(this.getEmail());
		data.add(this.getId().toString());
		
		dest.writeStringList(data);
		
		dest.writeList(this.joinedGames);
		
	}
}
