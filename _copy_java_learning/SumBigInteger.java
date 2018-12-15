import java.math.BigInteger;

public class SumBigInteger {
	public static void main(String[] args) {
		BigInteger result = new BigInteger("0");
		
		String sumOfString = "";
		for (int i = 0; i < args.length; ++i) {
			sumOfString += args[i] + ' ';
		}
		
		String[] arrayOfStringNumber = sumOfString.split("[^0-9-+]+");
		for (int j = 0; j < arrayOfStringNumber.length; ++j) {
			if (arrayOfStringNumber[j].length() == 0) {
				continue;
			}
			result = result.add(new BigInteger(arrayOfStringNumber[j]));
		}
		
		System.out.println(result);
		
		return;
	}
}
