package lab2.network3;

import java.util.ArrayList;

public class CommentedPost extends Post {
	private int likes;
	private ArrayList<String> comments;

	public CommentedPost(String author) {
		super(author);
		likes = 0;
		comments = new ArrayList<String>();
	}

	public void like() {
		likes++;
	}

	public void unlike() {
		if (likes > 0) {
			likes--;
		}
	}

	public void addComment(String text) {
		comments.add(text);
	}

	public void display() {
		super.display();
			if (likes > 0) {
			System.out.println("  -  " + likes + " people like this.");
		} else {
			System.out.println();
		}

		if (comments.isEmpty()) {
			System.out.println("   No comments.");
		} else {
			System.out.println("   " + comments.size() + " comment(s). Click here to view.");
		}
	}

}
