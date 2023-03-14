package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCategoryList;
import seedu.address.model.category.Category;
import seedu.address.model.person.Address;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    public static final String MESSAGE_DUPLICATE_CATEGORY = "Category list contains duplicate categories!";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    private final List<JsonAdaptedCategory> categories = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("categories") List<JsonAdaptedCategory> listOfCategories,
                                       @JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.categories.addAll(listOfCategories);
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        this.categories.addAll(source.getCategoryList()
                .stream().map(JsonAdaptedCategory::new).collect(Collectors.toList()));
        persons.addAll(source.getPersonList()
                .stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }


    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */

    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedCategory jsonAdaptedCategory : categories) {
            Category category = jsonAdaptedCategory.toModelType();
//            if (addressBook.hasCategory(category)) {
//                throw new IllegalValueException(MESSAGE_DUPLICATE_CATEGORY);
//            }
            addressBook.addCategory(category);
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
//            if (addressBook.hasCategory(category)) {
//                throw new IllegalValueException(MESSAGE_DUPLICATE_CATEGORY);
//            }
            addressBook.addPerson(person);
        }

//        if (addressBook.getCategoryList().size() == 2) {
//            throw new IllegalValueException("Categories have been loaded!");
//        }
        return addressBook;
    }

}
