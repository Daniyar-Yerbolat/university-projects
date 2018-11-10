
public class Book 
{
	private String Author;
	private String Title;
	private int Pages;
	private String refNumber;
	
	public Book(String bookAuthor, String bookTitle, int bookPages)
	{
		Author = bookAuthor;
		Title = bookTitle;
		Pages = bookPages;
	}
	public String getAuthor()
	{
		return Author;
	}
	public String getTitle()
	{
		return Title;
	}
	public int getPages()
	{
		return Pages;
	}
	public void printPages()
	{
		System.out.println ("The number of pages in this book is " + getPages());
	}
	public void printAuthor()
	{
		System.out.println ("The title of this book is "+ getTitle());
	}
	public void printTitle()
	{
		System.out.println ("The author of this book is "+getAuthor());
	}
	public void printRef()
	{
		System.out.println ("The reference number is " + getRefNumber());
	}
	public void setRefNumber (String ref)
	{
		if (ref.length() >= 3)
		{
			refNumber = ref;
		}
		else
		{
			System.out.println("Error");
		}
	}
	public String getRefNumber ()
	{
		return refNumber;
	}
	
	public static void main (String [] args)
	{
		Book a = new Book("Harry Potter", "Rolling", 500);
		a.printAuthor();
		a.printTitle();
		a.setRefNumber("avdc");
		a.printPages();
		a.printRef();
		
	}
}
