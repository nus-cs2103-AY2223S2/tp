package seedu.quickcontacts.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.quickcontacts.commons.exceptions.IllegalValueException;
import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.ReadOnlyQuickBook;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.person.Person;

/**
 * An Immutable QuickBook that is serializable to JSON format.
 */
@JsonRootName(value = "quickbook")
class JsonSerializableQuickBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_MEETING = "Meetings list contains duplicate meeting(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedMeeting> meetings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableQuickBook} with the given persons and meetings.
     */
    @JsonCreator
    public JsonSerializableQuickBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                     @JsonProperty("meetings") List<JsonAdaptedMeeting> meetings) {
        this.persons.addAll(persons);
        this.meetings.addAll(meetings);
    }

    /**
     * Converts a given {@code ReadOnlyQuickBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableQuickBook}.
     */
    public JsonSerializableQuickBook(ReadOnlyQuickBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        meetings.addAll(source.getMeetingList().stream().map(JsonAdaptedMeeting::new).collect(Collectors.toList()));
    }

    /**
     * Converts this quick book into the model's {@code QuickBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public QuickBook toModelType() throws IllegalValueException {
        QuickBook quickBook = new QuickBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (quickBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            quickBook.addPerson(person);
        }

        for (JsonAdaptedMeeting jsonAdaptedMeeting : meetings) {
            Meeting meeting = jsonAdaptedMeeting.toModelType();
            if (quickBook.hasMeeting(meeting)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEETING);
            }
            quickBook.addMeeting(meeting);
        }

        return quickBook;
    }

}
