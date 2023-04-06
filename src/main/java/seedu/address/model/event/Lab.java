package seedu.address.model.event;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Allows the teaching assistant to create a Lab event that is fixed at 2 hours due the feature of the application.
 */
public class Lab extends Event {

    /**
     * Assumes time is the current time the lab created.
     *
     * @param name the name of the lab.
     */
    public Lab(String name) {
        super(name);
    }

    /**
     * Allows the user to set the lab name and the students in the lab.
     * Assumes time is the current time the lab created.
     *
     * @param name the name of the lab.
     * @param students the students that belong to the lab.
     */
    public Lab(String name, List<Person> students) {
        super(name, students);
    }

    /**
     * Allows the user to set the lab name, lab date and students in the lab.
     *
     * @param name the name of the lab.
     * @param eventDate the lab date.
     * @param students the students that belong to the lab.
     */
    public Lab(String name, LocalDateTime eventDate, List<Person> students) {
        super(name, eventDate, students);
    }

    /**
     * Allows the user to set the lab name, lab date, students and attachments in the lab.
     *
     * @param name the name of the lab.
     * @param eventDate the lab date.
     * @param students the students in the lab.
     * @param attachments the attachments in the lab.
     */
    public Lab(String name, LocalDateTime eventDate, List<Person> students, List<File> attachments) {
        super(name, eventDate, students, attachments);
    }

    /**
     * Allows the user to set the lab name, lab date, students in the lab, attachments and notes in the lab.
     *
     * @param name the name of the lab.
     * @param eventDate the lab date.
     * @param students the students in the lab.
     * @param attachments the attachments in the lab.
     * @param notes the notes in the lab.
     */
    public Lab(String name, LocalDateTime eventDate, List<Person> students, List<File> attachments,
                    List<Note> notes) {
        super(name, eventDate, students, attachments, notes);
    }

    /**
     * Gets the lab name.
     *
     * @return the lab name.
     */
    public String getName() {
        return super.getName();
    }

    /**
     * Allows the user to change the lab name.
     *
     * @param name the new lab name.
     */
    public void setName(String name) {
        super.setName(name);
    }

    /**
     * Gets the list of students in the lab.
     *
     * @return students in the lab.
     */
    public List<Person> getStudents() {
        return super.getStudents();
    }

    /**
     * Adds a specific student into the current list of students in the lab.
     *
     * @param student the student to be added.
     */
    public void addStudent(Person student) {
        super.addStudent(student);
    }

    /**
     * Removes a specific student from the current list of students in the lab.
     *
     * @param student to be removed.
     */
    public void removeStudent(Person student) {
        super.removeStudent(student);
    }

    /**
     * Replaces a specific student in the current list of students should that student be present.
     *
     * @param student the student to be replaced.
     */
    public void replaceStudent(Person student, Person modifiedStudent) {
        super.replaceStudent(student, modifiedStudent);
    }

    /**
     * Removes student based on his 0 based index in the current list of students.
     *
     * @param index the index of the student to be removed.
     */
    public void removeIndexStudent(int index) {
        super.removeIndexStudent(index);
    }

    /**
     * Gets the total number of students enrolled in the lab.
     *
     * @return number of students present in the lab.
     */
    public int countStudents() {
        return super.countStudents();
    }

    /**
     * Gets the list of student photos.
     *
     * @return the list of student photos.
     */
    public List<String> getStudentProfiles() {
        return super.getStudentProfiles();
    }

    /**
     * Checks if student is already present in the lab.
     *
     * @param student the student whom presence is to be checked.
     * @return boolean of whether lab has student.
     */
    public boolean hasStudent(Person student) {
        return super.hasStudent(student);
    }

    /**
     * Gets the date of the lab.
     *
     * @return LocalDateTime of the lab.
     */
    public LocalDateTime getDate() {
        return super.getDate();
    }

    /**
     * Changes the date of the lab.
     *
     * @param date the new LocalDateTime of the lab.
     */
    public void changeDate(LocalDateTime date) {
        super.changeDate(date);
    }

    /**
     * Gets the attachment files present in the lab.
     *
     * @return the list of attachment files in the lab.
     */
    public List<File> getAttachments() {
        return super.getAttachments();
    }

    /**
     * Counts the number of attachments present in the lab.
     *
     * @return the number of attachments in the lab.
     */
    public int countAttachments() {
        return super.countAttachments();
    }

    /**
     * Ensures only 1 file can be present in the lab due to reduce bloating.
     *
     * @param file the file to be added.
     */
    public void addAttachment(File file) {
        super.addAttachment(file);
    }

    /**
     * Removes the file from the lab, should that file be present.
     *
     * @param file the file to be removed.
     */
    public void removeAttachment(File file) {
        super.removeAttachment(file);
    }

    /**
     * Gets all the notes in the lab.
     *
     * @return the list of notes for the lab.
     */
    public List<Note> getNotes() {
        return super.getNotes();
    }

    /**
     * Allows the user to add a note to the current list of notes in the lab.
     *
     * @param note the new note to be added.
     */
    public void addNote(Note note) {
        super.addNote(note);
    }

    /**
     * Counts the number of notes present in the lab.
     *
     * @return int of the number of notes present in the lab.
     */
    public int countNotes() {
        return super.countNotes();
    }

    /**
     * Removes from note list.
     *
     * @param note the note object to be removed.
     * @return remove outcome.
     */
    public boolean removeNote(Note note) {
        return super.removeNote(note);
    }


    /**
     * Checks for the same lab by comparing name and date.
     *
     * @param otherLab the lab to be compared to.
     * @return equality outcome.
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
     * Gets a copy of the lab.
     *
     * @return the newly copied lab.
     */
    @Override
    public Lab copy() {
        return new Lab(getName(), getDate(), getStudents(), getAttachments(), getNotes());
    }

    /**
     * Returns true if both labs have the same identity and data fields.
     * This defines a stronger notion of equality between two labs.
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
