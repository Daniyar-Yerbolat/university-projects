import static org.junit.Assert.*;

import org.junit.Test;

public class TrieTest {

	/*
	 * Part 1: complete
	 */
	
	@Test
	public void countAllWordsTest() {
		Trie trie = new Trie();
		trie.insertString("hello");
		trie.insertString("hell");
		trie.insertString("zebra");
		assertEquals(3, trie.countAllWords());
	}

}
