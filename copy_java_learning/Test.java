import java.io.*;
import java.util.*;

public class Test {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> lines = new ArrayList<ArrayList<Integer>>();
		String sForTest = "123\n456\n789\n\njasdf\ns\nt";
		Scanner inputStream = new Scanner(sForTest);
		
		while (inputStream.hasNextLine()) {
			String stringLine = inputStream.nextLine();
			System.out.println("S: <" + stringLine + ">, hashCode=" + stringLine.hashCode());
		}

		return;
	}
}
