package seedu.address.model.item.exceptions;

/**
 * The exception thrown when an operation would result in duplicate objects.
 */
public class ItemDuplicateException extends RuntimeException {
    /**
     * The class of the object that is duplicated.
     */
    private final Class<?> clazz;

    /**
     * Creates a new {@code ItemDuplicateException} that has the {@code clazz} of
     * the object that is duplicated.
     *
     * @param clazz
     */
    public ItemDuplicateException(Class<?> clazz) {
        super("Operation would result in duplicate " + clazz.getSimpleName()
                + "s");
        this.clazz = clazz;
    }

    /**
     * Returns the class of the object that is duplicated.
     *
     * @return the class of the object that is duplicated
     */
    public Class<?> getDuplicatedClass() {
        return clazz;
    }
}
