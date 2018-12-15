//import java.util.*;
import java.util.ArrayList;

public class ReverseSum {
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

		int[] sumInRow = new int[lines.size()];
		int[] sumInColumn = new int[maxCountOfColumns];

		for (int i = 0; i < sumInRow.length; ++i) {
			sumInRow[i] = 0;
		}

		for (int i = 0; i < sumInColumn.length; ++i) {
			sumInColumn[i] = 0;
		}

		for (int i = 0; i < lines.size(); ++i) {
			ArrayList<Integer> line = lines.get(i);
			for (int j = 0; j < line.size(); ++j) {
				sumInRow[i] += line.get(j);
				sumInColumn[j] += line.get(j);
			}
		}
		for (int i = 0; i < lines.size(); ++i) {
			ArrayList<Integer> line = lines.get(i);
			for (int j = 0; j < line.size(); ++j) {
				System.out.print((sumInRow[i] + sumInColumn[j] - line.get(j)) + " ");
			}
			System.out.println();
		}
		return;
	}
}
