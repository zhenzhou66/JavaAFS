
package javaafs;

public abstract class UserManage {

        protected String userID;
        protected String name;
        protected String email;
        protected String phone;
        protected String moduleID;

        public UserManage(String userID, String name, String email, String phone, String moduleID) {
            this.userID = userID;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.moduleID = moduleID;
        }

        // Abstract methods (must be implemented by subclasses)
        public abstract String getRole();
        public abstract String getPrefix();

        // Common getters
        public String getUserID() { return userID; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getModuleID() { return moduleID; }
    }
