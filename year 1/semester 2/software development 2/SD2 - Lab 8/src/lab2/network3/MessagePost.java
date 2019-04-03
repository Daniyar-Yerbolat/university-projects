package lab2.network3;


/**
 * This class stores information about a post in a social network news feed. 
 * The main part of the post consists of a (possibly multi-line)
 * text message. Other data, such as author and time, are also stored.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.3
 */
public class MessagePost extends CommentedPost
{
    private String message;  // an arbitrarily long, multi-line message

    public MessagePost(String author, String text)
    {
        super(author);
        message = text;
    }

    public String getText()
    {
        return message;
    }

    public void display()
    {
    	super.display();
    	printShortSummary();
        System.out.println(message);
    }
    
	private void printShortSummary(){
    	System.out.println("Message post from " + super.getUsername());
    }
}
