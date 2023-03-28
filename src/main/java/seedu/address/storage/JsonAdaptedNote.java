package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.event.Note;

/**
 * Jackson-friendly version of {@link Note}.
 */
public class JsonAdaptedNote {
    private final Note note;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("note") Note note) {
        this.note = note;
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code NoteList} object.
     **/
    public Note toModelType() {
        return note.copy();
    }
}
