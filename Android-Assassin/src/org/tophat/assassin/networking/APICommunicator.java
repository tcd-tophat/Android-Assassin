package org.tophat.assassin.networking;

import java.util.Map;

import org.tophat.assassin.AssassinActivity;
import org.tophat.assassin.model.CommandModel;

public class APICommunicator 
{

	/**
	 * The APIStream is the lower level HTTP Communicator with the remote server
	 */
	protected APIStream apis;
	
	protected JSONParser json;
	
	protected CommandModel cm;
	
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
	public APICommunicator(AssassinActivity a)
	{
		this.cm = new CommandModel();
		
		this.launcher = a;
		
		this.apis = new APIStream(this);
		
		this.json = new JSONParser(this, cm);
		
		//Prepares connection
		this.apis.connect();
	}
	
	/**
	 * Returns true when the login details are correct and the API is reachable. Otherwise returns false.
	 * @param user
	 * @param pass
	 * @return
	 */
	public boolean login(String user, String pass, String api, String url)
	{
		return false;
	}

	/**
	 * This JSONtest retrieves a random json string from the server in order to check if JSON parser + web retrieval is functional
	 * @return
	 */
	public String jsontest()
	{	
		//Prepare the response (reset error codes set)
		this.prepare();
		
		try
		{
			
			String input  = apis.requestAPI("jsontest");
			
			if( input == null )
			{
				this.setApiError("The API returned invalid data or the connection failed.");
			}
			
			System.out.println(input);
			
			Map<String, Object> mapping = json.getObjects(input);
			
			for( String s : mapping.keySet() )
			{
				System.err.println("Key 1: "+s);
			}
			
			System.err.println(mapping.size());
			
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			this.setApiError("The API returned invalid data.");
			return null;
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
