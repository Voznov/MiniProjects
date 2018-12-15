import java.io.*;

public class SumFile {
	public static void main(String[] args) {
		if (args.length < 2) {
			return;
		}

		try {
			int sumForOutput = 0;
			InputStreamReader inputF = new InputStreamReader(new FileInputStream(args[0]), "UTF-8");
			OutputStreamWriter outputF = new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8");

			String currentNumber = "";
			try {
				int c = 0;
				while ((c = inputF.read()) != -1) {
					if (((c >= '0') && (c <= '9')) || (c == '-')) {
						currentNumber += (char) c;
						continue;
					}

					sumForOutput += new Integer(currentNumber.length() == 0 ? "0": currentNumber);
					currentNumber = "";
				}
				sumForOutput += new Integer(currentNumber.length() == 0 ? "0": currentNumber);
				currentNumber = "";
			} catch (Exception ex) {
			} finally {
				inputF.close();
			}

			try {
				Integer intForOutput = new Integer(sumForOutput);
				String strForOutput = intForOutput.toString();
				outputF.write(strForOutput, 0, strForOutput.length());
			} catch (Exception ex) {
			} finally {
				outputF.close();
			}
		} catch (Exception ex) {
		}

		return;
	}
}
