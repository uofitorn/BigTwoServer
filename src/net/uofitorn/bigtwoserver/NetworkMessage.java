package net.uofitorn.bigtwoserver;

public class NetworkMessage {
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
