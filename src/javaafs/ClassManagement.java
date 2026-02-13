
package javaafs;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.Date;


public class ClassManagement extends javax.swing.JFrame {
    
    private final String username;
    private UserFunctions func = new UserFunctions();
    private final String filePath = "users.txt";
    private Map<String, String> moduleMap = new HashMap<>();

    private String[] allTimes = {
        "08:00","08:15","08:30","09:00","09:15","09:30",
        "10:00","10:15","10:30","11:00","11:15","11:30",
        "12:00","12:15","12:30","13:00","13:15","13:30",
        "14:00","14:15","14:30","15:00","15:15","15:30",
        "16:00","16:15","16:30","17:00","17:15","17:30",
        "18:00"
    };




    public ClassManagement(String userID) {
        this.username = userID;
        initComponents();
        loadModules();
        setCalendarDateLimit(); 
        updateHistoryTable();
        
        jCalendar1.addPropertyChangeListener("calendar", evt -> {
            updateStartTimeOptions();
        });

        updateStartTimeOptions();
    
         // Row selection only
        historyTable.setRowSelectionAllowed(true);
        historyTable.setColumnSelectionAllowed(false);
        historyTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        // Enable Delete button only if a row is selected
        deleteButton.setEnabled(historyTable.getSelectedRow() != -1);

        // Listen for row selection changes
        historyTable.getSelectionModel().addListSelectionListener(e -> {
            deleteButton.setEnabled(historyTable.getSelectedRow() != -1);
        });
        
        
        // Deselect row when clicking empty space
        historyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = historyTable.rowAtPoint(evt.getPoint());
                if (row == -1) {  // clicked outside any row
                    historyTable.clearSelection();
                }
            }
        });

    }
    
    
    
