import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Sulakshan, William, Hussain 
 * Date: April 2021
 * Description: Class to create a list to store all customer transaction records. 
 * List can be written to a file and list can be created by reading from a file.
 * 
 * Method List: 
 * public TransactionList() - default constructor
 * public int getSize() - get current size of list
 * public boolean insert(TransactionRecord record) - insert a record to the list 
 * public boolean delete(TransactionRecord record) - delete a record from the list
 * public String toString() - convert the list to a string for output
 * public TransactionList readFile(String fileName) - read a file to collect the list from
 * public int binarySearch(double searchFor, int low, int high) - search for a record
 * public void quickSort(int low, int high) - sort the list in ascending order by transaction amount
 * public static void main(String[] args) - self testing main
 */

public class TransactionList {
	// declare and initialize instance variables
	private int size, maxSize;
	private TransactionRecord list[];

	/**
	 * Default constructor
	 */
	public TransactionList() {
		// initialize variables
		this.size = 0;
		this.maxSize = 25;
		this.list = new TransactionRecord[maxSize];
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Method to insert a record into a list
	 * 
	 * @param record
	 * @return
	 */
	public boolean insert(TransactionRecord record) {
		// check if size is below the max
		if (this.size < this.maxSize) {
			// add 1 to the size
			this.size++;
			// add the record to list
			list[this.size - 1] = record;
			// return true to the main method
			return true;
		}
		// return false to the main method if not successful
		return false;
	}

	/**
	 * Method to delete record from list Uses binary search to find record within
	 * list. If record is found, it replaces the record with the last record in the
	 * list and decreases the current size by 1 Returns true if successful
	 */
	public boolean delete(TransactionRecord record) {

		// sort the list
		quickSort(0, this.getSize() - 1);

		// have variable for location of record in list according to binary search
		int location = binarySearch(record.getAmount(), 0, this.getSize() - 1);

		// check if location is greater than 0
		if (location >= 0) {
			// replace this record with last valid record
			list[location] = list[size - 1];

			// decrease valid list by 1
			size--;

			// sort list again
			quickSort(0, this.getSize() - 1);

			return true;

		}

		// return false if record not found
		return false;
	}

	/**
	 * Method to convert to a string
	 */
	public String toString() {
		// declare and initialize variable
		String record = "";
		// loop to go through each record
		for (int i = 0; i < size; i++) {
			record += list[i].toString() + "\n";
		}
		// return string to main method
		return record;
	}

	/**
	 * Method to read from a file
	 */
	public TransactionList readFile(String fileName) throws IOException {
		// open the file for reading
		FileReader fr = new FileReader(fileName);
		BufferedReader input = new BufferedReader(fr);
		// declare variables for number of accounts
		int numRec = 0;
		String temp;
		// read first line
		temp = input.readLine();
		// count the number of accounts in the file
		while (temp != null) {
			// add 1 to the counter
			numRec++;
			// read the next line
			temp = input.readLine();
		}
		// close file
		input.close();
		fr.close();

		// open file for reading again
		input = new BufferedReader(new FileReader(fileName));
		TransactionList tL = new TransactionList();
		// loop through lines of file
		for (int i = 0; i < numRec - 1; i++) {
			// create record object
			TransactionRecord tR = new TransactionRecord();

			// use processTranRecord to process each line
			tR.processTranRecord(input.readLine());

			// insert eeach record to list
			tL.insert(tR);

		}
		// close file
		input.close();

		// return list to main method
		return tL;
	}

	/**
	 * Overloaded binary search Method to use binary search to find a record by its
	 * amount using recursion
	 */
	public int binarySearch(double searchFor, int low, int high) {

		// sort array by amount
		quickSort(0, this.getSize() - 1);
		// declare and initialize variables for middle index
		int middle;
		// if low is greater than high
		if (low > high) {
			// simple case
			// record not found
			return -1;
		} else {
			middle = (high + low) / 2;// dividing the array into 2
			// check if amount was found
			if (searchFor == (this.list[middle].getAmount())) {
				return middle;// found it
			} else if (searchFor < this.list[middle].getAmount()) {
				// ignore upper side of the array
				return binarySearch(searchFor, low, middle - 1);
			} else {
				// ignore lower side of the array
				return binarySearch(searchFor, middle + 1, high);
			}
		}
	}

	/**
	 * Quick sort method to sort the list by transaction amount
	 */
	public void quickSort(int low, int high) {

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

			// make integer and transaction record for pivot
			double pivot = list[low].getAmount();
			TransactionRecord pivotRecord = list[low];

			// while the left is less than right (while left is still to the left)
			while (left < right) {

				// if the current direction is moving left
				if (currentDirection == moveLeft) {

					// subtract from right (move right one left) if the right transaction is higher
					// than the
					// left transaction and the right and left don't overlap
					while (list[right].getAmount() >= pivot && left < right)
						right--;

					// make left transaction the right one
					list[left] = list[right];

					// turn the currentDirection to right
					currentDirection = moveRight;
				}

				// if the current direction is moving right
				if (currentDirection == moveRight) {

					// add to left (move left one right) if the left transaction is lower than the
					// right transaction and the left and right don't overlap
					while (list[left].getAmount() <= pivot && left < right)
						left++;

					// make right transaction the left one
					list[right] = list[left];

					// turn the currentDirection to left
					currentDirection = moveLeft;
				}
			}

			// set the transaction of the record to the pivot
			list[left] = pivotRecord; // this can also be list[right] because they're equal at this point

			// quicksort the sublists
			quickSort(low, left - 1);
			quickSort(right + 1, high);
		}

	}

	/**
	 * self - testing main
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// create list and transaction records
		TransactionList tL = new TransactionList();
		TransactionRecord f = new TransactionRecord();
		TransactionRecord u = new TransactionRecord();
		TransactionRecord m = new TransactionRecord();
		// create string records
		String inputOne = "g/deposit/100/2100/2200";
		String inputTwo = "s/withdraw/50/4100/4150";
		String inputThree = "g/withdraw/150/2000/1850";
		// process string into records
		f.processTranRecord(inputOne);
		u.processTranRecord(inputTwo);
		m.processTranRecord(inputThree);
		// test insert method
		tL.insert(f);
		System.out.println(tL.toString());
		tL.insert(u);
		System.out.println(tL.toString());
		tL.insert(m);
		System.out.println(tL.toString());
		// test binary search
		int location = tL.binarySearch(u.getAmount(), 0, tL.getSize());
		// check if location of record is found
		if (location < 0) {
			System.out.println("Record not found");

		} else {
			System.out.println("Record was found at " + location);
		}
		// test write file method
		AccountsList.writeToFile("test.txt", tL.toString());
		// test read file
		TransactionList mL = new TransactionList();
		mL = mL.readFile("test.txt");
		System.out.println(mL.toString());

		// test delete method.
		tL.delete(u);
		System.out.println(tL.toString());

	}
}
