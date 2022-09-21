import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
	//instance variable or global variable
	private double deposit;
	private double withdraw;
	private double totalBalance;
	private double transactionBalance;
	int choice, choice1, choice2;
	ArrayList<BankAccount> bankAccountList = new ArrayList<>(); // declaring a new object(bankAccountList) ArrayList which has a data type BankAccount
	
	//Constructors are used to initialize the variables
	public ATM() {
		this.deposit = 0.0;
		this.withdraw = 0.0;
		this.totalBalance = 0.0;
		this.transactionBalance = 0.0;
	}
	
	/* This method is taking in the input of the text file by the user. It is also reading the text file
	while also looping through each line to get the username, password, and transactions. Parsing each line
	and separating each word. Used a for loop so that we can get every transaction and put it into an arraylist. 
	Assigning the transaction names into keys and transaction prices into values. */
	public void readfile(String fileText) throws NumberFormatException, IOException {
		
		File file = new File (fileText); // This puts a file into a File variable. This File variable allows me to open the file. 
	
		BufferedReader br = new BufferedReader(new FileReader(file)); // Used the BufferedReader to read the file
	
		String readline; // declared a variable

		// Used a while loop to loop through the file and read each line at a time
		while((readline = br.readLine()) != null){ 
			
			String delims = "[ ]+"; // grabbing all the delimiter(spaces)
			String[] word = readline.split(delims); // parsing a string by using a split method and putting the line that was read into an array
			
			
			BankAccount acc = new BankAccount(); // Calling in BankAccount by creating a new object
			acc.setUsername(word[0]); // Using setters to update the username with an array element. Array = []
			acc.setPassword(word[1]); // Using setters to update the password with an array element. Array = []
			
			
			double totalBalance = Double.parseDouble((word[2]));
			acc.setTotalBalance(totalBalance);
			
			/* using for loop to loop through the array to get the transactions. start the index at 2 (which is where the transaction list starts); 
			looping through the array until the end of the array; implementing by 2 */
			for(int i = 3; i < word.length; i=i+2) {
				
				double transactionPrice = Double.parseDouble(word[i+1]); // parsing the numbers that had string data type into a double data type
				acc.addTransaction(word[i], transactionPrice);	// using the addTransaction method from BankAccount so it can be added to the transaction Map
			}
			
			//acc.getTransaction(); // getting the initial total balance before adding the transactions
			bankAccountList.add(acc); // add the bank account to the ArrayList bankAccountList  
			
		}
	}
	

	
	// created a new method to display the BankAccountList that includes the username, password, total balance, and transaction list.
	public void displayTransactions(BankAccount account) {
		
		// used a for loop to loop through all the bankAccountList to get every single information from every bank account that exists in the array 
		//for(int i=0; i < bankAccountList.size(); i++) {
			
			//BankAccount account1 = bankAccountList.get(i); // created a new variable to get each bank account that exists. if we wanted to get one only, it would be like bankAccountList.get(0). 
			//The data type is BankAccount. The variable is account1. The ArrayList is bankAccountList. To use a getter, use the get() method
			
			//System.out.println("Username: " + account1.getUsername()); // grabbed username from BankAccount class. ArrayList = ()
			//System.out.println("Password: " + account1.getPassword()); // grabbed password from BankAccount class. ArrayList = ()
			account.getTransaction(); //got the transaction list from BankAccount class
			//System.out.println("\nTotal Balance: $" + account.getTotalBalance()); // grabbed the total balance from the BankAccount class
			//System.out.println(" ");
			
	}
	
	
	/*To get the input of the user's username and password and check to see if their login matches with the
	information in the text file given. If it is incorrect, the computer would ask if they would like to
	try again or exit.*/
	public void login() {
		
		while(choice != 2) {
			Scanner scan = new Scanner(System.in);
			System.out.println("\nEnter username: ");
			String username = scan.nextLine();

			for(int i=0; i < bankAccountList.size(); i++) {
			
				BankAccount account1 = bankAccountList.get(i); 
			
				if(account1.getUsername().contains(username)){
					while(choice != 2) {
						System.out.println("\nEnter password: ");
						String password = scan.next();
					
						if(account1.getPassword().contains(password)) {
							System.out.println("\nLOGIN SUCCESSFUL !");
							
							account1.calculateTransaction();
							Double totalBalance = account1.getTotalBalance() + account1.getTransactionBalance();
							account1.setTotalBalance(totalBalance);
							//System.out.println("\nThis is the total balance in your account: $" + totalBalance);
							menu(account1);
							return;
						}
						else {
							System.out.println("\nPassword is incorrect.");
							System.out.println("\nWould you like to try again or exit?");
							System.out.println("\n1) Try again");
							System.out.println("2) Exit");
							System.out.println("\nEnter choice here: ");
							choice = scan.nextInt();	
						}
					}		
					System.out.println("\nThank you. Have a great day!"); 
					return;
				}
		}
		System.out.println("\nIncorrect username/password. Please try again or exit.");
		System.out.println("\nWould you like to try again or exit?");
		System.out.println("\n1) Try again");
		System.out.println("2) Exit");
		System.out.println("\nEnter choice here: ");
		choice1 = scan.nextInt();	
		}
	}
	
	/*This method is used to get new transactions from the user by asking for their input and to calculate 
	 the money that is being added to the transaction. Adding it to the transaction list. */
	public void depositMoney(BankAccount account) {
		Scanner scan = new Scanner(System.in);
			
			System.out.println("\nEnter deposit date (MM/DD/YYYY): ");
			String depositName = scan.next();
		
			System.out.println("\nEnter deposit amount: ");
			Double deposit = scan.nextDouble();
		
			account.addTransaction(depositName, deposit);
			System.out.println("\nDeposit Successful!");
		
			Double totalBalance = account.getTotalBalance() + deposit;
			account.setTotalBalance(totalBalance);
			
			System.out.println("\nThis is the total balance in your account: $" + totalBalance);
		
	}
	
	/*This method is used to subtract the money being used by the user and making sure to add it to the
	 transactions list. */
	public void withdrawMoney(BankAccount account) {
		
		System.out.println("\nThe initial balance of your account is $" + account.getTotalBalance());
		//account.getTransaction();
		//System.out.println("\nThis is the transaction amount in your account is $" + account.getTransactionBalance());
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\nEnter withdraw name: ");
		String withdrawName = scan.nextLine();
		
		System.out.println("\nEnter withdraw amount (put - before amount):");
		Double withdraw = scan.nextDouble();
		
		account.addTransaction(withdrawName, withdraw);
		System.out.println("\nWithdraw Successful!");
		
		//Double totalBalance = account.getTotalBalance() + account.getTransactionBalance();
		Double totalBalance = account.getTotalBalance() + withdraw;
		account.setTotalBalance(totalBalance);
		
		System.out.println("\nThis is the total balance in your account: $" + totalBalance);
		
	}
	
	/*This method is used to basically display the totalBalance and all the transactions given 
	 (old and new). */
	public void checkBalance(BankAccount account) {
		
		System.out.println("\nThe total balance of your account is $" + account.getTotalBalance());
		
		
	}
	
	
	
	/*This method is used to give different options for the users to pick from including depositing,
	 withdrawing, checking the balance or exiting.*/
	public void menu(BankAccount account) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\nPlease decide what you would like to do next.");
		System.out.println("\n1) Deposit");
		System.out.println("2) Withdraw");
		System.out.println("3) Transactions statements");
		System.out.println("4) Check Balance");
		System.out.println("5) Exit");
		System.out.println("\nEnter choice here: ");
		choice2 = sc.nextInt();
		
		while(choice2 != 5) {
			
			switch(choice2) {
				case 1: 
					depositMoney(account);
					break;
				case 2: 
					withdrawMoney(account);
					break;
				case 3: 
					displayTransactions(account);
					break;	
				case 4: 
					checkBalance(account);
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid input");
					break;
			}System.out.println("\nPlease decide what you would like to do next.");
			System.out.println("\n1) Deposit");
			System.out.println("2) Withdraw");
			System.out.println("3) Transactions statements");
			System.out.println("4) Check Balance");
			System.out.println("5) Exit");
			System.out.println("\nEnter choice here: ");
			choice2 = sc.nextInt();
		
		}System.out.println("\nThank you. Have a great day!"); 
		
	}
	
	
	
	
	
	
	
	
}
