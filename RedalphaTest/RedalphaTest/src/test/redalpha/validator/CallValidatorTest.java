package test.redalpha.validator;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import redalpha.dataobject.Call;
import redalpha.exception.CallValidationException;
import redalpha.validator.CallValidator;

public class CallValidatorTest {

	@Before
	public void before() {
		CallValidator.clearErrorList();
	}
	
	@Test
	public void testValidateFirstName_correctData() throws CallValidationException {
		
		String firstName = "CorrectFirstName";
		String lastName = "CorrectLastName";
		String time = "20:20:20";
		String phone = "+(375) 293 778 210";
		Call call = new Call(firstName, lastName, time, phone);
		List<String> errorList = CallValidator.validate(call);
		assertTrue(errorList.isEmpty());
	}
	
	@Test
	public void testValidateFirstName_bigFirstName() throws CallValidationException {
		String firstName = "TheFirstNameMoreThanFiftySymbol";
		String lastName = "CorrectLastName";
		String time = "20:20:20";
		String phone = "+(375) 293 778 210";
		Call call = new Call(firstName, lastName, time, phone);
		List<String> errorList = CallValidator.validate(call);
		assertEquals("The length of first name more than 30", errorList.get(0));
	}
	
	@Test
	public void testValidateFirstName_emptyFirstName() throws CallValidationException {
		String firstName = "";
		String lastName = "CorrectLastName";
		String time = "20:20:20";
		String phone = "+(375) 293 778 210";
		Call call = new Call(firstName, lastName, time, phone);
		List<String> errorList = CallValidator.validate(call);
		assertTrue(errorList.isEmpty());
	}
	
	@Test
	public void testValidateFirstName_emptyLastName() throws CallValidationException {
		String firstName = "CorrectFirstName";
		String lastName = "";
		String time = "20:20:20";
		String phone = "+(375) 293 778 210";
		Call call = new Call(firstName, lastName, time, phone);
		List<String> errorList = CallValidator.validate(call);
		assertEquals("The last name is mandatory field", errorList.get(0));
	}
	
	@Test
	public void testValidateFirstName_emptyPhone() throws CallValidationException {
		String firstName = "CorrectFirstName";
		String lastName = "CorrectLastName";
		String time = "20:20:20";
		String phone = "";
		Call call = new Call(firstName, lastName, time, phone);
		List<String> errorList = CallValidator.validate(call);
		assertEquals("The phone is mandatory field", errorList.get(0));
	}
	
	@Test
	public void testValidateFirstName_phoneFormat1() throws CallValidationException {
		String firstName = "CorrectFirstName";
		String lastName = "CorrectLastName";
		String time = "20:20:20";
		String phone = "+(420) 111 222 333";
		Call call = new Call(firstName, lastName, time, phone);
		CallValidator.validate(call);
		assertEquals("00420 111 222 333", call.getPhone());
	}
	
	@Test
	public void testValidateFirstName_phoneFormat2() throws CallValidationException {
		String firstName = "CorrectFirstName";
		String lastName = "CorrectLastName";
		String time = "20:20:20";
		String phone = "+(420)-111222333";
		Call call = new Call(firstName, lastName, time, phone);
		CallValidator.validate(call);
		assertEquals("00420 111 222 333", call.getPhone());
	}
	
	@Test
	public void testValidateFirstName_phoneFormat3() throws CallValidationException {
		String firstName = "CorrectFirstName";
		String lastName = "CorrectLastName";
		String time = "20:20:20";
		String phone = "+420111222333";
		Call call = new Call(firstName, lastName, time, phone);
		CallValidator.validate(call);
		assertEquals("00420 111 222 333", call.getPhone());
	}
	
	@Test
	public void testValidateFirstName_phoneFormat4() throws CallValidationException {
		String firstName = "CorrectFirstName";
		String lastName = "CorrectLastName";
		String time = "20:20:20";
		String phone = "00420111222333";
		Call call = new Call(firstName, lastName, time, phone);
		CallValidator.validate(call);
		assertEquals("00420 111 222 333", call.getPhone());
	}
	
	@Test
	public void testValidateFirstName_phoneFormat5() throws CallValidationException {
		String firstName = "CorrectFirstName";
		String lastName = "CorrectLastName";
		String time = "20:20:20";
		String phone = "(111) 222 (333)";
		Call call = new Call(firstName, lastName, time, phone);
		CallValidator.validate(call);
		assertEquals("00420 111 222 333", call.getPhone());
	}
	
	@Test
	public void testValidateFirstName_phoneFormat6() throws CallValidationException {
		String firstName = "CorrectFirstName";
		String lastName = "CorrectLastName";
		String time = "20:20:20";
		String phone = "123456789";
		Call call = new Call(firstName, lastName, time, phone);
		CallValidator.validate(call);
		assertEquals("00420 123 456 789", call.getPhone());
	}
	
	@Test
	public void testValidateFirstName_phoneOnlyOpenBracket() throws CallValidationException {
		String firstName = "CorrectFirstName";
		String lastName = "CorrectLastName";
		String time = "20:20:20";
		String phone = "(111 222 (333)";
		Call call = new Call(firstName, lastName, time, phone);
		List<String> errorList = CallValidator.validate(call);
		assertEquals("The phone has incorrect format", errorList.get(0));
	}
	
	@Test
	public void testValidateFirstName_uniformFormat() throws CallValidationException {
		String firstName = "CorrectFirstName";
		String lastName = "CorrectLastName";
		String time = "20:20:20";
		String phone = "+(375) 293 778 210";
		Call call = new Call(firstName, lastName, time, phone);
		CallValidator.validate(call);
		assertEquals("00375 293 778 210", call.getPhone());
	}
}
