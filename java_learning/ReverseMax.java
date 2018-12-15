import java.util.*;

public class ReverseMax {
	public static void main(String[] args) {
		List<String[]> lines = new ArrayList<String[]>();
		Scanner inputStream = new Scanner(System.in);
		
		int lineMaxLength = 0, numberLineML = 0, totalNumbers = 0;
		while (inputStream.hasNextLine()) {
			//System.out.println("kek");
			String line = inputStream.nextLine();
			String[] line_split = line.split(" ");
			
			if (line.length() != 0) {
				lines.add(line_split);
				totalNumbers += line_split.length;
				
				if (lineMaxLength < line_split.length) {
					lineMaxLength = line_split.length;
					numberLineML = lines.size() - 1;
				}
			} else {
				lines.add(new String[0]);
			}
		}

		if (totalNumbers == 0) {
			for (int i = 0; i < lines.size(); ++i) {
				System.out.println();
			}
			return;
		}

		int linesLength = lines.size();
		int[] maxOnLine = new int[linesLength];
		int[] maxOnColumn = new int[lineMaxLength];

		for (int i = 0; i < maxOnLine.length; ++i) {
			maxOnLine[i] = new Integer(lines.get(i).length == 0 ? "0" : lines.get(i)[0]);
		}

		for (int i = 0; i < maxOnColumn.length; ++i) {
			String[] lineForDefault = lines.get(numberLineML);
			maxOnColumn[i] = new Integer(lineForDefault[i]);
		}
		
		for (int i = 0; i < linesLength; ++i) {
			for (int j = 0; j < lines.get(i).length; ++j) {
				int number = new Integer(lines.get(i)[j]);
				maxOnLine[i] = Math.max(maxOnLine[i], number);
				maxOnColumn[j] = Math.max(maxOnColumn[j], number);
			}
		}
		
		//System.out.println(linesLength);
		for (int i = 0; i < linesLength; ++i) {
			for (int j = 0; j < lines.get(i).length; ++j) {
				System.out.print((maxOnLine[i] > maxOnColumn[j] ? maxOnLine[i] : maxOnColumn[j]) + " ");
			}
			System.out.println();
		}
		return;
	}
}
