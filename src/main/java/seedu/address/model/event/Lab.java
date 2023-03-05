package seedu.address.model.event;

import java.time.LocalDate;
import java.util.List;

import seedu.address.model.person.Person;

/**
 * Allows the TA to create a Lab event that usually occurs once a week
 */
public class Lab extends Event {

    public Lab(List<Person> students) {
        super(students);
    }

    /**
     * Sets the students to a new List of students. The date will be set to a specific date
     * @param students
     * @param eventDate
     */
    public Lab(List<Person> students, LocalDate eventDate) {
        super(students, eventDate);
    }

    /**
     * Gets the list of students
     * @return students
     */
    public List<Person> getStudents() {
        return super.getStudents();
    }

    /**
     * Gets the date of the event
     * @return localdate
     */
    public LocalDate getDate() {
        return super.getDate();
    }

    /**
     * Adds a specific student into the list of students
     * @param student
     */
    public void addStudent(Person student) {
        super.addStudent(student);
    }

    /**
     * Removes a specific student from the list of students
     * @param student
     */
    public void removeStudent(Person student) {
        super.removeStudent(student);
    }

    /**
     * Removes student based on his 0 based index in the list of students
     * @param index
     */
    public void removeIndexStudent(int index) {
        super.removeIndexStudent(index);
    }

    /**
     * Changes the date of the event
     * @param date
     */
    public void changeDate(LocalDate date) {
        super.changeDate(date);
    }

    /**
     * Get the total number of students enrolled in the event
     * @return
     */
    public int countStudents() {
        return super.countStudents();
    }

    @Override
    public String toString() {
        return "Lab";
    }
}
