package org.tophat.assassin.networking;

import java.util.Map;

import org.tophat.assassin.AssassinActivity;
import org.tophat.assassin.model.CommandModel;

public class APICommunicator {

	/**
	 * The APIStream is the lower level HTTP Communicator with the remote server
	 */
	protected APIStream apis;
	
	protected JSONParser json;
	
	protected CommandModel cm;
	
	protected AssassinActivity aa;
	
	/**
	 * The JSONInterpreter 
	 */
	private String apikey;
	private String api_error;
	
	public APICommunicator(AssassinActivity a)
	{
		this.cm = new CommandModel();
		
		this.aa = a;
		
		this.apis = new APIStream(this);
		
		this.json = new JSONParser(this, cm);
		
		//Prepares connection
		this.apis.connect();
	}
	
	public AssassinActivity getLauncher()
	{
		return this.aa;
	}
	
	/**
	 * Returns true when the login details are correct and the API is reachable. Otherwise returns false.
	 * @param user
	 * @param pass
	 * @return
	 */
	public boolean login(String user, String pass, String api, String url)
	{
		boolean live = false;
		
		try
		{
			if(api.equals("1"))
			{
				live = false;
			}
			else
			{
				live = true;
			}
			
			String response = apis.requestAPI("user/login/"+user+"/"+pass, live, url);
			
			if(!response.equals("error"))
			{
				/*Login loginresult = jsonint.resultLogin(response);
			
				if(loginresult.getResult().equals("success"))
				{
					this.setApikey(loginresult.getApi_key());
					return true;
				}
				else
				{
					this.setApiError(loginresult.getError());
					return false;
				}*/
				
				return false;
			}
			else
			{
				this.setApiError("Server Connection timed out.");
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

	public String jsontest()
	{	
		try
		{
			this.setApiError("");
			
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

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	
	public String getApiError() {
		return this.api_error;
	}

	public void setApiError(String apierror) {
		this.api_error = apierror;
	}
}
