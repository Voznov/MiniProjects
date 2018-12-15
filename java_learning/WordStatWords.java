import java.util.*;
import java.io.*;

public class WordStatWords {
	private static class Word implements Comparable<Word> {
		String word;
		int count;

		public Word(String word) {
			this.word = word;
			count = 1;
		}
		public void add() {
			count++;
		}

		@Override
		public int compareTo(Word right) {
			return word.compareTo(right.word);
		}

	}

	public static void main(String[] args) {
		if (args.length < 2) {
			return;
		}

		List<Word> words = new ArrayList<Word>();
		
		boolean errorFlag = true;
		try {
			InputStreamReader inputF = new InputStreamReader(new FileInputStream(args[0]), "UTF-8");

			String s = "";
			try {
				int c = 0;
outer:				while ((c = inputF.read()) != -1) {
					c = Character.toLowerCase(c);
					if (Character.isLetter(c) || (Character.getType(c) == Character.DASH_PUNCTUATION) || (c == '\'')) {
						s += (char) c;
						continue;
					}


					if (s.length() == 0) {
						continue;
					}

					for (int i = 0; i < words.size(); ++i) {
						if (s.compareTo(words.get(i).word) == 0) {
							words.get(i).count++;
							s = "";
							continue outer;
						}
					}
					words.add(new Word(s));
					s = "";
				}
				if (s.length() > 0) {
					for (int i = 0; i < words.size(); ++i) {
						if (s.compareTo(words.get(i).word) == 0) {
							words.get(i).count++;
							s = "";
							continue;
						}
					}
					words.add(new Word(s));
					s = "";
				}
				errorFlag = false;
			} catch (IOException ex) {
				System.out.println("I/O error while reading");
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
		if (errorFlag == true) {
			return;
		}


		Collections.sort(words);


		errorFlag = true;
		try {
			OutputStreamWriter outputF = new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8");
			try {
				for (int i = 0; i < words.size(); ++i) {
					outputF.write(words.get(i).word, 0, words.get(i).word.length());
					outputF.write(" ", 0, 1);
					
					Integer countInteger = new Integer(words.get(i).count);
					String countString = countInteger.toString();
					outputF.write(countString);
					outputF.write("\n");
				}
				errorFlag = false;
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
		if (errorFlag == true) {
			return;
		}

		return;
	}
}
