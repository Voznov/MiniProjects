import java.io.*;
import java.util.*;
import java.math.*;

public class Scanner {
	private final int BUFFER_SIZE = 256;
	private final int MAX_GARBAGE_SIZE = 8;


	private ArrayList<char[]> listOfArrayOfChar = new ArrayList<>();
	private char[] cbuf;
	private int firstPositionInList = 0;
	private int totalSize = 0;

	private Reader inputS;
	private boolean inputIsClosed = false;

	private boolean tokenIsFresh = false;
	private String currentToken;
	private int tokenStartsFrom = 0;

	private boolean lineIsFresh = false;
	private int lineEndPosition = 0;


	private int listSize() {
		return totalSize - firstPositionInList;
	}
	private char listGet(int index) {
		return listOfArrayOfChar.get((firstPositionInList + index) / BUFFER_SIZE)[(firstPositionInList + index) % BUFFER_SIZE];
	}

	private boolean read(int count) {
		//System.out.println("D: read: count = " + count);
		int readed = 0;
		try {
			while (readed < count) {
				int startPosition = totalSize % BUFFER_SIZE;
				if (startPosition == 0) {
					cbuf = new char[BUFFER_SIZE];
				} else {
					cbuf = listOfArrayOfChar.get(listOfArrayOfChar.size() - 1);
				}
				int result = 0;
				while (result == 0) {
					result = inputS.read(cbuf, startPosition, BUFFER_SIZE - startPosition);
				}
				if (result == -1) {
					return false;
				}
				if (startPosition == 0) {
					listOfArrayOfChar.add(cbuf);
				}
				readed += result;
				totalSize += result;
			}
			return true;
		} catch (IOException ex) {
			return false;
		}
	}
	private boolean checkRead(int count) {
		if (inputIsClosed) {
			throw new IllegalStateException("Scanner is closed");
		}
		if (count <= listSize()) {
			return true;
		} else {
			return read(count - listSize());
		}
	}

	private int findCR() {
		return findCR(0);
	}
	private int findCR(int startPosition) {
		if (!checkRead(startPosition)) {
			return -1;
		}
		int it = startPosition;
		while (it < listSize()) {
			if (listGet(it) == '\n') {
				return it;
			}
			++it;
		}
		while (checkRead(listSize() + 1)) {
			while (it < listSize()) {
				if (listGet(it) == '\n') {
					return it;
				}
				++it;
			}
		}
		return -1;
	}
	private int findWS(boolean notWS) {
		return findWS(notWS, 0);
	}
	private int findWS(boolean notWS, int startPosition) {
		if (!checkRead(startPosition)) {
			return -1;
		}
		int it = startPosition;
		while (it < listSize()) {
			if (notWS ? Character.isWhitespace(listGet(it)) : !Character.isWhitespace(listGet(it))) {
				return it;
			}
			++it;
		}
		while (checkRead(listSize() + 1)) {
			while (it < listSize()) {
				if (notWS ? Character.isWhitespace(listGet(it)) : !Character.isWhitespace(listGet(it))) {
					return it;
				}
				++it;
			}
		}
		return -1;
	}

	private void checkForGarbage() {
		int garbageArrayOfChar = firstPositionInList / BUFFER_SIZE;
		if (garbageArrayOfChar >= MAX_GARBAGE_SIZE) {
			listOfArrayOfChar = new ArrayList<>(listOfArrayOfChar.subList(garbageArrayOfChar, listOfArrayOfChar.size()));
			firstPositionInList -= (garbageArrayOfChar * BUFFER_SIZE);
			totalSize -= (garbageArrayOfChar * BUFFER_SIZE);
		}
	}

