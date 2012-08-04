package org.tophat.android.networking;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.tophat.android.exceptions.BadResponse;
import org.tophat.android.exceptions.HttpException;
import org.tophat.android.mapping.ApiToken;
import org.tophat.android.mapping.GameList;
import org.tophat.assassin.AssassinActivity;

public class ApiCommunicator 
{

	/**
	 * The APIStream is the lower level HTTP Communicator with the remote server
	 */
	protected TopHatHttpClient http;
	
	protected JsonParser json;

	protected AssassinActivity launcher;

	private ApiToken apiToken;
	
	/**
	 * 
	 * @param a
	 */
	public ApiCommunicator(AssassinActivity a)
	{	
		this.launcher = a;
		
		this.http = new TopHatHttpClient(this);
		
		this.json = new JsonParser();
		
		//Prepares connection
		this.http.connect();
	}

	/**
	 * Returns true when the login details are correct and the API is reachable. Otherwise returns false.
	 * @param user
	 * @param pass
	 * @return
	 */
	/*public GameList games()
	{
		//Prepare the response (reset error codes set)
		this.prepare();
		
		try
		{	
			String input = http.get("games/?apitoken="+this.getApikey());
			
			if( input == null || input.equals("") )
			{
				this.setApiError("The API returned invalid data or the connection failed.");
			}
			
			Map<String, Object> mapping = json.getObjects(input);
			
			for( String s : mapping.keySet() )
			{
				
				if (s.equals("games"))
				{
					return new GameList((Map<String, Object>) mapping);
				}
				else if(s.equals("error_message"))
				{
					this.setApiError((String) mapping.get("error_message"));
				}
				else if(s.equals("error_code"))
				{
					if( (Integer) mapping.get("error_code") == 401)
					{
						if (this.getApiError() == null)
							this.setApiError("Unauthorised");
					}
				}			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			this.setApiError("The API returned invalid data.");
			return null;
		}
		return null;
	}*/
	
	/**
	 * 
	 * @param uri
	 * @return
	 * @throws HttpException
	 */
	public Map<String, Object> get(String uri) throws HttpException
	{
		String input;
		
		if(this.getApitoken() != null )
		{
			input = http.get(uri+"?apitoken="+this.getApitoken().getApitoken());
		}
		else
		{
			input = http.get(uri);
		}
		
		if( input == null || input.equals("") )
		{
			throw new BadResponse();
		}
		
		try
		{
			return json.getObjects(input);
		}
		catch(ParseException pe)
		{
			throw new BadResponse();
		}
	}
	
	/**
	 * 
	 * @param uri
	 * @return
	 * @throws HttpException
	 */
	public Map<String, Object> post(String uri, HashMap<String, String> data) throws HttpException
	{
		String input;
		
		if(this.getApitoken() != null )
		{
			input = http.post(uri+"?apitoken="+this.getApitoken().getApitoken(), json.toJson(data));
		}
		else
		{
			input = http.post(uri, json.toJson(data));
		}
		
		if( input == null || input.equals("") )
		{
			throw new BadResponse();
		}
		
		try
		{
			return json.getObjects(input);
		}
		catch(ParseException pe)
		{
			throw new BadResponse();
		}
	}
	
	public AssassinActivity getLauncher()
	{
		return this.launcher;
	}

	public void setApitoken(ApiToken apiToken) {
		this.apiToken = apiToken;
	}
	
	public ApiToken getApitoken()
	{
		return this.apiToken;
	}
}
