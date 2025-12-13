package org.alvee;

import lombok.Getter;
import lombok.Setter;
import util.Util;

import java.util.ArrayList;

@Getter
@Setter
public class Course {

    private String courseId;
    private String courseName;
    private double credits;
    private Department department;

    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Integer> finalScores;

    private static int nextId = 1;

    // Constructor
    public Course(String courseName, double credits, Department department) {
        this.courseName = Util.toTitleCase(courseName);
        this.credits = credits;
        this.department = department;

        String twoDigit = String.format("%02d", nextId);
        this.courseId = "C-" + department.getDepartmentId() + "-" + twoDigit;
        nextId++;

        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
        this.finalScores = new ArrayList<>();
    }

    // Check if assignment weights sum to 100
    public boolean isAssignmentWeightValid() {
        double sum = 0;
        for (Assignment a : assignments) {
            sum += a.getWeight();
        }
        return sum == 100;
    }

    // Register a student
    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);

        // Add null score for each assignment
        for (Assignment a : assignments) {
            a.getScores().add(null);
        }

        // Add null final score
        finalScores.add(null);

        return true;
    }

    // Add assignment
    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        assignments.add(new Assignment(assignmentName, weight));
        Assignment a = assignments.get(assignments.size() - 1);

        // Add null scores for existing students
        for (int i = 0; i < registeredStudents.size(); i++) {
            a.getScores().add(null);
        }

        return true;
    }

    // Calculate weighted averages for all students
    public int[] calcStudentsAverage() {
        int[] averages = new int[registeredStudents.size()];

        for (int i = 0; i < registeredStudents.size(); i++) {
            double sum = 0;

            for (Assignment a : assignments) {
                Integer score = a.getScores().get(i);
                if (score != null) {
                    sum += score * (a.getWeight() / 100.0);
                }
            }

            averages[i] = (int) Math.round(sum);
        }

        return averages;
    }

    // Generate all scores
    public void generateScores() {
        int studentCount = registeredStudents.size();

        for (Assignment a : assignments) {
            a.generateRandomScore(studentCount);
        }

        int[] averages = calcStudentsAverage();

        finalScores.clear();
        for (int avg : averages) {
            finalScores.add(avg);
        }
    }

    // Display score table
    public void displayScores() {
        System.out.println("Course: " + courseName + "(" + courseId + ")");

        // Header
        System.out.print("                ");
        for (Assignment a : assignments) {
            System.out.print(a.getAssignmentName() + "   ");
        }
        System.out.println("Final Score");

        // Students
        for (int i = 0; i < registeredStudents.size(); i++) {
            Student s = registeredStudents.get(i);

            System.out.print(s.getStudentName() + "   ");

            for (Assignment a : assignments) {
                System.out.print(a.getScores().get(i) + "           ");
            }

            System.out.println(finalScores.get(i));
        }

        // Averages
        System.out.print("Average         ");
        for (Assignment a : assignments) {
            a.calcAssignmentAvg();
        }
    }

    // Simplified string
    public String toSimplifiedString() {
        return courseId + " - " + courseName + " (" + credits + " credits, "
                + department.getDepartmentName() + ")";
    }

    // toString
    @Override
    public String toString() {
        String result = "Course{"
                + "courseId='" + courseId + '\''
                + ", courseName='" + courseName + '\''
                + ", credits=" + credits
                + ", department=" + department.getDepartmentName()
                + ", assignments=[";

        for (Assignment a : assignments) {
            result += a.toString() + ", ";
        }

        result += "], registeredStudents=[";

        for (Student s : registeredStudents) {
            result += s.toSimplifiedString() + ", ";
        }

        result += "], assignmentWeightValid=" + isAssignmentWeightValid()
                + "}";

        return result;
    }

    // equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Course)) return false;

        Course other = (Course) obj;
        return courseId.equals(other.courseId);
    }

    @Override
    public int hashCode() {
        return courseId.hashCode();
    }
}
