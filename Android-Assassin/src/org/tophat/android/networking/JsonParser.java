package org.tophat.android.networking;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON parser goes in between the HTTPParser and the CommandModel, and it's job is to convert the given data into the suitable types & also 
 * @author Kevin
 *
 */
public class JsonParser 
{

	private ObjectMapper mapper;
	
	/**
	 * Constructor
	 */
	public JsonParser ()
	{
		try
		{
			mapper = getMapper(); // can reuse, share globally
		} 
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	
	private ObjectMapper getMapper()
	{
		if( mapper == null )
		{
			try
			{
				mapper = new ObjectMapper(); // can reuse, share globally
			} 
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
		
		return mapper;
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
			return getMapper().readValue(input, Map.class);
		}
		catch( Exception e )
		{
			e.printStackTrace();
			
			return null;
		}
	}
	
	public String toJson(Object input)
	{
		try {
			return getMapper().writeValueAsString(input);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
