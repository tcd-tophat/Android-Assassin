package org.tophat.android.exceptions;

public class NotFound extends HttpException {

	private static String ERROR_MESSAGE = "The requested resource was not found.";
	
	public NotFound()
	{
		super(ERROR_MESSAGE, 404);
	}
	
	public NotFound(String json)
	{
		super(ERROR_MESSAGE, json);
	}
}
