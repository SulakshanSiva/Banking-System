
/**
 * @author Sulakshan, William, Hussain
 * Date: April 2021 
 * Description: Class to create customer object and store customer info of a bank account.
 * 
 * Method List: 
 * public Customer() - default customer object
 * public Customer(String firstName, String lastName, String address, String phoneNum) - overloaded customer object
 * public void processCustomer(String customerInfo) - takes in string containg customer info and processes it as a customer record
 * public String toString() - outputs customer info 
 * public String getPhoneNum() - returns customer phone number
 * public void setPhoneNum(String phoneNum) - takes in phone number and updates customer phone number 
 * public String getAddress() - returns customer address
 * public void setAddress(String address) - setter for customer address
 * public String getLastName() - returns customer last name
 * public void setLastName(String lastName) - setter for customer last name
 * public String getFirstName() - returns customer first name
 * public void setFirstName(String firstName) - setter for customer first name
 * public String getUser() public void setUser(String username) - returns customer username
 * public void setUser(String username) - setter for customer username
 * public String getPass() public void setPass(String password) - returns customer password
 * public void setPass(String password) - setter for customer password
 * public static void main(String[] args) - self testing main method
 */
public class Customer {
	// declare instance variables
	private String firstName, lastName, address, phoneNum, user, pass;

	/**
	 * Default constructor
	 */
	public Customer() {
		// set instance variables to empty string
		this.user = "";
		this.pass = "";
		this.firstName = "";
		this.lastName = "";
		this.address = "";
		this.phoneNum = "";
	}

	/**
	 * Overloaded Constructor
	 */
	public Customer(String firstName, String lastName, String address, String phoneNum, String username,
			String password) {
		// set the instance variables to user inputs
		this.user = username;
		this.pass = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNum = phoneNum;
	}

	/**
	 * Method to insert customer info into an account
	 */
	public void processCustomer(String customerInfo) {
		// split input at /
		String info[] = customerInfo.split("/");
		// call setters to set the customer info from the array

		this.setUser(info[0]);
		this.setPass(info[1]);
		this.setFirstName(info[2]);
		this.setLastName(info[3]);
		this.setAddress(info[4]);
		this.setPhoneNum(info[5]);
	}

	/**
	 * Method to convert a string
	 */
	public String toString() {
		// return customer info to main method
		return getUser() + "/" + getPass() + "/" + getFirstName() + "/" + getLastName() + "/" + getAddress() + "/"
				+ getPhoneNum();
	}

	/**
	 * getter, get phone number
	 * 
	 * @return
	 */
	public String getPhoneNum() {
		// return phone number to main method
		return phoneNum;
	}

	/**
	 * setter, set phone number
	 * 
	 * @param phoneNum
	 */
	public void setPhoneNum(String phoneNum) {
		// set phone number
		this.phoneNum = phoneNum;
	}

	/**
	 * getter, get address
	 * 
	 * @return
	 */
	public String getAddress() {
		// return address to main method
		return address;
	}

	/**
	 * setter, set the address
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		// set the address
		this.address = address;
	}

	/**
	 * getter, get last name
	 * 
	 * @return
	 */
	public String getLastName() {
		// return last name to main method
		return lastName;
	}

	/**
	 * setter, set the last name
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		// set the last name
		this.lastName = lastName;
	}

	/**
	 * getter, get the first name
	 * 
	 * @return
	 */
	public String getFirstName() {
		// return first name to main method
		return firstName;
	}

	/**
	 * setter, set the first name
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		// set the first name
		this.firstName = firstName;
	}

	/**
	 * getter, get username
	 * 
	 * @return
	 */
	public String getUser() {
		// return username to main maethod
		return user;
	}

	/**
	 * setter, set the username
	 * 
	 * @param username
	 */
	public void setUser(String username) {
		// set the username
		this.user = username;
	}

	/**
	 * getter, get the password
	 * 
	 * @return
	 */
	public String getPass() {
		// return password to main method
		return pass;
	}

	/**
	 * setter, set the pssword
	 * 
	 * @param password
	 */
	public void setPass(String password) {
		// set the passwrod
		this.pass = password;
	}

	/**
	 * self - testing main
	 */
	public static void main(String[] args) {
		// test defualt customer object
		Customer person = new Customer();
		// test to string method
		System.out.println(person.toString());
		// test overloaded customer object
		Customer owner = new Customer("Sulakshan", "Siva", "123 Cookie Street", "123-456-789", "kei576", "234asjk");
		System.out.println(owner.toString());
		// test setters and getters and toString
		owner.setFirstName("Bob");
		owner.setLastName("Mark");
		owner.setAddress("Candy 24 Street");
		owner.setPhoneNum("987-654-3210");
		owner.setUser("123okay");
		owner.setPass("no456");
		System.out.println(owner.getFirstName());
		System.out.println(owner.getLastName());
		System.out.println(owner.getAddress());
		System.out.println(owner.getPhoneNum());
		System.out.println(owner.getUser());
		System.out.println(owner.getPass());
		System.out.println(owner.toString());
		// test process method
		person.processCustomer(owner.toString());
		System.out.println(person.toString());
	}
}
