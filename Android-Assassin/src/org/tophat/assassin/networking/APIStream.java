package org.tophat.assassin.networking;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.security.cert.CertificateException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.tophat.assassin.Constants;

public class APIStream 
{

	protected APICommunicator apic;
	
	protected String host;
	protected String username;
	protected String password;
	protected HttpClient http;
	protected HttpGet httpget;
	protected HttpResponse response;
	protected HttpEntity entity;
	protected HttpPost httppost;
	protected boolean live;
	
	public APIStream(APICommunicator apic)
	{
		this.apic = apic;
		this.live = false;
	}
	
	/**
	 * Prepares for a connection to the JSON API.
	 */
	public void connect() {

		host = Constants.API_URL;
		
		HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
	     
		//Get the HTTP client that ignores the SSL sockets
		http = getNewHttpClient();
	
		HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
		
		HttpProtocolParams.setUserAgent( http.getParams(), Constants.USER_AGENT + " "+ Constants.APP_VERSION );
	}

	public DefaultHttpClient getNewHttpClient() 
	{
	    try 
	    {
	        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        trustStore.load(null, null);

	        SSLSocketFactory sf = new UntrustedSSLSocketFactory(trustStore);
	        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

	        HttpParams params = new BasicHttpParams();
	        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

	        SchemeRegistry registry = new SchemeRegistry();
	        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	        registry.register(new Scheme("https", sf, 443));
	       
	        ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

	        return new DefaultHttpClient(ccm, params);
	    } 
	    catch (Exception e) 
	    {
	        return new DefaultHttpClient();
	    }
	}

	/**
	 * Requests a call eg. req = user/login/{username}/{password} , returns eg. {"result":"success","api_key":"sghfd3456"}
	 * @param req
	 * @return
	 */
	public String postAPI(String req, String[] json)
	{
		try
		{
			return this.postHTTP(req, json);
			
		} 
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param req
	 * @param live
	 * @return
	 */
	public String requestAPI(String req, boolean live, String url)
	{
		if(url!=null)
		{
			if(url!="")
			{
				this.host = url;
				this.live = true;
			}
		}
		else
		{
			if(live)
			{
				this.host = "http://api.verifiapp.com/";
				this.live = live;
			}
			else
			{
				this.host = "http://test.api.verifiapp.com/";
				this.live = live;
			}	
		}
		
		return this.requestAPI(req);
	}
	
	/**
	 * Requests a call eg. req = user/login/{username}/{password} , returns eg. {"result":"success","api_key":"sghfd3456"}
	 * @param req
	 * @return
	 */
	public String requestAPI(String req)
	{
		try
		{
			String response = this.requestHTTP(req);
			
			if(response==null)
			{
				return null;
			}
			else
			{
				return response;
			}
			
		} 
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Requests a HTTP
	 * @param req
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String requestHTTP(String req) throws ClientProtocolException, IOException
	{
		httpget = new HttpGet(this.host+req);
		response = http.execute(httpget);
		entity = response.getEntity();
		
		//Returns the API returned strings.
		return EntityUtils.toString(entity);
	}
	
	/**
	 * POSTS via HTTP
	 * @param req
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String postHTTP(String req, String[] post) throws ClientProtocolException, IOException
	{
		httppost = new HttpPost(this.host+req);
		  
        // Adding POST data
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2); 
        nameValuePairs.add(new BasicNameValuePair(post[0], post[1])); 
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
	 

        // Execute HTTP Post Request
		response = http.execute(httppost);
		
		if( response.getStatusLine().getStatusCode()<300)
		{
			entity = response.getEntity();
			
			return EntityUtils.toString(entity);
		}
		else
		{
			apic.setApiError("Error: "+response.getStatusLine().getStatusCode());
			
			return null;
		}
	}
	
	/**
	 * 
	 * @param req
	 * @param imagedata
	 * @return
	 */
	public String postHTTPMultipart(String req, String text, List<File> images, List<String> titles)
	{
		HttpClient client = http;
		client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		HttpPost        post   = new HttpPost( host + req );
		MultipartEntity entity = new MultipartEntity( HttpMultipartMode.BROWSER_COMPATIBLE );

		for(int i = 0; i < images.size(); i++)
		{
			// For File parameters
			entity.addPart( "photos[]", new FileBody(images.get(i), "image/jpeg" ));
		}
		
		try 
		{
			entity.addPart("json", (ContentBody) new StringBody(text));
		}
		catch (UnsupportedEncodingException e1) 
		{
			e1.printStackTrace();
		}
		
		post.setEntity( entity );

		// Here we go!
		String response = null;
		
		try 
		{
			response = EntityUtils.toString( client.execute( post ).getEntity(), "UTF-8" );
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClientProtocolException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
}
