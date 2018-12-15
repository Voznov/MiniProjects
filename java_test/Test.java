import java.io.*;
import java.util.*;

public class Test {
	public static void main(String[] args) {
		String sForTest = "\u5169\n-\n123\n456\n\n789\n\n\n";
		Scanner scanner = new Scanner(sForTest);

		int nowInt = 0;
		while(scanner.hasNext()) {
			System.out.println(scanner.next());
		}
		while(scanner.hasNextLine()) {
			scanner.nextLine();
			System.out.println("kek");
		}
		System.out.println("\u5169");
	}
}
