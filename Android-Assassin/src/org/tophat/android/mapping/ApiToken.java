package org.tophat.android.mapping;

import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

public class ApiToken implements Parcelable {
	
	private String apitoken;
	
	public ApiToken()
	{
		this.apitoken = null;
	}
	
	public ApiToken(Map<String, Object> apitoken)
	{
		if(apitoken.containsKey("apitoken"))
		{
			this.apitoken = (String) apitoken.get("apitoken");
		}
	}

	// Parcelling part
	public ApiToken(Parcel in){
		this.setApitoken(in.readString());
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.getApitoken());
	}
	
    /**
	 * @return the apitoken
	 */
	public String getApitoken() {
		return apitoken;
	}

	/**
	 * @param apitoken the apitoken to set
	 */
	public void setApitoken(String apitoken) {
		this.apitoken = apitoken;
	}

	/**
    *
    * This field is needed for Android to be able to
    * create new objects, individually or as arrays.
    *
    * This also means that you can use use the default
    * constructor to create the object and use another
    * method to hyrdate it as necessary.
    *
    * I just find it easier to use the constructor.
    * It makes sense for the way my brain thinks ;-)
    *
    */
   public static final Parcelable.Creator CREATOR =
   	new Parcelable.Creator() {
           public ApiToken createFromParcel(Parcel in) {
               return new ApiToken(in);
           }

           public ApiToken[] newArray(int size) {
               return new ApiToken[size];
           }
       };
}
