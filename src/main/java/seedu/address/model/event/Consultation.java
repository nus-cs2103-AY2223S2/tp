package seedu.address.model.event;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import seedu.address.model.person.Person;

/**
 * Allows the TA to create a Consultation event that usually occurs once a week.
 * Currently, there are no different behaviours between Consultation, Consultations and Consultation.
 * Custom behaviours will be added in future versions / milestones.
 */
public class Consultation extends Event {

    public Consultation(String name) {
        super(name);
    }

    public Consultation(String name, List<Person> students) {
        super(name, students);
    }

    public Consultation(String name, LocalDate eventDate, List<Person> students) {
        super(name, eventDate, students);
    }

    public Consultation(String name, LocalDate eventDate, List<Person> students, List<File> attachments) {
        super(name, eventDate, students, attachments);
    }

    public Consultation(String name, LocalDate eventDate, List<Person> students, List<File> attachments,
                    List<Note> notes) {
        super(name, eventDate, students, attachments, notes);
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    /**
     * Gets the list of students
     * @return students
     */
    public List<Person> getStudents() {
        return super.getStudents();
    }

    /**
     * Adds a specific student into the list of students
     *
     * @param student
     */
    public void addStudent(Person student) {
        super.addStudent(student);
    }

    /**
     * Removes a specific student from the list of students
     *
     * @param student
     */
    public void removeStudent(Person student) {
        super.removeStudent(student);
    }

    /**
     * Removes student based on his 0 based index in the list of students
     *
     * @param index
     */
    public void removeIndexStudent(int index) {
        super.removeIndexStudent(index);
    }

    /**
     * Get the total number of students enrolled in the event
     *
     * @return
     */
    public int countStudents() {
        return super.countStudents();
    }

    /**
     * Gets the date of the event
     *
     * @return localdate
     */
    public LocalDate getDate() {
        return super.getDate();
    }

    /**
     * Changes the date of the event
     *
     * @param date
     */
    public void changeDate(LocalDate date) {
        super.changeDate(date);
    }

    public List<File> getAttachments() {
        return super.getAttachments();
    }

    public int countAttachments() {
        return super.countNotes();
    }

    public void addAttachment(File file) {
        super.addAttachment(file);
    }

    public void removeAttachment(File file) {
        super.removeAttachment(file);
    }

    public List<Note> getNotes() {
        return super.getNotes();
    }

    public void addNote(Note note) {
        super.addNote(note);
    }

    public int countNotes() {
        return super.countNotes();
    }

    public void removeNote(Note note) {
        super.removeNote(note);
    }

    @Override
    public String toString() {
        return "Consultation";
    }
}
