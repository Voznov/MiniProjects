import javafx.util.Pair;
import java.util.*;
import java.io.*;

public class WordStatLineIndex {
	private static class Pair<First, Second> {
		public final First first;
		public final Second second;

		public Pair(First f, Second s) {
			first = f;
			second = s;
		}
	}

	private static class Word implements Comparable<Word> {
		String word;
		int count;
		ArrayList<Pair<Integer, Integer> > positions = new ArrayList<>();

		public Word(String word, Pair<Integer, Integer> pos) {
			this.word = word;
			count = 1;
			positions.add(pos);
		}
		public void add(Pair<Integer, Integer> pos) {
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
				sB.append(positions.get(i).first);
				sB.append(':');
				sB.append(positions.get(i).second);
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

		HashMap<Integer, Word> words = new HashMap<Integer, Word>();

		try {
			Scanner inputF = new Scanner(new File(args[0]), "UTF-8");
			
			int lineCount = 0;
			while (inputF.hasNextLine()) {
				Scanner inputS = new Scanner(inputF.nextLine());
				++lineCount;
				int it = 0;
				while (inputS.hasNext()) {
					String s = inputS.next();
					
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
							Word w = words.putIfAbsent(ss.hashCode(), new Word(ss, new Pair<Integer, Integer>(lineCount, it)));
							if (w != null) {
								w.add(new Pair<Integer, Integer>(lineCount, it));
							}
							sB = new StringBuilder();
						}
					}
					
					String ss = sB.toString();
					if (ss.length() == 0) {
						continue;
					}

					++it;
					Word w = words.putIfAbsent(ss.hashCode(), new Word(ss, new Pair<Integer, Integer>(lineCount, it)));
					if (w != null) {
						w.add(new Pair<Integer, Integer>(lineCount, it));
					}
				}
				inputS.close();
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
				ArrayList<Word> wordsAL = new ArrayList<>(words.size());
				for (Map.Entry<Integer, Word> entry : words.entrySet()) {
					wordsAL.add(entry.getValue());
				}

				Collections.sort(wordsAL);

				for (int i = 0; i < wordsAL.size(); ++i) {
					outputF.write(wordsAL.get(i).toString());
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
