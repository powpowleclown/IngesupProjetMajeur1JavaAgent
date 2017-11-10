import java.net.UnknownHostException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.majeurProjet.metier.Computer;
import com.majeurProjet.metier.ComputerData;
import com.majeurProjet.metier.Rapport;

public class Agent {

	public static String ComputerIp;
	
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		MulticastReceiver broadcast = new MulticastReceiver();
		 while (true) 
		 {
			ComputerIp = "224.0.1.0";
			//ComputerIp = args[0];
			System.out.println(ComputerIp);
			Rapport rapport = new Rapport(ComputerIp, Runtime.getRuntime());
			//computer.Affichage();
			Rapport(rapport,ComputerIp);
			
			 try 
			 {
		          Thread.sleep(600000);
	        } 
			catch (InterruptedException ie) 
			{
	        }
		 }
	}
	
	public static void Rapport(com.majeurProjet.metier.Rapport rapport, String addrest)
	{
		Client client = ClientBuilder.newBuilder().newClient();
		WebTarget target = client.target("http://localhost:8080/MajeurProjet/rest");
		//WebTarget target = client.target("http://"+addrest+"/MajeurProjet/rest");
		target = target.path("computer_rest/by_ip/"+ComputerIp);
		Invocation.Builder builder = target.request();
		Response response = builder.get();	
		Computer computerGet = builder.get(Computer.class);
		
		System.out.println(computerGet);
		ComputerData computerdata = new ComputerData();
		computerdata.setProcessor(rapport.getAvailableProcessor());
		computerdata.setFreeMemory(rapport.getFreeMemory());
		computerdata.setMaxMemory(rapport.getMaxMemory());
		computerdata.setTotalMemory(rapport.getTotalMemory());
		computerdata.setComputer(computerGet);
		
		Client client2 = ClientBuilder.newBuilder().newClient();
		WebTarget target2 = client2.target("http://localhost:8080/MajeurProjet/rest");
		target2 = target2.path("computerdata_rest/");
		Invocation.Builder builder2 = target2.request();
		Response response2 = builder2.post(Entity.json(computerdata));

	}

}
