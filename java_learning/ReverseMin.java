//import java.util.*;
import java.util.ArrayList;

public class ReverseMin {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> lines = new ArrayList<ArrayList<Integer>>();
		Scanner inputStream = new Scanner(System.in);
		
		int maxCountOfColumns = 0;
		while (inputStream.hasNextLine()) {
			String stringLine = inputStream.nextLine();
			Scanner sc = new Scanner(stringLine);
			ArrayList<Integer> line = new ArrayList<Integer>();
			while (sc.hasNextInt()) {
				line.add(sc.nextInt());
			}
			maxCountOfColumns = Math.max(maxCountOfColumns, line.size());
			lines.add(line);
		}

		int[] minInRow = new int[lines.size()];
		int[] minInColumn = new int[maxCountOfColumns];

		for (int i = 0; i < minInRow.length; ++i) {
			minInRow[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < minInColumn.length; ++i) {
			minInColumn[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < lines.size(); ++i) {
			ArrayList<Integer> line = lines.get(i);
			for (int j = 0; j < line.size(); ++j) {
				minInRow[i] = Math.min(minInRow[i], line.get(j));
				minInColumn[j] = Math.min(minInColumn[j], line.get(j));
			}
		}
		for (int i = 0; i < lines.size(); ++i) {
			ArrayList<Integer> line = lines.get(i);
			for (int j = 0; j < line.size(); ++j) {
				System.out.print(Math.min(minInRow[i], minInColumn[j]) + " ");
			}
			System.out.println();
		}
		return;
	}
}
