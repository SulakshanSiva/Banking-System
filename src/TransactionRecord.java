/**
 * @author Sulakshan, Hussain, William
 * Date: April  2021 
 * Description: Class to create a record based on a customers transaction.
 * 
 * Method List: 
 * public TransactionRecord() - default constructor  
 * public TransactionRecord(String record) - overloaded constructor that calls process record method to get record
 * public void processTranRecord(String record) - processes a transaction record from a string
 * public String toString() - converts  transaction record object to string variable 
 * getAccType, setAccType, getTranType, setTranType, getAmount, setAmount, getStartBal, setStratBal, getEndBal, setEndBal - getters and setters for instance variables
 * public static void main(String[] args) - self testing main 
 */

public class TransactionRecord {
	// declare and initialize instance variables
	private char accType;
	private String tranType;
	private double amount, startBal, endBal;

	/**
	 * Default constructor
	 */
	public TransactionRecord() {
		// initialize variables
		this.accType = 'g';
		this.tranType = "deposit";
		this.amount = 0;
		this.startBal = 0;
		this.endBal = 0;
	}

	/**
	 * Overloaded constructor
	 */
	public TransactionRecord(String record) {

		// call method to convert string into record
		this.processTranRecord(record);
	}

	/**
	 * Method to process a string into a transaction record
	 * 
	 * @param record
	 */
	public void processTranRecord(String record) {
		// initialize array
		// split string at /
		// save individual strings in array
		String info[];
		info = record.split("/");
		// initialize variables from array contents
		this.accType = Character.toLowerCase(info[0].charAt(0));
		this.tranType = info[1];
		this.amount = Double.parseDouble(info[2]);
		this.startBal = Double.parseDouble(info[3]);
		this.endBal = Double.parseDouble(info[4]);

	}

	/**
	 * Method to convert an object into a string
	 */
	public String toString() {
		// initialize and declare a variable for account type
		String type = "";
		// check account type
		// depending on account type, set String to that account type
		switch (getAccType()) {
			case ('s'): {
				// set type to savings
				type = "Savings";
				break;
			}
			case ('g'): {
				// set type to GIC
				type = "GIC";
				break;
			}
			default: {
				// set type to unknown
				type = "Unknown";
				break;
			}
		}// end switch
		// return info of transactions record as string
		return type + "/" + getTranType() + "/" + getAmount() + "/" + getStartBal() + "/" + getEndBal();
	}

	/**
	 * @return the accType
	 */
	public char getAccType() {
		return accType;
	}

	/**
	 * @param accType the accType to set
	 */
	public void setAccType(char accType) {
		this.accType = accType;
	}

	/**
	 * @return the tranType
	 */
	public String getTranType() {
		return tranType;
	}

	/**
	 * @param tranType the tranType to set
	 */
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the startBal
	 */
	public double getStartBal() {
		return startBal;
	}

	/**
	 * @param startBal the startBal to set
	 */
	public void setStartBal(double startBal) {
		this.startBal = startBal;
	}

	/**
	 * @return the endBal
	 */
	public double getEndBal() {
		return endBal;
	}

	/**
	 * @param endBal the endBal to set
	 */
	public void setEndBal(double endBal) {
		this.endBal = endBal;
	}

	public static void main(String[] args) {
		// create string records
		String input = "g/deposit/100/2100/2200";
		// test process record method and default constructor
		TransactionRecord tR = new TransactionRecord();
		tR.processTranRecord(input);
		System.out.println(tR.toString());
		// test and create overloaded object
		TransactionRecord rec = new TransactionRecord(input);
		System.out.println(rec.toString());
		// test setters and getters
		tR.setAccType('g');
		tR.setTranType("withdraw");
		tR.setAmount(500);
		tR.setStartBal(1500);
		tR.setEndBal(2000);
		// print record and its component
		System.out.println(tR.toString());
		System.out.println(tR.getAccType());
		System.out.println(tR.getTranType());
		System.out.println(tR.getAmount());
		System.out.println(tR.getStartBal());
		System.out.println(tR.getEndBal());
		// test to string method
		tR.setAccType('s');
		System.out.println(tR.toString());
		tR.setAccType('g');
		System.out.println(tR.toString());
		tR.setAccType('x');
		System.out.println(tR.toString());

	}
}
