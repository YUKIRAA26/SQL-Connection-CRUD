package jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class StudentManager {
	private final static List<Student> student = new ArrayList<>();
	
	public void addStudent(){
		try {
			String insert = "INSERT INTO students(name, age, section) VALUES (?,?,?)";
			
			Connection con = DBConnector.getConnection();
			PreparedStatement ps = con.prepareStatement(insert);

			String name = Exceptions.StringException("Enter Student name: ");
			int age = Exceptions.IntegerException("Enter age: ");
			String section = Exceptions.StringException("Enter Section: ");
			
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, section);
			ps.executeUpdate();
			
			System.out.println("Succesfully added student! ");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewStudents() {
		try {
			student.clear();
			Connection con = DBConnector.getConnection();
			Statement st = con.createStatement();
			ResultSet rt = st.executeQuery("SELECT * FROM students");
			
			while(rt.next()) {
				int id = rt.getInt("studentID");
				String name = rt.getString("name");
				int age = rt.getInt("age");
				String section = rt.getString("section");
				
				student.add(new Student(id,name,age,section));
			}
			
			System.out.println("--- Students ---");
			for(int i = 0; i<student.size();i++) {
				Student s = student.get(i);
				System.out.println((i + 1) + ".");
				System.out.println("Student id: " + s.getStudentID());
				System.out.println("Name: " + s.getName());
				System.out.println("Age: " + s.getAge());
				System.out.println("Section: " + s.getSection());
				System.out.println();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStudent() {
		
		try {
			int id = idChecker();
			Connection con = DBConnector.getConnection();
			
			String choice = Exceptions.StringException("What do u want to update (name | age | section): ").toLowerCase();
			
			if(choice.equals("name")) {
				String update = "UPDATE students SET name = ? WHERE studentID = ?";
				PreparedStatement pt = con.prepareStatement(update);
				String name = Exceptions.StringException("Enter new name: ");
				pt.setString(1, name);
				pt.setInt(2, id);
				System.out.println("Succesfully updated name! ");
				pt.executeUpdate();
			}else if(choice.equals("age")) {
				String update = "UPDATE students SET age = ? WHERE studentID = ?";
				PreparedStatement pt = con.prepareStatement(update);
				int age = Exceptions.IntegerException("Enter new age: ");
				pt.setInt(1, age);
				pt.setInt(2, id);
				System.out.println("Succesfully updated age! ");
				pt.executeUpdate();
			}else if(choice.equals("section")) {
				String update = "UPDATE students SET section = ? WHERE studentID = ?";
				PreparedStatement pt = con.prepareStatement(update);
				String section = Exceptions.StringException("Enter new Section: ");
				pt.setString(1, section);
				pt.setInt(2, id);
				System.out.println("Succesfully updated section! ");
				pt.executeUpdate();
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteStudent() {
		try {
			String delete = "DELETE FROM students WHERE studentID = ?";
			
			int id = idChecker();
			Connection con = DBConnector.getConnection();
			PreparedStatement pt = con.prepareStatement(delete);
			pt.setInt(1, id);
			
			pt.executeUpdate();
			System.out.println("Succesfully deleted student! ");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private int idChecker() {
		while (true) {
	        viewStudents();

	        int id = Exceptions.IntegerException("Enter student id: ");

	        for (Student s : student) {
	            if (s.getStudentID() == id) {
	                return id;
	            }
	        }

	        System.out.println("ID does not exist!");
	    }
	}
}
