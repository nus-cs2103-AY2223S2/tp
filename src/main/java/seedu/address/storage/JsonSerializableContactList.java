package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ContactList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.contact.Contact;

/**
 * An Immutable ContactList that is serializable to JSON format.
 */
@JsonRootName(value = "contactlist")
public class JsonSerializableContactList {

    public static final String MESSAGE_DUPLICATE_CONTACT = "Contact list contains duplicate contact(s).";

    private final List<JsonAdaptedContact> contacts = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializaleContactList} with the given contacts.
     */
    @JsonCreator
    public JsonSerializableContactList(@JsonProperty("contacts") List<JsonAdaptedContact> contacts) {
        this.contacts.addAll(contacts);
    }

    /**
     * Converts a given {@code ReadOnlyContactList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableContactList}.
     */
    public JsonSerializableContactList(ReadOnlyContactList source) {
        contacts.addAll(source.getContactList().stream().map(JsonAdaptedContact::new).collect(Collectors.toList()));
    }

    /**
     * Converts this contact list into the model's {@code ContactList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ContactList toModelType() throws IllegalValueException {
        ContactList contactList = new ContactList();
        for (JsonAdaptedContact jsonAdaptedContact : contacts) {
            Contact contact = jsonAdaptedContact.toModelType();
            if (contactList.hasContact(contact)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CONTACT);
            }
            contactList.addContact(contact);
        }
        return contactList;
    }
}
