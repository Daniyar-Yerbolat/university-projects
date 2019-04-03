package hash_table;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import exceptions.MapException;

public class SpellCheck {
	private HashTableMap hashtable;
	private final int MAX = 5;
	private StringHashCode hashcode;

	public static void main(String[] args) throws java.io.IOException, MapException {
		if (args.length != 2) {
			System.out.println("Usage: SpellCheck dictionaryFile.txt inputFile.txt ");
			System.exit(0);
		}

		try {
			String dict = args[0];
			String file = args[1];

			SpellCheck spellcheck = new SpellCheck(dict, file);

		} catch (IOException e) {
			System.out.println("Check your file name");
			System.exit(0);
		}
	}

	public SpellCheck(String dictionary, String file) throws MapException, FileNotFoundException {

		createHashTable();
		addDictionary("src/" + dictionary);
		checkFile("src/" + file);
	}

	public void createHashTable() {
		hashcode = new StringHashCode();
		hashtable = new HashTableMap(hashcode, 0.8f);
	}

	public void addDictionary(String dictionary) throws MapException {
		System.out.println("adding dictionary to hash table: ");
		long dictionary_addtime_start = System.currentTimeMillis();
		readFile(dictionary);
		long dictionary_addtime_end = System.currentTimeMillis();
		System.out.println("time taken = " + (dictionary_addtime_end - dictionary_addtime_start) + " ms.");
		System.out.println(String.format("load factor = %.2f %%", hashtable.getLoadFactor() * 100));
	}

	public void checkFile(String file) throws MapException {
		System.out.println("Checking file for corrections: ");
		long file_checktime_start = System.currentTimeMillis();
		writeToFile(file);
		long file_checktime_end = System.currentTimeMillis();
		System.out.println("Time taken = " + (file_checktime_end - file_checktime_start) + " ms.");
	}

	private void writeToFile(String correctionFile) throws MapException {
		try {
			FileReader fileReader = new FileReader(correctionFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			FileWriter fileWriter = new FileWriter(
					correctionFile.substring(0, correctionFile.length() - 4) + "-corrected.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			String line = bufferedReader.readLine();
			while (line != null) {
				line = line.toLowerCase().trim();
				bufferedWriter.write(correctWord(line) + "\r\n");
				line = bufferedReader.readLine();
			}

			bufferedReader.close();
			bufferedWriter.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + correctionFile + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + correctionFile + "'");
		}

	}

	private void readFile(String textfile) throws MapException {
		String line = null;
		try {
			FileReader fileReader = new FileReader(textfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			line = bufferedReader.readLine();
			while (line != null) {
				line = line.toLowerCase().trim();
				if (line.equals("") == false) {
					hashtable.insert(line);
				}
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + textfile + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + textfile + "'");
		}
	}

	private String correctWord(String word) throws MapException {
		String return_string = word;
		if (hashtable.find(word)) {
			return return_string + " => correct.";
		} else {
			return_string = return_string + " => ";

			return_string = return_string + remove_duplicates(correction_substitution(word), correction_omission(word),
					correction_insertion(word), correction_reversal(word));
		}

		return return_string;
	}

	private String remove_duplicates(String[] substitution, String[] omission, String[] insertion, String[] reversal)
			throws MapException {
		String string = "";

		HashTableMap corrected_words = new HashTableMap(hashcode, 0.8f);

		for (int i = 0; i < MAX; i++) {
			if (substitution[i] != null && !corrected_words.find(substitution[i])) {
				corrected_words.insert(substitution[i]);
			}
			if (omission[i] != null && !corrected_words.find(omission[i])) {
				corrected_words.insert(omission[i]);
			}
			if (insertion[i] != null && !corrected_words.find(insertion[i])) {
				corrected_words.insert(insertion[i]);
			}
			if (reversal[i] != null && !corrected_words.find(reversal[i])) {
				corrected_words.insert(reversal[i]);
			}
		}

		Iterator<String> it = corrected_words.elements();
		while (it.hasNext()) {
			String current = it.next();
			if (current == null) {
				continue;
			} else {
				string = string + current;
			}

			if (it.hasNext()) {
				string = string + ", ";
			} else {
				string = string + ".";
			}
		}
		return string;

	}

	private String[] correction_substitution(String word) {
		char[] char_array = word.toCharArray();
		String[] correct_words = new String[MAX];
		int i = 0;
		for (int x = 0; x < char_array.length; x++) {
			char alphabet = 'a';
			char temp = char_array[x];
			while (alphabet <= 'z') {
				char_array[x] = alphabet;
				String s = String.valueOf(char_array);
				if (hashtable.find(s) && i < correct_words.length) {
					correct_words[i] = s.toLowerCase().trim();
					i++;
				}
				alphabet++;
			}
			char_array[x] = temp;
		}
		return correct_words;
	}

	private String[] correction_omission(String word) {
		char[] char_array = word.toCharArray();
		char[] new_word = new char[char_array.length - 1];
		String[] correct_words = new String[MAX];
		int i = 0;
		for (int x = 0; x < char_array.length; x++) {
			int a = 0;
			int b = 0;
			while (a < new_word.length) {
				if (a == x) {
					b++;
				}
				new_word[a] = char_array[b];
				a++;
				b++;
			}
			String s = String.valueOf(new_word);
			if (hashtable.find(s) && i < correct_words.length) {
				correct_words[i] = s.toLowerCase().trim();
				i++;
			}
		}
		return correct_words;
	}

	private String[] correction_insertion(String word) {
		char[] char_array = word.toCharArray();
		char[] new_word = new char[char_array.length + 1];
		String[] correct_words = new String[MAX];
		int i = 0;
		for (int x = 0; x < char_array.length; x++) {
			int a = 0;
			int b = 0;

			while (a != x) {
				new_word[a] = char_array[b];
				a++;
				b++;
			}
			int j = a + 1;
			while (j < new_word.length) {
				new_word[j] = char_array[b];
				j++;
				b++;
			}
			char alphabet = 'a';
			while (alphabet <= 'z') {
				new_word[a] = alphabet;
				String s = String.valueOf(new_word);
				if (hashtable.find(s) && i < correct_words.length) {
					correct_words[i] = s.toLowerCase().trim();
					i++;
				}
				alphabet++;
			}
		}
		return correct_words;
	}

	private String[] correction_reversal(String word) {
		char[] char_array = word.toCharArray();
		String[] correct_words = new String[MAX];
		int i = 0;
		for (int x = 0; x < (char_array.length - 1); x++) {
			char temp = char_array[x];
			char_array[x] = char_array[x + 1];
			char_array[x + 1] = temp;
			String s = String.valueOf(char_array);
			if (hashtable.find(s) && i < correct_words.length) {
				correct_words[i] = s.toLowerCase().trim();
				i++;
			}
			char_array[x + 1] = char_array[x];
			char_array[x] = temp;
		}

		return correct_words;
	}
}
