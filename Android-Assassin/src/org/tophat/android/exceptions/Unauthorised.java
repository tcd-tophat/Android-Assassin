package org.tophat.android.exceptions;

public class Unauthorised extends HttpException {

	private static String ERROR_MESSAGE = "The given request was performed without authorisation.";
	
	public Unauthorised()
	{
		super(ERROR_MESSAGE, 403);
	}
	
	public Unauthorised(String json)
	{
		super(ERROR_MESSAGE, json);
	}
}
