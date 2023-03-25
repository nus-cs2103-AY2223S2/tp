package vimification.internal.parser;

/**
 * Wrapper of a normal {@code String} instance. Instances of this class are used to represent slices
 * of the original string.
 * <p>
 * A {@code StringView} instance stores 2 fields: the original string and an index that represents
 * the offset into the original string.
 */
public class StringView {

    private String value;
    private int index;

    private StringView(String value, int index) {
        this.value = value;
        this.index = index;
    }

    /**
     * Creates a new instance of this class.
     *
     * @param value the original string
     * @param index the initial offset
     * @return an instance of this class, which represents a substring that starts from
     *         {@code index} in the original string.
     */
    public static StringView of(String value, int index) {
        return new StringView(value, index);
    }

    public static StringView of(String value) {
        return of(value, 0);
    }

    /**
     * Checks whether this view starts with the given prefix.
     *
     * @param prefix the prefix to check
     * @return whether this view starts with the given prefix
     */
    public boolean startsWith(String prefix) {
        return value.startsWith(prefix, index);
    }

    /**
     * Returns a subview of this view, starting from an offset.
     *
     * @param offset the offset from the start of this view
     * @return a new subview that starts from the given offset
     */
    public StringView subview(int offset) {
        return new StringView(value, index + offset);
    }

    /**
     * Returns a substring, from the start of this view to the given offset.
     *
     * @param offset the offset from the start of this view
     * @return a substring that starts at the start of this view, and ends before the given offset
     */
    public String substringTo(int offset) {
        return value.substring(index, index + offset);
    }

    /**
     * Returns the character at the given offset.
     *
     * @param offset the offset from the start of this view
     * @return the chatacter at the offset
     */
    public char charAt(int offset) {
        return value.charAt(index + offset);
    }

    /**
     * Returns the length of this view.
     *
     * @return the length of this view
     */
    public int length() {
        return value.length() - index;
    }

    /**
     * Returns the index of the first occurence of the given substring in this view.
     *
     * @param str the given substring to find
     * @return the index of the first occurence of the given substring in this view, {@code -1} if
     *         the substring does not appear in this view
     */
    public int indexOf(String str) {
        int result = value.indexOf(str, index);
        return result < 0 ? result : result - index;
    }

    /**
     * Checks whether this view represents an empty string.
     *
     * @return {@code true} if this view represents an empty string, otherwise {@code false}
     */
    public boolean isEmpty() {
        return index >= value.length();
    }

    public String getOriginalString() {
        return value;
    }

    @Override
    public String toString() {
        return value.substring(index);
    }
}
