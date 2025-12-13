package org.alvee;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;

    private static int nextId = 1;

    // Constructor
    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;

        this.studentId = String.format("S%06d", nextId);
        nextId++;

        this.registeredCourses = new ArrayList<>();
    }
        // registerCourse
        public boolean registerCourse(Course course) {
            if (registeredCourses.contains(course)) {
                return false;
            }

            registeredCourses.add(course);
            course.getRegisteredStudents().add(this);

            // Add null score for each assignment in the course
            for (Assignment a : course.getAssignments()) {
                a.getScores().add(null);
            }

            // Add null final score
            course.getFinalScores().add(null);

            return true;
        }

        // dropCourse
        public boolean dropCourse(Course course) {
            if (!registeredCourses.contains(course)) {
                return false;
            }

            // Find this student's index in the course
            int index = course.getRegisteredStudents().indexOf(this);

            registeredCourses.remove(course);
            course.getRegisteredStudents().remove(this);

            // Remove this student's score from each assignment
            for (Assignment a : course.getAssignments()) {
                a.getScores().remove(index);
            }

            // Remove final score
            course.getFinalScores().remove(index);

            return true;
        }

        // toSimplifiedString
        public String toSimplifiedString() {
            return studentId + " - " + studentName + " (" + department.getDepartmentName() + ")";
        }

        // toString
        @Override
        public String toString() {
            String result = "Student{"
                    + "studentId='" + studentId + '\''
                    + ", studentName='" + studentName + '\''
                    + ", gender=" + gender
                    + ", address=" + address
                    + ", department=" + department
                    + ", registeredCourses=[";

            for (Course c : registeredCourses) {
                result += "{" + c.getCourseId() + ", " + c.getCourseName() + ", "
                        + c.getDepartment().getDepartmentName() + "}, ";
            }

            result += "]}";

            return result;
        }

        // equals
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Student)) return false;

            Student other = (Student) obj;
            return studentId.equals(other.studentId);
        }

        @Override
        public int hashCode() {
            return studentId.hashCode();
        }
    }
