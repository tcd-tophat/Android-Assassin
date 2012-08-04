package org.tophat.android.networking;

import java.util.HashMap;
import java.util.Map;

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
	
	/**
	 * The JSONInterpreter 
	 */
	private String apikey;
	
	/**
	 * Contains the API Error response as text, which can be accessed in order to display a meaningful message to the user.
	 */
	private String api_error;
	
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
	public GameList games()
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
	}
	
	/**
	 * Returns true when the login details are correct and the API is reachable. Otherwise returns false.
	 * @param user
	 * @param pass
	 * @return
	 */
	public boolean login(String user, String pass)
	{
		//Prepare the response (reset error codes set)
		this.prepare();
		
		try
		{
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("email", user);
			map.put("password", pass);
			
			String input = http.post("apitokens/", json.toJson(map));
			
			System.err.println("RESPONSE: "+input);
			
			if( input == null )
			{
				this.setApiError("The API returned invalid data or the connection failed.");
			}
			
			Map<String, Object> mapping = json.getObjects(input);
			
			for( String s : mapping.keySet() )
			{
				
				if (s.equals("apitoken"))
				{
					this.apikey = (String) mapping.get("apitoken");
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
			
			System.err.println("Token "+this.apikey);
			
			if ( this.apikey != null )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			this.setApiError("The API returned invalid data.");
			return false;
		}
	}

	public String getApikey() 
	{
		return apikey;
	}

	public void setApikey(String apikey) 
	{
		this.apikey = apikey;
	}
	
	public String getApiError() 
	{
		return this.api_error;
	}

	public AssassinActivity getLauncher()
	{
		return this.launcher;
	}
	
	public void setApiError(String apierror) 
	{
		this.api_error = apierror;
	}
	
	/**
	 * This method prepares the response of the client
	 */
	public void prepare()
	{
		this.setApiError(null);
	}
}
