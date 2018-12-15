import java.util.*;
import java.io.*;

public class WordStatIndex {
	private static class Word implements Comparable<Word> {
		String word;
		int count;
		ArrayList<Integer> positions = new ArrayList<>();

		public Word(String word, int pos) {
			this.word = word;
			count = 1;
			positions.add(pos);
		}
		public void add(int pos) {
			count++;
			positions.add(pos);
		}

		@Override
		public int compareTo(Word right) {
			return word.compareTo(right.word);
		}

		@Override
		public String toString() {
			StringBuilder sB = new StringBuilder();
			sB.append(word);
			sB.append(' ');
			sB.append(count);
			for (int i = 0; i < positions.size(); ++i) {
				sB.append(' ');
				sB.append(positions.get(i));
			}

			return sB.toString();
		}

		@Override
		public int hashCode() {
			return word.hashCode();
		}

	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("2 arguments was excepted");
			return;
		}

		LinkedHashMap<Integer, Word> words = new LinkedHashMap<Integer, Word>();

		try {
			Scanner inputF = new Scanner(new File(args[0]), "UTF-8");
			
			int it = 0;
			while (inputF.hasNext()) {
				String s = inputF.next();
				
				StringBuilder sB = new StringBuilder();
				for (int i = 0; i < s.length(); ++i) {
					if (Character.isLetter(s.charAt(i)) || (s.charAt(i) == '\'') || (Character.getType(s.charAt(i)) == Character.DASH_PUNCTUATION)) {
						sB.append(Character.toLowerCase(s.charAt(i)));
					} else {
						String ss = sB.toString();
						if (ss.length() == 0) {
							continue;
						}

						++it;
						Word w = words.putIfAbsent(ss.hashCode(), new Word(ss, it));
						if (w != null) {
							w.add(it);
						}
						sB = new StringBuilder();
					}
				}
				
				String ss = sB.toString();
				if (ss.length() == 0) {
					continue;
				}

				++it;
				Word w = words.putIfAbsent(ss.hashCode(), new Word(ss, it));
				if (w != null) {
					w.add(it);
				}
			}
			inputF.close();
		} catch (UnsupportedEncodingException ex) {
			System.out.println("Proger - mudak, ne smog napisat' ponormal'nomu \"UTF-8\"");
		} catch(FileNotFoundException ex) {
			System.out.println("Incorrect input filename");
			return;
		}

		boolean errorFlag = true;
		try {
			OutputStreamWriter outputF = new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8");
			try {
				Object[] wordsArray = words.values().toArray();

				for (int i = 0; i < wordsArray.length; ++i) {
					outputF.write(wordsArray[i].toString());
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
