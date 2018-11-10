package lab7;

public class Character {
	static String text = "rrAE";
	static char firstChar = text.charAt(0);
	static char secondChar = text.charAt(1);
	static char endChar = text.charAt(text.length() - 1);
	static char end2Char = text.charAt(text.length() - 2);
	static int i;

	public static void main(String[] args) {

		System.out.println(text);
		int count = 0;

		if (firstChar == 'e') {
			System.out.print("The text starts on 'e',");

			if (secondChar == 'f') {
				System.out.println(" and is followed by 'f'.");
			} else {
				System.out.println(" and is not followed by 'f'.");
			}
		}

		else {
			System.out.println("The text doesn't start on 'e'.");
		}

		if (endChar == 'E') {
			System.out.print("The text ends on 'E',");

			if (end2Char == 'A') {
				System.out.println(" and is preceded by 'A'.");
			} 
			else {
				System.out.println(" and is not preceded by 'A'.");
			}
		}

		else {
			System.out.println("The text doesn't end on 'E'.");
		}

		for (i = 0; i < text.length(); i++) {
			char anyChar = text.charAt(i);
			if (anyChar == 'e') {
				count++;
			}

		}
		System.out.println("Amount of e's: " + count);
	}
}