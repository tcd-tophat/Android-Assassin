package org.tophat.assassin.networking;

import java.util.HashMap;
import java.util.Map;

import org.tophat.assassin.model.CommandModel;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON parser goes in between the HTTPParser and the CommandModel, and it's job is to convert the given data into the suitable types & also 
 * @author Kevin
 *
 */
public class JSONParser {

	private ObjectMapper mapper;
	
	/**
	 * Constructor
	 */
	public JSONParser (HTTPParser http, CommandModel cm)
	{
		mapper = new ObjectMapper(); // can reuse, share globally
	}
	
	
	/**
	 * This method Takes JSON data, converts to objects and provides exception handling.
	 * @param input
	 * @return
	 */
	public Map<String, Object> getObjects(String input)
	{
		try
		{
			return mapper.readValue(input, Map.class);
		}
		catch( Exception e )
		{
			e.printStackTrace();
			
			return null;
		}
	}
}
