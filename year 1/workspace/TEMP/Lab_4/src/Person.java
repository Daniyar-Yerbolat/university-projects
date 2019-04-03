public class Person 
{
	private String name;
	private int age;
	
	public Person(String myName, int myAge)
	{
		name = myName;
		age = myAge;
	}
	
	public int getAge()
	{
		return age;
	}
	public String getName()
	{
		return name;
	}
	public void setage(int sAge)
	{
		 age = sAge;
	}
	public void printDetails()
	{
		System.out.println("The name of this person is "+ getName());
	}
	public static void main(String [] args)
	{
		Person p = new Person("Eliza",66);
		p.printDetails();
		
	}
}