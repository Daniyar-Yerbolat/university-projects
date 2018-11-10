package lab2.network3;

import java.util.ArrayList;

public class EventPost extends Post {
	String eventtype;
	
	public EventPost(String author,String etype) {
		super(author);
		eventtype = etype;
	}
	
	public void display()
    {
		super.display();
		System.out.println();
		System.out.println(eventtype);
    	
    }

}
