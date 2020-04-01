/**
 * This class is used to find the rule for the Automaton. It uses a conditional statement to determine 
 * if based on the rule, the left Cell value, the middle Cell value, and the right Cell value if the next Cell will be true or false.
 * 
 * @author John Hendry
 *
 */
public class Rule {
	/**
	 * An integer that stores the integer representation of the rule.
	 */
	private int ruleNum;
	/**
	 * A string object that represents the binary version of the rule integer. It is formatted to have 8 total characters.
	 */
	private String ruleBinary;
	
	/**
	 * A constructor that uses a given rule integer to create a Rule object.
	 * @param ruleNum An integer that determines the properties and rules.
	 */
	public Rule(int ruleNum) {
		this.ruleNum = ruleNum;
		ruleBinary = ruleToBinary();
	}
	/**
	 * Returns the formatted ruleBinary as a String.
	 * @return A String formatted with 8 characters.
	 */
	public String ruleToBinary() {
		String ruleString = Integer.toBinaryString(ruleNum);
		int x = Integer.parseInt(ruleString);
		ruleBinary = String.format("%08d", x);
		return ruleBinary;
	}
	/**
	 * This is the most important method of the class. It uses a given Generation object to determine the values of the next Generation object 
	 * based off of the given rule, the left Cell's value, the middle Cell's value, and the right Cell's value. For the edge cases, the first 
	 * Cell and the last Cell in a Generation, they are determined by looking at, in the first Cell's case, the last Cell's value as it's left neighbor 
	 * and, in the last Cell's case, the first Cell's value as it's right neighbor.
	 * It returns the next Generation object.
	 * @param genObj A previous Generation that is used to determine the values of the next Generation.
	 * @return A new Generation object with Cells that have boolean values based off of the rule.
	 */
	public Generation ruleComparison(Generation genObj) {
		Generation newGen = new Generation(genObj.getLength());
		boolean rightCellVal;
		boolean midCellVal;
		boolean leftCellVal;
		
		for(int i = 0; i < newGen.getLength() ; i++) {
			midCellVal = genObj.getCellVal(i);
			if(i == 0) {
				leftCellVal = genObj.getCellVal(newGen.getLength() - 1);
			}
			else {
				leftCellVal = genObj.getCellVal(i-1);
			}
			
			if(i == newGen.getLength() - 1) {
				rightCellVal = genObj.getCellVal(0);
			}
			else {
				rightCellVal = genObj.getCellVal(i+1);
			}
			
			Cell cellObj = new Cell(false);
			
			if (((ruleBinary.charAt(0) == '1') && ((leftCellVal == true) && (midCellVal == true) && (rightCellVal == true))) || ((ruleBinary.charAt(1) == '1') && ((leftCellVal == true) && (midCellVal == true) && (rightCellVal == false))) || ((ruleBinary.charAt(2) == '1') && ((leftCellVal == true) && (midCellVal == false) && (rightCellVal == true))) || ((ruleBinary.charAt(3) == '1') && ((leftCellVal == true) && (midCellVal == false) && (rightCellVal == false))) || ((ruleBinary.charAt(4) == '1') && ((leftCellVal == false) && (midCellVal == true) && (rightCellVal == true))) || ((ruleBinary.charAt(5) == '1') && ((leftCellVal == false) && (midCellVal == true) && (rightCellVal == false))) || ((ruleBinary.charAt(6) == '1') && ((leftCellVal == false) && (midCellVal == false) && (rightCellVal == true))) || ((ruleBinary.charAt(7) == '1') && ((leftCellVal == false) && (midCellVal == false) && (rightCellVal == false)))){
				cellObj.setState(true);
			}
			newGen.addCell(cellObj, i);
		}
		return newGen;
	
	}
	}