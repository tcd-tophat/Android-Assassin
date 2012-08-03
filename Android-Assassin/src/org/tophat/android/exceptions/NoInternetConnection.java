package org.tophat.android.exceptions;

public class NoInternetConnection extends HttpException {

	private static String ERROR_MESSAGE = "No internet connection";
	
	public NoInternetConnection()
	{
		super(ERROR_MESSAGE);
	}
}
