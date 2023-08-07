import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Sulakshan, William, Hussain 
 * Date: April 14th, 2021 
 * Description: Class to create a list to store all customer info and bank account info.
 * List can be written to a file and list can be created by reading from a file.
 * 
 * Method List: 
 * public AccountsList() - default constructor for account list
 * public GIC[] getGICList() - returns GIC account list
 * public Savings[] getSaveList() - returns savings account list
 * public void setSize(int size) - setter for actual size of the array 
 * public int getSize() - returns the size 
 * public boolean insert(Savings saveAcc, GIC GICAcc) - takes in a savings and gic account and inserts them into a gic and savings account list 
 *                                                                                                    returns true if successful and vice versa
 * public String toString() - returns string containing GIC and Savings accoutn list info
 * public static void writeToFile(String fileName, String acc) - takes in a file name and string and writes that string to the file
 * public AccountsList readFile(String fileName) - reads from a file containing account information and returns an account list
 * public void quickSort(int low, int high, Account[] list) - sorts the list by username
 * public int binarySearch(Account[] list, String searchFor, int low, int high) - searchs for an account inside of account list
 * public static void main(String[] args) - self testing main method 
 */

public class AccountsList {
	// initialize and declare instance variables
	private GIC[] GICList;
	private Savings[] saveList;
	private int size, maxSize;

	/**
	 * Default Constructor
	 */
	public AccountsList() {
		// initialize variables
		this.maxSize = 10;
		this.size = 0;
		this.GICList = new GIC[maxSize];
		this.saveList = new Savings[maxSize];
	}

	/**
	 * @return the gICList
	 */
	public GIC[] getGICList() {
		return GICList;
	}

