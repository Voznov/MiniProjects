public class SumDoubleSpace {
	public static void main(String[] args) {
		double result = 0;
		
		String sumOfString = "";
		for (int i = 0; i < args.length; ++i) {
			sumOfString += args[i] + ' ';
		}
		
		String[] arrayOfStringNumber = sumOfString.split("[^0-9-+.eE]");
		for (int j = 0; j < arrayOfStringNumber.length; ++j) {
			if (arrayOfStringNumber[j].length() == 0) {
				continue;
			}
			result += new Double(arrayOfStringNumber[j]);
		}
		
		System.out.println(result);
		
		return;
	}
}
