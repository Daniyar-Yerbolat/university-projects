public class Q1 {

	/*
	 * 1: complete implementation
	 */
	public static void reverseStack(Stack st) {
		st = new Stack();
		Queue qu = new Queue();

		for (int i = 0; i < st.size(); i++) {
			qu.enqueue(st.pop());
		}

		for (int x = 0; x < qu.size(); x++) {
			st.push(qu.dequeue());
		}
	}

}
