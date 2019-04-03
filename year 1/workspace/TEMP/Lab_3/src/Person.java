
public class Person 
{
	private String name;
	private int age;
	
	public Person(String myname, int myage)
	{
		name = myname;
		age = myage;
	}
	
	public int getage()
	{
		return age;
	}
	public String getname()
	{
		return name;
	}
	public void setage(int fage)
	{
		 age = fage;
	}
	public void printDetails()
	{
		System.out.println("The name of this person is "+ getname());
	}
	
}
