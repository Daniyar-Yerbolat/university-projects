import java.util.ArrayList;
import java.util.Iterator;

public class Trie {

	private TrieNode rootNode = null;
	static private final char ROOT_NODE_CHAR = '*'; // A dummy char to represent
													// the Root Node

	private class TrieNode { // This is the class that represents a node in the
								// trie
		private char value; // the character contained at this node
		private TrieNode[] subnodes; // the subtrees that stem from this node
		private boolean isValidEnd = false; // We need to know if this node
											// represents the end point of a a
											// valid word

		public TrieNode(char c) {
			value = c;
			isValidEnd = false;
			subnodes = new TrieNode[26];
			for (int i = 0; i < 26; i++) { // initialise each node to null. 26
											// nodes for 26 letters
				subnodes[i] = null;
			}
		}

		public void addWord(String s) {
			int positionOfNextNode = ((int) s.codePointAt(0)) - 97; // 97 is 'a'
																	// in ASCII
																	// //Where
																	// is this
																	// character
																	// based in
																	// the array
			if (subnodes[positionOfNextNode] == null) { // add a new node for
														// this value
				subnodes[positionOfNextNode] = new TrieNode(s.charAt(0));
			}
			if (s.length() == 1) // if this is the last character, and we don't
									// need to add a node, then set the end
									// point to be valid
				subnodes[positionOfNextNode].isValidEnd = true;
			else
				subnodes[positionOfNextNode].addWord(s.substring(1)); // add the
																		// substring
																		// from
																		// 1 on
																		// to
																		// that
																		// node
		}

		public TrieNode deleteWord(String s) {
			// similar to deleting a linked list, we rebuild the Trie as we
			// return.
			if (s.length() == 0) { // this is the last char
				isValidEnd = false;
			} else {
				int positionOfNextNode = ((int) s.codePointAt(0)) - 97; // 97 is
																		// 'a'
																		// in
																		// ASCII
				if (subnodes[positionOfNextNode] == null) {
					return this; // we don't have the word at all
				} else { // there are still more characters
					subnodes[positionOfNextNode] = subnodes[positionOfNextNode].deleteWord(s.substring(1));
				}
			}
			// As a final step. can we delete the nodes
			// remember. we can only delete a node at this point if it is not a
			// valid end point and it has no subtrees
			// otherwise we need to leave it alone.
			if (!isValidEnd) {
				boolean canDeleteNode = true;
				for (int i = 0; i < subnodes.length; i++) {
					if (subnodes[i] != null)
						canDeleteNode = false;
				}

				// if we can remove this node then return false
				if (canDeleteNode)
					return null;
				else
					return this;
			}
			return this;
		}

		public boolean containsWord(String s) {
			int positionOfNextNode = ((int) s.codePointAt(0)) - 97; // 97 is 'a'
																	// in ASCII
			if (subnodes[positionOfNextNode] == null) {
				return false; // we don't have the word
			} else { // there are still more characters
				if (s.length() == 1) {
					return isValidEnd;
				} else {
					return subnodes[positionOfNextNode].containsWord(s.substring(1));
				}
			}
		}

		public ArrayList returnAllStrings() {
			ArrayList al = new ArrayList();
			// if this is the root node, then we don't want to add that
			// character on
			String prefixString = "";
			if (value == ROOT_NODE_CHAR) {
				prefixString = "";
			} else {
				prefixString = "" + value;
			}
			if (isValidEnd) { // if this is a valid end point we need to add the
								// char we store as a string
				al.add(prefixString);
			}

			// Find all the substrings and add them on
			for (int i = 0; i < subnodes.length; i++) {
				if (subnodes[i] != null) {
					// there be substrings
					ArrayList tempAL = subnodes[i].returnAllStrings();
					Iterator it = tempAL.iterator();
					while (it.hasNext()) {
						al.add(prefixString + it.next()); // add our prefix onto
															// each suffix
					}
				}
			}
			return al;
		}

	}

	// Prints all of the words in the Trie to the console
	public void printAllWords() {
		if (rootNode == null) {
			return;
		} else {
			ArrayList al = rootNode.returnAllStrings();

			Iterator it = al.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		}
	}

	// inserts
	public void insertString(String s) {
		if (rootNode == null) {
			rootNode = new TrieNode(ROOT_NODE_CHAR);
		}
		rootNode.addWord(s.toLowerCase());
	}

	public boolean containsString(String s) {
		return rootNode.containsWord(s.toLowerCase());
	}

	public void deleteString(String s) {
		rootNode.deleteWord(s.toLowerCase());
	}

	/*
	 * Q2: complete implementation
	 */
	public int countAllWords() {
		int count = 0;
		if (rootNode == null) {
			return count;
		} else {
			ArrayList al = rootNode.returnAllStrings();

			Iterator it = al.iterator();
			while (it.hasNext()) {
				count = count + 1;
			}
		}
		return count;
	}

	/*
	 * Optional: complete implementation
	 */
	public boolean areWordsWithPrefix(String str) {
		return false;
	}

	public static void main(String[] args) {
		Trie t = new Trie();
		t.insertString("hello");
		t.insertString("hell");
		t.insertString("zebra");
		t.printAllWords();
	}
}
