package seedu.address.model.event;

import java.util.LinkedList;
import java.util.List;


/**
 * Encapsulates a list of notes for each event
 */
public class NoteList {
    private List<Note> notes = new LinkedList<>();

    /**
     * Set notes with a predefined list of notes
     * @param notes A list of {@code Note} objects
     */
    public NoteList(List<Note> notes) {
        this.notes.addAll(notes);
    }

    /**
     * Initialize a note list with a single note
     * @param singleNote A single {@code Note} object
     */
    public NoteList(Note singleNote) {
        notes.add(singleNote);
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
        getNotes().addAll(noteList.getNotes());
    }

    /**
     * Removes the specified note
     * @param note The note to remove from the list
     */
    public boolean remove(Note note) {
        return getNotes().remove(note);
    }

    /**
     * Removes the indexed note
     * @param index Integer for index
     */
    public Note remove(int index) throws IndexOutOfBoundsException {
        return getNotes().remove(index);
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        getNotes().stream()
                .map(note -> "\n Note #" + getNotes().indexOf(note) + ":\n" + note.toString())
                .forEach(builder::append);
        return builder.toString();
    }
}
