import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable   {
	private  String username; 
	private  String password; 
	private  String first; 
	private  String last; 

	
	public User(String username, String password, String first, String last){
		this.username = username; 
		this.password = password; 
		this.first = first;
		this.last = last; 
	}
	
	public void viewCourseBank(Database d){
		for (Course c: d.courseBank){
			System.out.println("Course Name: " + c.getName());
			System.out.println("Course ID: "+c.getCourseID());
			System.out.println("Students Enrolled: " + c.getStudentsEnrolled());
			System.out.println("Student Capacity: " + c.getMAX_STUDENTS_ALLOWED());
			System.out.println("\n");
		}
	}
	
	public String getUsername(){
		return this.username;
	}
	public String getPassword(){
		return this.password;
	}
	
	public String getFirstName(){
		return this.first;
	}
	public String getLastName(){
		return this.last;
	}
	public String getName(){
		return this.first + " " + this.last;
	}
	
}
