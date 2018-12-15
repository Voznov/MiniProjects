//import java.util.*;
import java.util.ArrayList;

public class Reverse {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> lines = new ArrayList<ArrayList<Integer>>();
		Scanner inputStream = new Scanner(System.in);
		
		while (inputStream.hasNextLine()) {
			String stringLine = inputStream.nextLine();
			Scanner sc = new Scanner(stringLine);
			ArrayList<Integer> line = new ArrayList<Integer>();
			while (sc.hasNextInt()) {
				line.add(sc.nextInt());
			}
			lines.add(line);
		}

		for (int i = lines.size() - 1; i >= 0; --i) {
			ArrayList<Integer> line = lines.get(i);

			for (int j = line.size() - 1; j >= 0; --j) {
				System.out.print(line.get(j) + " ");
			}
			System.out.println();
		}
		return;
	}
}
