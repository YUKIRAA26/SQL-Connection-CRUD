package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class JDBCExample {
	private final static StudentManager student = new StudentManager();
	
	public static void main(String[] args){
		
		while(true) {
			displayMenu();
			int choice = Exceptions.IntegerException("Choice: ");
			
			switch(choice) {
				case 1 -> student.addStudent();
				case 2 -> student.viewStudents();
				case 3 -> student.updateStudent();
				case 4 -> student.deleteStudent();
				case 5 -> {
					System.out.println("Thank you! ");
					return;
				}
			}
		}
		
	}

	
	private static void displayMenu() {
		
		System.out.println("--- Student Management ---");
		System.out.println("1. Add Student");
		System.out.println("2. View Student");
		System.out.println("3. Update Student");
		System.out.println("4. Delete Student");
		System.out.println("5. Exit");
		
	}
}
