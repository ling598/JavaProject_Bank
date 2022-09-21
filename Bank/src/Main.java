import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		ATM atm = new ATM();
		
		Scanner textFile = new Scanner(System.in); //giving a variable to the scanner;
		System.out.println("Enter the text file (with .txt at the end): ");
		
		String fileText = textFile.nextLine(); //get the line of input
		
		atm.readfile(fileText);
		atm.login();
		

	
	}
}

/*String printTest = "";
ArrayList<String> newDataStructure = new ArrayList<>(); 
String[] test ={"help", "test", "new", "run"};
for(int i = 0; i < test.length; i++) {
	printTest = test[i];
	//System.out.println("This is printTest inside the loop: " + printTest);
	newDataStructure.add(printTest);
	
}
System.out.println(newDataStructure);

*/