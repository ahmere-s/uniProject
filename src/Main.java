import com.lab.utils.MatrixUtils;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
	    final String LOG_FILE = "log.txt";
	    final String SESSION_FILE = "session.ser";
	    
    	MatrixUtils haha = new MatrixUtils();
    	int[][] matrix = {{0, 1, 2},
				          {1, 1, 2},
				          {2, 1, 2}};
		
		int userChoice;
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.println("You have three options...");
			System.out.println("Option 1: Search Matrix.");
			System.out.println("Option 2: Sum Even Numbers.");
			System.out.println("Option 3: Exit program.");
			
			System.out.print("Enter your number option -- 1-3 here: ");
			userChoice = input.nextInt();
			
			switch (userChoice){
			    case 1:
			    	System.out.println("What number would you like to search for?");
			    	int userMatrix;
			    	System.out.print("Enter it here: ");
			    	userMatrix = input.nextInt();
			    	boolean found = false;
			    	mySearch:
			    		for (int a = 0; a < matrix.length; a++){
			    			for (int b = 0; b < matrix[0].length; b++){
			    				if (matrix[a][b] == userMatrix){found = true; break mySearch;}
			    			}
			    		}
			    	if (found == true){System.out.println("We found it!"); System.out.println();}
			    	else {System.out.println("Number NOT found!"); System.out.println();}
			    	break;
			    case 2:
			    	System.out.println("You have selected to calculate the sum of all even numbers!");
			    	int userTotal = 0;
			    	for (int[] r : matrix){
			    		for (int c : r){
			    			if (c % 2 == 0){userTotal += c;}
			    		}
			    	}
			    	System.out.println("The total for all even numbers is " + userTotal + ".");
			    	System.out.println();
			    	
			    	//Write results to file
			    	try (BufferedWriter fileWrite = new BufferedWriter(new FileWriter(LOG_FILE))){
			    		fileWrite.write("Last result: " + userTotal + "\n");
			    		LocalDateTime currently = LocalDateTime.now();
			    		String timestamp = currently.format(DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm:ss"));
			    		fileWrite.write(timestamp);
			    	}
			    	catch (IOException err){
			    		err.printStackTrace();
			    	}
			    	break;
			    case 3:
			    	System.out.println("Exiting...");
			    	break;
			    default:
			    	System.out.println("Read carefully and try again!");
			    	System.out.println();
			    	break;
			}
			
		} while (userChoice != 3);
		
		input.close();
	}

}
