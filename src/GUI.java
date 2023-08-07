import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Font;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * @author: Sulakshan, Hussain, William 
 * Date: April 12th, 2021 
 * Description: Class to create a bank application where user can create and interact with bank accounts.
 * 
 * Method List: 
 * public GUI() - default constructor for the user interface
 * public void actionPerformed (ActionEvent e) - executes certain action depending on if a button is pressed
 * public static double promptAmount(char type) - prompts user for a certain amount type depending on what button is pressed
 * public void itemStateChanged(ItemEvent e) - executes a certain action if an item state is changed such as a checkbox
 * public static void main(String[] args) - start banking program
 */

public class GUI extends JFrame implements ActionListener, ItemListener {
	// declare and initialize GUI components
	private JPanel logPanel, signUpPanel, accountPanel, employeeDisplayPanel, logControlPanel, controlPanel,
	employeeControlPanel;
	private JButton btnLogIn, btnSignUp, btnRegister, btnLogOut, btnWithdraw, btnDeposit, btnExit, btnEmpLogIn,
	btnLogoutEmployee, btnSaveTransactionHistory, btnDisplayFile, btnDelete, btnBack, btnMinBal, btnFees,
	btnPen, btnRate;
	private JTextField username, fName, lName, phoneNum, address, createUser, createPass;
	private JPasswordField password;
	private JLabel userLbl, passLbl, fNameLbl, lNameLbl, phoneNumLbl, addressLbl, createUserLbl, createPassLbl,
	instructionsLbl, employeeInstructionsLbl, titleLbl;
	private JTextArea accInfo, accEmployeeInfo;
	private JScrollPane scroller;
	// declare and initialize variables
	private int width, height, location;
	private String balInfo;
	private AccountsList aL;
	private TransactionList tL;
	private DecimalFormat moneyFormat;
	private JComboBox accChoose;
	private char accSelected;
	private JCheckBox reveal;
	private Font fontlabel, fontTitle;

