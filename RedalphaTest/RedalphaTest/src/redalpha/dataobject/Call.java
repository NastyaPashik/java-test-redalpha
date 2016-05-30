package redalpha.dataobject;

/**
 * 
 * @author anastasia
 *
 * The main entity.
 */
public class Call {
	private String firstName;
	private String lastName;
	private String time;
	private String phone;
	
	public Call(String firstName, String lastName, String time, String phone) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setTime(time);
		this.setPhone(phone);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
