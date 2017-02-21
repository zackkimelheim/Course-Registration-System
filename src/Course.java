import java.io.Serializable;
import java.util.ArrayList; 

public class Course implements Serializable, Comparable<Course> {
	private  String name; 
	private  String courseID; 
	private  int MAX_STUDENTS_ALLOWED;
	private  int num_students_registered = 0;
	private  ArrayList<Student> students = new ArrayList<>(); 
	private  String instructor; 
	private  int sectionNum; 
	private  String location; 
	
	//string names is a placeholder since it will always be null upon creating course with 0 students
	public Course (String name, String courseID, int MAX_STUDENTS_ALLOWED, int studentsEnrolled, String empty, String instructor, int sectionNum,String location){
		this.name = name; 
		this.courseID = courseID; 
		this.MAX_STUDENTS_ALLOWED = MAX_STUDENTS_ALLOWED; 
		this.instructor = instructor; 
		this.sectionNum = sectionNum; 
		this.location = location; 
		this.num_students_registered += studentsEnrolled; 
	}
	public void addStudent(Student s){
		students.add(s);
		num_students_registered++;
	}
	
	public void removeStudent(Student s){
		students.remove(s);
		num_students_registered--;
	}
	public String getName(){
		return this.name; 
	}
	public void setName(String s){
		this.name = s; 
	}
	
	public String getStudentRoster(){
		String roster = " ";
		for (Student s: students){
			roster += s.getName() + "\n";
		}
		return roster;
	}
	public String getCourseID(){
		return this.courseID;
	}
	public void setCourseID(String d){
		this.courseID = d; 
	}
	public int getMAX_STUDENTS_ALLOWED(){
		return this.MAX_STUDENTS_ALLOWED;
	}
	public void setMAX_STUDENTS_ALLOWED(int x){
		this.MAX_STUDENTS_ALLOWED = x; 
	}
	public String getInstructor(){
		return this.instructor;
	}
	public void setInstructor(String s){
		this.instructor = s; 
	}
	public int getSectionNum(){
		return this.sectionNum;
	}
	public void setSectionNum(int g){
		this.sectionNum = g;
	}
	public String getLocation(){
		return this.location;
	}
	public void setLocation(String s){
		this.location = s; 
	}
	public int getStudentsEnrolled(){
		return this.num_students_registered;
	}
	
	public int compareTo(Course other) {
		Integer a = new Integer(this.num_students_registered);
		Integer b = new Integer(other.num_students_registered);
        return a.compareTo(b);     
    }
	
	public void printInfo(){
		System.out.println("Course Name: " + this.name);
		System.out.println("Course ID#: " + this.courseID);
		System.out.println("Student Capacity: " + this.MAX_STUDENTS_ALLOWED);
		System.out.println("Students Enrolled: " + this.num_students_registered);
		System.out.println("Instructor: " + this.instructor);
		System.out.println("Section Number: " + this.sectionNum);
		System.out.println("Location: " + this.location);
	}
	
	 
	
	
}
