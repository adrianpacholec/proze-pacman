import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Odczyt {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("ala.txt");
		Scanner in = new Scanner(file);

		while (in.hasNextLine()) {
			String line = in.nextLine();
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '1')
					System.out.println("Jeden");
			}
		}
		in.close();
	}
}