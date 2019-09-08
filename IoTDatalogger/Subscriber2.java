import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;
import java.io.IOException;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortPacketListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;
import java.io.*;


public class Subscriber2 implements MqttCallback {

    private final int qos = 1;
    private String topic = "test";
    private MqttClient client;
    
    static public String outpath = "/home/pi/datalogger";

    public double temp0;
    public double temp1;
    public double temp2;
    public double temp3;

    static ThermometerDemo1 panel = new ThermometerDemo1();

    public Subscriber2() throws MqttException 
    {
        String host = CloudMQTT.host;
        String username = CloudMQTT.username;
        String password = CloudMQTT.password;
        String clientId = CloudMQTT.clientId;
        topic = CloudMQTT.topic;//  "#";
        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);
        conOpt.setUserName(username);
        conOpt.setPassword(password.toCharArray());
        this.client = new MqttClient(host, clientId, new MemoryPersistence());
        this.client.setCallback(this);
        this.client.connect(conOpt);
        this.client.subscribe(this.topic, qos);
    }

    private String[] getAuth(URI uri) 
    {
        String a = uri.getAuthority();
        String[] first = a.split("@");
        return first[0].split(":");
    }

    public void sendMessage(String topic,String payload) throws MqttException 
    {
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(qos);
        this.client.publish(topic, message); 
    }

   
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost because: " + cause);
        System.exit(1);
    }


    public void deliveryComplete(IMqttDeliveryToken token) {
    }

 
    public void messageArrived(String topic, MqttMessage message) throws MqttException {
        System.out.println(String.format("%s: %s", topic, new String(message.getPayload())));
        if(topic.equals("temp0")){
            temp0 = Double.parseDouble(message.toString());
            panel.setValueAbs(0,temp0);
        } else  if(topic.equals("temp1")){
            temp1 = Double.parseDouble(message.toString());
            panel.setValueAbs(0,temp1);
        } else  if(topic.equals("temp2")){
            temp2 = Double.parseDouble(message.toString());
            panel.setValueAbs(0,temp2);
        } else  if(topic.equals("temp3")){
            temp3 = Double.parseDouble(message.toString());
            panel.setValueAbs(0,temp3);
        }

    }

    public static void main(final String[] args) 
    {
        final JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout(5, 5));
        frame.setDefaultCloseOperation(3);
        frame.setTitle("Thermometer Test");
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setSize(700, 400);
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getSize().width) / 2,
            (d.height - frame.getSize().height) / 2);
        frame.setVisible(true);
        panel.setValueAbs(0,33);
        panel.setValueAbs(1,34);
        panel.setValueAbs(2,35);
        panel.setMeterValueAbs(10);
        try{
            Subscriber2 s = new Subscriber2();
            CloudMQTT.instance1();
            s.sendMessage("atualiza","1");
            s.sendMessage("setp","0");
            for(int i=0;;i++){
                if( i%15 ==0) 
                    System.out.println("iteração " + i  + "   " +    getTemporalName("") );
                if( i%60 ==0) {
                    loop();
                }
                if( i%3600 ==0) {
                    s.sendMessage("atualiza","1");
                }
                if( i%3600 ==60) {
                    s.datalog();
                }
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch(MqttException err){}

    }

    public static void setup() 
    { 

    }

    public static void loop() {

    }

    public void datalog() {
        String textToAppend = getTemporalName("")+ "; " + temp0 +  "; " + temp1 +  "; " + temp2 +  "; " + temp3 ;
        try{
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(outpath + getTemporalName("").substring(0,10) + ".csv", true)	//Set true for append mode
                );	
            writer.newLine();	//Add new line
            writer.write(textToAppend);
            writer.close();

        } catch(IOException err){err.printStackTrace();}
    }

    static public String getTemporalName(String nome) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return nome+dateFormat.format(date);
    }

}