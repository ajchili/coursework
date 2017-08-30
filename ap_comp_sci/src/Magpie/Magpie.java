import java.util.Random;

public class Magpie {
	
	public static String previousStatement = "";
	
	public String getGreeting()	{
		return "Hello, let's talk.";
	}
	
	public String getFarewell()	{
		return "See you soon.";
	}
	
	public String getResponse(String statement)	{
		if (statement.equalsIgnoreCase("")) {
			return "Looks like you hit enter without typing. Make sure to type something first.";
		}
		String response = "";
		statement = statement.toLowerCase();
		if (statement.indexOf("no") >= 0) {
			response = "Why so negative?";
		} else if (findKeyword(statement, "mother") >= 0
				|| findKeyword(statement, "father") >= 0
				|| findKeyword(statement, "sister") >= 0
				|| findKeyword(statement, "brother") >= 0) {
			response = "Tell me more about your family.";
		} else if (findKeyword(statement, "dog") >= 0
				|| findKeyword(statement, "cat") >= 0) {
			response = "Tell me more about your pets.";
		} else if (findKeyword(statement, "ms. o'meara") >= 0) {
			response = "She looks different today right?";
		} else if (findKeyword(statement, "potato") >= 0
				|| findKeyword(statement, "patato") >= 0) {
			response = "Potato, patato\nTomato, tamato";
		} else if (findKeyword(statement, "tomato") >= 0
				|| findKeyword(statement, "tamato") >= 0) {
			response = "Tomato, tamato\nPotato, patato";			
		} else if (findKeyword(statement, "yellow") >= 0
				|| findKeyword(statement, "green") >= 0
				|| findKeyword(statement, "blue") >= 0) {
			response = "I love that color!";
		} else if (findKeyword(statement, "I want to") >= 0) {
			String want = statement.replace("i want to", "");
			response = "Would you really be happy if you had" + want;
		} else {
			response = getRandomResponse();
		}
		previousStatement = statement;
		return response;
	}
	
	private int findKeyword(String statement, String goal, int startPos) {
		String phrase = statement.trim();
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);

		while (psn >= 0) {
			String before = " ", after = " ";
			if (psn > 0) {
				before = phrase.substring(psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length()) {
				after = phrase.substring(
					psn + goal.length(),
					psn + goal.length() + 1).toLowerCase();
			}
			if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0))
				&& ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))) {
				return psn;
			}
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
		}
		return -1;
	}

	private int findKeyword(String statement, String goal) {
		return findKeyword(statement, goal, 0);
	}
	
	private String getRandomResponse() {
		final int NUMBER_OF_RESPONSES = 5;
		Random r = new Random();
		int whichResponse = r.nextInt(NUMBER_OF_RESPONSES) + 1;
		String response = "";
		
		if (whichResponse == 0) {
			response = "Interesting, tell me more.";
		} else if (whichResponse == 1) {
			response = "Hmmm.";
		} else if (whichResponse == 2) {
			response = "Do you really think so?";
		} else if (whichResponse == 3) {
			response = "You don't say.";
		} else if (whichResponse == 4) {
			response = "Cool cool.";
		}

		return response;
	}
	
}
