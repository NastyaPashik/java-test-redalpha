package redalpha.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import redalpha.dataobject.Call;
import redalpha.exception.CallValidationException;

/**
 * 
 * @author anastasia
 *
 * Class for validate call attributes.
 */
public class CallValidator {
	private static final String PHONE_PATTERN = "^\\+?[-\\d\\(\\)\\s]*";
	private static List<String> errorList = new ArrayList<String>();
	
	public static List<String> validate(Call call) throws CallValidationException {
		
		validateFirstName(call.getFirstName());
		validateLastName(call.getLastName());
		String newPhone = validatePhone(call.getPhone());
		if(newPhone != "")
			call.setPhone(newPhone);
		return errorList;
	}
	
	/**
	 * Check length of first name field.
	 * @param name - first name
	 */
	private static void validateFirstName(String name) {
		if(name.length() > 30)
			errorList.add("The length of first name more than 30");
	}
	
	/**
	 * Check length and not null value.
	 * @param name - last name
	 */
	private static void validateLastName(String name) {
		if(name.isEmpty() || name == null)
			errorList.add("The last name is mandatory field");
		else if(name.length() > 30)
			errorList.add("The length of last name more than 30");
	}
	
	/**
	 * Validating phone number by specific pattern.
	 * @param phone - phone number 
	 * @return phone number or throw error
	 * @throws CallValidationException
	 */
	private static String validatePhone(String phone) throws CallValidationException {
		if(phone.isEmpty() || phone == null)
			errorList.add("The phone is mandatory field");
		
		else {
			long openBracketsCount = phone.chars().filter(ch -> ch =='(').count();
			long closeBracketsCount = phone.chars().filter(ch -> ch ==')').count();
			Pattern p = Pattern.compile(PHONE_PATTERN);
	        Matcher m = p.matcher(phone);
	        if(m.matches() && openBracketsCount == closeBracketsCount) {
	        	String newPhone = phone.replaceAll("[\\(\\)\\s\\+-]", "");
	        	if(newPhone.length() < 9 || newPhone.length() > 14)
	        		errorList.add("The phone incorrect length");
	        	else
	        	    return toUniformFormat(newPhone);
	        }
	        else 
	        	errorList.add("The phone has incorrect format");
			}
	    	
		return "";
	}

	/**
	 * Transformation phone to uniform format
	 * @param newPhone - phone number
	 * @return - number in uniform format
	 * @throws CallValidationException
	 */
	private static String toUniformFormat(String newPhone) throws CallValidationException{
		if(newPhone.length() == 9)
			return "00420 " + newPhone.substring(0, 3) + " " + newPhone.substring(3, 6) + " " + newPhone.substring(6);
		else if(newPhone.length() == 12) 
			return "00" + newPhone.substring(0, 3) + " " + newPhone.substring(3, 6) + " " + newPhone.substring(6, 9) + " " + newPhone.substring(9);
		else if(newPhone.length() == 14 && newPhone.startsWith("00"))
			return newPhone.substring(0, 5) + " " + newPhone.substring(5, 8) + " " + newPhone.substring(8, 11) + " " + newPhone.substring(11);
		throw new CallValidationException("Failed to convert a string to uniform format");
	}
	
	/**
	 * Needs for testing
	 */
	public static void clearErrorList() {
		errorList = new ArrayList<String>();
	}

}
