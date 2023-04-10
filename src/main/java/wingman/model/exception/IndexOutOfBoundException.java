package wingman.model.exception;

/**
 * The error message that indicates the given index
 * is out of bound of the data structure.
 */
public class IndexOutOfBoundException extends RuntimeException {
    static final String ERROR_MESSAGE = "Index out of bound. ";

    public IndexOutOfBoundException() {
        super(ERROR_MESSAGE);
    }

    /**
     * Returns the exception object with more specific error
     * message that includes information about the index and
     * the maximum index allowed.
     *
     * @param givenIndex the index queried
     * @param totalSize the maximum index value allowed
     *                  by the data structure
     */
    public IndexOutOfBoundException(int givenIndex, int totalSize) {
        super(
                String.format("The given index %d is larger than "
                                + "the maximum size %d",
                        givenIndex,
                        totalSize)
        );
    }
}
