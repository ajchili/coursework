import java.util.ArrayList;

public class LexicographTester
{
	
	public static void main(String[] args) {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Tom");
		names.add("Dick");
		names.add("Harry");
		Lexicograph lex = new Lexicograph(names);
		System.out.println(lex.printListLexicographically());
	}
	
}
