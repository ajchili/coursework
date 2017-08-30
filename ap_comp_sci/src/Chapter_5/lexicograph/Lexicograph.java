import java.util.ArrayList;

public class Lexicograph
{
	
	private ArrayList<String> list;

	public Lexicograph(ArrayList<String> list) {
		this.list = list;
	}
	
	public ArrayList<String> printListLexicographically() {
		ArrayList<String> list2 = new ArrayList<String>();
		if (list.get(0).compareTo(list.get(1)) > 0) {
			list2.add(list.get(1));
			if (list.get(0).compareTo(list.get(2)) > 0) {
				list2.add(list.get(2));
				list2.add(list.get(0));
			} else {
				list2.add(list.get(0));
				list2.add(list.get(2));
			}
		} else {
			list2.add(list.get(0));
			if (list.get(1).compareTo(list.get(2)) > 0) {
				list2.add(list.get(1));
				list2.add(list.get(0));
			} else {
				list2.add(list.get(0));
				list2.add(list.get(1));
			}
		}
		return list2;
	}
	
}
