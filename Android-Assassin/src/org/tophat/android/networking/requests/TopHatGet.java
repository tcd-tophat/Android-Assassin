package org.tophat.android.networking.requests;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.tophat.android.exceptions.HttpException;
import org.tophat.android.networking.ResponseProcessor;

public class TopHatGet extends ResponseProcessor{

	private HttpGet get;
	private HttpClient http;
	
	public TopHatGet(HttpClient http, String url)
	{	
		this.http = http;
		get = new HttpGet(url);
	}
	
	public String execute() throws ParseException, ClientProtocolException, HttpException, IOException
	{
		return this.process(http.execute(get));
	}
}
