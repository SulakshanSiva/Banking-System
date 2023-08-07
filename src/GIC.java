import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author William, Hussain, Sulakshan
 *  Date: April 2021
 *  Description: Class for GIC type account. Contains account where user receives 
 *  a penalty if they withdraw from this account before a certain date
 *  Account also contains an interest rate where user gets interest 
 *  every time they login
 *  
 *  Method List: 
 *  public GIC() - default GIC account constructor
 *  public GIC(Customer client) -  overloaded constructor taking in customer object 
 *  public boolean withdraw(double amount) - overloaded withdraw method that includes a penalty
 *  public void add interest() - deposits interest into balance
 *  getInterestRate, setInterestRate, getPenalty, setPenalty, getMatureDate - getters and setters for respective instance variables
 *  public void processGIC(String account) - collect data from string file
 *  public String toString() - convert GIC account to string file
 *  public static void main(String[] args) - self testing main
 */

public class GIC extends Account {

	// declare instance variables
	private Date investDate;
	private Date matureDate;
	private Date currDate;
	private double interestRate;
	private double penalty;

	// default constructor
	public GIC() {
		super(); // call default parent class constructor
		this.investDate = new Date();
		// set maturity date to 7 days after date of creation (invest date)
		this.matureDate = new Date(this.investDate.getYear(), this.investDate.getMonth(),
				this.investDate.getDate() + 7);
		this.currDate = new Date();
		this.interestRate = 0.05;
		this.penalty = 0.20;
	}

	// overloaded constructor
	public GIC(Customer client) {
		super(client); // call overloaded parent class constructor
		this.investDate = new Date();
		this.matureDate = new Date(this.investDate.getYear(), this.investDate.getMonth(),
				this.investDate.getDate() + 7);
		this.currDate = new Date();
		this.interestRate = 0.05;
		this.penalty = 0.20;
	}

	/**
	 * withdraw method for GIC class
	 */
	public boolean withdraw(double amount) {

		// update current date
		currDate = new Date();

		// if current date is before the maturity date
		if (currDate.before(matureDate)) {

			// call parent withdraw method with interest rate and penalty
			if (super.withdraw(amount + amount * penalty)) {
				return true; // return true if successful
			}

			else
				return false; // return false if unsuccessful

		}

		// call parent withdraw method with amount plus the interest rate
		if (super.withdraw(amount)) {
			return true; // return true if successful
		}

		else
			return false; // return false if unsuccessful

	}

	/**
	 * Method to get interest and deposit it into balance
	 */
	public void addInterest() {
		this.deposit(this.getBalance() * this.getInterestRate());
	}

	/**
	 * @return the interestRate
	 */
	public double getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the penalty
	 */
	public double getPenalty() {
		return penalty;
	}

	/**
	 * @param penalty the penalty to set
	 */
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	/**
	 * @return the matureDate
	 */
	public Date getMatureDate() {
		return matureDate;
	}

	/**
	 * Method to process string with GIC account info
	 */
	public void processGIC(String account) {
		// split input at |
		String info[] = account.split("\\|");

		// load info into private data
		this.setAccNum(info[0]);
		this.deposit(Double.parseDouble(info[1]));
		try {
			this.investDate = (new SimpleDateFormat("dd/MM/yyyy").parse(info[2]));
			this.matureDate = (new SimpleDateFormat("dd/MM/yyyy").parse(info[3]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setInterestRate(Double.parseDouble(info[4]));
		this.setPenalty(Double.parseDouble(info[5]));

	}

	/**
	 * Method to convert a string
	 */
	public String toString() {
		// create date formatter variable to format dates
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

		// return GIC account info in a string
		return this.getAccNum() + "|" + this.getBalance() + "|" + f.format(this.investDate) + "|"
		+ f.format(this.matureDate) + "|" + this.getInterestRate() + "|" + this.getPenalty();
	}

	/**
	 * self - testing main
	 */
	public static void main(String[] args) {

		// make default GIC account class
		GIC g1 = new GIC();

		// set mature date to 7 days after the invest date
		g1.matureDate.setDate(g1.investDate.getDate() + 7);

		// set interest rate to 5%
		g1.setInterestRate(0.05);

		// deposit to account
		g1.deposit(5000);

		// add interest to account
		g1.addInterest();

		// see how much is in account with toString method
		System.out.println(g1.toString());

		// withdraw from account (should withdraw with penalty as current date is before
		// maturity date)
		g1.withdraw(1500);

		// see how much is in account after withdrawal
		System.out.println(g1.toString());

		// make customer object to be used in overloaded account constructor
		Customer person = new Customer("Hussain", "Muhammed", "321 Caker Plane", "123-456-7890", "asd897", "23klj");
		GIC g2 = new GIC(person);

		// set mature date to a date before the current one so a withdraw without the
		// penalty is called
		g2.matureDate.setDate(g2.currDate.getDate() - 2);

		// set interest rate to 5%
		g2.setInterestRate(0.05);

		// deposit to account
		g2.deposit(5000);

		// add interest to account
		g2.addInterest();

		// see how much is in account with toString method
		System.out.println(g2.toString());

		// withdraw from account (should withdraw with penalty as current date is before
		// maturity date)
		g2.withdraw(1500);

		// see how much is in account after withdrawal
		System.out.println(g2.toString());
	}
}
