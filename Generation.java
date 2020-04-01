/**
 * 
 * This class object is made up of an array of Cell objects. 
 * 
 * @author John Hendry
 * @version 1.0
 */
public class Generation {
	/**
	 * Array of Cell objects
	 */
	private Cell[] row;
	
	/**
	 * Constructor for the Generation class that uses a boolean array to create a new Generation. 
	 * When called, the constructor creates a new Cell array and loops through the given boolean array, 
	 * creating new Cell objects which are added to the Cell array.
	 * @param booleanArr Given boolean array
	 */
	public Generation(boolean[] booleanArr) {
		row = new Cell[booleanArr.length];
		for(int i = 0; i < booleanArr.length; i++) {
			row[i] = new Cell(booleanArr[i]);
		}
	}
	/**
	 * Constructor that creates a Generation object that has all Cell values initialized to false.
	 * 
	 * @param length Integer given to specify the length of the Cell array
	 */
	public Generation(int length) {
		row = new Cell[length];
		for(int i = 0; i< row.length ;i++) {
			Cell newCell =  new Cell(false);
			row[i] = newCell;
		}
	}
	/**
	 * Method that adds a Cell object to the Cell array at a certain index.
	 * @param cell Cell object that will be added to the Cell array
	 * @param index Index at which the Cell will be added
	 */
	public void addCell(Cell cell, int index) {
		row[index] = cell;
	}
	/**
	 * Returns the boolean value of a Cell at a given index.
	 * @param index Index of the desired Cell
	 * @return Boolean value of a Cell at a given index.
	 */
	public boolean getCellVal(int index) {
		return row[index].getState();
	}
	/**
	 * Returns an array of the boolean values in each Cell in the Generation.
	 * @return boolean array
	 */
	public boolean[] toBooleanArr() {
		boolean[] toBoolean = new boolean[row.length];
		for(int i = 0; i < row.length; i++) {
			toBoolean[i] = row[i].getState();
		}
		return toBoolean;
	}
	/**
	 * Returns the length of the Cell array.
	 * @return The length of the Cell array
	 */
	public int getLength() {
		return row.length;
	}
}
