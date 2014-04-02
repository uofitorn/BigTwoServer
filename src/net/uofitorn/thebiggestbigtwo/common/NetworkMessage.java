package net.uofitorn.thebiggestbigtwo.common;

import java.io.Serializable;

public class NetworkMessage implements Serializable {
	private static final String TAG = "NetworkMessage";
	
	String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NetworkMessage(String message) {
		this.message = message;
	}
}
