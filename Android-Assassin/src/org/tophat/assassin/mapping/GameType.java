package org.tophat.assassin.mapping;

import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

public class GameType implements Parcelable {

	
	private Integer id;
	private String name;
	
	public GameType()
	{
		this.setId(null);
		this.setName(null);
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

	public GameType(Map<String, Object> gametype)
	{
		if (gametype.containsKey("id"))
			this.setId((Integer)gametype.get("id"));
		
		if(gametype.containsKey("name"))
			this.setName((String)gametype.get("name"));
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
