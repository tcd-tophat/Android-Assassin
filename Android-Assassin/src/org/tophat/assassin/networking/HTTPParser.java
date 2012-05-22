package org.tophat.assassin.networking;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.tophat.assassin.AssassinActivity;
import org.tophat.assassin.Constants;

import android.os.AsyncTask;

public class HTTPParser {

	public TCPStream stream;
	
	public HTTPParser ()
	{
        stream = new TCPStream( this );
        
        //Setup and start the SocketTask
        new SocketTask().execute();
	}
	
	/**
	 * This method sends a given string to the TCPStream which sends the data to the server
	 * @param message
	 */
	public void sendString(String message)
	{
		if( stream.isConnected() )
		{
			stream.writeToSocket(message);
		}
	}
	
	/**
	 * Sends a JSON message to the server
	 * @param type
	 * @param url
	 * @param message
	 */
	public boolean sendJson(String type, String url, String message)
	{
		if( stream.isConnected() )
		{
			String data = type + " ";
			
			data += url + "\n";
			
			data += message;
			
			stream.writeToSocket(data);
			
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * This method takes a reponse with pseudo-HTTP/1.1 and converts into into Data that is suitable for the JSON parser.
	 * @param firstline
	 * @param response 
	 */
	public void parseResponse(String firstline, String response) 
	{
		try
		{
			
			if ( firstline != null && response != null )
			{
				String[] resType = firstline.split(" ");
				
				int index = 1;
				
				//For some early alphas, HTTP/1.1 is not always returned.
				if( !resType[0].equals("HTTP/1.1") )
				{
					index = 0;
				}
				
				int errorCode = Integer.parseInt(resType[index]);
				
				String errorText = "";
				
				for( int i = index+1; i < resType.length; i++ )
				{
					errorText += resType[i];
					
					if( i != resType.length-1)
					{
						errorText += " ";
					}
				}
				
				System.err.println("[Error "+errorCode+"]"+" "+errorText);
				System.err.println(response);
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * The SocketTask connects to the socket of the game server and then awaits IO
	 * @author Kevin
	 */
	private class SocketTask extends AsyncTask<Void, Boolean, Boolean> {
		
		private boolean reconnect;
		
		private String errorMessage;
		
	    protected Boolean doInBackground(Void... nothing) {
	    	 
	    	reconnect = true;
	    	errorMessage = "";
	    	 
			try 
			{
				if ( stream.connect() ) 
				{
					stream.readFromSocket();
				}
				else
				{
					errorMessage = "Failed for unknown reason.";
					reconnect = true;
				}
			} 
			catch (KeyManagementException e) 
			{
				e.printStackTrace();
				
				errorMessage = e.getLocalizedMessage();
				
				reconnect = false;
			} 
			catch (NoSuchAlgorithmException e) 
			{
				e.printStackTrace();
				
				errorMessage = e.getLocalizedMessage();
				
				reconnect = false;
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				
				reconnect = true;
			} 
	    	 
	        return false;
	     }

	     protected void onPostExecute(Boolean result) {
	    	 
	    	 // Should reconnect ensures that the soft doesn't try to reconnect to the server more than three times.
	    	 if(reconnect && !result && stream.shouldReconnect())
	    	 {
	    		 //Count reconnection attempt
	    		 stream.incrementReconnection();
	    		 
	    		 try
	    		 {
	    			 Thread.sleep( Constants.RECONNECTION_DELAY );
	    		 }
	    		 catch ( Exception e )
	    		 {
	    			 e.printStackTrace();
	    		 }
	    		 
	    		 new SocketTask().execute();
	    		 
	    		 AssassinActivity.showNotification("Connection to server failed. Reconnecting...");
	    	 }
	    	 else if( !reconnect && !result )
	    	 {
	    		 AssassinActivity.showNotification("Connection to server failed. Reconnection not possible.");
	    	 }
	     }
	     
	     protected void onProgressUpdate(Boolean... values)  {
	          super.onProgressUpdate(values);
	     }
	 }
}
