package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.InternshipApplication;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Internship Application list "
                                                        + "contains duplicate InternshipApplication(s).";

    private final List<JsonAdaptedInternshipApplication> applications = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given applications.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("applications")
                                            List<JsonAdaptedInternshipApplication> applications) {
        this.applications.addAll(applications);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        applications.addAll(source.getInternshipList().stream().map(
                                                JsonAdaptedInternshipApplication::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedInternshipApplication jsonAdaptedInternshipApplication : applications) {
            InternshipApplication application = jsonAdaptedInternshipApplication.toModelType();
            if (addressBook.hasApplication(application)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addApplication(application);
        }
        return addressBook;
    }

}
