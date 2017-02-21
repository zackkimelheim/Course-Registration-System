import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner; 

public class Student extends User implements StudentDuties, Serializable {
	
	//save student object's schedule in an arraylist 
	private ArrayList<Course> schedule = new ArrayList<>();
	
	public Student(String username, String password, String first, String last, Database d){
		super(username, password,first,last);
		d.studentBank.add(this);
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
	
	public ArrayList<Course> getStuSchedule(){
		return this.schedule;
	}
	
	public void viewOpenCourses(Database d){
		int count = 0; 
		for (Course c: d.courseBank){
			if (c.getMAX_STUDENTS_ALLOWED() > c.getStudentsEnrolled()){
				count ++;
				System.out.println("Course Name: " + c.getName());
				System.out.println("Course ID: "+c.getCourseID());
				System.out.println("Students Enrolled: " + c.getStudentsEnrolled());
				System.out.println("Student Capacity: " + c.getMAX_STUDENTS_ALLOWED());
				System.out.println("\n");
			}
		}
		if (count == 0){
			System.out.println("\tAll courses are currently closed.");
		}
	}
	
	public void register(Database d){
		Scanner input = new Scanner (System.in);
		System.out.print("\n\tEnter Course Id: ");
		String id = input.next();
		System.out.print("\tEnter Course Section: ");
		int section = input.nextInt();
		
		for (Course c: d.courseBank){
			if (c.getCourseID().equalsIgnoreCase(id)){
				if(c.getSectionNum() == section){
					c.addStudent(this);
					schedule.add(c);
					System.out.println("\n\tSuccess! Register for more?" );
					System.out.print("\n\t\t(1) yes\n\t\t(2) no");
					System.out.print("\n\t_______________:");
					if (input.nextInt() == 1){
						register(d);
					}
				}		
			} 
		}
	}

	public void withdraw(Database d){
		Scanner input = new Scanner (System.in);
		System.out.print("\n\tEnter Course ID: ");
		String id = input.next();
		System.out.print("\tEnter Course Section: ");
		int section = input.nextInt();

		for (Iterator<Course> it = schedule.iterator(); it.hasNext();) {
		    Course c = it.next();
		    if (c.getCourseID().equalsIgnoreCase(id)){
				if(c.getSectionNum() == section){		       
					it.remove();
					c.removeStudent(this);
					System.out.println("\n\tClass successfully removed from schedule." );
				}
				else {
					System.out.println("Could not remove course. Try again later.");
				}
		    
		    }
				}
	}
		
	public void viewSchedule(){
		for (Course d: schedule){
				System.out.printf("Course : \n %s --- %d --- %s --- %s --- %s ", d.getName(),d.getSectionNum(),
		d.getCourseID(),d.getInstructor(),d.getLocation());
				System.out.println();
		}
	}
}
	

