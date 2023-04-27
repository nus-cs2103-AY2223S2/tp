package seedu.address.model.event;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Allows the teaching assistant to create a Tutorial event that is fixed at 1 hour due the feature of the application.
 */
public class Tutorial extends Event {

    /**
     * Assumes time is the current time the tutorial created.
     *
     * @param name the name of the tutorial.
     */
    public Tutorial(String name) {
        super(name);
    }

    /**
     * Allows the user to set the lab name and the students in the tutorial.
     * Assumes time is the current time the tutorial created.
     *
     * @param name the name of the tutorial.
     * @param students the students that belong to the tutorial.
     */
    public Tutorial(String name, List<Person> students) {
        super(name, students);
    }

    /**
     * Allows the user to set the tutorial name, tutorial date and students in the tutorial.
     *
     * @param name the name of the tutorial.
     * @param eventDate the tutorial date.
     * @param students the students that belong to the tutorial.
     */
    public Tutorial(String name, LocalDateTime eventDate, List<Person> students) {
        super(name, eventDate, students);
    }

    /**
     * Allows the user to set the tutorial name, tutorial date, students and attachments in the tutorial.
     *
     * @param name the name of the tutorial.
     * @param eventDate the tutorial date.
     * @param students the students in the tutorial.
     * @param attachments the attachments in the tutorial.
     */
    public Tutorial(String name, LocalDateTime eventDate, List<Person> students, List<File> attachments) {
        super(name, eventDate, students, attachments);
    }

    /**
     * Allows the user to set the tutorial name, tutorial date, students in the tutorial,
     * attachments and notes in the tutorial.
     *
     * @param name the name of the tutorial.
     * @param eventDate the tutorial date.
     * @param students the students in the tutorial.
     * @param attachments the attachments in the tutorial.
     * @param notes the notes in the tutorial.
     */
    public Tutorial(String name, LocalDateTime eventDate, List<Person> students, List<File> attachments,
               List<Note> notes) {
        super(name, eventDate, students, attachments, notes);
    }

    /**
     * Gets the tutorial name.
     *
     * @return the tutorial name.
     */
    public String getName() {
        return super.getName();
    }

    /**
     * Allows the user to change the tutorial name.
     *
     * @param name the new tutorial name.
     */
    public void setName(String name) {
        super.setName(name);
    }

    /**
     * Gets the list of students in the tutorial.
     *
     * @return students in the tutorial.
     */
    public List<Person> getStudents() {
        return super.getStudents();
    }

    /**
     * Adds a specific student into the current list of students in the tutorial.
     *
     * @param student the student to be added.
     */
    public void addStudent(Person student) {
        super.addStudent(student);
    }

    /**
     * Removes a specific student from the current list of students in the tutorial.
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
     * Gets the total number of students enrolled in the tutorial.
     *
     * @return number of students present in the tutorial.
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
     * Checks if student is already present in the tutorial.
     *
     * @param student the student whom presence is to be checked.
     * @return boolean of whether tutorial has student.
     */
    public boolean hasStudent(Person student) {
        return super.hasStudent(student);
    }

    /**
     * Gets the date of the tutorial.
     *
     * @return LocalDateTime of the tutorial.
     */
    public LocalDateTime getDate() {
        return super.getDate();
    }

    /**
     * Changes the date of the tutorial.
     *
     * @param date the new LocalDateTime of the tutorial.
     */
    public void changeDate(LocalDateTime date) {
        super.changeDate(date);
    }

    /**
     * Gets the attachment files present in the tutorial.
     *
     * @return the list of attachment files in the tutorial.
     */
    public List<File> getAttachments() {
        return super.getAttachments();
    }

    /**
     * Counts the number of attachments present in the tutorial.
     *
     * @return the number of attachments in the tutorial.
     */
    public int countAttachments() {
        return super.countAttachments();
    }

    /**
     * Ensures only 1 file can be present in the tutorial due to reduce bloating.
     *
     * @param file the file to be added.
     */
    public void addAttachment(File file) {
        super.addAttachment(file);
    }

    /**
     * Removes the file from the tutorial, should that file be present.
     *
     * @param file the file to be removed.
     */
    public void removeAttachment(File file) {
        super.removeAttachment(file);
    }

    /**
     * Gets all the notes in the tutorial.
     *
     * @return the list of notes for the tutorial.
     */
    public List<Note> getNotes() {
        return super.getNotes();
    }

    /**
     * Allows the user to add a note to the current list of notes in the tutorial.
     *
     * @param note the new note to be added.
     */
    public void addNote(Note note) {
        super.addNote(note);
    }

    /**
     * Counts the number of notes present in the tutorial.
     *
     * @return int of the number of notes present in the tutorial.
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
     * Checks for the same tutorial by comparing name and date.
     *
     * @param otherTutorial the tutorial to be compared to.
     * @return equality outcome.
     */
    public boolean isSameTutorial(Tutorial otherTutorial) {
        if (otherTutorial == this) {
            return true;
        }

        return otherTutorial != null
                && otherTutorial.getName().equals(getName())
                && otherTutorial.getDate().equals(getDate());
    }

    /**
     * Gets a copy of the tutorial.
     *
     * @return the newly copied tutorial.
     */
    @Override
    public Tutorial copy() {
        return new Tutorial(getName(), getDate(), getStudents(), getAttachments(), getNotes());
    }

    /**
     * Returns true if both tutorial have the same identity and data fields.
     * This defines a stronger notion of equality between two tutorial.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tutorial)) {
            return false;
        }

        Tutorial otherTutorial = (Tutorial) other;
        return otherTutorial.getName().equals(getName())
                && otherTutorial.getName().equals(getName())
                && otherTutorial.getStudents().equals(getStudents())
                && otherTutorial.getDate().equals(getDate())
                && otherTutorial.getAttachments().equals(getAttachments())
                && otherTutorial.getNotes().equals(getNotes());
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
