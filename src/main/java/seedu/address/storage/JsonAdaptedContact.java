package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;

/**
 * Jackson-friendly version of {@link Contact}.
 */
public class JsonAdaptedContact {

    public static final String MISSING_CFIELD_MESSAGE_FORMAT = "Contact's %s field is missing!";

    private final String name;
    private final String phone;

    /**
     * Contructs a {@code JsonAdaptedContact} with the give contact details.
     */
    @JsonCreator
    public JsonAdaptedContact(@JsonProperty("name") String name, @JsonProperty("phone") String phone) {
        this.name = name;
        this.phone = phone;
    }

    /**
     * Converts a given {@code Contact} into this class for Jackson use.
     */
    public JsonAdaptedContact(Contact source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
    }

    /**
     * Converts this Jackson-friendly adopted event object into the model's {@code Contact} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Contact toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_CFIELD_MESSAGE_FORMAT,
                    ContactName.class.getSimpleName()));
        }
        if (!ContactName.isValidName(name)) {
            throw new IllegalValueException(ContactName.MESSAGE_CONSTRAINTS);
        }
        final ContactName modelName = new ContactName(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_CFIELD_MESSAGE_FORMAT,
                    ContactPhone.class.getSimpleName()));
        }
        if (!ContactPhone.isValidPhone(phone)) {
            throw new IllegalValueException(ContactPhone.MESSAGE_CONSTRAINTS);
        }
        final ContactPhone modelPhone = new ContactPhone(phone);

        return new Contact(modelName, modelPhone);
    }
}
