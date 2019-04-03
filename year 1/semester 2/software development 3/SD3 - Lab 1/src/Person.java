public class Person {
	
	private String fname;
	private String lname;
	private int age;
	
	public Person(String firstname,String lastname,int age){
		fname = firstname;
		lname = lastname;
		this.age = age;
	}
	
	// get methods
	public String getFirstName(){ return fname;}
	public String getLastName(){ return lname;}
	public int getAge(){ return age;}
	
	// set methods
	public void setFirstName(String name){ fname = name; }
	public void setLastName(String name){ lname = name;}
	public void setAge(int age){ this.age = age;}
	
	
	public String toString(){
		return "Name : " + fname + " " + lname + " Age :" + age;
	}
}
