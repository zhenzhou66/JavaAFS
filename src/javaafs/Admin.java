package javaafs;

public class Admin extends UserManage {

    public Admin(String userID, String name, String email, String phone, String moduleID) {
        super(userID, name, email, phone, moduleID);
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    @Override
    public String getPrefix() {
        return "A";
    }
}
