package org.tophat.android.exceptions;

public class Forbidden extends HttpException {

	private static String ERROR_MESSAGE = "Request is forbidden by TopHat server.";
	
	public Forbidden()
	{
		super(ERROR_MESSAGE, 403);
	}
	
	public Forbidden(String json)
	{
		super(ERROR_MESSAGE, json);
	}
}
