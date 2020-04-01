import java.io.IOException;

public class Driver {

	public static void main(String[] args) throws IOException {
		
		String filename = "CoolThing.txt";
		String inputFile = "rule30-79cells-input.txt";
		
		Automaton b = new Automaton(inputFile);
		System.out.println(b.getTrueSymbol());
		System.out.println(b.getFalseSymbol());
		b.evolve(79);
		
		//System.out.println(x.toString());
		System.out.println(b.toString());
		b.save(filename);
		
		
		
	}
}
