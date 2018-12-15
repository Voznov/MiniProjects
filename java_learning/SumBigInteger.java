import java.math.BigInteger;

public class SumBigInteger {
	public static void main(String[] args) {
		BigInteger result = new BigInteger("0");
		
		String sumOfString = "";
		for (int i = 0; i < args.length; ++i) {
			sumOfString += args[i] + ' ';
		}
		sumOfString = sumOfString.replaceAll("[^0-9-+]+", " ");
		sumOfString = sumOfString.trim();

		if (sumOfString.length() == 0) {
			System.out.println(0);
			return;
		}

		String[] arrayOfStringNumber = sumOfString.split(" ");
		for (int j = 0; j < arrayOfStringNumber.length; ++j) {
			result = result.add(new BigInteger(arrayOfStringNumber[j]));
		}
		
		System.out.println(result);
		
		return;
	}
}
