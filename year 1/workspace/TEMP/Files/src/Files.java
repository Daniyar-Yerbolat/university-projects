import java.util.Scanner;
import java.io.*;

public class Files {
	public static void main(String args[]) {
		Scanner prompt = new Scanner(System.in);
		try {
			File f = new File("file.txt");
			File f2 = new File("empty.txt");

			FileWriter writer = new FileWriter(f2);
			BufferedWriter bwriter = new BufferedWriter(writer);
			Scanner s = new Scanner(f);

			while (s.hasNext()) {
				String a = s.nextLine();
				System.out.println(a);
				System.out.println("Copy this line to file empty.txt? Type 1 to copy and 0 not to copy.");
				int check = prompt.nextInt();
				if (check == 1) {
					bwriter.write(a);

				} else if (check == 0) {
					continue;
				}
			}
			s.close();
			prompt.close();
			bwriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("END.");
	}
}
