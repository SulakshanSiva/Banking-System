import java.text.NumberFormat;

/**
 * @author Sulakshan, William, Hussain 
 * Date: April 2021 
 * Description: Class to create a bank account and store account info relative to a customer
 * 
 * Method List: 
 * public Account() - default account object
 * public Account(Customer owner, int balance) - overloaded account object that takes in customer and balance
 * public static String createAccNum() - randomly generate account number
 * public void deposit(double amount) - takes in an amount and adds the amount to balance
 * public boolean withdraw(double amount) - takes in an amount and balance with amount unless balance is less than amount, returns true if possible and vice versa
 * public String toString() - returns string containing account information
 * public Customer getCustomer() - returns customer
 * public String getAccNum() - returns account number
 * public void setAccNum(String accNum) - setter for account number 
 * public double getBalance() - returns balance for an account
 * public static void main(String[] args) - self tesing main method 
 */
public class Account {
	// declare and initialize instance variables
	private double balance;
	private String accNum;
	private Customer owner;

	/**
	 * Default Constructor
	 */
	public Account() {
		this.balance = 0;
		this.accNum = "000000000000";
		this.owner = new Customer();
	}

	/**
	 * Overloaded Constructor
	 */
	public Account(Customer owner) {
		// initialize variables
		this.accNum = createAccNum();
		this.balance = 0;
		this.owner = owner;
	}

	/**
	 * Method to create an account number
	 */
	public static String createAccNum() {
		// declare and initialize variables
		String num = "";
		// randomize 12 digits
		for (int i = 1; i <= 12; i++) {
			// randomize a digit from 1 to 9
			// add the digit to the string
			num = num + String.valueOf((int) (Math.random() * 9));
		}
		// return the accout number to the main method
		return num;
	}

	/**
	 * Method to deposit money into your account
	 */
	public void deposit(double amount) {
		// deposit money in acc
		// add to balance
		this.balance = this.balance + amount;
	}

	/**
	 * Method to withdraw money from your account
	 */
	public boolean withdraw(double amount) {
		// if amount is greater than balance
		if (amount > this.balance) {
			// return false to main method
			return false;
		} else {
			// withdraw amount from balance
			this.balance = this.balance - amount;
			// return true to main method
			return true;
		}
	}

	/**
	 * Method to convert a string
	 */
	public String toString() {
		// currency format
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		// round to two decimal places
		nf.setMaximumFractionDigits(2);
		// return account info in a string
		return getAccNum() + "/" + nf.format(getBalance());
	}

	/**
	 * getter, get customer object
	 */
	public Customer getCustomer() {
		// return Customer object
		return owner;
	}

	/**
	 * getter, get account number
	 * 
	 * @return
	 */
	public String getAccNum() {
		// return account number to main method
		return accNum;
	}

	/**
	 * setter, set the account number
	 * 
	 * @param accNum
	 */
	public void setAccNum(String accNum) {
		// set account number
		this.accNum = accNum;
	}

	/**
	 * getter, get balance
	 * 
	 * @return
	 */
	public double getBalance() {
		// get balance
		return balance;
	}

	/**
	 * self - testing main
	 */
	public static void main(String[] args) {
		// test default account object
		Account aR = new Account();
		System.out.println(aR.toString());
		// test create account number method
		String num = createAccNum();
		System.out.println(num);
		// test overloaded account object
		Customer owner = new Customer("Sulakshan", "Siva", "123 Cookie Street", "123-456-789", "kei576", "234asjk");
		Account acc = new Account(owner);
		System.out.println(acc.toString());
		// test deposit and withdraw methods
		acc.deposit(100);
		System.out.println(acc.getBalance());
		acc.withdraw(110);
		System.out.println(acc.getBalance());
		acc.withdraw(50);
		System.out.println(acc.getBalance());
		// test setters and getters
		acc.setAccNum("123456789345");
		System.out.println(acc.getAccNum());
		System.out.println(acc.getBalance());
		System.out.println(acc.getCustomer().toString());
	}
}
