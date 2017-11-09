import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.provider.json.JSONProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.majeurProjet.metier.Computer;
import com.majeurProjet.metier.ComputerData;
import com.majeurProjet.metier.Role;

public class Agent {

	public static String ComputerIp;
	
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		ComputerIp = InetAddress.getLocalHost().getHostAddress();
		System.out.println(ComputerIp);
		Rapport rapport = new Rapport(Runtime.getRuntime());
		//computer.Affichage();
		Rapport(rapport);
	}
	
	public static void Rapport(Rapport rapport)
	{
		Client client = ClientBuilder.newBuilder().newClient();
		WebTarget target = client.target("http://localhost:8080/MajeurProjet/rest");
		
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
