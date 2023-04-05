package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * List of notes class
 */
public class NoteList {
    private final List<Note> notes = new LinkedList<>();

    /**
     * Set notes with a predefined list of notes
     * @param notes A list of {@code Note} objects
     */
    public NoteList(List<Note> notes) {
        this.notes.addAll(notes);
    }

    /**
     * Initialize an empty list
     */
    public NoteList() {
        return;
    }

    /**
     * Gets the list notes saved in this object
     * @return A list of {@code Note} objects
     */
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * Merges an incoming note list with the current list
     * @param noteList Input {@code NoteList} object to merge with
     * @return A new {@code NoteList} object with merged notes
     */
    public NoteList merge(NoteList noteList) {
        List<Note> mergedNotes = new LinkedList<>(this.getNotes());
        mergedNotes.addAll(noteList.getNotes());
        return new NoteList(mergedNotes);
    }

    /**
     * Adds a single note
     * @param note The {@code Note} object to add
     */
    public void add(Note note) {
        getNotes().add(note);
    }

    /**
     * Add a list of notes
     * @param noteList The {@code NoteList} to add
     */
    public void addAll(NoteList noteList) {
        assert len() + noteList.len() <= 20 : "upper limit of adding notes is 20";
        getNotes().addAll(noteList.getNotes());
    }

    /**
     * Get the indexed position of note
     * @param index The integer index
     * @return Desired note at index position
     */
    public Note get(int index) throws IndexOutOfBoundsException {
        requireNonNull(index);
        return getNotes().get(index);
    }

    /**
     * Replaces the indexed note with a new {@code Note} object.
     * @param index
     */
    public void replace(Note note, int index) throws IndexOutOfBoundsException {
        requireNonNull(index);
        getNotes().set(index, note);
    }

    /**
     * Removes the specified note
     * @param note The note to remove from the list
     */
    public boolean remove(Note note) throws NullPointerException {
        requireNonNull(note);
        return getNotes().remove(note);
    }

    /**
     * Removes the indexed note
     * @param index Integer for index
     */
    public boolean remove(int index) throws IndexOutOfBoundsException {
        requireNonNull(index);
        getNotes().remove(index);
        return true;
    }

    /**
     * Contains a note or not
     * @param note The note to check against
     * @return A boolean response
     */
    public boolean contains(Note note) {
        return getNotes().contains(note);
    }

    /**
     * Clears the notes inside the list
     */
    public void clear() {
        getNotes().clear();
    }

    /**
     * Counts the number of notes in the list (this would be a bit slow)
     * @return An integer of length
     */
    public int len() {
        return getNotes().size();
    }

    /**
     * Copy the original list to avoid conflicts
     * @return A new list with the same notes
     */
    public NoteList copy() {
        return new NoteList(new ArrayList<>(getNotes()));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        getNotes().stream()
                .map(note -> "\n Note #" + getNotes().indexOf(note) + ":\n" + note.toString())
                .forEach(builder::append);
        return builder.toString();
    }
}
