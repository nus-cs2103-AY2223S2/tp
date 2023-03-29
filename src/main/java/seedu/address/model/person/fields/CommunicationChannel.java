package seedu.address.model.person.fields;

/**
 * Represents a Person's preferred communication channel in the address book.
 */
public class CommunicationChannel extends Field {

    public static final String MESSAGE_CONSTRAINTS = "Communication Channel can take any value";

    public CommunicationChannel(String nameOfCommunicationChannel) {
        super(nameOfCommunicationChannel);
    }

    public static boolean isValidComms(String trimmedComms) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommunicationChannel // instanceof handles nulls
                && this.value
                .equals(((CommunicationChannel) other).value)); // state check
    }
}
