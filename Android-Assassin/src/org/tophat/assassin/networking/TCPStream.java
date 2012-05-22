package org.tophat.assassin.networking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.tophat.assassin.AssassinActivity;

import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

public class TCPStream {

	//TopHat Alpha Server IP Address
	public static final String host = "109.200.29.197";
	
	public boolean connected;
	
	public boolean secured;
	
	public SSLSocket socket;
	
	public SSLContext sslContext;
	
	public TrustManager[] tm;
	
	public BufferedInputStream input;
	
	public BufferedOutputStream output;
	
	public HTTPParser http;
	
    
    public int reconnectionAttempts = 0;
	
	public TCPStream (HTTPParser http)
	{
		connected = false;
		
		this.http = http;
		
		tm = new TrustManager[]{
			    new X509TrustManager() {
			        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			            return null;
			        }
			        public void checkClientTrusted( java.security.cert.X509Certificate[] certs, String authType ) {
			        }
			        public void checkServerTrusted( java.security.cert.X509Certificate[] certs, String authType ) {
			        }
			    }
			};
	}
	
	public boolean connect() throws IOException, NoSuchAlgorithmException, KeyManagementException
	{
		//try{
			// Connect
			if (socket == null || socket.isClosed() || !socket.isConnected()) {
				if (socket != null && !socket.isClosed())
					socket.close();
				
				if (sslContext == null) {
					sslContext = SSLContext.getInstance("SSL");
					sslContext.init(null, tm, new java.security.SecureRandom()); 
				}
				
				SSLSocketFactory socketFactory = sslContext.getSocketFactory();
		        
				socket = (SSLSocket) socketFactory.createSocket(host, 443);
		        socket.setSoTimeout(0);
		        socket.setUseClientMode(true);
		        connected = true;
		        Log.i(getClass().toString(), "Connected.");
		  }
		
		  // Secure
		  if (connected) {
			  Log.i(getClass().toString(), "Securing...");
			  SSLSession session = socket.getSession();
			  secured = session.isValid();
			  
			  if (secured) {
				  Log.i(getClass().toString(), "Secured.");
				  
				  output = new BufferedOutputStream(socket.getOutputStream());
			  }
			  else
			  {
				  Log.i(getClass().toString(), "Securing failed.");
		  	  }
		  }
		
		return connected;
	}

	public void incrementReconnection()
	{
		reconnectionAttempts++;
	}
	
	public boolean shouldReconnect()
	{
		if ( this.reconnectionAttempts <= 3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 
	 */
	public void writeToSocket(String data)
	{
		try {
			
			if( output != null )
			{
				output.write(data.getBytes());
				output.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readFromSocket()
	{
		Looper.prepare();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			
			AssassinActivity.showNotification("Initial Read Connection");
			
			System.out.println("Initial Connection");
			
			while( socket.isConnected() && br != null)
			{	
				String read = "";
				
				final String firstline = br.readLine();
				
				if ( firstline == null )
					continue;
				
				String bufferbit = "";

				while( (bufferbit = br.readLine()) != null && socket.isConnected() )
				{
					System.err.println("Recieved data from Server "+bufferbit);
					
					read += bufferbit;
				}
				
				final String input = read;

				new Thread(){
					public void run()
					{
						http.parseResponse(firstline, input);
					}
				}.start();
			}
			
			System.err.println("QUIT READING THREAD!!!!!!!!!!!");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
	public boolean isConnected()
	{
		if( socket != null )
			return socket.isConnected();
		else
			return false;
	}
	
	/**
	 * The SocketTask connects to the socket of the game server and then awaits IO
	 * @author Kevin
	 */
	private class ResponseTask extends AsyncTask<String, Boolean, Boolean> {
		
		protected Boolean doInBackground(String... input) 
		{
			try
			{
				if( input.length >= 2)
					http.parseResponse(input[0], input[1]);
				else
					System.err.println("[TCPStream] Parse Response length failed.");
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
				
			return false;
		}
		
		protected void onPostExecute(Boolean result)
		{
			
		}
	 }
}
