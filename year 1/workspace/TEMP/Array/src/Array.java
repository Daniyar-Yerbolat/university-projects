import java.util.Scanner;

public class Array {
	public static void main(String[] args) {
		int[] values = new int[10];
		Scanner input = new Scanner(System.in);
		int number;
		int i;
		int j;
		int total = 0;
		

		for (i = 0; i < values.length; i++) {
			number = input.nextInt();
			if (number != -1) {
				values[i] = number;
				System.out.println(values[i]);
			} else {
				break;
			}
		}
		for (j = 0; j < values.length; j++){
			if (values[j]%2==0){
				total = total + values[j];
		}
		}
		System.out.println("Sum of even numbers:" + total);
	}
}