//    private void updateStartTimeOptions() {
//
//        Date selectedDate = jCalendar1.getDate();
//        if (selectedDate == null) return;
//
//        Calendar today = Calendar.getInstance();
//        Calendar selected = Calendar.getInstance();
//        selected.setTime(selectedDate);
//
//        startTime.removeAllItems();
//
//        boolean isToday =
//                today.get(Calendar.YEAR) == selected.get(Calendar.YEAR) &&
//                today.get(Calendar.DAY_OF_YEAR) == selected.get(Calendar.DAY_OF_YEAR);
//
//        int currentMinutes =
//                today.get(Calendar.HOUR_OF_DAY) * 60 +
//                today.get(Calendar.MINUTE);
//        
//        // Minimum 1 hour duration
//        // Last end time is 18:00
//        // So latest start time allowed is 17:00
//        int latestStartAllowed = 18 * 60 - 60; // 17:00
//
//        boolean hasAvailableTime = false;
//
//        for (String time : allTimes) {
//            
//            int timeMinutes = timeToMinutes(time);
//            if (timeMinutes < 0) continue;
//
//            if (!isToday) {
//                // Future date → allow any time that can still fit 1 hour
//                if (timeMinutes <= latestStartAllowed) {
//                    startTime.addItem(time);
//                    hasAvailableTime = true;
//                }
//            } else {
//                // Today → must be future time AND allow 1 hour
//                if (timeMinutes > currentMinutes &&
//                    timeMinutes <= latestStartAllowed) {
//
//                    startTime.addItem(time);
//                    hasAvailableTime = true;
//                }
//            }
//            
//            
//        // If no available time → disable Save button
//        if (!hasAvailableTime) {
//            saveButton.setEnabled(false);
//
//            if (isToday) {
//                JOptionPane.showMessageDialog(this,
//                    "No available class time remaining for today.\n" +
//                    "Minimum class duration is 1 hour.");
//            }
//        } else {
//            saveButton.setEnabled(true);
//        }
//    }
//        
//        
//        
//    }
    
    
    
    
    private void updateStartTimeOptions() {

        startTime.removeAllItems();

        int latestStartAllowed = 18 * 60 - 60; // 17:00, since max end time = 18:00

        for (String time : allTimes) {
            int timeMinutes = timeToMinutes(time);
            if (timeMinutes < 0) continue;

            // Allow any time that can fit minimum 1-hour duration
            if (timeMinutes <= latestStartAllowed) {
                startTime.addItem(time);
            }
        }

        // Enable Save button if at least one start time exists
        saveButton.setEnabled(startTime.getItemCount() > 0);
    }




    // ================= LOAD MODULES =================
    private final void loadModules() {
        moduleName.removeAllItems();
        moduleMap.clear();
        
        ArrayList<String[]> modules = UserFunctions.readCSV("modules.txt");

        for (int i = 1; i < modules.size(); i++) { // start from 1
            String[] data = modules.get(i);
            if (data.length >= 2) {
                String moduleID = data[0].trim();
                String moduleNameText = data[1].trim();

                moduleName.addItem(moduleNameText);      // add to combo box
                moduleMap.put(moduleNameText, moduleID); // keep internal mapping
            }
        }

        
        
    }
    
    

    // ================= TIME VALIDATION =================
    private int timeToMinutes(String time) {
        try {
            String[] parts = time.split(":");
            return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
        } catch (Exception e) {
            return -1; // invalid time
        }
    }

    private boolean isValidDuration(String start, String end) {
        int startMinutes = timeToMinutes(start);
        int endMinutes = timeToMinutes(end);

        if (startMinutes < 0 || endMinutes < 0) {
            JOptionPane.showMessageDialog(this, "Invalid time format. Use HH:mm.");
            return false;
        }

        int duration = endMinutes - startMinutes;

        if (duration <= 0) {
            JOptionPane.showMessageDialog(this, "End time must be after start time.");
            return false;
        }

        if (duration < 60 || duration > 180) {
            JOptionPane.showMessageDialog(this, "Class duration must be between 1 and 3 hours.");
            return false;
        }

        return true;
    }
    
    
    private boolean isTimeOverlap(String s1, String e1, String s2, String e2) {
        return timeToMinutes(s1) < timeToMinutes(e2)
            && timeToMinutes(s2) < timeToMinutes(e1);
    }
    
    
    
    private boolean hasModuleTimeClash(String moduleID, String newStart, String newEnd) {
        ArrayList<String[]> classes = UserFunctions.readCSV("classes.txt");

        for (String[] data : classes) {

            if (data.length < 5) continue;

            String existingStart = data[1];
            String existingEnd = data[2];
            String existingModuleID = data[3];

            if (existingModuleID.equals(moduleID)) {
                if (isTimeOverlap(existingStart, existingEnd, newStart, newEnd)) {
                    return true;
                }
            }
        }

        return false;
    }

    

    
    private void setCalendarDateLimit() {
        Calendar today = Calendar.getInstance();

        // Remove time part (important)
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        // Minimum selectable date = today
        jCalendar1.setMinSelectableDate(today.getTime());

        // Maximum selectable date = today + 2 years
        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.YEAR, 2);
    
        // Disable all dates before today
        jCalendar1.setMinSelectableDate(today.getTime());
    }
    
    
    private boolean hasModuleClassTypeOnDate(String moduleID, String classType, Date date) {
        ArrayList<String[]> classes = UserFunctions.readCSV("classes.txt");

        for (int i = 1; i < classes.size(); i++) { // skip first row if it's header
            String[] cls = classes.get(i);
            if (cls.length < 7) continue;

            String existingModuleID = cls[4];
            String existingGroupID = cls[5];
            String existingClassID = cls[0];
            String existingClassName = cls[1]; // e.g., Lecture-M101

            // Determine existing class type from className
            String existingType = existingClassName.split("-")[0]; // Lecture or Tutorial

            Date classDate = UserFunctions.getClassDate(existingClassID);
            if (existingModuleID.equals(moduleID)
                    && existingType.equalsIgnoreCase(classType)
                    && classDate != null
                    && UserFunctions.formatDateToString(classDate).equals(UserFunctions.formatDateToString(date))) {
                return true;
            }

        }

        return false;
    }




    

    
    private boolean isGroupNameDuplicateInModule(String groupNameText, String moduleID, String classType) {


        ArrayList<String[]> groups = UserFunctions.readCSV("group.txt");
        ArrayList<String[]> classes = UserFunctions.readCSV("classes.txt");


        // Find if there is already a class for this module with the same classType
        for (String[] cls : classes) {
            if (cls.length >= 7) {
                String existingModuleID = cls[4];
                String existingClassID = cls[0];
                String existingClassName = cls[1]; // e.g., Lecture-M101

                if (existingModuleID.equals(moduleID) && existingClassName.startsWith(classType)) {
                    // A class of this type already exists for this module
                    return true;
                }
            }
        }

        // Optional: you can still check duplicate group names within the same class type if needed
        // (for example, prevent "Group A" being added twice in Lecture)
        for (String[] group : groups) {
            if (group.length >= 3) {
                String existingGroupName = group[1];
                String existingGroupModuleID = group[2];

                if (existingGroupModuleID.equals(moduleID) &&
                    existingGroupName.equalsIgnoreCase(groupNameText.trim())) {
                    // Duplicate group name exists for module
                    return true;
                }
            }
        }
        return false;

    }

    
    
    // ================= SAVE GROUP =================
    private String saveGroup(String groupNameText, String moduleID, String classType) {

        ArrayList<String[]> groups = UserFunctions.readCSV("group.txt");

        // STEP 1: Check if group already exists
        for (String[] group : groups) {
            if (group.length >= 3) {

                String existingGroupID = group[0];
                String existingGroupName = group[1];
                String existingModuleID = group[2];

                // If same group name + same module → reuse ID
                if (existingGroupName.equalsIgnoreCase(groupNameText.trim())
                        && existingModuleID.equals(moduleID)) {

                    return existingGroupID; // reuse existing groupID
                }
            }
        }

        // STEP 2: If not found → create new group
        String groupID = UserFunctions.generateNextID("group.txt", "G");

        try {
            groups.add(new String[]{
                groupID,
                groupNameText.trim(),
                moduleID
            });

            UserFunctions.writeCSV("group.txt", groups);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving group: " + e.getMessage());
            return null;
        }

        return groupID;
    }





    
    
    
    // ================= HISTORY TABLE =================
    private final void updateHistoryTable() {
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        model.setRowCount(0); // clear existing rows

        try (BufferedReader br = new BufferedReader(new FileReader("classes.txt"))) {
            String line;
            boolean firstLine = true; // skip header if present
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; 
                    // optionally check if this is a header line like "classID,classStartTime,..."
                    if (line.toLowerCase().contains("classid")) continue; 
                }

                String[] data = line.split(",");
                if (data.length == 7) {
                    model.addRow(new Object[]{
                        data[4], // Module ID
                        data[5], // Group ID
                        data[0], // Class ID
                        data[2], // Start Time
                        data[3]  // End Time
                    });
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading classes: " + e.getMessage());
        }
    }



    



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        moduleLabel = new javax.swing.JLabel();
        startTime = new javax.swing.JComboBox<>();
        historyLabel = new javax.swing.JLabel();
        endTime = new javax.swing.JComboBox<>();
        deleteButton = new javax.swing.JButton();
        groupName = new javax.swing.JTextField();
        startTimeLabel = new javax.swing.JLabel();
        endTimeLabel = new javax.swing.JLabel();
        GradingSystem = new javax.swing.JButton();
        ClassManagement = new javax.swing.JButton();
        AdminHomepage = new javax.swing.JButton();
        AdminProfile = new javax.swing.JButton();
        UserManagement = new javax.swing.JButton();
        AssignLecturer = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        groupNameFormat = new javax.swing.JLabel();
        moduleName = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        classType = new javax.swing.JComboBox<>();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        groupLabel.setText("Group : ");

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module ID", "Group ID", "Class ID", "Start Time", "End Time"
            }
        ));
        jScrollPane2.setViewportView(historyTable);

        moduleLabel.setText("Module : ");

        startTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08:00", "08:15", "08:30", "09:00", "09:15", "09:30", "10:00", "10:15", "10:30", "11:00", "11:15", "11:30", "12:00", "12:15", "12:30", "13:00", "13:15", "13:30", "14:00", "14:15", "14:30", "15:00", "15:15", "15:30", "16:00", "16:15", "16:30", "17:00", "17:15", "17:30", "18:00" }));

        historyLabel.setText("Class Creation History");

        endTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08:00", "08:15", "08:30", "09:00", "09:15", "09:30", "10:00", "10:15", "10:30", "11:00", "11:15", "11:30", "12:00", "12:15", "12:30", "13:00", "13:15", "13:30", "14:00", "14:15", "14:30", "15:00", "15:15", "15:30", "16:00", "16:15", "16:30", "17:00", "17:15", "17:30", "18:00" }));

        deleteButton.setBackground(new java.awt.Color(255, 51, 51));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        startTimeLabel.setText("Start Time :");

        endTimeLabel.setText("End Time :");

        GradingSystem.setText("Grading System");
        GradingSystem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GradingSystemActionPerformed(evt);
            }
        });

        ClassManagement.setText("Class Management");
        ClassManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClassManagementActionPerformed(evt);
            }
        });

        AdminHomepage.setText("Homepage");
        AdminHomepage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminHomepageActionPerformed(evt);
            }
        });

        AdminProfile.setText("Profile");
        AdminProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminProfileActionPerformed(evt);
            }
        });

        UserManagement.setText("Manage User");
        UserManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserManagementActionPerformed(evt);
            }
        });

        AssignLecturer.setText("Assign Lecturer");
        AssignLecturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssignLecturerActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        groupNameFormat.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        groupNameFormat.setText("Format: Group A, Group B,....");

        moduleName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        moduleName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moduleNameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Class Management");

        jLabel2.setText("Class Type :");

        classType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lecture", "Tutorial" }));

        jLabel3.setText("Date :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AdminProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GradingSystem, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AssignLecturer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ClassManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UserManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AdminHomepage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(historyLabel))
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(groupLabel)
                                            .addComponent(moduleLabel))
                                        .addGap(40, 40, 40)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(moduleName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(groupNameFormat)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(groupName)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(31, 31, 31)
                                        .addComponent(classType, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 3, Short.MAX_VALUE)))
                                .addGap(64, 64, 64))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(saveButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(startTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(endTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(30, 30, 30)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(endTime, 0, 179, Short.MAX_VALUE)
                                                .addComponent(startTime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(31, 31, 31)
                                            .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel1)
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AdminHomepage)
                                .addGap(18, 18, 18)
                                .addComponent(UserManagement)
                                .addGap(18, 18, 18)
                                .addComponent(ClassManagement)
                                .addGap(18, 18, 18)
                                .addComponent(AssignLecturer)
                                .addGap(18, 18, 18)
                                .addComponent(GradingSystem)
                                .addGap(18, 18, 18)
                                .addComponent(AdminProfile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(moduleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(moduleLabel))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(groupLabel)
                                    .addComponent(groupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(groupNameFormat)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(classType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(startTimeLabel)
                                            .addComponent(startTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(endTimeLabel)
                                            .addComponent(endTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel3))
                                .addGap(27, 27, 27)
                                .addComponent(saveButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(historyLabel)
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(27, 27, 27)
                        .addComponent(deleteButton)))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selectedRow = historyTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a class from the table to delete.");
            return;
        }

        // Get class ID from the selected row (column 2)
        String classID = (String) historyTable.getValueAt(selectedRow, 2);
        String groupID = historyTable.getValueAt(selectedRow, 1).toString();

        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this class?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        // Delete class from classes.txt
        UserFunctions.deleteLineFromFile("classes.txt", classID, 0);
        UserFunctions.deleteLineFromFile("group.txt", groupID, 0);

        // Refresh table
        updateHistoryTable();

        historyTable.clearSelection();
        deleteButton.setEnabled(false);

        JOptionPane.showMessageDialog(this, "Class deleted successfully.");
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void GradingSystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GradingSystemActionPerformed
        new GradingSystem(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_GradingSystemActionPerformed

    private void ClassManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClassManagementActionPerformed
        new ClassManagement(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ClassManagementActionPerformed

    private void AdminHomepageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminHomepageActionPerformed
        new AdminHomepage(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AdminHomepageActionPerformed

    private void AdminProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminProfileActionPerformed
        new AdminProfile(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AdminProfileActionPerformed

    private void UserManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserManagementActionPerformed
        new UserManagement(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_UserManagementActionPerformed

    private void AssignLecturerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssignLecturerActionPerformed
        new AssignLecturer(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AssignLecturerActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        String selectedModuleName = (String) moduleName.getSelectedItem();
        if (selectedModuleName == null || selectedModuleName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a module.");
            return;
        }

        String moduleID = moduleMap.get(selectedModuleName);
        String groupText = groupName.getText().trim();
        String start = (String) startTime.getSelectedItem();
        String end = (String) endTime.getSelectedItem();
        String type = (String) classType.getSelectedItem();
        Date selectedDate = jCalendar1.getDate();
        String dateStr = UserFunctions.formatDateToString(selectedDate);


        if (groupText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter group name.");
            return;
        }

        // Validate format
        if (!groupText.matches("Group [A-Z]")) {
            JOptionPane.showMessageDialog(this,
                "Group name must strictly follow the format 'Group A'");
            return;
        }
        
        // Check if Lecture/Tutorial already exists for this module on that date
        if (hasModuleClassTypeOnDate(moduleID, type, selectedDate)) {
            JOptionPane.showMessageDialog(this, 
                "A " + type + " for this module already exists on the selected date.");
            return;
        }
        
        
        
        // Validate selected start time against real-world current time
        Calendar selectedDateTime = Calendar.getInstance();
        selectedDateTime.setTime(selectedDate); // set calendar to the selected date

        String[] timeParts = start.split(":");
        selectedDateTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeParts[0]));
        selectedDateTime.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
        selectedDateTime.set(Calendar.SECOND, 0);
        selectedDateTime.set(Calendar.MILLISECOND, 0);

        Date startTimeDate = selectedDateTime.getTime();
        Date now = new Date();

        if (startTimeDate.before(now)) {
            JOptionPane.showMessageDialog(this, "Selected start time has already passed. Please choose a future time.");
            return;
        }
    
    
    
        
        // Validate Time
        if (!isValidDuration(start, end)) {
            return;
        }
        
        // Check for time clash
        if (hasModuleTimeClash(moduleID, start, end)) {
            JOptionPane.showMessageDialog(this,
                "Time clash detected!\n" +
                "Another class under this module already occupies this time range.");
            return;
        }

        // Save Group
        String groupID = saveGroup(groupText, moduleID, type);
        if (groupID == null) return;

        // Save Class
        String classID = UserFunctions.generateNextID("classes.txt", "C");       
        String className = type + "-" + moduleID; // automatically generated className

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("classes.txt", true))) {
            bw.write(classID + "," + className + "," + start + "," + end + "," + moduleID + "," + groupID + "," + dateStr);
            bw.newLine();

            JOptionPane.showMessageDialog(this, "Class created successfully!");

            // Reset Input to Empty
            moduleName.setSelectedIndex(0);
            groupName.setText("");
            startTime.setSelectedIndex(0);
            endTime.setSelectedIndex(0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving class: " + e.getMessage());
        }

        updateHistoryTable();

        // Refresh table
        updateHistoryTable();

        // Reset calendar to today
        Calendar today = Calendar.getInstance();
        jCalendar1.setDate(today.getTime());

    }//GEN-LAST:event_saveButtonActionPerformed

    private void moduleNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moduleNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moduleNameActionPerformed


//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
////                new ClassManagement().setVisible(true);
//            }
//        }); 
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminHomepage;
    private javax.swing.JButton AdminProfile;
    private javax.swing.JButton AssignLecturer;
    private javax.swing.JButton ClassManagement;
    private javax.swing.JButton GradingSystem;
    private javax.swing.JButton UserManagement;
    private javax.swing.JComboBox<String> classType;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> endTime;
    private javax.swing.JLabel endTimeLabel;
    private javax.swing.JLabel groupLabel;
    private javax.swing.JTextField groupName;
    private javax.swing.JLabel groupNameFormat;
    private javax.swing.JLabel historyLabel;
    private javax.swing.JTable historyTable;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel moduleLabel;
    private javax.swing.JComboBox<String> moduleName;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> startTime;
    private javax.swing.JLabel startTimeLabel;
    // End of variables declaration//GEN-END:variables
}