	/**
	 * @return the saveList
	 */
	public Savings[] getSaveList() {
		return saveList;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	public boolean insert(Savings saveAcc, GIC GICAcc) {
		// check size is below the max
		if (this.size < this.maxSize) {
			// add 1 to the size
			this.size++;
			// add the acc into the list
			GICList[this.size - 1] = GICAcc;
			saveList[this.size - 1] = saveAcc;
			// return true to the main method
			return true;
		}
		// return false to the main method if not successful
		return false;
	}

	/**
	 * Method to convert to a string
	 */
	public String toString() {
		// declare and initialize variable
		String list = "";
		// loop through list and add list info to string
		for (int i = 0; i < getSize(); i++) {
			list += GICList[i].getCustomer().toString() + "*" + GICList[i].toString() + "*" + saveList[i].toString()
					+ "\n";
		}
		// return string to main method
		return list;

	}

	/**
	 * Method to write to a file
	 */
	public static void writeToFile(String fileName, String acc) throws IOException {
		// open file to write to
		PrintWriter fw = new PrintWriter(new FileWriter(fileName));
		// print record into file
		fw.println(acc);
		// close file
		fw.close();
	}

	/**
	 * Method to read from a file
	 */
	public AccountsList readFile(String fileName) throws IOException {
		// open the file for reading
		FileReader fr = new FileReader(fileName);
		BufferedReader input = new BufferedReader(fr);
		// declare variables for number of accounts
		int numAcc = 0;
		String temp;
		// read first line
		temp = input.readLine();
		// count the number of accounts in the file
		while (temp != null) {
			// add 1 to the counter
			numAcc++;
			// read the next line
			temp = input.readLine();
		}
		// close file
		input.close();
		fr.close();
		// open file for reading again
		input = new BufferedReader(new FileReader(fileName));
		AccountsList aL = new AccountsList();

		// loop through lines of file
		for (int i = 0; i < numAcc - 1; i++) {
			// create customer and account objects
			Customer cus = new Customer();
			GIC g = new GIC(cus);
			Savings s = new Savings(cus);
			// store the contents of the file line
			String hold = input.readLine();
			// split the file line at * and store in array
			String info[] = hold.split("\\*");
			// call methods to process array contents and store in objects
			cus.processCustomer(info[0]);
			g.processGIC(info[1]);
			s.processRecord(info[2]);
			// insert the accounts in the list
			aL.insert(s, g);
		}
		// close the file
		input.close();

		// resets size of list
		setSize(numAcc);
		// return list to main method
		return aL;
	}

	/**
	 * Quick sort method to sort each list by username
	 */
	public void quickSort(int low, int high, Account[] list) {

		// integer variables for moving right
		int moveLeft = 0, moveRight = 1;

		// if low end is less than high of list
		if (low < high) {

			// declare left variable to be low end of list
			int left = low;

			// declare right variable to be high end of list
			int right = high;

			// declare current direction variable to be moving left
			int currentDirection = moveLeft;

			// make account object for pivot
			Account pivot = list[low];

			// while the left is less than right (while left is still to the left)
			while (left < right) {

				// if the current direction is moving left
				if (currentDirection == moveLeft) {

					// subtract from right (move right one left) if the right username is
					// lexicographically higher than the
					// left username and the right and left don't overlap
					while (list[right].getCustomer().getUser().compareToIgnoreCase(pivot.getCustomer().getUser()) > 0
							&& left < right)
						right--;

					// make left account the right one
					list[left] = list[right];

					// turn the currentDirection to right
					currentDirection = moveRight;
				}

				// if the current direction is moving right
				if (currentDirection == moveRight) {

					// add to left (move left one right) if the left username is lexicographically
					// lower than the
					// right username and the left and right don't overlap
					while (list[left].getCustomer().getUser().compareToIgnoreCase(pivot.getCustomer().getUser()) < 0
							&& left < right)
						left++;

					// make right account the left one
					list[right] = list[left];

					// turn the currentDirection to left
					currentDirection = moveLeft;
				}
			}

			// set the username of the account to the pivot
			list[left] = pivot; // this can also be list[right] because they're equal at this point

			// quicksort the sublists
			quickSort(low, left - 1, list);
			quickSort(right + 1, high, list);
		}
	}

	/**
	 * Binary search Method to use binary search to find an account by its user name
	 */
	public int binarySearch(Account[] list, String searchFor, int low, int high) {
		// seperate key into individual strings
		// save to array
		String search[] = searchFor.split("/");

		// sort array alphabetically using quicksort
		quickSort(0, getSize() - 1, list);

		// declare and initialize variables for middle index
		int middle;
		// if low is greater than high
		if (low > high) {
			// simple case
			// username not found
			return -1;
		} else {
			middle = (high + low) / 2;// dividing the array into 2
			// check if username and password was found
			if (search[0].equals(list[middle].getCustomer().getUser())
					&& search[1].equals(list[middle].getCustomer().getPass())) {
				return middle;// found it
			} else if (search[0].compareTo(list[middle].getCustomer().getUser()) < 0
					&& search[1].compareTo(list[middle].getCustomer().getPass()) < 0) {
				// ignore upper side of the array
				return binarySearch(list, searchFor, low, high = middle - 1);
			} else {
				// ignore lower side of the array
				return binarySearch(list, searchFor, low = middle + 1, high);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// declare and initialize list
		AccountsList aL = new AccountsList();
		// create customer object
		Customer owner = new Customer("William", "Sran", "123 Baker Street", "123-456-7890", "cba", "password");
		Customer owner2 = new Customer("Hussain", "Muhahaha", "123 Cookie Street", "987-654-3210", "abc", "password2");
		// create account objects
		GIC gicAcc = new GIC(owner);
		GIC gicAcc2 = new GIC(owner2);
		Savings savingsAcc = new Savings(owner);
		Savings savingsAcc2 = new Savings(owner2);
		// test insert method
		// insert accounts into list
		aL.insert(savingsAcc, gicAcc);
		aL.insert(savingsAcc2, gicAcc2);
		// test to string method
		System.out.println(aL.toString());
		// test quicksort
		aL.quickSort(0, aL.getSize() - 1, aL.GICList);
		aL.quickSort(0, aL.getSize() - 1, aL.saveList);
		System.out.println(aL.toString());
		// test binary search
		System.out.println(aL.binarySearch(aL.GICList, "cba/password", 0, aL.getSize() - 1));
		System.out.println(aL.binarySearch(aL.saveList, "abc/password2", 0, aL.getSize() - 1));
		System.out.println(aL.binarySearch(aL.GICList, "k/password", 0, aL.getSize() - 1));
		// test file writing
		// write the list to user inputted file
		String fileName = JOptionPane.showInputDialog(null, "enter file name to write to");
		writeToFile(fileName, aL.toString());
		// test file reading
		AccountsList b = new AccountsList();
		// read the list and write into list
		b = b.readFile(fileName);
		System.out.println(b.toString());
	}
}
