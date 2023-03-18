package seedu.address.model.event;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Person;

/**
 * Represents an abstract event that tutorial, lab and consultation sessions will inherit from.
 * It contains the standard getters and setters that is common among tutorial, lab and consultations
 */
public abstract class Event {

    private String name;
    private LocalDate eventDate;
    private final List<Person> students;
    private final List<File> attachments;
    private final List<Note> notes;

    public static final String MESSAGE_CONSTRAINTS = "Repititon for recur must be a number between 0 and 10";
    /**
     * Constructor with name parameter only. The time of the event will be
     * assumed to be the current time the constructor is executed
     * @param name
     */
    public Event(String name) {
        this.name = name;
        eventDate = LocalDate.now();
        students = new ArrayList<>();
        attachments = new ArrayList<>();
        notes = new ArrayList<>();
    }

    /**
     * Sets the custom name students to a current List of students.
     * The date will be set to the current time of execution
     * @param name
     * @param students
     */
    public Event(String name, List<Person> students) {
        this.name = name;
        this.eventDate = LocalDate.now();
        this.students = students;
        attachments = new ArrayList<>();
        notes = new ArrayList<>();
    }

    /**
     * Sets the name and date. Also sets the students to a current List of students.
     * The date will be set to a specific date
     * @param name
     * @param students
     * @param eventDate
     */
    public Event(String name, LocalDate eventDate, List<Person> students) {
        this.name = name;
        this.eventDate = eventDate;
        this.students = students;
        attachments = new ArrayList<>();
        notes = new ArrayList<>();
    }

    /**
     * Sets the name and date. Also sets the students to a current list of students.
     * Sets the attachments to a current list of attachments as well.
     * @param name
     * @param eventDate
     * @param students
     * @param attachments
     */
    public Event(String name, LocalDate eventDate, List<Person> students, List<File> attachments) {
        this.name = name;
        this.eventDate = eventDate;
        this.students = students;
        this.attachments = attachments;
        notes = new ArrayList<>();
    }

    /**
     * Sets all the variables
     * @param name
     * @param eventDate
     * @param students
     * @param attachments
     * @param notes
     */
    public Event(String name, LocalDate eventDate, List<Person> students,
                 List<File> attachments, List<Note> notes) {
        this.name = name;
        this.eventDate = eventDate;
        this.students = students;
        this.attachments = attachments;
        this.notes = notes;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasMatchByName(String name) {
        return this.name.equals(name);
    }

    /* *************************************************************************
     *                                                                         *
     * Methods to manipulate students in an event                              *
     *                                                                         *
     **************************************************************************/

    /**
     * Gets the list of students
     * @return students
     */
    public List<Person> getStudents() {
        return students;
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
     * Get the total number of students enrolled in the event
     * @return
     */
    public int countStudents() {
        return students.size();
    }

    /* *************************************************************************
     *                                                                         *
     * Methods to manipulate date in an event                                  *
     *                                                                         *
     **************************************************************************/

    /**
     * Gets the date of the event
     * @return localdate
     */
    public LocalDate getDate() {
        return eventDate;
    }

    /**
     * Changes the date of the event
     * @param date
     */
    public void changeDate(LocalDate date) {
        eventDate = date;
    }

    /* *************************************************************************
     *                                                                         *
     * Methods to manipulate file attachments in an event                      *
     *                                                                         *
     **************************************************************************/

    public List<File> getAttachments() {
        return attachments;
    }

    public int countAttachments() {
        return attachments.size();
    }

    public void addAttachment(File file) {
        attachments.add(file);
    }

    public void removeAttachment(File file) {
        attachments.remove(file);
    }

    /* *************************************************************************
     *                                                                         *
     * Methods to manipulate notes in an event                                 *
     *                                                                         *
     **************************************************************************/

    public List<Note> getNotes() {
        return notes;
    }

    public int countNotes() {
        return notes.size();
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void removeNote(Note note) {
        notes.remove(note);
    }

    /**
     * Checks if input {@code String count} is valid
     * @param count Input string to check upon
     * @return Whether the count event is a validated integer
     */
    public static boolean isValidCount(String count) {
        boolean validInteger = true;
        for (int i = 0; i < count.length(); i++) {
            if (!Character.isDigit(count.charAt(i))) {
                validInteger = false;
            }
        }
        int convertedNumber = 0;
        try {
            convertedNumber = Integer.parseInt(count);
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (validInteger) {
            if (convertedNumber < 0 || convertedNumber > 10) {
                validInteger = false;
            }
        }
        return validInteger;
    }

}
