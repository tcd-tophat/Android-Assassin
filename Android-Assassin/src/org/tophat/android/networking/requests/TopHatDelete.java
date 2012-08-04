package org.tophat.android.networking.requests;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.tophat.android.exceptions.HttpException;
import org.tophat.android.networking.ResponseProcessor;

public class TopHatDelete extends ResponseProcessor {

	private HttpDelete delete;
	private HttpClient http;
	
	public TopHatDelete(HttpClient http, String url)
	{	
		this.http = http;
		delete = new HttpDelete(url);
	}
	
	public String execute() throws ParseException, ClientProtocolException, HttpException, IOException
	{
		return this.process(http.execute(delete));
	}
}
