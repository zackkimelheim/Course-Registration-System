import java.util.ArrayList;

public interface AdminDuties {

	public void createCourse(Database d);
	
	public void delete(Database d);

	public void editCourse(Database d);

	public void displayCourseInfo(Database d);

	public void registerStudent(Database d);

	public void viewCourseBank(Database d);

	public void viewFullCourses(Database d);

	public void documentFullCourses(Database d);

	public void viewStudentRoster(Database d);

	public void viewStudentEnrollment(Database d);

	public void sortCourseBank(Database d);

}
