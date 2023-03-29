package seedu.address.storage;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Note;
import seedu.address.model.event.Tutorial;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Tutorial}.
 */
class JsonAdaptedTutorial {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial's %s field is missing!";

    private final String name;
    private final LocalDateTime eventDate;
    private final List<Person> students;
    private final List<File> attachments;
    private final List<Note> notes;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTutorial(@JsonProperty("name") String name, @JsonProperty("eventDate") LocalDateTime eventDate,
                             @JsonProperty("students") List<Person> students,
                            @JsonProperty("attachments") List<File> attachments,
                             @JsonProperty("notes") List<Note> notes) {
        this.name = name;
        this.eventDate = eventDate;
        this.students = students;
        this.attachments = attachments;
        this.notes = notes;
    }

    /**
     * Converts a given {@code Tutorial} into this class for Jackson use.
     */
    public JsonAdaptedTutorial(Tutorial source) {
        name = source.getName();
        eventDate = source.getDate();
        students = source.getStudents();
        attachments = source.getAttachments();
        notes = source.getNotes();
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Tutorial} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Tutorial toModelType() throws IllegalValueException {
        /*
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
         */
        return new Tutorial(name, eventDate, students, attachments, notes);
    }

}
