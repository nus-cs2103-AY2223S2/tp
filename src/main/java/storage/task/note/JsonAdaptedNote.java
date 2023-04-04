package storage.task.note;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.TaskType;
import seedu.address.model.task.Note;
import seedu.address.model.task.NoteContent;

/**
 * Jackson-friendly version of {@link Note}.
 */
public class JsonAdaptedNote {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Note 's %s field is missing!";

    private final String type;
    private final String date;
    private final String note;

    /**
     * Constructs a {@code JsonAdaptedNote} with the given Note details.
     */
    @JsonCreator
    public JsonAdaptedNote(@JsonProperty("type") String type,
                           @JsonProperty("date") String date,
                           @JsonProperty("note") String note) {
        this.type = type;
        this.date = date;
        this.note = note;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        type = source.getType().toString();
        date = source.getJsonDate();
        note = source.getNote().content;
    }

    /**
     * Converts this Jackson-friendly adapted Note object
     * into the model's {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Note.
     */
    public Note toModelType() throws IllegalValueException {
        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                            TaskType.class.getSimpleName()));
        }
        if (!TaskType.isValidNote(type)) {
            throw new IllegalValueException(String.format(TaskType.MESSAGE_CONSTRAINTS, type));
        }
        final TaskType type = TaskType.NOTE;

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date created"));
        }

        final LocalDate dateCreated = LocalDate.parse(date);

        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NoteContent.class.getSimpleName()));
        }
        if (!NoteContent.isValidContent(note)) {
            throw new IllegalValueException(NoteContent.MESSAGE_CONSTRAINTS);
        }
        final NoteContent content = new NoteContent(note);

        return new Note(content, dateCreated, type);
    }
}
