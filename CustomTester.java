//this file is the custom tester PA5

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * cutom tester for PA5
 */
public class CustomTester {

    // ----------------Student class----------------

    /**
     * Test Student.equals given non student argument
     */
    @Test
    public void testStudentEquals() {
        Student student = new Student("Test", "Student", "A12345678");
        assertEquals("Check when given argument not Student object", false, student.equals("Student"));
    }

    /**
     * Call compareTo to compare two Students that have
     * the same last name and same first name.
     * this Student should have a PID that is lexigraphically
     * smaller than the other Student.
     */
    @Test
    public void testStudentCompareTo() {
        Student student1 = new Student("Test", "Student",
                "A12345678");
        Student student2 = new Student("Test", "Student",
                "A22345678");
        assertTrue("check if student pid smaller than student 2", student1.compareTo(student2) < 0);
    }

    // ----------------Course class----------------

    /**
     * Attempt to drop a non-existing
     * student with a non-empty course
     * roster. This means the course
     * should have at least 1 student
     * enrolled already.
     */
    @Test
    public void testCourseDrop() {
        Student student1 = new Student("Test", "Student",
                "A12345678");
        Student student2 = new Student("Test", "Student",
                "A22345678");
        Course cse12 = new Course("cse", "12", "lol", 10);
        cse12.enroll(student1);
        assertFalse("drop non enrolled student", cse12.drop(student2));
        assertTrue("drop exist student", cse12.drop(student1));
        assertEquals("Available seat after drop", 10, cse12.getAvailableSeats());
    }

    /**
     * Attempt to enroll a valid student into
     * a course that is already at maximum capacity
     */
    @Test
    public void testCourseEnroll() {
        Course course = new Course("CSE", "12", "Data Structure", 2);
        Student student1 = new Student("Test", "Student",
                "A12345678");
        Student student2 = new Student("Test", "Student",
                "A22345678");
        Student student3 = new Student("Test", "Student",
                "A32345678");
        course.enroll(student1);
        assertFalse("enroll repetitive", course.enroll(student1));
        course.enroll(student2);
        assertFalse("enroll at full capacity", course.enroll(student3));
        assertEquals("check enroll status", 2, course.getEnrolledCount());
        assertEquals("check enroll status", 0, course.getAvailableSeats());
    }

    /**
     * Call getRoster on a course with
     * a large number of enrolled students
     */
    @Test
    public void testCourseGetRoster() {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        for (int i = 99; i >= 0; i--) {
            Student stu = new Student("Dummy", "Stu", "A" + (100 + i));
            course.enroll(stu);
        }
        ArrayList<Student> result = course.getRoster();
        for (int i = 0; i < 100; i++) {
            // assertEquals("check if equal: ", new Student("Dummy", "Stu", "A" + (100 +
            // i)), result.get(i));
            assertEquals("check if equal: ", "A" + (100 + i), result.get(i).getPID());
        }
    }

    // ----------------Sanctuary class----------------

    /**
     * Call the Sanctuary constructor with
     * a negative argument for maxAnimals
     */
    @Test
    public void testSanctConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sanctuary sanct = new Sanctuary(-100, 10);
        });
    }

    /**
     * rescue animals from an existing species, where
     * rescuing num animals will cause the number of
     * animals to exceed the sanctuary's max capacity.
     * This means only some of the animals should be
     * rescued successfully
     */
    @Test
    public void testSanctRescuePartial() {
        Sanctuary sanct = new Sanctuary(500, 50);
        sanct.sanctuary.put("Giraffe", 490);
        assertEquals("check input", 490, sanct.getTotalAnimals());
        assertEquals("check num animal not rescued", 10, sanct.rescue("Giraffe", 20));
        assertEquals("check if full", 500, sanct.getTotalAnimals());
    }

    /**
     * rescue a new non-null species when the
     * sanctuary is already at the max capacity for species
     */
    @Test
    public void testSanctRescueMaxSpecies() {
        Sanctuary sanct = new Sanctuary(100, 1);
        sanct.sanctuary.put("Koala", 55);
        assertEquals("check num animal not rescued", 40, sanct.rescue("Zebra", 40));
        assertEquals("check sanct status", 55, sanct.getTotalAnimals());
        assertEquals("check sanct status", 1, sanct.getTotalSpecies());
    }

    /**
     * release some (not all) of the
     * animals of an existing species in
     * the sanctuary
     */
    @Test
    public void testSanctReleasePartial() {
        Sanctuary sanct = new Sanctuary(1000, 4);
        sanct.sanctuary.put("Koala", 55);
        sanct.sanctuary.put("Capybara", 70);
        sanct.sanctuary.put("Groundhog", 22);
        sanct.sanctuary.put("Vulture", 3);
        sanct.release("Koala", 15);
        assertEquals("check after release", 40, sanct.countForSpecies("Koala"));
        assertEquals("check total num species", 135, sanct.getTotalAnimals());
        assertEquals("check num species", 4, sanct.getTotalSpecies());
    }

    /**
     * Attempt to release more animals than exists
     * for a specific animal species in the sanctuary.
     * The existing number of animals for this species should be non-zero
     */
    @Test
    public void testSanctReleaseTooMany() {
        Sanctuary sanct = new Sanctuary(100, 20);
        sanct.sanctuary.put("Zebra", 10);
        sanct.sanctuary.put("Bear", 20);
        assertEquals("check total before release", 30, sanct.getTotalAnimals());
        assertEquals("check species", 2, sanct.getTotalSpecies());
        assertEquals("check if zebra", 10, sanct.countForSpecies("Zebra"));
        assertEquals("check if bear", 20, sanct.countForSpecies("Bear"));
        assertThrows(IllegalArgumentException.class, () -> {
            sanct.release("Zebra", 15);
        });
    }
}
