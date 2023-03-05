package seedu.address.model.event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Person;

/**
 * Represents an abstract event that tutorial, lab and consultation sessions will inherit from.
 * It contains the standard getters and setters that is common among tutorial, lab and consultations
 */
public abstract class Event {

    private final List<Person> students;
    //Add time in v 1.2
    private LocalDate eventDate;
    //Add notes class in v 1.2
    private String notes = "";
    //Add status tags in v 1.2
    private String status = "upcoming";

    /**
     * Constructor with no parameters sets an empty list of students enrolled in the event.
     * The time of the event will be assumed to be the current time the constructor is executed
     */
    public Event() {
        students = new ArrayList<>();
        eventDate = LocalDate.now();
    }

    /**
     * Sets the students to a new List of students. The date will be set to the current time of execution
     * @param students
     */
    public Event(List<Person> students) {
        this.students = students;
        this.eventDate = LocalDate.now();
    }

    /**
     * Sets the students to a new List of students. The date will be set to a specific date
     * @param students
     * @param eventDate
     */
    public Event(List<Person> students, LocalDate eventDate) {
        this.students = new ArrayList<>();
        this.eventDate = eventDate;
    }

    /**
     * Gets the list of students
     * @return students
     */
    public List<Person> getStudents() {
        return students;
    }

    /**
     * Gets the date of the event
     * @return localdate
     */
    public LocalDate getDate() {
        return eventDate;
    }

    /**
     * Adds a specific student into the list of students
     * @param student
     */
    public void addStudent(Person student) {
        students.add(student);
    }

    /**
     * Removes a specific student from the list of students
     * @param student
     */
    public void removeStudent(Person student) {
        students.remove(student);
    }

    /**
     * Removes student based on his 0 based index in the list of students
     * @param index
     */
    public void removeIndexStudent(int index) {
        students.remove(index);
    }

    /**
     * Changes the date of the event
     * @param date
     */
    public void changeDate(LocalDate date) {
        eventDate = date;
    }

    /**
     * Get the total number of students enrolled in the event
     * @return
     */
    public int countStudents() {
        return students.size();
    }
}
