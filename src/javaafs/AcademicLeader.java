package javaafs;

public class AcademicLeader extends User {

    public AcademicLeader(String userID, String name, String email, String phone, String moduleID) {
        super(userID, name, email, phone, moduleID);
    }

    @Override
    public String getRole() {
        return "AcademicLeader";
    }

    @Override
    public String getPrefix() {
        return "AL";
    }
}
