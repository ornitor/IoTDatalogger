 

public class CloudMQTT {
	
    static public String host = String.format("tcp://%s:%d", "url.mqtt.broker",8300); 
    static public String username = "Username";
    static public String password = "password";
    static public String clientId = "MQTT-Java-Example";
    static public String topic = "#";
	
	 

	static public void instance1()
	{
		    host = String.format("tcp://%s:%d", "url.mqtt.broker",8300); 
		    username = "username";
		    password = "password";
		    clientId = "MQTT-Java-Example";
		    topic = "#";
			
	}
	
	static public void instance2()
	{
		    host = String.format("tcp://%s:%d", "url.mqtt.broker",8300); 
		    username = "username";
		    password = "password";
		    clientId = "MQTT-Java-Example";
		    topic = "#";
				
	}

}
