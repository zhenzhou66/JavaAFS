package javaafs;

public class Student extends User {

    public Student(String userID, String name, String email, String phone, String moduleID) {
        super(userID, name, email, phone, moduleID);
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String getPrefix() {
        return "S";
    }
}
