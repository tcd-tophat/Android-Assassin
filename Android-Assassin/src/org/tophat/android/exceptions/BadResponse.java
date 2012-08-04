package org.tophat.android.exceptions;

public class BadResponse extends HttpException {

	private static String ERROR_MESSAGE = "Server returned bad data.";
	
	public BadResponse()
	{
		super(ERROR_MESSAGE, 510);
	}
	
	public BadResponse(String json)
	{
		super(ERROR_MESSAGE, json);
	}
}
