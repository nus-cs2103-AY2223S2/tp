package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.note.Note;

/**
 * Jackson-friendly version of {@link Note}.
 */
class JsonAdaptedNote {

    private final String noteName;

    /**
     * Constructs a {@code JsonAdaptedNote} with the given {@code noteName}.
     */
    @JsonCreator
    public JsonAdaptedNote(String noteName) {
        this.noteName = noteName;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        noteName = source.noteName;
    }

    @JsonValue
    public String getNoteName() {
        return noteName;
    }

    /**
     * Converts this Jackson-friendly adapted note object into the model's {@code Note} object.
     */
    public Note toModelType() throws IllegalValueException {
        return new Note(noteName);
    }

}
