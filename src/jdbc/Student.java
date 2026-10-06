package jdbc;

public class Student {
	private int studentID;
	private String name;
	private int age;
	private String section;
	
	Student(int studentID, String name, int age, String section){
		this.setStudentID(studentID);
		this.setName(name);
		this.setAge(age);
		this.setSection(section);
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
}
