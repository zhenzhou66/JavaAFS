/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaafs;

import java.util.List;

/**
 *
 * @author junjun
 */
public class SystemCalculateGrade {
    
    public static double calculateAssessmentGrade(List<Integer> studentMarks, int totalAssessmentInModule) {
        int sum = 0;
        for(int mark : studentMarks) {
            sum += mark;
        }
        return (double) sum / totalAssessmentInModule;
    }
}
