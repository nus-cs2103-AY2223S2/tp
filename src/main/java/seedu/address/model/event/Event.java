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
 * It contains the standard getters and setters that is common among tutorial, lab and consultations.
 */
public abstract class Event {

    public static final String MESSAGE_CONSTRAINTS =
            "1. An event name cannot be just the event itself. e.g. Tutorial name cannot be just tutorial \n"
            + "2. An event name cannot contain other event names. "
            + "e.g. Tutorial name cannot contain lab or consultation";
    private String name;
    private LocalDateTime eventDate;
    private final List<Person> students;
    private final List<File> attachments;
    private final NoteList notes;

    /**
     * Assumes time is the current time the event created.
     *
     * @param name the name of the event.
     */
    public Event(String name) {
        this.name = name;
        eventDate = LocalDateTime.now();
        students = new ArrayList<>();
        attachments = new ArrayList<>();
        notes = new NoteList();
    }

    /**
     * Allows the user to set the event name and the students in the event.
     * Assumes time is the current time the event created.
     *
     * @param name the name of the event.
     * @param students the students that belong to the event.
     */
    public Event(String name, List<Person> students) {
        this.name = name;
        this.eventDate = LocalDateTime.now();
        this.students = students;
        attachments = new ArrayList<>();
        notes = new NoteList();
    }

    /**
     * Allows the user to set the event name, event date and students in the event.
     *
     * @param name the name of the event.
     * @param eventDate the event date.
     * @param students the students that belong to the event.
     */
    public Event(String name, LocalDateTime eventDate, List<Person> students) {
        this.name = name;
        this.eventDate = eventDate;
        this.students = students;
        attachments = new ArrayList<>();
        notes = new NoteList();
    }

    /**
     * Allows the user to set the event name, event date, students and attachments in the event.
     *
     * @param name the name of the event.
     * @param eventDate the event date.
     * @param students the students in the event.
     * @param attachments the attachments in the event.
     */
    public Event(String name, LocalDateTime eventDate, List<Person> students, List<File> attachments) {
        this.name = name;
        this.eventDate = eventDate;
        this.students = students;
        this.attachments = attachments;
        notes = new NoteList();
    }

    /**
     * Allows the user to set the event name, students in the event, notes in the event and the event date.
     * Assumes there are no attachments in the event.
     *
     * @param name the name of the event.
     * @param students the students in the event.
     * @param notes the notes of the event,
     * @param eventDate the event date.
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
     * Allows the user to set the event name, event date, students in the event, attachments and notes in the event.
     *
     * @param name the name of the event.
     * @param eventDate the event date.
     * @param students the students in the event.
     * @param attachments the attachments in the event.
     * @param notes the notes in the event.
     */
    public Event(String name, LocalDateTime eventDate, List<Person> students,
                 List<File> attachments, List<Note> notes) {
        this.name = name;
        this.eventDate = eventDate;
        this.students = students;
        this.attachments = attachments;
        this.notes = new NoteList(notes);
    }

    /**
     * Gets the event name.
     *
     * @return the event name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Allows the user to change the event name.
     *
     * @param name the new event name.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Checks if the name of one event is the same as the input by ignoring any casing.
     *
     * @param name the name to be checked with.
     * @return if the name are equal, ignoring casing.
     */
    public boolean hasMatchByName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    /* *************************************************************************
     *                                                                         *
     * Methods to manipulate students in an event                              *
     *                                                                         *
     **************************************************************************/

    /**
     * Gets the list of students in the event.
     *
     * @return students in the event.
     */
    public List<Person> getStudents() {
        return students;
    }

    /**
     * Adds a specific student into the current list of students in the event.
     *
     * @param student the student to be added.
     */
    public void addStudent(Person student) {
        students.add(student);
    }

    /**
     * Removes a specific student from the current list of students in the event.
     *
     * @param student to be removed.
     */
    public void removeStudent(Person student) {
        students.remove(student);
    }

