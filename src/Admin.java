import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

//Admin extends User SuperClass and inherits AdminDuties Interface and Serializable 
public class Admin extends User implements AdminDuties, Serializable {
		
	public Admin(String username, String password, String first, String last) {
		super(username, password, first, last);
	}
	
	public void createCourse(Database d){
	
		Scanner input = new Scanner(System.in);
		
		System.out.print("\tEnter course name: ");
		String name = input.nextLine();
		System.out.print("\tEnter course ID: ");
		String courseID = input.nextLine();
		System.out.print("\tEnter Student Capacity: ");
		int MAX_STUDENTS_ALLOWED = input.nextInt();
		System.out.print("\tEnter Current Students Enrolled Amount: ");
		int studentsEnrolled = input.nextInt(); 
		String names = ""; 
		System.out.print("\tEnter course Instructor:");
		input.nextLine();
		String instructor = input.nextLine();
		System.out.print("\tEnter Section Number:");
		int sectionNum = input.nextInt();
		System.out.print("\tEnter Location: ");
		input.nextLine();
		String location = input.nextLine();
	
		Course c = new Course(name, courseID, MAX_STUDENTS_ALLOWED, studentsEnrolled, names, instructor, sectionNum,
				location);
		d.courseBank.add(c);
		System.out.println("\t\t\tSuccessfully created course.");
	}

	public void delete(Database d) {
		Scanner input = new Scanner(System.in);
		System.out.println("\tWhat course would you like to delete? (Enter Course ID)");
		String id = input.next();
		System.out.println("\tWhat course section? ");
		int section = input.nextInt();
		for (Iterator<Course> it = d.courseBank.iterator(); it.hasNext(); ) {
		    Course my = it.next();
		    if (my.getCourseID().equalsIgnoreCase(id)){
				if(my.getSectionNum() == section){		       
					it.remove();
				}
		    }
		}
		System.out.println("\n\tSuccessfully deleted course.");
	}

	public void editCourse(Database d) {
		Scanner input = new Scanner(System.in);

		System.out.println("What course would you like to Edit? (Enter Course ID)");
		String id = input.next();
		System.out.println("What course section? ");
		int section = input.nextInt();

		for (Course c : d.courseBank) {
			if (c.getCourseID().equalsIgnoreCase(id)) {
				if (c.getSectionNum() == section) {

					System.out.println("What field you like to edit?");
					// c i m t s l
					System.out.println("(1)Course Name\n(2)Course ID\n(3)Max Students Allowed\n" + "(4)Instructor\n"
							+ "(5)Section Number\n" + "(6)Location");
					int choice = input.nextInt();

					System.out.println("Type the revision you'd like done");
					input.nextLine();
					String revision = input.nextLine();

					for (Course a : d.courseBank) {
						// found the course to edit
						if (a.getCourseID() == c.getCourseID()) {

							// change course name
							if (choice == 1) {
								a.setName(revision);
							}
							// change course id
							if (choice == 2) {
								a.setCourseID(revision);
							}
							// change max students
							if (choice == 3) {
								int x = Integer.parseInt(revision);
								a.setMAX_STUDENTS_ALLOWED(x);
							}
							// change instructor
							if (choice == 4) {
								a.setInstructor(revision);

							}
							// change section number
							if (choice == 5) {
								int x = Integer.parseInt(revision);
								a.setSectionNum(x);
							}
							// change location
							if (choice == 6) {
								a.setLocation(revision);
							}
						}
					}
					System.out.println("\n\tSuccess! Continue editing?" );
					System.out.print("\n\t\t(1) yes\n\t\t(2) no");
					System.out.print("\n\t_______________:");
					if (input.nextInt() == 1){
						editCourse(d);
					}				}
			}
		}
	}

