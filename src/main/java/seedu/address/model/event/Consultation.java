package seedu.address.model.event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Allows the teaching assistant to create a Lab event that is fixed at 1 hour due the feature of the application.
 */
public class Consultation extends Event {

    /**
     * Assumes time is the current time the consultation created.
     *
     * @param name the name of the consultation.
     */
    public Consultation(String name) {
        super(name);
    }

    /**
     * Allows the user to set the lab name and the students in the consultation.
     * Assumes time is the current time the consultation created.
     *
     * @param name the name of the consultation.
     * @param students the students that belong to the consultation.
     */
    public Consultation(String name, List<Person> students) {
        super(name, students);
    }

    /**
     * Allows the user to set the consultation name, consultation date and students in the consultation.
     *
     * @param name the name of the consultation.
     * @param eventDate the consultation date.
     * @param students the students that belong to the consultation.
     */
    public Consultation(String name, LocalDateTime eventDate, List<Person> students) {
        super(name, eventDate, students);
    }

    /**
     * Allows the user to set the consultation name, students in the consultation,
     * notes in the consultation and the consultation date.
     * Assumes there are no attachments in the consultation.
     *
     * @param name the name of the consultation.
     * @param students the students in the consultation.
     * @param notes the notes of the consultation,
     * @param eventDate the consultation date.
     */
    public Consultation(String name, List<Person> students, List<Note> notes, LocalDateTime eventDate) {
        super(name, students, notes, eventDate);
    }

    /**
     * Gets the consultation name.
     *
     * @return the consultation name.
     */
    public String getName() {
        return super.getName();
    }

    /**
     * Allows the user to change the consultation name.
     *
     * @param name the new consultation name.
     */
    public void setName(String name) {
        super.setName(name);
    }

    /**
     * Gets the list of students in the consultation.
     *
     * @return students in the consultation.
     */
    public List<Person> getStudents() {
        return super.getStudents();
    }

    /**
     * Adds a specific student into the current list of students in the consultation.
     *
     * @param student the student to be added.
     */
    public void addStudent(Person student) {
        super.addStudent(student);
    }

    /**
     * Removes a specific student from the current list of students in the consultation.
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
     * Gets the total number of students enrolled in the consultation.
     *
     * @return number of students present in the consultation.
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
     * Checks if student is already present in the consultation.
     *
     * @param student the student whom presence is to be checked.
     * @return boolean of whether lab has student.
     */
    public boolean hasStudent(Person student) {
        return super.hasStudent(student);
    }

    /**
     * Gets the date of the consultation.
     *
     * @return LocalDateTime of the consultation.
     */
    public LocalDateTime getDate() {
        return super.getDate();
    }

    /**
     * Changes the date of the consultation.
     *
     * @param date the new LocalDateTime of the consultation.
     */
    public void changeDate(LocalDateTime date) {
        super.changeDate(date);
    }

    /**
     * Gets all the notes in the consultation.
     *
     * @return the list of notes for the consultation.
     */
    public List<Note> getNotes() {
        return super.getNotes();
    }

    /**
     * Allows the user to add a note to the current list of notes in the consultation.
     *
     * @param note the new note to be added.
     */
    public void addNote(Note note) {
        super.addNote(note);
    }

    /**
     * Counts the number of notes present in the consultation.
     *
     * @return int of the number of notes present in the consultation.
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
     * Checks for the same consultation by comparing name and date.
     *
     * @param otherConsultation the consultation to be compared to.
     * @return equality outcome.
     */
    public boolean isSameConsultation(Consultation otherConsultation) {
        if (otherConsultation == this) {
            return true;
        }

        return otherConsultation != null
                && otherConsultation.getName().equals(getName())
                && otherConsultation.getDate().equals(getDate());
    }

    /**
     * Gets a copy of the consultation.
     *
     * @return the newly copied consultation.
     */
    @Override
    public Consultation copy() {
        return new Consultation(getName(), getStudents(), getNotes(), getDate());
    }

    /**
     * Returns true if both consultations have the same identity and data fields.
     * This defines a stronger notion of equality between two consultations.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Consultation)) {
            return false;
        }

        Consultation otherConsultation = (Consultation) other;
        return otherConsultation.getName().equals(getName())
                && otherConsultation.getName().equals(getName())
                && otherConsultation.getStudents().equals(getStudents())
                && otherConsultation.getDate().equals(getDate())
                && otherConsultation.getNotes().equals(getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStudents(), getDate(), getNotes());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Students: ")
                .append(getStudents())
                .append("; Date: ")
                .append(getDate())
                .append("; Notes: ")
                .append(getNotes());
        return builder.toString();
    }
}
