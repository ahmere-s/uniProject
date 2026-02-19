import com.lab.utils.MatrixUtils;
import com.lab.utils.MathTools;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Random;

public class Main {
	private static final String LOG_FILE = "log.txt";
	private static final String SESSION_FILE = "session.ser";
	
    public static void main(String[] args) {
    	UserSession session = loadSession();
    	System.out.println("Welcome back, " + session.userName + ".");
    	System.out.println("Operations: " + session.numOperations);
    	System.out.println("Prime number checks: " + session.totalPrimeChecks);
    	
	    MatrixUtils haha = new MatrixUtils();
	    MathTools math = new MathTools();
	    
	    System.out.println("First I need you to specify the size of your matrix.");
	    int userRow;
	    int userCol;
		int userChoice;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter digit for number of rows: ");
		userRow = input.nextInt();
		System.out.println("Enter digit for number of columns: ");
		userCol = input.nextInt();
		
		int[][] matrix = new int[userRow][userCol];
		Random randy = new Random();
		
		for (int row = 0; row < userRow; row++){
		    for (int col = 0; col < userCol; col++){
		    	if (matrix[row][col] == 0){
		    		matrix[row][col] = randy.nextInt(100) + 1;
		    	}
		    }	
		}
		System.out.println("Matrix has been created.");
		boolean foundPrime = false;
		
		do {
			System.out.println("You have four options...");
			System.out.println("Option 1: Search Matrix.");
			System.out.println("Option 2: Sum Even Numbers.");
			System.out.println("Option 3: Show Matrix.");
			System.out.println("Option 4: Find First Prime.");
			System.out.println("Option 5: Exit Program.");
			
			System.out.print("Enter your number option -- 1-5 here: ");
			userChoice = input.nextInt();
			session.numOperations++; //Same as += 1
			
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
			    	try (BufferedWriter fileWrite = new BufferedWriter(new FileWriter(LOG_FILE, true))){ //Instead of just.. new FileWriter(LOG_FILE) because without ", true" it continues to create new and overwrite file instead of just appending.
			    		LocalDateTime currently = LocalDateTime.now();
			    		String timestamp = currently.format(DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm:ss"));
			    		fileWrite.write(timestamp + "Sum calculated: " + userTotal + "\n");
			    	}
			    	catch (IOException err){
			    		err.printStackTrace();
			    	}
			    	break;
			    case 3:
			    	System.out.println("Here is the matrix: ");
			    	haha.printMatrix(matrix);
			    	System.out.println();
			    	break;
			    case 4:
			    	System.out.println("You chose to find the first prime number.");
			    	session.totalPrimeChecks++;
			    	searchPrime:
			    	    for (int r = 0; r < userRow; r++){
			    	    	for (int c = 0; c < userCol; c++){
			    	    		if (math.isPrime(matrix[r][c])){
			    	    			foundPrime = true;
			    	    			System.out.println("Your first prime number is " + matrix[r][c] + ".");
			    	    			
			    	    			//Log if found
			    	    			try (BufferedWriter fileWrite = new BufferedWriter(new FileWriter(LOG_FILE, true))){
			    	    				LocalDateTime currently = LocalDateTime.now();
			    	    				String timestamp = currently.format(DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm:ss"));
			    	    				fileWrite.write(timestamp + " PRIME FOUND: " + matrix[r][c] + " at index [" + r + "][" + c + "]");
			    	    				fileWrite.write("\n");
			    	    			}
			    	    			catch (IOException err){
			    	    				err.printStackTrace();
			    	    			}
			    	    			break searchPrime;
			    	    		}
			    	    	}
			    	    }
			    	if (foundPrime != true){System.out.println("No prime found :(");}
			    	//System.out.println(MathTools.primeCheckCount);   //This would print if I made primeCheckCount public inside of the MathTools class.
			    	System.out.println();
			    	break;
			    case 5:
			    	System.out.println("You chose to EXIT!");
			    	break;
			    default:
			    	System.out.println("Read carefully and try again!");
			    	System.out.println();
			    	break;
			}
			
		} while (userChoice != 5);
		
		saveSession(session); //Log user session
		System.out.println("Session saved.");
		System.out.println("Exiting...");
		input.close(); //Avoid Leaks
	}
    
    // --------- HELPER METHODS ----------- //
    //Serialize Object
    private static void saveSession(UserSession session){
    	try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SESSION_FILE))){
    		oos.writeObject(session);
    	}
    	catch(IOException err){
    		err.printStackTrace();
    	}
    }
    //Deserialize Object
    private static UserSession loadSession(){
    	try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SESSION_FILE))){
    		return (UserSession) ois.readObject();
    	}
    	catch(IOException | ClassNotFoundException err){
    	    return new UserSession("studentUser"); // If file doesn't already exist. 
    	}
    }
 
}
