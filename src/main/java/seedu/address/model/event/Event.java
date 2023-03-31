package seedu.address.model.event;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.person.Photo;

/**
 * Represents an abstract event that tutorial, lab and consultation sessions will inherit from.
 * It contains the standard getters and setters that is common among tutorial, lab and consultations
 */
public abstract class Event {

    public static final String MESSAGE_CONSTRAINTS = "Repetition for recur must be a number between 0 and 10. "
            + "Also, an event name cannot be just the name of the event alone. For example, tutorial cannot be "
            + "just tutorial, or just Tutorial etc. "
            + "Lastly, the event name cannot contain the other type of event. So, tutorial's name cannot have the"
            + " word consultation or lab in it.";
    private String name;
    private LocalDateTime eventDate;
    private final List<Person> students;
    private final List<File> attachments;
    private final NoteList notes;

    /**
     * Constructor with name parameter only. The time of the event will be
     * assumed to be the current time the constructor is executed
     * @param name
     */
    public Event(String name) {
        this.name = name;
        eventDate = LocalDateTime.now();
        students = new ArrayList<>();
        attachments = new ArrayList<>();
        notes = new NoteList();
    }

    /**
     * Sets the custom name students to a current List of students.
     * The date will be set to the current time of execution
     * @param name
     * @param students
     */
    public Event(String name, List<Person> students) {
        this.name = name;
        this.eventDate = LocalDateTime.now();
        this.students = students;
        attachments = new ArrayList<>();
        notes = new NoteList();
    }

    /**
     * Sets the name and date. Also sets the students to a current List of students.
     * The date will be set to a specific date
     * @param name
     * @param students
     * @param eventDate
     */
    public Event(String name, LocalDateTime eventDate, List<Person> students) {
        this.name = name;
        this.eventDate = eventDate;
        this.students = students;
        attachments = new ArrayList<>();
        notes = new NoteList();
    }

    /**
     * Sets the name and date. Also sets the students to a current list of students.
     * Sets the attachments to a current list of attachments as well.
     * @param name
     * @param eventDate
     * @param students
     * @param attachments
     */
    public Event(String name, LocalDateTime eventDate, List<Person> students, List<File> attachments) {
        this.name = name;
        this.eventDate = eventDate;
        this.students = students;
        this.attachments = attachments;
        notes = new NoteList();
    }

    /**
     * If file attachments are not needed. Different parameter order
     * to avoid duplicate constructor due to type erasure error
     * @param name
     * @param students
     * @param notes
     * @param eventDate
     */
    public Event(String name, List<Person> students, List<Note> notes, LocalDateTime eventDate) {
        this.name = name;
        this.eventDate = eventDate;
        this.students = students;
        //Ensures the list of attachments is kept at zero instead of null
        this.attachments = Arrays.asList(new File[0]);;
        this.notes = new NoteList(notes);
    }

    /**
     * Sets all the variables
     * @param name
     * @param eventDate
     * @param students
     * @param attachments
     * @param notes
     */
    public Event(String name, LocalDateTime eventDate, List<Person> students,
                 List<File> attachments, List<Note> notes) {
        this.name = name;
        this.eventDate = eventDate;
        this.students = students;
        this.attachments = attachments;
        this.notes = new NoteList(notes);
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
     * Replaces a specific student in the list of students
     * @param student
     */
    public void replaceStudent(Person student, Person modifiedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).equals(student)) {
                students.set(i, modifiedStudent);
            }
        }
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
     * @return size
     */
    public int countStudents() {
        return students.size();
    }

    /**
     * Checks if student is already present in the event
     * @param student
     * @return boolean of whether event has student
     */
    public boolean hasStudent(Person student) {
        return this.students.contains(student);
    }

    /* *************************************************************************
     *                                                                         *
     * Methods to manipulate date in an event                                  *
     *                                                                         *
     **************************************************************************/

    /**
     * Gets the date of the event
     * @return LocalDateTime
     */
    public LocalDateTime getDate() {
        return eventDate;
    }

    /**
     * Changes the date of the event
     * @param date
     */
    public void changeDate(LocalDateTime date) {
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

    /**
     * Ensures only 1 file can be added
     * @param file
     */
    public void addAttachment(File file) {
        attachments.clear();
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
    public boolean hasNote(Note note) {
        return notes.contains(note);
    }

    public void setNote(Note note, Index index) {
        notes.replace(note, index.getZeroBased());
    }

    public List<Note> getNotes() {
        return notes.getNotes();
    }

    public NoteList getNoteList() {
        return notes.copy();
    }

    public int countNotes() {
        return notes.len();
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

    public List<Photo> getStudentPhotos() {
        return students.stream().map(Person::getPhoto).collect(Collectors.toList());
    }

    public List<String> getStudentProfiles() {
        return getStudentPhotos().stream().map(Photo::getUrlPath).collect(Collectors.toList());
    }

    public abstract Event copy();
}
