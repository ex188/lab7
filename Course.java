
//This file is to implement course
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collections;

/**
 * Implementation of course
 */
public class Course {
    HashSet<Student> enrolled;
    private final int capacity;

    /**
     * return capacity
     * 
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    private final String department, number, description;

    /**
     * return department
     * 
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * return number
     * 
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * return description
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Course constructor
     * 
     * @param department  department
     * @param number      number
     * @param description number
     * @param capacity    number
     */
    public Course(String department, String number,
            String description, int capacity) {
        if (department == null || number == null
                || description == null || capacity < 0) {
            throw new IllegalArgumentException();
        }
        this.department = department;
        this.number = number;
        this.description = description;
        this.capacity = capacity;
        enrolled = new HashSet<>(0);
    }

    /**
     * entroll a student if valid
     * 
     * @param student given student
     * @return true if enrolled successfully false otherwise
     */
    public boolean enroll(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (enrolled.size() >= capacity || enrolled.contains(student)) {
            return false;
        }
        enrolled.add(student);
        return true;
    }

    /**
     * drop a student if valid
     * 
     * @param student student
     * @return true if drop false otherwise
     */
    public boolean drop(Student student) {
        if (enrolled.contains(student)) {
            enrolled.remove(student);
            return true;
        }
        return false;
    }

    /**
     * cancel/clear the course
     */
    public void cancel() {
        enrolled.clear();
    }

    /**
     * idenfity if the course is full
     * 
     * @return num student equals capacity
     */
    public boolean isFull() {
        return enrolled.size() == capacity;
    }

    /**
     * count enrolled student
     * 
     * @return num of student
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }

    /**
     * count available
     * 
     * @return available seats
     */
    public int getAvailableSeats() {
        return capacity - getEnrolledCount();
    }

    /**
     * get the set of student
     * 
     * @return the set student
     */
    public HashSet<Student> getStudents() {
        return (HashSet) enrolled.clone();
    }

    /**
     * get roster
     * 
     * @return an arraylist of student
     */
    public ArrayList<Student> getRoster() {
        ArrayList<Student> result = new ArrayList<>();
        Iterator ic = enrolled.iterator();
        while (ic.hasNext()) {
            result.add((Student) ic.next());
        }
        Collections.sort(result);
        return result;
    }

    /**
     * print the course info
     * 
     * @return string course info
     */
    public String toString() {
        return department + " " + number + " [" + capacity + "] " + description;
    }
}