	//default constructor for gui
	//look and feel of user interface
	public GUI() {
		super("SHW Bank");

		setLayout(null);
		// create account abject
		aL = new AccountsList();
		tL = new TransactionList();
		// set width and height of frame
		width = 900;
		height = 600;
		// read file and load account into list
		try {
			aL = aL.readFile("Accounts.txt");
			tL = tL.readFile("Transactions.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// default accSelected to g
		accSelected = 'g';

		// make account char array
		String accounts[] = { "GIC", "Savings" };

		// create combo box with list of accounts
		accChoose = new JComboBox(accounts);

		// create decimal format variable
		moneyFormat = new DecimalFormat("$0.00");

		// initialize panels
		logPanel = new JPanel(null);
		signUpPanel = new JPanel(null);
		accountPanel = new JPanel(null);
		employeeDisplayPanel = new JPanel(null);
		employeeControlPanel = new JPanel();
		logControlPanel = new JPanel();
		controlPanel = new JPanel();
		// initialize GUI components
		// buttons
		btnLogIn = new JButton("Log In");
		btnSignUp = new JButton("Sign Up");
		btnRegister = new JButton("Register");
		btnLogOut = new JButton("Log Out");
		btnWithdraw = new JButton("Withdraw");
		btnDeposit = new JButton("Deposit");
		btnEmpLogIn = new JButton("Employee Log In");
		btnExit = new JButton("Exit");
		btnSaveTransactionHistory = new JButton("Save Transactions");
		btnDisplayFile = new JButton("Display File Information");
		btnLogoutEmployee = new JButton("Log Out");
		btnDelete = new JButton("Delete");
		btnBack = new JButton("Back");
		btnMinBal = new JButton("Change Min Bal");
		btnFees = new JButton("Changes Savings Fees");
		btnPen = new JButton("Change Penalty");
		btnRate = new JButton("Change Interest Rate");
		// TextFields
		username = new JTextField("");
		password = new JPasswordField("");
		fName = new JTextField("");
		lName = new JTextField("");
		address = new JTextField("");
		phoneNum = new JTextField("");
		createUser = new JTextField("");
		createPass = new JTextField("");

		accInfo = new JTextArea("");
		accEmployeeInfo = new JTextArea("");
		// add scroller to text area
		scroller = new JScrollPane(accEmployeeInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// Labels
		userLbl = new JLabel("Username");
		passLbl = new JLabel("Password");
		fNameLbl = new JLabel("First Name");
		lNameLbl = new JLabel("Last Name");
		addressLbl = new JLabel("Address");
		phoneNumLbl = new JLabel("Phone Number");
		createUserLbl = new JLabel("Create a username");
		createPassLbl = new JLabel("Create a password");
		instructionsLbl = new JLabel(
				"<html>Welcome to your account! <br/> Here you can withdraw, deposit and check your account balance. <br/> Please log out when you are finished to save any account actions. </html>");
		employeeInstructionsLbl = new JLabel(
				"<html>Welcome employee! <br/> Click the \"Search User\" button to search for a user and displayed below is their transaction history.</html>");
		titleLbl = new JLabel("SHW Bank");
		reveal = new JCheckBox("Show Password");
		// set location of GUI components
		// panels
		logPanel.setBounds(0, 0, width, height - 100);
		signUpPanel.setBounds(0, 0, width, height - 100);
		accountPanel.setBounds(0, 0, width, height - 100);
		employeeDisplayPanel.setBounds(0, 0, width, height - 100);
		employeeControlPanel.setBounds(0, height - 100, width, height - 400);
		logControlPanel.setBounds(0, height - 100, width, height - 400);
		controlPanel.setBounds(0, height - 100, width, height - 400);

		// TextFields
		username.setBounds(350, 150, 200, 25);
		password.setBounds(350, 250, 200, 25);
		reveal.setBounds(550, 250, 200, 25);

		createUser.setBounds(350, 55, 200, 25);
		createPass.setBounds(350, 130, 200, 25);
		fName.setBounds(350, 205, 200, 25);
		lName.setBounds(350, 280, 200, 25);
		address.setBounds(350, 355, 200, 25);
		phoneNum.setBounds(350, 430, 200, 25);

		accInfo.setBounds(100, 150, 400, 200);
		scroller.setBounds(100, 150, 400, 200);
		accInfo.setBorder(new LineBorder(Color.BLACK));
		scroller.setBorder(new LineBorder(Color.BLACK));

		// Labels
		userLbl.setBounds(350, 100, 100, 25);
		passLbl.setBounds(350, 200, 100, 25);
		createUserLbl.setBounds(350, 0, 200, 75);
		createPassLbl.setBounds(350, 75, 200, 75);
		fNameLbl.setBounds(350, 150, 100, 75);
		lNameLbl.setBounds(350, 225, 100, 75);
		addressLbl.setBounds(350, 300, 100, 75);
		phoneNumLbl.setBounds(350, 375, 100, 75);
		instructionsLbl.setBounds(100, 50, 500, 100);
		employeeInstructionsLbl.setBounds(100, 50, 500, 100);
		titleLbl.setBounds(320, 25, 500, 100);

		// Combobox
		accChoose.setBounds(600, 150, 120, 25);

		// add GUI components to panel
		logPanel.add(username);
		logPanel.add(password);
		logPanel.add(reveal);
		logPanel.add(userLbl);
		logPanel.add(passLbl);
		logPanel.add(titleLbl);
		signUpPanel.add(createUser);
		signUpPanel.add(createPass);
		signUpPanel.add(createUserLbl);
		signUpPanel.add(createPassLbl);
		signUpPanel.add(fName);
		signUpPanel.add(lName);
		signUpPanel.add(address);
		signUpPanel.add(phoneNum);
		signUpPanel.add(fNameLbl);
		signUpPanel.add(lNameLbl);
		signUpPanel.add(addressLbl);
		signUpPanel.add(phoneNumLbl);
		accountPanel.add(accInfo);
		accountPanel.add(instructionsLbl);
		accountPanel.add(accChoose);
		logControlPanel.add(btnLogIn);
		logControlPanel.add(btnEmpLogIn);
		logControlPanel.add(btnSignUp);
		logControlPanel.add(btnRegister);
		logControlPanel.add(btnExit);
		logControlPanel.add(btnBack);

		controlPanel.add(btnDeposit);
		controlPanel.add(btnWithdraw);
		controlPanel.add(btnLogOut);

		employeeDisplayPanel.add(employeeInstructionsLbl);
		employeeDisplayPanel.add(scroller);

		employeeControlPanel.add(btnSaveTransactionHistory);
		employeeControlPanel.add(btnDisplayFile);
		employeeControlPanel.add(btnDelete);
		employeeControlPanel.add(btnFees);
		employeeControlPanel.add(btnMinBal);
		employeeControlPanel.add(btnRate);
		employeeControlPanel.add(btnPen);
		employeeControlPanel.add(btnLogoutEmployee);

		// manage fonts and colors
		fontlabel = new Font("Arial", Font.BOLD, 20);
		userLbl.setFont(fontlabel);
		passLbl.setFont(fontlabel);

		fontTitle = new Font("Arial", Font.BOLD, 50);
		titleLbl.setFont(fontTitle);
		titleLbl.setForeground(Color.BLUE);

		logPanel.setBackground(new java.awt.Color(163, 229, 106));
		accountPanel.setBackground(new java.awt.Color(163, 229, 106));
		reveal.setBackground(new java.awt.Color(163, 229, 106));
		employeeDisplayPanel.setBackground(new java.awt.Color(163, 229, 106));
		signUpPanel.setBackground(new java.awt.Color(163, 229, 106));

		logControlPanel.setBackground(new java.awt.Color(157, 208, 203));
		logControlPanel.setBackground(new java.awt.Color(157, 208, 203));
		employeeControlPanel.setBackground(new java.awt.Color(157, 208, 203));
		controlPanel.setBackground(new java.awt.Color(157, 208, 203));

		// add components to the frame
		add(logPanel);
		add(signUpPanel);
		add(accountPanel);
		add(employeeDisplayPanel);
		add(employeeControlPanel);
		add(logControlPanel);
		add(controlPanel);

		// make buttons active
		btnLogIn.addActionListener(this);
		btnSignUp.addActionListener(this);
		btnRegister.addActionListener(this);
		btnLogOut.addActionListener(this);
		btnWithdraw.addActionListener(this);
		btnDeposit.addActionListener(this);
		btnEmpLogIn.addActionListener(this);
		btnExit.addActionListener(this);
		btnSaveTransactionHistory.addActionListener(this);
		btnDisplayFile.addActionListener(this);
		btnLogoutEmployee.addActionListener(this);
		btnDelete.addActionListener(this);
		btnBack.addActionListener(this);
		btnFees.addActionListener(this);
		btnMinBal.addActionListener(this);
		btnRate.addActionListener(this);
		btnPen.addActionListener(this);

		accChoose.addItemListener(this);
		reveal.addItemListener(this);

		// set GUI components to visible or not visible
		signUpPanel.setVisible(false);
		employeeDisplayPanel.setVisible(false);
		employeeControlPanel.setVisible(false);
		accountPanel.setVisible(false);
		controlPanel.setVisible(false);
		btnRegister.setVisible(false);
		btnBack.setVisible(false);
		accInfo.setEditable(false);
		accEmployeeInfo.setEditable(false);
		
		// set size and location of the frame
		setSize(width, height);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Method to excecute a task if a button is clicked
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {

		// if exit button is clicked
		if (e.getSource() == btnExit) {
			// close program
			System.exit(0);
		}
		// if the Search user button is clicked
		if (e.getSource() == btnDelete) {
			// prompt user for records
			String record = JOptionPane.showInputDialog(null,
					"Please enter the transaction record that you would like to delete.()", "s/withdraw/500/4500/4000");
			// create and process record
			TransactionRecord tR = new TransactionRecord();
			tR.processTranRecord(record);

			// call method to delete record from list
			if (tL.delete(tR)) {
				// display message to user
				JOptionPane.showMessageDialog(null, "Deletion successful");
			} else {
				// display error to message
				JOptionPane.showMessageDialog(null, "Error, failed to find record");
			}

		}
		// if the Save Transaction History button is clicked
		if (e.getSource() == btnSaveTransactionHistory) {
			// prompt user for filename
			String fileName = JOptionPane.showInputDialog(null,
					"Enter the name of the file that you would like to save to");

			try {
				// save list to file
				AccountsList.writeToFile(fileName, tL.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// display message to user
			JOptionPane.showMessageDialog(null, "File saved.");
		}
		// if the display file button is pressed
		if (e.getSource() == btnDisplayFile) {
			// prompt user for file name
			String fileName = JOptionPane.showInputDialog(null, "Enter the name of that you would like to read from",
					"Transactions.txt");

			// read filename
			try {
				tL.readFile(fileName);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// sort list by amount
			tL.quickSort(0, tL.getSize() - 1);

			accEmployeeInfo.setText(tL.toString());
		}
		// if employee log in button is clicked
		if (e.getSource() == btnEmpLogIn) {
			// check if employee username and password is correct
			if (username.getText().equals("admin1") && password.getText().equals("app")) {

				// update list
				try {
					aL = aL.readFile("Accounts.txt");
					tL = tL.readFile("Transactions.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// switch panels to display
				logPanel.setVisible(false);
				employeeDisplayPanel.setVisible(true);
				logControlPanel.setVisible(false);
				employeeControlPanel.setVisible(true);
				accountPanel.setVisible(false);

			} else {
				JOptionPane.showMessageDialog(null, "Incorrect username or password");
			}

		}

		// if log in button is clicked
		if (e.getSource() == btnLogIn) {
			// reset location to invalid place
			location = -1;
			try {
				// see if account exists
				// search for username and password and get location
				location = aL.binarySearch(aL.getGICList(), username.getText() + "/" + password.getText(), 0,
						aL.getSize() - 1);
			} catch (NullPointerException e1) {
			} catch (ArrayIndexOutOfBoundsException e2) {
			}

			// if location is not found
			if (location < 0) {
				// display error message to user
				JOptionPane.showMessageDialog(null, "Error. Username or password not found.");
			}

			else {

				// update list
				try {
					aL = aL.readFile("Accounts.txt");
					tL = tL.readFile("Transactions.txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// display message to user
				JOptionPane.showMessageDialog(null,
						"Log in successsful! Welcome " + aL.getGICList()[location].getCustomer().getFirstName());

				// add interest to account
				aL.getGICList()[location].addInterest();
				// set textfield to display customer info
				balInfo = "GIC Balance: " + moneyFormat.format(aL.getGICList()[location].getBalance())
				+ "\nSavings Balance: " + moneyFormat.format(aL.getSaveList()[location].getBalance());
				accInfo.setText(balInfo);

				// switch panels to display
				logPanel.setVisible(false);
				accountPanel.setVisible(true);
				logControlPanel.setVisible(false);
				controlPanel.setVisible(true);
			}
		}
		// if sign up button is clicked
		if (e.getSource() == btnSignUp) {
			// switch panels to display
			logPanel.setVisible(false);
			signUpPanel.setVisible(true);
			
			// display register button only
			btnLogIn.setVisible(false);
			btnSignUp.setVisible(false);
			btnEmpLogIn.setVisible(false);
			btnBack.setVisible(true);
			btnRegister.setVisible(true);

		}
		// if back button is clicked
		if (e.getSource() == btnBack) {
			// switch panels to display
			signUpPanel.setVisible(false);
			logPanel.setVisible(true);

			// switch buttons to display
			btnLogIn.setVisible(true);
			btnSignUp.setVisible(true);
			btnEmpLogIn.setVisible(true);
			btnBack.setVisible(false);
			btnRegister.setVisible(false);

			// clear textfields
			fName.setText("");
			lName.setText("");
			address.setText("");
			phoneNum.setText("");
			createUser.setText("");
			createPass.setText("");

		}
		// if register button is clicked
		if (e.getSource() == btnRegister) {
			// declare variables
			boolean createAcc;
			// create customer object using user inputted info
			Customer owner = new Customer(fName.getText(), lName.getText(), address.getText(), phoneNum.getText(),
					createUser.getText(), createPass.getText());
			// create accounts
			GIC gAcc = new GIC(owner);
			Savings sAcc = new Savings(owner);
			// insert account into account list
			createAcc = aL.insert(sAcc, gAcc);
			// if account has been createed
			if (createAcc == true) {
				// display message to user
				JOptionPane.showMessageDialog(null, "Account has been created.");

				try {
					// sort list by username
					aL.quickSort(0, aL.getSize() - 1, aL.getGICList());
					aL.quickSort(0, aL.getSize() - 1, aL.getSaveList());
					// update and save list contents to file
					// write to file
					AccountsList.writeToFile("Accounts.txt", aL.toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// clear textfields
				fName.setText("");
				lName.setText("");
				address.setText("");
				phoneNum.setText("");
				createUser.setText("");
				createPass.setText("");

				// switch panels to display
				signUpPanel.setVisible(false);
				logPanel.setVisible(true);

				// switch buttons to display
				btnRegister.setVisible(false);
				btnLogIn.setVisible(true);
				btnSignUp.setVisible(true);
				btnBack.setVisible(false);
				btnEmpLogIn.setVisible(true);

			} else {
				// display error message to user
				JOptionPane.showMessageDialog(null, "Error. Account has not been created.");

				// clear textfields
				fName.setText("");
				lName.setText("");
				address.setText("");
				phoneNum.setText("");
				createUser.setText("");
				createPass.setText("");
			}

		}
		if (e.getSource() == btnLogoutEmployee) {
			// update and save list contents to file
			try {
				AccountsList.writeToFile("Accounts.txt", aL.toString());
				AccountsList.writeToFile("Transactions.txt", tL.toString());

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// clear log in text boxes
			username.setText("");
			password.setText("");
			accEmployeeInfo.setText("");
			
			// switch panels to display
			employeeControlPanel.setVisible(false);
			logPanel.setVisible(true);
			employeeDisplayPanel.setVisible(false);
			logControlPanel.setVisible(true);
			
		}
		// if log out button is clicked
		if (e.getSource() == btnLogOut) {
			// reset location
			location = -1;
			try {
				// sort lists by username
				aL.quickSort(0, aL.getSize() - 1, aL.getGICList());
				aL.quickSort(0, aL.getSize() - 1, aL.getSaveList());
				tL.quickSort(0, tL.getSize() - 1);
				// update and save list contents to file
				AccountsList.writeToFile("Accounts.txt", aL.toString());
				// update and save transaction list to file
				AccountsList.writeToFile("Transactions.txt", tL.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// clear log in text boxs
			username.setText("");
			password.setText("");
			// switch panels to display
			accountPanel.setVisible(false);
			logPanel.setVisible(true);
			controlPanel.setVisible(false);
			logControlPanel.setVisible(true);

		}
		// if withdraw out button is clicked
		if (e.getSource() == btnWithdraw) {

			// use switch case on the accSelected variable
			switch (accSelected) {
			case ('g'): {

				// prompt for money using method
				double money = promptAmount('w');

				// make variable to get initial balance
				double initialBal = aL.getGICList()[location].getBalance();

				// make current date variable to get current date
				Date currDate = new Date();

				// check if withdraw is possible
				if (aL.getGICList()[location].withdraw(money)) {

					// if withdraw is successful and current date is before maturity date
					if (currDate.before(aL.getGICList()[location].getMatureDate())) {

						JOptionPane.showMessageDialog(null, "Withdraw successful! Penalty included. An additional $"
								+ aL.getGICList()[location].getPenalty() * money + " was withdrawn.");
					}

					// if withdraw is successful and current date is after maturity date
					else {
						JOptionPane.showMessageDialog(null, "Withdraw successful! Penalty not included.");

					}
					// create record
					TransactionRecord tR = new TransactionRecord();

					// process transaction record
					tR.processTranRecord("GIC/withdraw/" + String.valueOf(money) + "/" + String.valueOf(initialBal)
					+ "/" + String.valueOf(aL.getGICList()[location].getBalance()));
					// insert transaction record into list
					tL.insert(tR);

					// update balance in TextArea
					balInfo = "GIC Balance: " + moneyFormat.format(aL.getGICList()[location].getBalance())
					+ "\nSavings Balance: " + moneyFormat.format(aL.getSaveList()[location].getBalance());

					accInfo.setText(balInfo);

				} // end if
				// amount must be higher than in account
				else
					JOptionPane.showMessageDialog(null, "Withdraw unsuccessful! Insufficient funds.");

				break;
			}
			case ('s'): {

				// prompt for money using method
				double money = promptAmount('w');

				// make variable to get initial balance
				double initialBal = aL.getSaveList()[location].getBalance();

				// variable to check if amount in bank is over or under 4000 before withdrawing
				boolean overMinBal = false;

				if (aL.getSaveList()[location].getBalance() >= aL.getSaveList()[location].getMinBal()) {

					overMinBal = true;
				}

				if (aL.getSaveList()[location].withdraw(money)) {

					// if withdraw is successful and balance is greater or equal to 4000
					if (overMinBal) {
						JOptionPane.showMessageDialog(null, "Withdraw successful! Fees not included.");
					}

					// if withdraw is successful and balance is less than 4000
					else {
						JOptionPane.showMessageDialog(null,
								"Withdraw successful! A $" + aL.getSaveList()[location].getFees() + " was included.");
					}
					// create transaction record
					TransactionRecord tR = new TransactionRecord();
					// process transaction record
					tR.processTranRecord("Savings/withdraw/" + String.valueOf(money) + "/" + String.valueOf(initialBal)
					+ "/" + String.valueOf(aL.getSaveList()[location].getBalance()));
					// insert record into list
					tL.insert(tR);

					// update balance in Textarea
					balInfo = "GIC Balance: " + moneyFormat.format(aL.getGICList()[location].getBalance())
					+ "\nSavings Balance: " + moneyFormat.format(aL.getSaveList()[location].getBalance());
					accInfo.setText(balInfo);

				} // end if
				else {

					JOptionPane.showMessageDialog(null, "Error. Withdrawing too much.");
				}

				break;
			}
			default: {
				JOptionPane.showMessageDialog(null, "Error. Invalid entree.");
				break;
			}
			}// end switch
		}

		// if deposit button is clicked
		if (e.getSource() == btnDeposit) {

			// use switch case on the accSelected char variable
			switch (accSelected) {
			case ('g'): {

				// prompt for money using method
				double money = promptAmount('d');

				// make variable to get initial balance
				double initialBal = aL.getGICList()[location].getBalance();

				// if input is negative
				if (money < 0) {
					JOptionPane.showMessageDialog(null, "Error. Please enter a valid amount.");
				} else {
					aL.getGICList()[location].deposit(money);

					// create transaction record
					TransactionRecord tR = new TransactionRecord();
					// process transaction record
					tR.processTranRecord("GIC/deposit/" + String.valueOf(money) + "/" + String.valueOf(initialBal) + "/"
							+ String.valueOf(aL.getGICList()[location].getBalance()));
					// insert record into list
					tL.insert(tR);

					// update balance for user in textarea
					balInfo = "GIC Balance: " + moneyFormat.format(aL.getGICList()[location].getBalance())
					+ "\nSavings Balance: " + moneyFormat.format(aL.getSaveList()[location].getBalance());
					accInfo.setText(balInfo);
				}

				break;
			}
			case ('s'): {

				// prompt for money using method
				double money = promptAmount('d');

				// make variable to get initial balance
				double initialBal = aL.getSaveList()[location].getBalance();

				// if input is negative
				if (money < 0) {
					JOptionPane.showMessageDialog(null, "Error. Please enter a valid amount.");
				} else {
					aL.getSaveList()[location].deposit(money);
					// create transaction record
					TransactionRecord tR = new TransactionRecord();
					// process transaction record
					tR.processTranRecord("Savings/deposit/" + String.valueOf(money) + "/" + String.valueOf(initialBal)
					+ "/" + String.valueOf(aL.getSaveList()[location].getBalance()));
					// insert record into list
					tL.insert(tR);
					// update balance for user in textarea
					balInfo = "GIC Balance: " + moneyFormat.format(aL.getGICList()[location].getBalance())
					+ "\nSavings Balance: " + moneyFormat.format(aL.getSaveList()[location].getBalance());

					accInfo.setText(balInfo);
				}

				break;
			}
			default: {
				JOptionPane.showMessageDialog(null, "Error. Invalid entree.");
				break;
			}
			}// end switch
		}

		// if change fees button is clicked
		if (e.getSource() == btnFees) {
			// employee can only change one specific customer's account
			// default location to william's account
			location = 0;
			Double newFees = promptAmount('f');
			aL.getSaveList()[location].setFees(newFees);
			JOptionPane.showMessageDialog(null, "The fees has been set to $" + aL.getSaveList()[location].getFees());
		}

		// if change minBal button is clicked
		if (e.getSource() == btnMinBal) {
			// employee can only change one specific customer's account
			// default location to william's account
			location = 0;
			Double newMinBal = promptAmount('b');
			aL.getSaveList()[location].setMinBal(newMinBal);
			JOptionPane.showMessageDialog(null,
					"The min balance has been set to $" + aL.getSaveList()[location].getMinBal());
		}

		// if change penalty button is clicked
		if (e.getSource() == btnPen) {
			// employee can only change one specific customer's account
			// default location to william's account
			location = 0;
			Double newPen = promptAmount('p');
			aL.getGICList()[location].setPenalty(newPen);
			JOptionPane.showMessageDialog(null,
					"The penalty has been set to " + aL.getGICList()[location].getPenalty() * 100 + "%");
		}

		// if change interest rate button is clicked
		if (e.getSource() == btnRate) {
			// employee can only change one specific customer's account
			// default location to william's account
			location = 0;
			Double newRate = promptAmount('r');
			aL.getGICList()[location].setInterestRate(newRate);
			JOptionPane.showMessageDialog(null,
					"The interest rate has been set to " + aL.getGICList()[location].getInterestRate() * 100 + "%");
		}

	}

	// method to prompt for a type of amount
	public static double promptAmount(char type) {
		double amount = 0;

		// make error checker
		boolean checker = false;

		// use do while to loop until valid entree
		do {
			// catch any invalid formats
			try {
				switch (type) {

				// if user wants to deposit
				case 'd': {
					// prompt for amount
					amount = Double.parseDouble(
							JOptionPane.showInputDialog(null, "Enter the amount you would like to deposit."));
					break;
				}
				// if user wants to withdraw
				case 'w': {
					// prompt for amount
					amount = Double.parseDouble(
							JOptionPane.showInputDialog(null, "Enter the amount you would like to withdraw."));
					break;
				}
				// if user wants to change fees
				case 'f': {
					// prompt for amount
					amount = Double.parseDouble(
							JOptionPane.showInputDialog(null, "What would you like to change the fees to?"));
					break;
				}
				// if user wants to change minimum balance
				case 'b': {
					// prompt for amount
					amount = Double.parseDouble(JOptionPane.showInputDialog(null,
							"What would you like to change the minimum balance for fees to be charged to?"));
					break;
				}
				// if user wants to change penalty
				case 'p': {
					// prompt for amount
					amount = Double.parseDouble(JOptionPane.showInputDialog(null,
							"What percent would you like to change penalty for the gic account to? (Enter Decimal Number)",
							"0.20"));
					break;
				}
				// if user wants to change interest rate
				case 'r': {
					// prompt for amount
					amount = Double.parseDouble(JOptionPane.showInputDialog(null,
							"What percent would you like to change the interest rate to? (Enter Decimal Number)",
							"0.05"));
					break;
				}
				// no default as one of the above cases will always be true
				// as cases are hardcoded
				}// end switch

				// error trap negative amounts
				if (amount < 0) {
					JOptionPane.showMessageDialog(null, "Error. Invalid entree.");
					checker = true;
				} else
					checker = false;

				//check for wrong entrees(ie. none double values)
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error. Invalid entree.");
				checker = true;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Error. Invalid entree.");
				checker = true;
			}
		} while (checker);

		return amount;
	}

	// method to listen to changes in item states such as check boxes
	public void itemStateChanged(ItemEvent e) {

		// if the checkbox is selected
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// reveal password
			password.setEchoChar((char) 0);
		} else {
			// hide password
			password.setEchoChar('•');
		}

		// if checkbox is changed
		if (e.getSource() == accChoose) {

			// if selected item is GIC
			if (String.valueOf(accChoose.getSelectedItem()).equals("GIC"))
				accSelected = 'g';
			else
				accSelected = 's';
		}

	}

	/**
	 * self - testing main
	 */
	public static void main(String[] args) {
		// create JFrame constructor
		GUI f = new GUI();

	}
}
