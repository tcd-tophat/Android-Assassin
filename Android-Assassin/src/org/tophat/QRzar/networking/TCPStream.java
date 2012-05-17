package org.tophat.QRzar.networking;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import android.util.Log;

public class TCPStream {

	//TopHat Alpha Server IP Address
	public static final String host = "109.200.29.197";
	
	public boolean connected;
	
	public boolean secured;
	
	public SSLSocket socket;
	
	public SSLContext sslContext;
	
	public TrustManager[] tm;
	
	public TCPStream ( )
	{
		connected = false;
		
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
		        socket.setSoTimeout(20000);
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
			  }
			  else
			  {
				  Log.i(getClass().toString(), "Securing failed.");
		  	  }
		  }
		  
		/*}
		catch( Exception e )
		{
			if( e != null)
			{
				e.printStackTrace();
			}
		}*/
		
		return connected;
	}
}
