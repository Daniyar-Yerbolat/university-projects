package lab2.network3;

import java.util.ArrayList;

/**
 * This class stores information about a news feed post in a 
 * social network. Posts can be stored and displayed. This class
 * serves as a superclass for more specific post types.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.3
 */
public class Post 
{
    private String username;  // username of the post's author
	private long timestamp;

    public Post(String author)
    {
        username = author;
        timestamp = System.currentTimeMillis();
    }
    
    public String getUsername() {
		return username;
	}

    public long getTimeStamp()
    {
        return timestamp;
    }
    
    public void display()
    {
    	System.out.println(username);
		System.out.print(timeString(timestamp));
    }
    protected String timeString(long time)
    {
        long current = System.currentTimeMillis();
        long pastMillis = current - time;      // time passed in milliseconds
        long seconds = pastMillis/1000;
        long minutes = seconds/60;
        if(minutes > 0) {
            return minutes + " minutes ago";
        }
        else {
            return seconds + " seconds ago";
        }
    }
}
