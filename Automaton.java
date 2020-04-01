	import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * This class is the largest class of the program.
 * These objects obey certain properties or rules based off of their rule number.
 * Each Automaton object is made of an ArrayList of Generation objects.
 * The Automaton objects can be evolved to "grow" the objects based on the rule number and amount of steps taken.
 * This adds a new Generation object to the ArrayList for every step taken by the evolve method.
 * Objects from this class can be saved to a file using the save method and can also be printed to the console.
 * 
 * 
 * @author John Hendry
 * @version 1.0
 */
public class Automaton {
	/**
	 * An ArrayList of Generation objects used to hold the states of each Cell.
	 */
	private ArrayList<Generation> generationList;
	/**
	 * An integer between 1 and 256 used to represent the rule number.
	 */
	private int ruleNum;
	/**
	 * The Rule object created from the ruleNum int.
	 */
	private Rule ruleObj;
	/**
	 * An integer value that represents the number of "steps" or generations added to the generationList. Begins at 0.
	 */
	private int totalSteps = 0;
	/**
	 * Character used to represent false in the Automaton. Default value is '0'.
	 */
	private char falseSymbol = '0';
	/**
	 * Character used to represent true in the Automaton. Default is '1'.
	 */
	private char trueSymbol = '1';
	/**
	 * Integer used to represent the length of any given Generation object in the Generation ArrayList.
	 */
	private int lengthOfRows;
	
	/**
	 * Automaton constructor that uses a ruleNum integer and an initialState boolean array. 
	 * Sets the ruleNum integer to the ruleNum class field and instantiates a new Rule object using the ruleNum integer.
	 * Sets lengthOfRows to the initState array's length. Creates a Generation object using the array, 
	 * instantiates the generationList and adds the Generation object to the list.
	 * 
	 * @param ruleNum Integer variable between 1 and 256.
	 * @param initState Boolean array used to create the first Generation object in the Automaton.
	 */
	public Automaton(int ruleNum, boolean[] initState) {
		
		this.ruleNum = ruleNum;
		ruleObj = new Rule(ruleNum);
		lengthOfRows = initState.length;
		
		Generation firstGen = new Generation(initState); 
		generationList = new ArrayList<Generation>();
		generationList.add(firstGen);
		
	}
	/**
	 * Automaton constructor that uses a given filename to create an Automaton object. Throws an IOException if file is not found.
	 * Uses a BufferedReader to read the rule number, the true and false symbols and the initial state. 
	 * The rule number is parsed as an integer from the String given from the BufferedReader.
	 * The true and false symbols are read as integers from the BufferedReader and casted as characters. 
	 * The initialState is read as a String from the BufferedReader, which is then looped through the characters in the String 
	 * and used to create a boolean array. The boolean array is then used to create a Generation object and is added to the generationList.
	 * 
	 * @param filename String given to the constructor for a filename.
	 * @throws IOException If a file cannot be found, throws IOException.
	 */
	public Automaton(String filename) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String rule = br.readLine();
		
		ruleNum = Integer.parseInt(rule);
		ruleObj = new Rule(ruleNum);
		falseSymbol = (char)br.read();
		br.read();
		trueSymbol = (char)br.read();
		br.readLine();
		String initStateString = br.readLine();
		br.close();
		boolean[] boolArr = new boolean[initStateString.length()];
		
		for(int i = 0; i < boolArr.length; i++) {
			if(initStateString.charAt(i) == falseSymbol) {
				boolArr[i] = false;
			}
			else {
				boolArr[i] = true;
			}
		}
		Generation firstGen = new Generation(boolArr);
		generationList = new ArrayList<Generation>();
		generationList.add(firstGen);
		
	}
	/**
	 * Returns the ruleNum
	 * @return ruleNum integer
	 */
	public int getRuleNum() {
		return ruleNum;
	}
	
	
	/**
	 * Uses a loop to create and add a certain number of new Generation objects to the generationList. 
	 * Uses the rule and the previous Generation to create the next Generation.
	 * After each iteration of the loop, totalSteps is incremented. 
	 * @param numSteps A given integer used to represent the number of new Generation objects that will be added to the generationList.
	 */
	public void evolve(int numSteps) {
		for(int i = 0; i < numSteps; i++) {
			Generation newGen = new Generation(lengthOfRows);
			
			newGen = ruleObj.ruleComparison(getLastGeneration());
			generationList.add(newGen);
			++totalSteps;
		}
	}
	
	
	/**
	 * Returns the totalSteps.
	 * @return totalSteps integer
	 */
	public int getTotalSteps() {
		return totalSteps;
	}
	/**
	 * Returns a boolean array of the values of a Generation at a given index, stepNum.
	 * @param stepNum Index of generationList
	 * @return Array of boolean values
	 */
	public boolean[] getState(int stepNum) {
		boolean[] statesAtStep = generationList.get(stepNum).toBooleanArr();
		return statesAtStep;
	}
	/**
	 * Returns a String that represents the boolean values at a given index using the true and false symbols.
	 * @param stepNum Index used
	 * @return String of a boolean array. If a value is true, then it concatenates a true symbol to the String. 
	 * If the value is false, it concatenates a false symbol to the String.
	 */
	public String getStateString(int stepNum) {
		String stateString = "";
		
		boolean[] boolArr = generationList.get(stepNum).toBooleanArr();
		
		for(int i = 0; i < boolArr.length; i++) {
			if(boolArr[i] == true) {
				stateString = stateString + trueSymbol;
			}
			if(boolArr[i] == false) {
				stateString = stateString + falseSymbol;
			}
		}
		
		return stateString;
	}
	/**
	 * The String representation of the Automaton object.
	 * Loops through the generationList and calls the getStateString at each stepNum. 
	 * Concatenates each to the fullString with a newline character. For the last string, it does not add a newline character.
	 * @return The String representation of the Automaton object.
	 */
	public String toString() {
		String fullString = "";
		
		for(int i = 0; i <= totalSteps; i++) {
			if(i != totalSteps) {
			fullString = fullString  + getStateString(i) + "\n";
		}
			else {
				fullString = fullString + getStateString(i);
			}
		}
		
		return fullString;
		
	}
	/**
	 * Saves the Automaton object to the given file.
	 * @param filename Given filename to save to.
	 * @throws IOException Can throw this exception.
	 */
	public void save(String filename) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		
		bw.write(toString());
		
		bw.close();
	}
	/**
	 * Returns the falseSymbol
	 * @return falseSymbol 
	 */
	public char getFalseSymbol() {
		return falseSymbol;
	}
	/**
	 * Setter for falseSymbol
	 * @param symbol Given falseSymbol
	 */
	public void setFalseSymbol(char symbol) {
		falseSymbol = symbol;
	}
	/**
	 * Getter for trueSymbol
	 * @return trueSymbol 
	 */
	public char getTrueSymbol() {
		return trueSymbol;
	}
	/**
	 * Setter for trueSymbol
	 * @param symbol Given trueSymbol
	 */
	public void setTrueSymbol(char symbol) {
		trueSymbol = symbol;
	}
	/**
	 * Returns the last Generation object in the generationList
	 * @return lastGen
	 */
	public Generation getLastGeneration() {
		Generation lastGen = new Generation(lengthOfRows);
		lastGen = generationList.get(totalSteps);
		return lastGen;
	}
}
