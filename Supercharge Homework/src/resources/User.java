package resources;

public class User {

	private final String firstName;
	private final String lastName;
	private final String name;
	
	public User(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
		this.name = firstName + " " + lastName;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getName(){
		return this.name;
	}
}
