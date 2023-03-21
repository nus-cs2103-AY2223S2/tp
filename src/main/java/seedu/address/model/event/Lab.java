package seedu.address.model.event;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Allows the TA to create a Lab event that usually occurs once a week.
 * Currently, there are no different behaviours between Lab, Labs and Consultations.
 * Custom behaviours will be added in future versions / milestones.
 */
public class Lab extends Event {

    public Lab(String name) {
        super(name);
    }

    public Lab(String name, List<Person> students) {
        super(name, students);
    }

    public Lab(String name, LocalDate eventDate, List<Person> students) {
        super(name, eventDate, students);
    }

    public Lab(String name, LocalDate eventDate, List<Person> students, List<File> attachments) {
        super(name, eventDate, students, attachments);
    }

    public Lab(String name, LocalDate eventDate, List<Person> students, List<File> attachments,
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
     *
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

    /**
     * Checks for the same lab by comparing name and date
     *
     * @param otherLab
     * @return
     */
    public boolean isSameLab(Lab otherLab) {
        if (otherLab == this) {
            return true;
        }

        return otherLab != null
                && otherLab.getName().equals(getName())
                && otherLab.getDate().equals(getDate());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lab)) {
            return false;
        }

        Lab otherLab = (Lab) other;
        return otherLab.getName().equals(getName())
                && otherLab.getName().equals(getName())
                && otherLab.getStudents().equals(getStudents())
                && otherLab.getDate().equals(getDate())
                && otherLab.getAttachments().equals(getAttachments())
                && otherLab.getNotes().equals(getNotes());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getStudents(), getDate(), getAttachments(), getNotes());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Students: ")
                .append(getStudents())
                .append("; Date: ")
                .append(getDate())
                .append("; Attachments: ")
                .append(getAttachments())
                .append("; Notes: ")
                .append(getNotes());
        return builder.toString();
    }
}
