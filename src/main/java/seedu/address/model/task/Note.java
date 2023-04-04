package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.model.tag.TaskType;

/**
 * Represents a note object in the planner.
 * Guarantees: immutable.
 */
public class Note {

    // Identity fields
    private final LocalDate date;
    private final TaskType type;
    private NoteContent note;

    /**
     * A Note constructor to create an instance of note with content {@code note}.
     * Every field must be present and not null.
     */
    public Note(NoteContent note) {
        requireAllNonNull(note);
        this.note = note;
        this.date = LocalDate.now();
        this.type = TaskType.NOTE;
    }

    /**
     * A Note constructor to create an instance of note with content {@code note}, {@code date} and {@code type}.
     * Every field must be present and not null.
     * This constructor is used to create a {@code Note} from the respective Json data file entry.
     */
    public Note(NoteContent note, LocalDate date, TaskType type) {
        requireAllNonNull(note, date, type);
        this.note = note;
        this.date = date;
        this.type = type;
    }

    /**
     * Getter for the note content.
     */
    public NoteContent getNote() {
        return note;
    }

    /**
     * Getter for the note's created date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Getter for a note's created date, it produces string of date in an acceptable format for the date parser before
     * being stored in the Json file.
     */
    public String getJsonDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date);
    }

    /**
     * Getter for a note's created date string, it produces string of date in format for the displayed date.
     */
    public String getDateString() {
        return DateTimeFormatter.ofPattern("dd MMM yyyy, EEEE").format(date);
    }

    /**
     * Getter for the note's type.
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Set the note content for the note.
     */
    public void setNote(NoteContent note) {
        this.note = note;
    }

    /**
     * Returns true if both note have the same note content.
     * This defines a weaker notion of equality between two notes.
     */
    public boolean isSameNote(Note otherNote) {
        if (otherNote == this) {
            return true;
        }

        return otherNote != null
                && otherNote.getNote().equals(getNote());
    }

    /**
     * Returns true if both interested internships have the same fields.
     * This defines a stronger notion of equality between two notes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Note)) {
            return false;
        }

        Note otherCompany = (Note) other;
        return otherCompany.getNote().equals(getNote())
                && otherCompany.getDate().equals(getDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(note, date);
    }

    @Override
    public String toString() {

        return getType() + "; Create Date: " + getDate() + "; NoteList: " + getNote();
    }
}
