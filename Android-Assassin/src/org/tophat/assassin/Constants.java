package org.tophat.assassin;

public class Constants 
{
	/**
	 * This is the id of the data stream.
	 */
	public static final int DATA_STREAM = 0x01;
	
	/**
	 * This is the reconnection delay to the server in milliseconds.
	 */
	public static final int RECONNECTION_DELAY = 4000;
	
	/**
	 * 
	 */
	public static final String FACEBOOK_APP_ID = "385044381546657";
	
	public static final String USER_AGENT = "org.tophat.android.PlatformClient";
	
	public static final String APP_VERSION = "0.1a";
	
	/**
	 * The API URL requires a trailing slash
	 */
	public static final String API_URL = "http://www.arboroia.com:443/";
	
	public static final int SIGNIN_ACTIVITY = 0x1001;
	
	public static final int MENU_ACTIVITY = 0x1002;

	public static final boolean TRUST_UNSIGNED_SSL_CERTS = true;
}
