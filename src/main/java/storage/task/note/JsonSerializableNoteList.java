package storage.task.note;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.NoteList;
import seedu.address.model.ReadOnlyNote;
import seedu.address.model.task.Note;

/**
 * An Immutable NoteList that is serializable to JSON format.
 */
@JsonRootName(value = "notelist")
class JsonSerializableNoteList {

    public static final String MESSAGE_DUPLICATE_NOTE = "Note list contains duplicate Note(s).";

    private final List<JsonAdaptedNote> notes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableNoteList} with the given notes.
     */
    @JsonCreator
    public JsonSerializableNoteList(@JsonProperty("notes")
                                            List<JsonAdaptedNote> notes) {
        this.notes.addAll(notes);
    }

    /**
     * Converts a given {@code ReadOnlyNote} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableNote}.
     */
    public JsonSerializableNoteList(ReadOnlyNote source) {
        notes.addAll(source.getNoteList().stream().map(
                                                JsonAdaptedNote::new).collect(Collectors.toList()));
    }

    /**
     * Converts this note list into the model's {@code Note List} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public NoteList toModelType() throws IllegalValueException {
        NoteList todoList = new NoteList();
        for (JsonAdaptedNote jsonAdaptedNote : notes) {
            Note note = jsonAdaptedNote.toModelType();
            if (todoList.hasNote(note)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_NOTE);
            }
            todoList.addNote(note);
        }
        return todoList;
    }

}
