package redalpha.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class CallClient {
	public static void main(String[] args)  {
		try{
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8080/RedalphaTest/rest");
			 WebTarget call =
			 target.path("call").path("createCall").
			 	queryParam("firstName", "Anastasia").
			 	queryParam("lastName", "Pashik").
			 	queryParam("phone", "+(375) 294 889 321");
			 Response response = call.request().get();
			 String result = response.readEntity(String.class);
			 System.out.println(result);
			 response.close();
			 } catch (Exception ex) {
			 ex.printStackTrace();
			 }
    }
}
