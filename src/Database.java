import java.io.Serializable;
import java.util.ArrayList;


//Database class that holds the Arraylists for courseBanks and studentBanks 
public class Database implements Serializable {
	public  ArrayList<Course> courseBank;
	public  ArrayList<Student> studentBank;
	
	public Database(){
		this.courseBank = new ArrayList<>();
		this.studentBank = new ArrayList<>();
	}
	
}




