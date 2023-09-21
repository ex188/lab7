
//This file is for implementing student class
import java.util.Objects;

/**
 * Implementation of Student object
 */
public class Student implements Comparable<Student> {
    private final String firstName;
    private final String lastName;
    private final String PID;

    /**
     * Student constructor
     * 
     * @param firstName given firstName
     * @param lastName  given lastName
     * @param PID       given PID
     */
    public Student(String firstName, String lastName, String PID) {
        if (firstName == null || lastName == null || PID == null) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }

    /**
     * get firstName
     * 
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * get lastName
     * 
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * get PID
     * 
     * @return PID
     */
    public String getPID() {
        return PID;
    }

    /**
     * see if this student is equal to another by comparing all three variables
     * 
     * @param o another student object
     * @return true or false
     */
    public boolean equals(Object o) {
        if (o != null && o instanceof Student) {
            Student os = (Student) o;
            if (os.getFirstName().equals(firstName)
                    && os.getLastName().equals(lastName)
                    && os.getPID().equals(PID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * generate a hashcode given three variables
     * 
     * @return hashcode generated
     */
    public int hashCode() {
        return Objects.hash(firstName, lastName, PID);
    }

    /**
     * compare this student to another student
     * 
     * @param o another student
     * @return the result of comparison
     */
    public int compareTo(Student o) {
        if (o == null) {
            throw new NullPointerException();
        }
        Student os = (Student) o;
        if (lastName.compareTo(os.getLastName()) == 0) {
            if (firstName.compareTo(os.getFirstName()) == 0) {
                return PID.compareTo(os.getPID());
            }
            return firstName.compareTo(os.getFirstName());
        }
        return lastName.compareTo(os.getLastName());
    }
}