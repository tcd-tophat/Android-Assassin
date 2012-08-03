package org.tophat.android.exceptions;

public class BadRequest extends HttpException {

	private static String ERROR_MESSAGE = "Server is unresponsive";
	
	public BadRequest()
	{
		super(ERROR_MESSAGE, 403);
	}
	
	public BadRequest(String json)
	{
		super(ERROR_MESSAGE, json);
	}
}
