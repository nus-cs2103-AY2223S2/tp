package seedu.address.model.event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Allows the TA to create a Consultation event that usually occurs once a week.
 * Currently, there are no different behaviours between Consultation, Consultations and Consultations.
 * Custom behaviours will be added in future versions / milestones.
 */
public class Consultation extends Event {

    public Consultation(String name) {
        super(name);
    }

    public Consultation(String name, List<Person> students) {
        super(name, students);
    }

    public Consultation(String name, LocalDateTime eventDate, List<Person> students) {
        super(name, eventDate, students);
    }

    //Unique constructor for consultation compared to lab and tutorial as file attachments not needed
    public Consultation(String name, List<Person> students, List<Note> notes, LocalDateTime eventDate) {
        super(name, students, notes, eventDate);
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
     * Replaces a specific student in the list of students
     * @param student
     */
    public void replaceStudent(Person student, Person modifiedStudent) {
        super.replaceStudent(student, modifiedStudent);
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

    public List<String> getStudentProfiles() {
        return super.getStudentProfiles();
    }

    public boolean hasStudent(Person student) {
        return super.hasStudent(student);
    }

    /**
     * Gets the date of the event
     *
     * @return LocalDateTime
     */
    public LocalDateTime getDate() {
        return super.getDate();
    }

    /**
     * Changes the date of the event
     *
     * @param date
     */
    public void changeDate(LocalDateTime date) {
        super.changeDate(date);
    }

    //Consultations does not need any attachments, hence no getters and setters for attachments

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
     * Checks for the same consultation by comparing name and date
     *
     * @param otherConsultation
     * @return
     */
    public boolean isSameConsultation(Consultation otherConsultation) {
        if (otherConsultation == this) {
            return true;
        }

        return otherConsultation != null
                && otherConsultation.getName().equals(getName())
                && otherConsultation.getDate().equals(getDate());
    }

    //Get copy of tutorial
    @Override
    public Consultation copy() {
        return new Consultation(getName(), getStudents(), getNotes(), getDate());
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
        // use this method for custom fields hashing instead of implementing your own
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
