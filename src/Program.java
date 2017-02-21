import java.io.*;
import java.util.*;

public class Program implements Serializable {
		
	public static void main(String[] args) {
		 
		String fileName = "database1.ser";

		//Read in file part of the assignment to populate course bank
		Scanner input = new Scanner(System.in);
		File file = new File("src/MyUniversityCourses.csv");
		
		
		Database database = null;
		//De-serializing work and populating null database object
		try {
			FileInputStream fiss = new FileInputStream(fileName);
			ObjectInputStream oiss = new ObjectInputStream(fiss);
			database = (Database) oiss.readObject();
			oiss.close();
			fiss.close();
			System.out.println("Successfully read Serialized data.");
		} catch (FileNotFoundException ee) {
			ee.printStackTrace();
		} catch (IOException ioee) {
			ioee.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}
		
		//just for proof-checking my work by displaying contents of serialized work to see if deserialization worked
		System.out.println("List of all courses in CourseBank post-serialization.");
		for (Course c: database.courseBank){
			System.out.println("\t"+c.getCourseID() + "  "+c.getName());
		}
		
		System.out.println("\nList of all students in StudentBank post-serialization.");
		for (Student c: database.studentBank){
			System.out.println("\t"+c.getName());
		}
		
		//already loaded contents of .csv file in to serialization, no need to do it again
		//see code here for what I did to do that
//		try {
//			Scanner inputFile = new Scanner(file);
//			while (inputFile.hasNextLine()) {
//				String line = inputFile.nextLine();
//
//				// split up the line based on our delimiter
//				// (in this case it's the '-' character)
//				String[] splitLine = line.split(",");
//				Course course = new Course(splitLine[0], splitLine[1], Integer.parseInt(splitLine[2]),
//						Integer.parseInt(splitLine[3]), splitLine[4], splitLine[5], Integer.parseInt(splitLine[6]),
//						splitLine[7]);
//				database.courseBank.add(course);
//				//inputFile.close();
//			}
//		} catch (FileNotFoundException e) {
//			System.out.println(e);
//		}

		//create Admin 
		Admin a = new Admin("Admin", "Admin001", "Zack", "Kimelheim");
	
		
		System.out.println("\n\t***NYU Course Registration System Software***");
		System.out.println("Student Login (s) \nAdmin Login   (a) ");
		System.out.print("\n\t Enter here:  ");
		String sora = input.nextLine().toLowerCase();

		// user is admin
		if (sora.equals("a")) {
			System.out.print("Admin Username: ");
			String username = input.nextLine();

			// username matched
			if (a.getUsername().equalsIgnoreCase(username)) {
				System.out.print("Admin Password: ");
				String password = input.nextLine();

				// password matched we are in!
				if (a.getPassword().equalsIgnoreCase(password)) {
					System.out.println("\n\n\tSuccess! Logging you in...\n");

					// enter actions here
					System.out.println("\t\t*Menu Options*");
					System.out.println("\t(1) Create Course\n\t(2) Delete course\n\t(3) Edit Course\n\t"
							+ "(4) Display Course Info\n\t"
							+ "(5) Register Student\n\t(6) Delete Student\n\t(7) View Course List\n\t(8) View Full Courses\n\t"
							+ "(9) Submit Full Courses\n\t(10) View Course Student Roster\n\t"
							+ "(11) View Student Schedule\n\t" + "(12) Sort Course Bank\n\t(13) Exit");
					System.out.print("\n\t_______________:");
					int sel = input.nextInt();
					while (sel != 13) {
						// Create Course
						if (sel == 1) {
							a.createCourse(database);
						}
						// Delete course
						else if (sel == 2) {
							a.delete(database);
						}
						// Edit Course
						else if (sel == 3) {
							a.editCourse(database);
						}

						// display course info
						else if (sel == 4) {
							a.displayCourseInfo(database);
						}
						// register student
						else if (sel == 5) {
							a.registerStudent(database);
						}
						// delete student
						else if (sel == 6) {
							a.deleteStudent(database);
						}
						// view course list
						else if (sel == 7) {
							a.viewCourseBank(database);
						}

						// view full courses
						else if (sel == 8) {
							a.viewFullCourses(database);
						}

						// file full courses
						else if (sel == 9) {
							a.documentFullCourses(database);
						}

						// view course roster
						else if (sel == 10) {
							a.viewStudentRoster(database);

						}
						// view student schedule
						else if (sel == 11) {
							a.viewStudentEnrollment(database);
						}

						// sort course bank
						else if (sel == 12) {
							a.sortCourseBank(database);

						} else if (sel == 13) {
							System.out.println("\n\n\tLogging you out....");
						}

						// exit
						else {
							System.out.println("\n\n\tPlease enter a valid command....");

						}

						System.out.println("\n\tReturn to main menu?");
						System.out.println("\t(1)yes\n\t(2)no");
						System.out.print("\t_______________:");
						int cont = input.nextInt();
						if (cont == 1) {
							System.out.println("\t\t*Menu Options*");
							System.out.println("\t(1) Create Course\n\t(2) Delete course\n\t(3) Edit Course\n\t"
									+ "(4) Display Course Info\n\t"
									+ "(5) Register Student\n\t(6) Delete Student\n\t(7) View Course List\n\t(8) View Full Courses\n\t"
									+ "(9) Submit Full Courses\n\t(10) View Course Student Roster\n\t"
									+ "(11) View Student Schedule\n\t" + "(12) Sort Course Bank\n\t(13) Exit");
							System.out.print("\n\t_______________:");
							sel = input.nextInt();
						} else if (cont == 2) {
							break;
						} else {
							System.out.println("Enter valid selection.");
						}
					}
					System.out.println("\n\n\tLogging you out....");

				} else {
					System.out.println("wrong password. try again.");
				}
			} else {
				System.out.println("could not find username. try again.");
			}
		}
		// user is student
		else if (sora.equals("s")) {
			System.out.print("Student Username: ");
			String username = input.nextLine();

			for (Student s: database.studentBank){

				// username matched
				if (s.getUsername().equalsIgnoreCase(username)) {
					// ask for password now
					System.out.print("Student Password: ");
					String password = input.nextLine();

					// password matched
					if (s.getPassword().equals(password)) {
						System.out.println("\n\n\tSuccess! Logging you in...\n");

						// enter actions here
						System.out.println("\t\t*Menu Options*");
						System.out.println("\t(1) View Course Bank\n\t(2) View Open Courses\n\t(3) Register\n\t"
								+ "(4) Withdraw\n\t" + "(5) View Schedule\n\t(6) Exit");
						System.out.print("\n\t_______________:");
						int sel = input.nextInt();
						while (sel != 6) {

							// view course bank
							if (sel == 1) {
								s.viewCourseBank(database);
							}
							// view open courses
							if (sel == 2) {
								s.viewOpenCourses(database);
							}
							// register
							if (sel == 3) {
								s.register(database);
							}
							// withdraw
							if (sel == 4) {
								s.withdraw(database);
							}
							// view schedule
							if (sel == 5) {
								s.viewSchedule();
							}
							// exit
							if (sel == 6) {
								break;
							}
							System.out.println("\n\tReturn to main menu?");
							System.out.println("\t(1)yes\n\t(2)no");
							System.out.print("\t_______________:");
							int cont = input.nextInt();
							if (cont == 1) {
								System.out.println("\t\t*Menu Options*");
								System.out.println("\t(1) View Course Bank\n\t(2) View Open Courses\n\t(3) Register\n\t"
										+ "(4) Withdraw\n\t" + "(5) View Schedule\n\t(6) Exit");
								System.out.print("\n\t_______________:");
								sel = input.nextInt();
							} else {
								System.out.println("Logging you out.");
								break;
							}

						}
					} else {
						System.out.println("wrong password. try again.");
					}
				}
			}
		}


		// serialization
		try {
			FileOutputStream foss = new FileOutputStream(fileName);
			ObjectOutputStream oss = new ObjectOutputStream(foss);
			oss.writeObject(database);
			oss.close();
			foss.close();
			System.out.println("\n\n\tSuccessfully serialized work...");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();		
			}
		

		

	}
}
