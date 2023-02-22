package seedu.address.model.common.exceptions;

/**
 * The exception thrown when an operation would result in duplicate objects.
 */
public class ItemDuplicateException extends RuntimeException {
    public ItemDuplicateException(Class<?> clazz) {
        super("Operation would result in duplicate " + clazz.getSimpleName() +
                "s");
        this.clazz = clazz;
    }

    /**
     * The class of the object that is duplicated.
     */
    private final Class<?> clazz;

    /**
     * Returns the class of the object that is duplicated.
     *
     * @return the class of the object that is duplicated
     */
    public Class<?> getDuplicatedClass() {
        return clazz;
    }
}
