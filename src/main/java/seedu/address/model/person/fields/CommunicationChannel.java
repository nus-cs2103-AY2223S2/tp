package seedu.address.model.person.fields;

/**
 * Represents a Person's preferred communication channel in the address book.
 */
public class CommunicationChannel {

    public static final String MESSAGE_CONSTRAINTS = "Communication Channel can take any value";
    public final String nameOfCommunicationChannel;

    public CommunicationChannel(String nameOfCommunicationChannel) {
        this.nameOfCommunicationChannel = nameOfCommunicationChannel;
    }

    public static boolean isValidComms(String trimmedComms) {
        return true;
    }

    @Override
    public String toString() {
        return this.nameOfCommunicationChannel;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommunicationChannel // instanceof handles nulls
                && this.nameOfCommunicationChannel
                .equals(((CommunicationChannel) other).nameOfCommunicationChannel)); // state check
    }

    @Override
    public int hashCode() {
        return this.nameOfCommunicationChannel.hashCode();
    }

}
