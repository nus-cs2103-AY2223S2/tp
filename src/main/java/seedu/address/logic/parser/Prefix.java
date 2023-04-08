package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;
    private final String placeholderText;
    private final boolean isOptionalPrefix;
    private final boolean isRepeatablePrefix;
    private final List<String> examples;

    private Prefix(String prefix, String placeholderText, boolean isOptionalPrefix,
            boolean isRepeatablePrefix, List<String> examples) {
        this.prefix = prefix;
        this.placeholderText = placeholderText;
        this.isOptionalPrefix = isOptionalPrefix;
        this.isRepeatablePrefix = isRepeatablePrefix;
        this.examples = List.copyOf(examples);
    }

    /**
     * Constructs a {@code Prefix} with given prefix and placeholder text.
     * @param prefix Prefix value.
     * @param placeholderText Placeholder text describing the expected data for the prefix.
     */
    public Prefix(String prefix, String placeholderText) {
        //Solution below adapted from https://github.com/AY2223S1-CS2103T-T12-2/tp
        this(prefix, placeholderText, false, false, List.of());
    }

    /**
     * Constructs a {@code Prefix} with given prefix and an no placeholder text.
     * @param prefix Prefix value.
     */
    public Prefix(String prefix) {
        this(prefix, "");
    }

    /**
     * Returns true if this {@code Prefix} is just a placeholder for
     * index/keywords arguments.
     * @return Whether this is a placeholder.
     */
    public boolean isPlaceholder() {
        return getPrefix().isEmpty();
    }

    public String getPlaceholderText() {
        return placeholderText;
    }

    public String getPrefix() {
        return prefix;
    }

    /** Returns the example values that this {@code Prefix} can take. */
    public List<String> getExamples() {
        return examples;
    }

    @Override
    public String toString() {
        return getPrefix();
    }

    /**
     * Returns true if this prefix is optional in a command.
     */
    public boolean isOptional() {
        return isOptionalPrefix;
    }

    /**
     * Returns true if this prefix can be repeated multiple times in a command.
     */
    public boolean isRepeatable() {
        return isRepeatablePrefix;
    }

    /**
     * Returns a copy of this {@code Prefix}, but as an optional prefix.
     */
    public Prefix asOptional() {
        return new Prefix(getPrefix(), getPlaceholderText(), true, isRepeatable(), getExamples());
    }

    /**
     * Returns a copy of this {@code Prefix}, but as an optional prefix.
     */
    public Prefix asRepeatable() {
        return new Prefix(getPrefix(), getPlaceholderText(), isOptional(), true, getExamples());
    }

    /**
     * Returns a copy of this {@code Prefix}, but set the stored example values
     * to {@code exampleValues}. The examples won't be accounted for in {@code Prefix::equals}.
     * @param exampleValues Example values that the prefix can take.
     */
    public Prefix setExamples(List<String> exampleValues) {
        return new Prefix(getPrefix(), getPlaceholderText(), isOptional(), isRepeatable(), exampleValues);
    }

    public Prefix setExamples(String... exampleValues) {
        return this.setExamples(Arrays.asList(exampleValues));
    }

    /** Returns the example usage of this {@code Prefix}. */
    public String toExampleString() {
        return getExamples().isEmpty()
            ? ""
            : getExamples().stream()
                    .map(example -> getPrefix() + example)
                    .collect(Collectors.joining(" "));
    }

    /** Returns the prefix with it's a placeholder text as the value. (eg. n/NAME, p/PHONE) */
    public String toPlaceholderString() {
        String output = getPrefix() + getPlaceholderText();
        output = isOptional() ? String.format("[%s]", output) : output;
        output = isRepeatable() ? output + "..." : output;
        return output;
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Prefix)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Prefix otherPrefix = (Prefix) obj;
        return !getPrefix().isBlank()
                ? otherPrefix.getPrefix().equals(getPrefix())
                : otherPrefix.getPrefix().isBlank()
                        && otherPrefix.getPlaceholderText().equals(getPlaceholderText());
    }
}
