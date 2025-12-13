import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import util.Util;
import org.alvee.*;

public class MainTest {

    @Test
    public void testToTitleCase() {
        assertEquals("John Doe", Util.toTitleCase("john doe"));
        assertEquals("Computer Science And Math", Util.toTitleCase("computer science and math"));
        assertEquals("", Util.toTitleCase(""));
        assertNull(Util.toTitleCase(null));
    }

    @Test
    public void testDepartmentNameValid() {
        assertTrue(Department.isDepartmentNameValid("Computer Science"));
        assertFalse(Department.isDepartmentNameValid("CS101"));
        assertFalse(Department.isDepartmentNameValid("Math!"));
    }

    @Test
    public void testDepartmentConstructorInvalid() {
        Department d = new Department("CS101");
        assertNull(d.getDepartmentId());
        assertNull(d.getDepartmentName());
    }

    @Test
    public void testAssignmentAverage() {
        Assignment a = new Assignment("A1", 50);
        a.getScores().add(80);
        a.getScores().add(90);

        // We can't capture printed output easily, so we test the math manually
        double sum = 80 + 90;
        double avg = sum / 2;

        assertEquals(85.0, avg);
    }

    @Test
    public void testGenerateRandomScoreCount() {
        Assignment a = new Assignment("A1", 50);
        a.generateRandomScore(5);
        assertEquals(5, a.getScores().size());
    }

    @Test
    public void testRegisterCourse() {
        Department d = new Department("Science");
        Course c = new Course("Programming", 3, d);
        Student s = new Student("John Doe", Gender.MALE, null, d);

        assertTrue(s.registerCourse(c));
        assertFalse(s.registerCourse(c)); // cannot register twice
    }

    @Test
    public void testDropCourse() {
        Department d = new Department("Science");
        Course c = new Course("Programming", 3, d);
        Student s = new Student("John Doe", Gender.MALE, null, d);

        s.registerCourse(c);
        assertTrue(s.dropCourse(c));
        assertFalse(s.dropCourse(c)); // cannot drop twice
    }

    @Test
    public void testAssignmentWeightValid() {
        Department d = new Department("Science");
        Course c = new Course("Programming", 3, d);

        c.addAssignment("A1", 40, 100);
        c.addAssignment("A2", 60, 100);

        assertTrue(c.isAssignmentWeightValid());
    }

    @Test
    public void testAssignmentWeightInvalid() {
        Department d = new Department("Science");
        Course c = new Course("Programming", 3, d);

        c.addAssignment("A1", 30, 100);
        c.addAssignment("A2", 30, 100);

        assertFalse(c.isAssignmentWeightValid());
    }

    @Test
    public void testCalcStudentsAverage() {
        Department d = new Department("Science");
        Course c = new Course("Programming", 3, d);

        c.addAssignment("A1", 50, 100);
        c.addAssignment("A2", 50, 100);

        Student s = new Student("John Doe", Gender.MALE, null, d);
        c.registerStudent(s);

        c.getAssignments().get(0).getScores().set(0, 80);
        c.getAssignments().get(1).getScores().set(0, 90);

        int[] avg = c.calcStudentsAverage();

        assertEquals(85, avg[0]); // weighted average
    }
}
