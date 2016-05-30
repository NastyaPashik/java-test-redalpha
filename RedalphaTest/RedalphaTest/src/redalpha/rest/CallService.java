package redalpha.rest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import redalpha.dataobject.Call;
import redalpha.dataobject.CallDAO;
import redalpha.dataobject.ICallDAO;
import redalpha.exception.CallValidationException;
import redalpha.validator.CallValidator;

/**
 * 
 * @author anastasia
 *
 * Service work with entity Call. Parameters firstName, lastName, phone - comes from client. Time - current time. 
 * All attributes are validated at @see redalpha.validator.CallValidator#validate
 * 
 * Send to client string "Call has been created" - if validation passed and string with list of errors if not.
 */
@Path("call")
public class CallService implements ICallService{

	@Override
	@POST
	@Path("createCall")
	public String createCall(
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("phone") String phone) {
		String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		Call call = new Call(firstName, lastName, time, phone);
		try {
		List<String> errorList = CallValidator.validate(call);
		if(errorList.isEmpty()) {
			ICallDAO callDAO = new CallDAO();
			callDAO.createCall(call);
			return "Call has been created";
		}
		else {
			return String.join("; ", errorList);
		}
		} catch (CallValidationException e) {
			return e.getMessage();
		}
	}

}
