package org.tophat.assassin.model;

import android.os.AsyncTask;

public class CommandModel {
	
	public CommandModel()
	{
		
	}
	
	public void processCommand(String command )
	{
		
	}
	
	 private class CommandProcessTask extends AsyncTask<String, Integer, Long> {
	     protected Long doInBackground(String... urls) {
			return null;
	         
	    	 /**
	    	  * COMMAND RESPONSE PROCESSING.
	    	  */
	     }

	     protected void onProgressUpdate(Integer... progress) {
	         //setProgressPercent(progress[0]);
	     }

	     protected void onPostExecute(Long result) {
	         //showDialog("Downloaded " + result + " bytes");
	     }
	 }
	 
}
