import java.util.*;

public class ReverseTranspose {
	public static void main(String[] args) {
		List<String[]> lines = new ArrayList<String[]>();
		Scanner inputStream = new Scanner(System.in);
		
		int lineMaxLength = 0;
		while (inputStream.hasNextLine()) {
			String line = inputStream.nextLine();
			String[] line_split = line.split(" ");
			lineMaxLength = (line_split.length > lineMaxLength ? line_split.length : lineMaxLength);
			if (line.length() != 0) {
				lines.add(line_split);
			}
		}

		int linesLength = lines.size();
		for (int i = 0; i < lineMaxLength; ++i) {
			for (int j = 0; j < linesLength; ++j) {
				if (lines.get(j).length > i) {
					System.out.print(lines.get(j)[i] + " ");
				}
			}
			System.out.println();
		}
		return;
	}
}
