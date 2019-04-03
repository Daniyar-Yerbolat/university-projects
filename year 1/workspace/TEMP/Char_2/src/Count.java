import java.util.Scanner;

public class Count {
	private static String text;
	private static String text2;
	private static Scanner scan;
	private static int i;
	private static int count = 0;
	private static int count2 = 0;
	private static int count3 = 0;
	private static int count4 = 0;

	public static void main(String[] args) {

		scan = new Scanner(System.in);

		text = scan.nextLine();
		text2 = scan.nextLine();

		System.out.println(text);
		System.out.println(text2);

		for (i = 0; i < text.length(); i++) {

			if (text.charAt(i) != 'h') {
				count++;
			} else if (text.charAt(i) == 'h') {
				break;
			}
		}
		for (i = 0; i < text.length(); i++) {

			if (text.charAt(i) == 'h') {
				count2++;
			}
			if (text.charAt(i) == 'H') {
				count3++;
			}
		}
		for (i = 0; i < text.length() && i < text2.length(); i++) {
			if (text.charAt(i) == text2.charAt(i)) {
				count4++;
			}
		}
		if (count4 == text.length() ) {
			System.out.println("Texts are identical");
		}
		else if ( count4 != text.length()){
			System.out.println("Texts are not identical");
		}

		System.out.println("Amount of characters before h: " + count);
		System.out.println("Amount of lower case h: " + count2);
		System.out.println("Amount of upper case H: " + count3);
		System.out.println("Amount of identical characters: " + count4);

	}
}