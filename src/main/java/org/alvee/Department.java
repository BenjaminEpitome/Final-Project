package org.alvee;

import lombok.Getter;
import lombok.Setter;
import util.Util;

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
            this.departmentName = Util.toTitleCase(departmentName);

            // id generation
            this.departmentId = String.format("D%02d", nextId);
            nextId++;
        } else {
            this.departmentId = null;
            this.departmentName = null;
        }
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Department)) return false;

        Department other = (Department) obj;

        return java.util.Objects.equals(departmentId, other.departmentId) &&
                java.util.Objects.equals(departmentName, other.departmentName);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(departmentId, departmentName);
    }
}
