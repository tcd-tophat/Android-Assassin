package org.tophat.android.exceptions;

public class ServerUnresponsive extends HttpException {

	public ServerUnresponsive()
	{
		super("Server is unresponsive", 504);
	}
}
