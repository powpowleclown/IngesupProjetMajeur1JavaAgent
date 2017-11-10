
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.majeurProjet.metier.Rapport;

public class MulticastReceiver implements Runnable 
{
	Thread mythread;
	MulticastSocket socket;
    DatagramPacket inPacket;
    DatagramPacket outPacket;
    byte[] outBuf;
    final int PORT = 8888;
    byte[] inBuf = new byte[256];

	  public MulticastReceiver() 
	  {
			socket = null;
		    inPacket = null;
		    outPacket = null;
		    mythread = new Thread(this,"MulticastReceiver");
		    mythread.start();
	  }

	@Override
	public void run() {
		 
		    try {
		      //Prepare to join multicast group
		      socket = new MulticastSocket(8888);
		      InetAddress address = InetAddress.getByName("224.0.1.0");
		      socket.joinGroup(address);
		      while (true) {
		        inPacket = new DatagramPacket(inBuf, inBuf.length);
		        socket.receive(inPacket);
		        String inmsg = new String(inBuf, 0, inPacket.getLength());
		        String outmsg;
		        System.out.println("IN " + inmsg);
		        switch(inmsg.toUpperCase())
		        {
		        case "PING":
		        	outmsg = "PING-"+address.toString()+"-"+getMacAddress();
			        outBuf = outmsg.getBytes();
			        outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);
			        socket.send(outPacket);
		        	break;
		        case "DATA":
		        	ObjectMapper mapper = new ObjectMapper();
		        	Rapport rapport = new Rapport(address.toString().substring(1),Runtime.getRuntime());
		        	String jsonInString = mapper.writeValueAsString(rapport);
		        	outmsg = "DATA-"+jsonInString;
			        outBuf = outmsg.getBytes();
			        outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);
			        socket.send(outPacket);
		        	break;
		        default:
		        	System.out.println("WAIT");
		        	break;
		        }
		       
		      }
		    } 
		    catch (Exception ioe) 
		    {
		      System.out.println(ioe);
		    }
	}
	
	public static String getMacAddress(){
		InetAddress ip;
		StringBuilder sb = new StringBuilder();
		try {
			ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
			//sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e){
			e.printStackTrace();
		}
		return sb.toString();
	}
}


