package org.tophat.android.exceptions;

import java.util.Map;

import org.tophat.android.networking.JsonParser;

public class HttpException extends Exception {

	public static String ERROR_CODE_KEY = "error_code";
	public static String ERROR_MSG_KEY = "error_message";
	
	private Map<String, Object> parsedError;
	private JsonParser parser;
	
	private Integer statusCode;
	private String statusMessage;
	
	public HttpException() 
	{}
	
	/*
	 * Constructor that accepts a message
	 */
	public HttpException(String message)
	{
		super(message);
	}

	/*
	 * Constructor that accepts a message
	 */
	public HttpException(String message, Integer statusCode)
	{
		super(message);
		
		this.statusMessage = message;
		this.statusCode = statusCode;
	}
	
	/*
	 * Constructor that accepts a message and a JSON error message
	 */
	public HttpException(String message, String json)
	{
		super(message);
		
		parser = new JsonParser();
		
		try
		{
			this.parsedError = parser.getObjects(json);
			
			if (parsedError.containsKey(ERROR_CODE_KEY))
			{
				this.statusCode = Integer.parseInt((String)parsedError.get(ERROR_CODE_KEY));
				this.statusMessage = (String)parsedError.get(ERROR_MSG_KEY);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Integer getStatusCode()
	{
		return this.statusCode;
	}
	
	public String getStatusMessage()
	{
		return this.statusMessage;
	}
}
