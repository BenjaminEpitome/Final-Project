package org.alvee;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Department {
    private String departmentId;
    @Setter
    private String departmentName;

    private static int nextId = 1;

    // static helper
    public static boolean isDepartmentNameValid(String departmentName) {
        if (departmentName == null || departmentName.isEmpty()) {
            return false;
        }

        for (char c : departmentName.toCharArray()) {
            if (!(Character.isLetter(c) || c == ' ')) {
                return false;
            }
        }
        return true;
    }

    // constructor
    public Department(String departmentName) {
        if (isDepartmentNameValid(departmentName)) {
            this.departmentName = departmentName;

            // id generation
            this.departmentId = String.format("D%02d", nextId);
            nextId++;
        } else {
            this.departmentId = null;
            this.departmentName = null;
        }
    }
}
