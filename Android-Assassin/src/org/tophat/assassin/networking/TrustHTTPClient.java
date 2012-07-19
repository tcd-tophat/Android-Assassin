package org.tophat.assassin.networking;

import java.security.KeyStore;

import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.tophat.assassin.Constants;

/**
 * This class creates the required client depending on the users setting for a client that forces Unsigned certs to be accepted or otherwise.
 * @author Kevin
 *
 */
public class TrustHTTPClient
{

	public TrustHTTPClient()
	{
		
	}
	
	public DefaultHttpClient getClient()
	{
		if ( Constants.TRUST_UNSIGNED_SSL_CERTS )
		{
			return createUnsignedClient();
		}
		else
		{
			return createSignedClient();
		}
	}
	
	private DefaultHttpClient createSignedClient() 
	{
		try 
		{
	        HttpParams params = new BasicHttpParams();
	        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
	
	        SchemeRegistry registry = new SchemeRegistry();
	        // registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	        registry.register( new Scheme("https", SSLSocketFactory.getSocketFactory(), 443 ) );

	        ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
	
	        return new DefaultHttpClient(ccm, params);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
			return null;
		}   	
    }

	public DefaultHttpClient createUnsignedClient()
	{
		try 
		{
	    	SSLSocketFactory sf = null;
	    	
	        KeyStore trustStore;
	        
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			
	        trustStore.load(null, null);
	
	        sf = new UntrustedSSLSocketFactory(trustStore);
	        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	
	        HttpParams params = new BasicHttpParams();
	        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
	
	        SchemeRegistry registry = new SchemeRegistry();
	        //registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

	        registry.register( new Scheme("https", sf, 443) );

	        ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
	
	        return new DefaultHttpClient(ccm, params);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
			return null;
		}   
	}
	
}
