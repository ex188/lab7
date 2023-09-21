/*
 * Name: Anthony Chu
 * Email: abc004@ucsd.edu
 * PID: A17496679
 * Sources Used: Non
 *
 * This is Custom tester for CSE 12 PA5 in Spring 2023.
 * It contains sanity checks on all 3 classes.
 */
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * The custom tester for PA5
 */
public class CustomTester {

    // ----------------Student class----------------

    /**
     * Test Student.equals given non student argument
     */

    // Student object initailize
    Student student1 = new Student("Anthony", "Chu", "A17496679");
    Student student2 = new Student("James", "Chu", "A27496679");
    Student student3EqualsStudent1 = new Student("Anthony", "Chu", "A17496679");
    Course course = new Course("cse", "12", "Data Structure", 100);
    
    @Test
    public void testStudentEquals() {
        assertEquals("Call equals when the argument is a non-Student object.", false, student1.equals(1));
    }

    /**
     * Call compareTo to compare two Students that 
     * have the same last name and same first name.
     *  this Student should have a PID that 
     * is lexigraphically smaller than the other Student.
     */
    @Test
    public void testStudentCompareTo() {

        assertTrue("this Student should have a PID that is lexigraphically smaller than the other Student.", student1.compareTo(student2) < 0);
    }

    // ----------------Course class----------------

    /**
     * Attempt to drop a non-existing student with a
     *  non-empty course roster. This means the 
     * course should have at least 1 student enrolled already.
     */
    @Test
    public void testCourseDrop() {
        assertEquals("cse", course.getDepartment());
        assertEquals("12", course.getNumber());
        assertEquals("Data Structure", course.getDescription());
        assertEquals(100, course.getCapacity());
        course.enroll(student1);
        assertFalse("drop non-existing student", course.drop(student2));
        assertTrue("drop existing student", course.drop(student1));
        assertEquals("Seat avalible after dropping", 100, course.getAvailableSeats());
    }

    /**
     * Attempt to enroll a valid student into a
     * course that is already at maximum capacity.
     */
    @Test
    public void testCourseEnroll() {
        Course course1 = new Course("CSE", "12", "Data Structure", 2);
        course1.enroll(student1);
        course1.enroll(student2);
        assertFalse("enroll when course that is already at maximum capacity.", 
            course1.enroll(student3EqualsStudent1));
        assertEquals("check used", 2, course1.getEnrolledCount());
        assertEquals("check available", 0, course1.getAvailableSeats());
    }

    /**
     * Call getRoster on a course with a 
     * large number of enrolled students
     */
    @Test
    public void testCourseGetRoster() {
        for (int i = 0; i <100; i++) {
            Student stu = new Student("Anthony", "Chu", 1000+i+"");
            course.enroll(stu);
        }
        ArrayList<Student> output = course.getRoster();
        for (int i = 0; i < 100; i++) {
            assertEquals("check if equal: ", true, output.get(i).equals(new Student("Anthony", "Chu", 1000+i+"")));
        }
    }

    // ----------------Sanctuary class----------------

    /**
     * Call the Sanctuary constructor with a 
     * negative argument for maxAnimals
     */
    @Test
    public void testSanctConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sanctuary output = new Sanctuary(-1
            , 100);
        });
    }

    /**
     * rescue animals from an existing species, 
     * where rescuing num animals will cause the number of animals to exceed 
     * the sanctuary's max capacity. 
     * This means only some of the animals should be rescued successfully.	
     */
    @Test
    public void testSanctRescuePartial() {
        Sanctuary output = new Sanctuary(10, 2);
        output.sanctuary.put("a", 9);
        assertEquals("Total Animals", 9, output.getTotalAnimals());
        assertEquals("The animal not rescued", 1, output.rescue("a", 2));
        assertEquals("If meet maximum", 10, output.getTotalAnimals());
    }

    /**
     * rescue a new non-null species when the sanctuary i
     * s already at the max capacity for species
     */
    @Test
    public void testSanctRescueMaxSpecies() {
        Sanctuary sanct = new Sanctuary(1000, 1);
        sanct.sanctuary.put("a", 550);
        assertEquals("The animal not rescued", 400, sanct.rescue("b", 400));
        assertEquals("Animal inside", 550, sanct.getTotalAnimals());
        assertEquals("Specise inside", 1, sanct.getTotalSpecies());
    }

    /**
     * release some (not all) of 
     * the animals of an existing species in the sanctuary.
     */
    @Test
    public void testSanctReleasePartial() {
        Sanctuary sanct = new Sanctuary(10000, 4);
        sanct.sanctuary.put("a", 550);
        sanct.sanctuary.put("b", 700);
        sanct.sanctuary.put("c", 220);
        sanct.sanctuary.put("d", 30);
        sanct.release("a", 150);
        assertEquals("number of animal after release", 400, sanct.countForSpecies("a"));
        assertEquals("Animal inside", 1350, sanct.getTotalAnimals());
        assertEquals("Specise inside", 4, sanct.getTotalSpecies());
    }

    /**
     * Attempt to release more animals than exists for 
     * a specific animal species in the sanctuary. 
     * The existing number of animals for this species should be non-zero.	
     */
    @Test
    public void testSanctReleaseTooMany() {
        Sanctuary sanct = new Sanctuary(1000, 200);
        sanct.sanctuary.put("a", 100);
        sanct.sanctuary.put("b", 200);
        assertEquals("check total before release", 300, sanct.getTotalAnimals());
        assertEquals("check species", 2, sanct.getTotalSpecies());
        assertEquals("check if a", 100, sanct.countForSpecies("a"));
        assertEquals("check if b", 200, sanct.countForSpecies("b"));
        assertThrows(IllegalArgumentException.class, () -> {
            sanct.release("a", 150);
        });
    }
}
