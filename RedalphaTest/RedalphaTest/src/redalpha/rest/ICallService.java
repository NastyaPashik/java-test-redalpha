package redalpha.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * 
 * @author anastasia
 *
 */
public interface ICallService {
	@POST
	@Path("createCall")
	String createCall(
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("phone") String phone);
}
