import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class BankAccount {
	// instance variables or global variables
	private String username;
	private String password;
	private double transactionBalance;
	Map<String, Double> transaction = new HashMap<>(); // declaring a Map Class called transactions. The data types are String and Double.
	private Double totalBalance;
	
	//Constructor = initializing the variables
	public BankAccount() {
		this.username = "";
		this.password = "";
		this.totalBalance = 0.0;
		this.transactionBalance = 0.0;
		
	}
	
	/* This method is used to set(setter) the username.  */
	public void setUsername(String username) {
		this.username = username;
	}
	/* This method is used to get(getter) the username. */	
	public String getUsername() {
		return this.username;
	}
	/* This method is used to set(setter) the password. */
	public void setPassword(String password) {
		this.password = password;
	}
	/* This method is used to get(getter) the password. */
	public String getPassword() {
		return this.password;
	}
	
	public void setTransactionBalance(Double transactionBalance) {
		this.transactionBalance = transactionBalance;
	}
	
	/* This method is used to get(getter) the totalBalance. */
	public Double getTransactionBalance() {
		return this.transactionBalance;
	}
	
	
	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}
	
	// This method is used to get(getter) the totalBalance. 
	public Double getTotalBalance() {
		return this.totalBalance;
	}
	
	
	
	
	/* This method is used to add all the transactions into the Map. */
	public void addTransaction(String transactionName, Double transactionTotal) {
		
		transaction.put(transactionName,transactionTotal);  // adding the key and values into the transaction map
			
	}


	// This method gets the transaction. We want to check to see if it is empty or not. 
	// If it is empty, print "The map is empty
	// If it is not empty, print every transaction
	public void getTransaction() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\nEnter text file name: ");
		String newTextFile = scan.nextLine();
		
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(newTextFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    
	    
	    //printWriter.print
	    //printWriter.print(account.getTransaction());
	    //printWriter.println("\nTotal Balance: $" + account.getTotalBalance());
	    
		
		if(transaction.size()== 0) { // checking if the transaction map has any key or value then it will print no transactions if there is none.
			System.out.println("No transactions have been made.");
		}
		else {
			printWriter.printf("\n============= ATM ===============\n");
			printWriter.printf("=================================\n");
			printWriter.printf("\n  This is the transaction list.\n");
			
			for(Entry<String, Double> withdrawList : transaction.entrySet()) { // giving the map a new variable and looping through the entry.
				Double val= withdrawList.getValue(); // putting the values into a variable 
				String key = withdrawList.getKey(); // putting the keys into a variable

				printWriter.printf("\n%-25s $%.2f\n", key ,val); // printing out the key and values of all the transactions
				
			}printWriter.println("\n  Total Balance: $" + getTotalBalance());
		}
	
		printWriter.close();
	}
		
	
	/* This method is used to get the total balance of all the transactions given. Collecting all the
	 values from the Map and adding it all up. This method also use an if statement to check if there 
	 any transactions in the Map*/
	public void calculateTransaction() {
		if(transaction.size() == 0) {  // checking if the transaction map has any key or value then it will print no transactions if there is none.
			
			System.out.println("No transactions have been made.");
		}
		
		else {
			
			for(Entry<String, Double> e : transaction.entrySet()) { // giving the map a new variable and looping through the entry. if it does have transactions, they will add all the values from the transaction map and get the total balance
				Double val= e.getValue(); // putting e.getValue() into a variable
				
				//System.out.println("\nCurrent value: " + val);
				//System.out.println("Current Transaction Balance : " + this.transactionBalance);
				this.transactionBalance += val; // adding all the values together
				//System.out.println("After adding val: " + this.transactionBalance);
				
	        }
		}		 
	}	
	
	

	
	
}