	public void displayCourseInfo(Database d) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Course ID: ");
		String id = input.next();
		System.out.println("Enter Course Section: ");
		int section = input.nextInt();
		for (Course c : d.courseBank) {
			if (c.getCourseID().equalsIgnoreCase(id)) {
				if (c.getSectionNum() == section) {
					c.printInfo();
				}
			}
		}
	}

	public void registerStudent(Database d) {
		Scanner input = new Scanner(System.in);
		System.out.println("Student Username: ");
		String username = input.next();
		System.out.println("Student password: ");
		String password = input.next();
		System.out.println("Student First name: ");
		String first = input.next();
		System.out.println("Student Last name: ");
		String last = input.next(); 
		
		Student s = new Student(username, password, first, last, d);
		System.out.println("Successfully registered student");
	}

	public void viewCourseBank(Database d) {
		for (Course c : d.courseBank) {
			System.out.println("Course Name: " + c.getName());
			System.out.println("Course ID: " + c.getCourseID());
			System.out.println("Section: " + c.getSectionNum());
			System.out.println("Students Enrolled: " + c.getStudentsEnrolled());
			System.out.println("Student Capacity: " + c.getMAX_STUDENTS_ALLOWED());
			System.out.println("\n");
		}
	}
	
	public void viewStudentBank(Database d) {
		for (Student c : d.studentBank) {
			System.out.println("Student Name: " + c.getName());
		}
	}
	
	//*not needed* extra method put in place to delete student from studentbank by admin
	public void deleteStudent(Database d) {
		Scanner input = new Scanner(System.in);
		System.out.println("Student Username: ");
		String username = input.next();
		System.out.println("Student Last name: ");
		String last = input.next(); 
		for (Student c : d.studentBank) {
			if (c.getUsername().equalsIgnoreCase(username)){
				if (c.getLastName().equalsIgnoreCase(last)){
					//student is enrolled in classes, needs to remove from these courses too
//					if (!(c.getStuSchedule().isEmpty())){
//						for (Course cc : c.getStuSchedule()){
//							for (Course cr : d.courseBank){
//								if (cc.getCourseID().equalsIgnoreCase(cr.getCourseID())){
//									cr.removeStudent(c);
//								}
//							}
//						}
//					}
					d.studentBank.remove(c);
				}
			}
		}
		System.out.println("\tStudent successfully deleted.");
	}

	public void viewFullCourses(Database d) {
		int count = 0; 
		for (Course c : d.courseBank) {
			if (c.getMAX_STUDENTS_ALLOWED() == c.getStudentsEnrolled()) {
				count++;
				System.out.println("Course Name: " + c.getName());
				System.out.println("Course ID: " + c.getCourseID());
				System.out.println("Students Enrolled: " + c.getStudentsEnrolled());
				System.out.println("Student Capacity: " + c.getMAX_STUDENTS_ALLOWED());
				System.out.println("\n");
			}
		}
		if (count == 0){
			System.out.println("\tAll courses are currently open.");
		}
	}

	public void documentFullCourses(Database d) {
		File file = new File("src/fullcourses.txt");
		try {
			PrintWriter writer = new PrintWriter(file);
			for (Course c : d.courseBank) {
				if (c.getMAX_STUDENTS_ALLOWED() == c.getStudentsEnrolled()) {
					writer.println(c.getName() + c.getCourseID() + c.getSectionNum());
				}

			}
			System.out.println("\nCompleted file. ");
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		
	}


	public void viewStudentRoster(Database d) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Course ID: ");
		String id = input.next();
		System.out.println("Enter Course Section: ");
		int section = input.nextInt();
		for (Course c: d.courseBank){
			if (c.getCourseID().equalsIgnoreCase(id)){
				if (c.getSectionNum()==section){
					System.out.println(c.getStudentRoster());	
					if (c.getStudentRoster().equals(" ")){
						System.out.println("No students enrolled yet.");
					}
			}
		}
	}
		
	}
	
	
	public void viewStudentEnrollment(Database d) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter student first name:");
		String first = input.next();
		System.out.println("Enter student last name: ");
		String last = input.next();
		
		for (Student s: d.studentBank){
			if (s.getLastName().equalsIgnoreCase(last)){
				if (s.getFirstName().equalsIgnoreCase(first)){
					if (s.getStuSchedule().isEmpty()){
						System.out.println(s.getName() + " currently not enrolled in any courses.");
					}
					s.viewSchedule();
					
				}
			}
		}
		
	}

	//sort course bank based on the student enrollment amount 
	public void sortCourseBank(Database d) {
		Collections.sort(d.courseBank);
		viewCourseBank(d);
	}


}