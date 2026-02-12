
package javaafs;

import javax.swing.JFrame;

public abstract class User {

        protected String userID;
        protected String name;
        protected String email;
        protected String phone;
        protected String moduleID;
        
        protected UserFunctions func = new UserFunctions();

        public User(String userID, String name, String email, String phone, String moduleID) {
            this.userID = userID;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.moduleID = moduleID;
        }

        // Abstract methods (must be implemented by subclasses)
        public abstract String getRole();
        public abstract String getPrefix();
        
        public void logout(JFrame currentFrame) {
            func.logout(currentFrame, userID);
        }

        // Common getters
        public String getUserID() { return userID; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getModuleID() { return moduleID; }
        
        
        
        
    }
