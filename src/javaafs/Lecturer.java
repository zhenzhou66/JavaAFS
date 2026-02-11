package javaafs;

public class Lecturer extends UserManage {

    public Lecturer(String userID, String name, String email, String phone, String moduleID) {
        super(userID, name, email, phone, moduleID);
    }

    @Override
    public String getRole() {
        return "Lecturer";
    }

    @Override
    public String getPrefix() {
        return "L";
    }
}
