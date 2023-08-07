
/**
 * @author William, Hussain, Sulakshan
 * Date: April 2021
 * Desc: Savings account class which is the same as a normal account
 * except for the withdrawal which charges a fee when
 * withdrawing from an account with a balance of less than a minimum balance
 * 
 * Method List: 
 * public Savings() - defualt savings account object public
 * Savings(Customer client) - overloaded constructor that takes in owner as input 
 * public boolean withdraw(double amount) - overloaded withdraw method that charges a fee if acc balance is under 4000 
 * public double getMinBal - returns minimum balance for an account
 * public void setMinBal(double minBal) - setter for minimum balance
 * public double getFees() - returns the fees for a certain savings acc
 * public void setFees(double fees) - takes in a double and sets the fees for a certain acc equal to that input 
 * public String toString() - returns a string with information on a savings account
 * public void processRecord(String savingsRecord) - takes in a string and processes it as a savings record
 *  public static void main(String[] args) - self testing main
 */

public class Savings extends Account {

	// declare instance variables
	private double fees;
	private double minBal;

	// default constructor
	public Savings() {
		super(); // call default parent class constructor
		this.fees = 4.25; // initialize private data
		this.minBal = 4000;
	}

	// overloaded constructor
	public Savings(Customer client) {

		super(client); // call overloaded parent class constructor
		this.fees = 4.25;
		this.minBal = 4000;
	}

	// withdraw method for savings Account
	public boolean withdraw(double amount) {

		if (this.getBalance() >= minBal) { // balance equal to or over $4000

			if (super.withdraw(amount)) { // withdraw that amount without fees

				return true; // if withdraw successful

			} else {

				return false; // if withdraw failed
			}
		}

		// else if balance under $4000
		if (super.withdraw(amount + this.fees)) { // withdraw amount with fees added on

			return true; // if withdraw successful

		} else {

			return false; // if withdraw failed
		}
	}

	// getter for fees
	public double getMinBal() {
		// return fees to main method
		return this.minBal;
	}

	// setter for fees
	public void setMinBal(double minBal) {
		// set fees to fees passed in
		this.minBal = minBal;
	}

	// getter for fees
	public double getFees() {
		// return fees to main method
		return this.fees;
	}

	// setter for fees
	public void setFees(double fees) {
		// set fees to fees passed in
		this.fees = fees;
	}

	// to String method
	// Returns a string containing the information for a savings account
	public String toString() {

		// return account info in a string
		return this.getAccNum() + "/" + this.getBalance() + "/" + this.getFees() + "/" + this.getMinBal();

	}

	// Method to insert savings account info into an account
	public void processRecord(String savingsRecord) {
		// split input at /
		String smallerSections[] = savingsRecord.split("/");

		// call setters to set the savings account info from the array
		this.setAccNum(smallerSections[0]);
		this.deposit(Double.parseDouble(smallerSections[1]));
		this.setFees(Double.parseDouble(smallerSections[2]));
		this.setMinBal(Double.parseDouble(smallerSections[3]));

	}

	/**
	 * self - testing main
	 */
	public static void main(String[] args) {

		// create savings account and have owner as input
		Customer owner = new Customer("William", "Sran", "123 Baker Street", "123-456-789", "username", "password");
		Savings acc = new Savings(owner);

		acc.deposit(5000); // deposit 5000 dollars into acc
		System.out.println(acc.toString()); // print balance and acc number

		acc.withdraw(2000); // withdraw 2000 and print again
		System.out.println(acc.toString());

		acc.withdraw(2000); // withdraw 2000 and print again(should have fee charged)
		System.out.println(acc.toString());

		Savings norm = new Savings(); // create default savings acc and deposit 5000 and print info
		norm.deposit(1500);
		System.out.println(norm.toString());

		norm.setMinBal(1001);
		System.out.println(norm.getMinBal()); // test setters and getters for minimum balance

		norm.setFees(5); // set the fees for this savings acc to $1000
		System.out.println(norm.getFees()); // print fees

		norm.withdraw(500); // withdraw some money and print info
		System.out.println(norm.toString());

		norm.withdraw(500);
		System.out.println(norm.toString()); // withdraw to see if fees are charged

	}
}
