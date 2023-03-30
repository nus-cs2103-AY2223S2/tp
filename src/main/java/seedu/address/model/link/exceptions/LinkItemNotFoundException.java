package seedu.address.model.link.exceptions;

/**
 * The exception to be thrown when the given link item is not found.
 */
public class LinkItemNotFoundException extends LinkException {
    /**
     * Creates a link exception.
     *
     * @param key the key of the item.
     * @param id  the id of the item.
     */
    public LinkItemNotFoundException(String key, String id) {
        super(String.format(
                "A %s with given index can't be found in the existing link relations.\n"
                        + "Please check if the index is used in any valid links. ",
                key
        ));
    }
}
