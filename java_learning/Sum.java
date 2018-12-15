public class Sum {
	public static void main(String[] args) {
		int result = 0;
		
		String sumOfString = "";
		for (int i = 0; i < args.length; ++i) {
			sumOfString += args[i] + ' ';
		}
		
		String[] arrayOfStringNumber = sumOfString.split("[^0-9-]+");
		for (int j = 0; j < arrayOfStringNumber.length; ++j) {
			if (arrayOfStringNumber[j].length() == 0) {
				continue;
			}
			result += new Integer(arrayOfStringNumber[j]);
		}
		
		System.out.println(result);
		
		return;
	}
}