	private char write() {
		char result = listGet(0);
		++firstPositionInList;
		checkForGarbage();
		return result;
	}
	private String write(int count) {
		return writeFromTo(0, count);
	}
	private String writeFromTo(int beginPosition, int endPosition) {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < endPosition; ++i) {
			char c = listGet(i);
			if (i >= beginPosition) {
				sBuilder.append(c);
			}
		}
		firstPositionInList += endPosition;
		checkForGarbage();
		return sBuilder.toString();
	}

	private String getFirst(int count) {
		return getFromTo(0, count);
	}
	private String getFromTo(int beginPosition, int endPosition) {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < endPosition; ++i) {
			char c = listGet(i);
			if (i >= beginPosition) {
				sBuilder.append(c);
			}
		}
		return sBuilder.toString();
	}
	private String getNext() {
		//System.out.println("D: getNext(): tokenIsFresh = " + tokenIsFresh);
		if (tokenIsFresh) {
			return currentToken;
		}
		int notSpacePos = findWS(false);
		if (notSpacePos == -1) {
			return "";
		}
		int spacePos = findWS(true, notSpacePos);
		
		tokenIsFresh = true;
		tokenStartsFrom = notSpacePos;
		currentToken = getFromTo(notSpacePos, spacePos == -1 ? listSize() : spacePos);
		//System.out.println("D: getNext(): currentToken = " + currentToken);
		return currentToken;
	}


	public Scanner(InputStream source) {
		inputS = new InputStreamReader(source);
	}
	public Scanner(InputStream source, String charsetName) throws UnsupportedEncodingException {
		inputS = new InputStreamReader(source, charsetName);
	}
	public Scanner(File source) throws FileNotFoundException {
		inputS = new InputStreamReader(new FileInputStream(source));
	}
	public Scanner(File source, String charsetName) throws UnsupportedEncodingException, FileNotFoundException {
		inputS = new InputStreamReader(new FileInputStream(source), charsetName);
	}
	public Scanner(String source) {
		inputS = new StringReader(source);
	}


	public boolean hasNext() {
		return getNext().length() > 0;
	}
	public String next() {
		String token = getNext();
		if (token.length() == 0) {
			throw new NoSuchElementException("Input is empty");
		}
		//System.out.println("D: notSpacePos = " + notSpacePos + ", spacePos = " + spacePos);
		
		tokenIsFresh = false;
		return writeFromTo(tokenStartsFrom, token.length() + tokenStartsFrom);
	}

	public boolean hasNextLine() {
		if (!lineIsFresh) {
			lineEndPosition = findCR();
			lineIsFresh = true;
		}
		//System.out.println("D: hasNextLine: findCR() = " + lineEndPosition + ", totalSize = " + totalSize);
		return checkRead(1);
	}
	public String nextLine() {
		if (checkRead(1)) {
			int crPos = (lineIsFresh ? lineEndPosition : findCR());
			lineIsFresh = false;
			if (crPos == -1) {
				return write(listSize());
			} else {
				String result = write(crPos);
				write(1);
				return result;
			}
		} else {
			throw new NoSuchElementException("Line was not found");
		}
	}

	public boolean hasNextInt() {
		int result;
		try {
			result = new Integer(getNext());
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
	public int nextInt() {
		int result;
		try {
			result = new Integer(next());
		} catch (NumberFormatException ex) {
			throw new InputMismatchException("Uncorrect input");
		}
		return result;
	}

	public boolean hasNextFloat() {
		float result;
		try {
			result = new Float(getNext());
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
	public float nextFloat() {
		float result;
		try {
			result = new Float(next());
		} catch (NumberFormatException ex) {
			throw new InputMismatchException("Uncorrect input");
		}
		return result;
	}

	public boolean hasNextDouble() {
		double result;
		try {
			result = new Double(getNext());
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
	public double nextDouble() {
		double result;
		try {
			result = new Double(next());
		} catch (NumberFormatException ex) {
			throw new InputMismatchException("Uncorrect input");
		}
		return result;
	}

	public boolean hasNextBigInteger() {
		BigInteger result;
		try {
			result = new BigInteger(getNext());
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
	public BigInteger nextBigInteger() {
		BigInteger result;
		try {
			result = new BigInteger(next());
		} catch (NumberFormatException ex) {
			throw new InputMismatchException("Uncorrect input");
		}
		return result;
	}

	public void close() {
		try {
			inputS.close();
		} catch (IOException ex) {
		}
		inputIsClosed = true;
	}
}
