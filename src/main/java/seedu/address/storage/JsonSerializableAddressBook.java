package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTuteeManagingSystem;
import seedu.address.model.TuteeManagingSystem;
import seedu.address.model.tutee.Tutee;

/**
 * An Immutable TuteeManagingSystem that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate tutee(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyTuteeManagingSystem} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyTuteeManagingSystem source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code TuteeManagingSystem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TuteeManagingSystem toModelType() throws IllegalValueException {
        TuteeManagingSystem tuteeManagingSystem = new TuteeManagingSystem();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Tutee tutee = jsonAdaptedPerson.toModelType();
            if (tuteeManagingSystem.hasPerson(tutee)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            tuteeManagingSystem.addPerson(tutee);
        }
        return tuteeManagingSystem;
    }

}
