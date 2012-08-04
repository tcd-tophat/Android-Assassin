package org.tophat.android.networking.requests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicNameValuePair;
import org.tophat.android.exceptions.HttpException;
import org.tophat.android.networking.ResponseProcessor;

public class TopHatPut extends ResponseProcessor {
	
	private HttpPut put;
	private HttpClient http;
	
	public TopHatPut(HttpClient http, String url, String json) throws UnsupportedEncodingException
	{	
		this.http = http;
		put = new HttpPut(url);
		
        // Adding POST data
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2); 
        nameValuePairs.add(new BasicNameValuePair("data", json)); 
        put.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
	}
	
	public String execute() throws ParseException, ClientProtocolException, HttpException, IOException
	{
		return this.process(http.execute(put));
	}
}
