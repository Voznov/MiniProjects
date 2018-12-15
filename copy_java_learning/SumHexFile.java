import java.io.*;

public class SumHexFile {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Too few arguments");
			return;
		}

		int sumForOutput = 0;
		
		try {
			InputStreamReader inputF = new InputStreamReader(new FileInputStream(args[0]), "UTF-8");
			
			String currentNumber = "";
			try {
				int c;
				while ((c = inputF.read()) != -1) {
					if (((c >= '0') && (c <= '9')) || ((c >= 'a') && (c <= 'f')) || ((c >= 'A') && (c <= 'F')) || (c == '-') || (c == 'x') || (c == 'X')) {
						currentNumber += (char) c;
						continue;
					}

					if (currentNumber.length() == 0) {
						continue;
					}
					
					if (currentNumber.startsWith("0x") || currentNumber.startsWith("0X")) {
						sumForOutput += Integer.parseUnsignedInt(currentNumber.substring(2), 16);
					} else {
						sumForOutput += Integer.parseInt(currentNumber);
					}
					currentNumber = "";
				}
				if (currentNumber.startsWith("0x") || currentNumber.startsWith("0X")) {
					sumForOutput += Integer.parseUnsignedInt(currentNumber.substring(2), 16);
				} else {
					sumForOutput += Integer.parseInt(currentNumber);
				}
				currentNumber = "";
			} catch (IOException ex) {
				System.out.println("I/O error while reading");
			} catch (NumberFormatException ex) {
				System.out.println("Incorrect number format");
			} finally {
				inputF.close();
			}
		} catch (UnsupportedEncodingException ex) {
			System.out.println("Proger - mudak, ne smog napisat' ponormal'nomu \"UTF-8\"");
		} catch (FileNotFoundException ex) {
			System.out.println("Incorrect input file");
		} catch (IOException ex) {
			System.out.println("I/O error while closing input file");
		} catch (SecurityException ex) {
			System.out.println("Read access is denied for input file");
		}

		try {
			OutputStreamWriter outputF = new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8");
			
			Integer intForOutput = new Integer(sumForOutput);
			String strForOutput = intForOutput.toString();
			try {
				outputF.write(strForOutput, 0, strForOutput.length());
			} catch (IOException ex) {
				System.out.println("I/O error while writing");
			} finally {
				outputF.close();
			}
		} catch (UnsupportedEncodingException ex) {
			System.out.println("Proger - mudak, ne smog napisat' ponormal'nomu \"UTF-8\"");
		} catch (FileNotFoundException ex) {
			System.out.println("Output file can not be created");
		} catch (IOException ex) {
			System.out.println("I/O error while closing output file");
		} catch (SecurityException ex) {
			System.out.println("Write access is denied for output file");
		}

		return;
	}
}
