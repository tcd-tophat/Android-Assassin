package org.tophat.assassin;
import org.tophat.android.*;

public class Constants extends org.tophat.android.Constants
{
	
	public Constants()
	{
		super();
		this.setAPI_URL(this.API_URL);
		this.setAPP_VERSION(this.APP_VERSION);
		this.setUSER_AGENT(this.USER_AGENT);
	}
	
	/**
	 * This is the id of the data stream.
	 */
	public final int DATA_STREAM = 0x01;
	
	/**
	 * This is the reconnection delay to the server in milliseconds.
	 */
	public final int RECONNECTION_DELAY = 4000;
	
	/**
	 * 
	 */
	public final String FACEBOOK_APP_ID = "385044381546657";
	
	public final String USER_AGENT = "org.tophat.android.PlatformClient";
	
	public final String APP_VERSION = "0.1a";
	
	/**
	 * The API URL requires a trailing slash
	 */
	public final String API_URL = "http://www.arboroia.com:443/";
	
	public final int SIGNIN_ACTIVITY = 0x1001;
	
	public final int MENU_ACTIVITY = 0x1002;

	public final boolean TRUST_UNSIGNED_SSL_CERTS = true;
}
