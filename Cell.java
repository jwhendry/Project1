/**
 * This class creates objects that represent boolean values.
 * 
 * @author John Hendry
 * @version 1.0
 */
public class Cell {
	/**
	 * Boolean value for the state of the Cell.
	 */
	private boolean state;
	
	/**
	 * Constructor for a Cell that uses a boolean parameter.
	 * Sets state to the given state.
	 * @param state Given boolean value
	 */
	public Cell(boolean state) {
		this.state = state;
	}
	/**
	 * Getter for the state
	 * @return state
	 */
	public boolean getState() {
		return state;
	}
	/**
	 * Setter for state
	 * @param state Given state boolean
	 */
	public void setState(boolean state) {
		this.state = state;
	}
}