    /**
     * Replaces a specific student in the current list of students should that student be present.
     *
     * @param student the student to be replaced.
     */
    public void replaceStudent(Person student, Person modifiedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).equals(student)) {
                students.set(i, modifiedStudent);
            }
        }
    }

    /**
     * Removes student based on his 0 based index in the current list of students.
     *
     * @param index the index of the student to be removed.
     */
    public void removeIndexStudent(int index) {
        students.remove(index);
    }

    /**
     * Gets the total number of students enrolled in the event.
     *
     * @return number of students present in the event.
     */
    public int countStudents() {
        return students.size();
    }

    /**
     * Checks if student is already present in the event.
     *
     * @param student the student whose presence is to be checked.
     * @return boolean of whether event has student.
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
     * Gets the date of the event.
     *
     * @return LocalDateTime of the event.
     */
    public LocalDateTime getDate() {
        return eventDate;
    }

    /**
     * Changes the date of the event.
     *
     * @param date the new LocalDateTime of the event.
     */
    public void changeDate(LocalDateTime date) {
        eventDate = date;
    }

    /* *************************************************************************
     *                                                                         *
     * Methods to manipulate file attachments in an event                      *
     *                                                                         *
     **************************************************************************/

    /**
     * Gets the attachment files present in the event.
     *
     * @return the list of attachment files in the event.
     */
    public List<File> getAttachments() {
        return attachments;
    }

    /**
     * Counts the number of attachments present in the event.
     *
     * @return the number of attachments in the event.
     */
    public int countAttachments() {
        return attachments.size();
    }

    /**
     * Ensures only 1 file can be present in the event due to reduce bloating.
     *
     * @param file the file to be added.
     */
    public void addAttachment(File file) {
        attachments.clear();
        attachments.add(file);
    }

    /**
     * Removes the file from the event, should that file be present.
     *
     * @param file the file to be removed.
     */
    public void removeAttachment(File file) {
        attachments.remove(file);
    }

    /* *************************************************************************
     *                                                                         *
     * Methods to manipulate notes in an event                                 *
     *                                                                         *
     **************************************************************************/

    /**
     * Checks if the event has a particular note to it.
     *
     * @param note the note be checked for its presence.
     * @return boolean of whether the event has the note.
     */
    public boolean hasNote(Note note) {
        return notes.contains(note);
    }

    /**
     * Changes the note of the event based on an index.
     *
     * @param note the new note to replace the old note.
     * @param index the index of the old note to be replaced.
     * @return boolean of whether the old note has been successfully replaced.
     */
    public boolean setNote(Note note, Index index) {
        try {
            notes.replace(note, index.getZeroBased());
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Gets all the notes in the event.
     *
     * @return the list of notes for the event.
     */
    public List<Note> getNotes() {
        return notes.getNotes();
    }

    /**
     * Gets the note list of the event.
     *
     * @return the NoteList of the event.
     */
    public NoteList getNoteList() {
        return notes.copy();
    }

    /**
     * Counts the number of notes present in the event.
     *
     * @return int of the number of notes present in the event.
     */
    public int countNotes() {
        return notes.len();
    }

    /**
     * Allows the user to add a note to the current list of notes in the event.
     *
     * @param note the new note to be added.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Removes from note list.
     *
     * @param note the note object to be removed.
     * @return remove outcome.
     */
    public boolean removeNote(Note note) {
        try {
            return notes.remove(note);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Removes a note from note list by index.
     *
     * @param index the index of note to remove.
     * @return removal outcome.
     */
    public boolean removeNote(int index) {
        try {
            return notes.remove(index);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Checks if input {@code String count} is valid.
     *
     * @param count input string to check upon.
     * @return Whether the count event is a validated integer.
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

    /**
     * Gets the list of student photos.
     *
     * @return the list of student photos.
     */
    public List<Photo> getStudentPhotos() {
        return students.stream().map(Person::getPhoto).collect(Collectors.toList());
    }

    /**
     * Gets the student details, which is also known as student profiles.
     *
     * @return the list of student profiles.
     */
    public List<String> getStudentProfiles() {
        return getStudentPhotos().stream().map(Photo::getUrlPath).collect(Collectors.toList());
    }

    /**
     * Copies the event.
     *
     * @return the copied event.
     */
    public abstract Event copy();
}
