package seedu.address.storage;

import java.io.File;
import java.time.LocalDate;
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
    private final LocalDate eventDate;
    private final List<Person> students;
    private final List<File> attachments;
    private final List<Note> notes;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTutorial(@JsonProperty("name") String name, @JsonProperty("eventDate") LocalDate eventDate,
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
        final String modelName = name;

        /*
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
         */
        final LocalDate modelDate = eventDate;

        /*
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        */
        final List<Person> modelStudents = students;

        /*
        if (photo == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Photo.class.getSimpleName()));
        }
        */

        final List<File> modelAttachments = attachments;

        /*
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        */

        final List<Note> modelNotes = notes;

        /*
        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        */

        /*
        if (performance == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Performance.class.getSimpleName()));
        }
        */

        return new Tutorial(modelName, modelDate, modelStudents, modelAttachments, modelNotes);
    }

}
