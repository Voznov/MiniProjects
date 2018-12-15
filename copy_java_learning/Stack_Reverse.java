import java.util.*;

public class Reverse {
	public static void main(String[] args) {
		Stack<String[]> lines = new Stack<String[]>();
		Scanner inputStream = new Scanner(System.in);
		
		while (inputStream.hasNextLine()) {
			String line = inputStream.nextLine();
			lines.push(line.split(" "));
		}

		while (!lines.empty()) {
			String[] line = lines.pop();

			for (int j = line.length - 1; j >= 0; --j) {
				System.out.print(line[j] + " ");
			}
			System.out.println();
		}
		return;
	}
}
