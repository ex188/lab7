/*
 * Name: Anthony Chu
 * Email: abc004@ucsd.edu
 * PID: A17496679
 * Sources Used: Non
 *
 * The file gathered the methods to 
 * implement the Course class
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collections;

/**
 * A class with Student object's method
 */
public class Course {
    HashSet<Student> enrolled; 
    //A collection of students that are enrolled in this course.
    private final int capacity; 
    //The maximum number of students that can be enrolled in this course.
    private final String department;
    //This course falls under this department.
    private final String number;
    //A string representing the course number.
    private final String description;
    //A string representing the description of the course.

    /**
     * The constructor
     * Initialize the course’s information with an initial enrollment of 0 students.	
     * 
     * @param department  Course department
     * @param number      Course number
     * @param description Course description
     * @param capacity    Course capacity
     */
    public Course(String department, String number,
            String description, int capacity) {
        if (department == null || number == null) {
            throw new IllegalArgumentException();
        }
        if (description == null || capacity < 0){
            throw new IllegalArgumentException();
        }
        this.department = department;
        this.number = number;
        this.description = description;
        this.capacity = capacity;
        enrolled = new HashSet<>(0);
    }

    /**
     * Return the department name.	
     * 
     * @return department name
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Return the course number.
     * 
     * @return course number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Return the description of the course.	
     * 
     * @return description of the course
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return the capacity of the course.	
     * 
     * @return capacity of the course.	
     */
    public int getCapacity() {
        return capacity;
    }

    
    /**
     * If there is room in this course and the student 
     * is not currently enrolled, add the student to the course.
     * 
     * @param student the student
     * @return true for successful enrollment and false otherwise.
     */
    public boolean enroll(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (enrolled.size() >= this.getCapacity() || enrolled.contains(student)) {
            return false;
        }
        enrolled.add(student);
        return true;
    }

    /**
     * If the student is enrolled in the course, drop them from the course.
     * 
     * @param student the student
     * @return Return true for successfully dropping student and false otherwise
     */
    public boolean drop(Student student) {
        if (enrolled.contains(student)) {
            enrolled.remove(student);
            return true;
        }
        return false;
    }

    /**
     * If the course is canceled, all of the students are dropped from the course. 
     * Remove all the students from the course.
     */
    public void cancel() {
        enrolled.clear();
    }

    /**
     * Check if the course is at its capacity
     * 
     * @return If the course is at its capacity, 
     * return true. Otherwise, return false.
     */
    public boolean isFull() {
        return enrolled.size() == this.getCapacity();
    }

    /**
     * Return the current number of enrolled students.	
     * 
     * @return number of enrolled students
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }

    /**
     * Return the number of students that can still enroll in the course
     * 
     * @return number of students that can still enroll in the course
     */
    public int getAvailableSeats() {
        return this.getCapacity() - this.getEnrolledCount();
    }

    /**
     * Return a shallow copy of all the students enrolled in this course. 
     * 
     * @return all the students enrolled in this course
     */
    public HashSet<Student> getStudents() {
        return (HashSet) enrolled.clone();
    }

    /**
     * Turn the collection of enrolled students into an ArrayList<Student> 
     * collection by iterating through the set using the iterator or a for-each loop. 
     * 
     * @return the final result as a sorted ArrayList<Student>
     */
    public ArrayList<Student> getRoster() {
        ArrayList<Student> output = new ArrayList<>();
        Iterator enrolledIterator = enrolled.iterator();
        while (enrolledIterator.hasNext()) {
            output.add((Student) enrolledIterator.next());
        }
        Collections.sort(output);
        return output;
    }

    /**
     * Return a string representation for a Course object.
     * 
     * @return string representation ofr a Course object
     */
    public String toString() {
        return department + " " + number + " [" + capacity + "] " + description;
    }
}
