package seedu.address.model.entity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents the classification of an entity: char (template), item or mob
 */
public class Classification {

    public static final String MESSAGE_CONSTRAINTS =
        "Classifications can only be char, item or mob, and should not be left blank.";

    public static final String VALIDATION_REGEX = "(char|item|mob|c|i|m)";

    public static final String CHAR_STRING = "char";

    public static final String CHAR_ITEM = "item";

    public static final String CHAR_MOB = "mob";

    public final String classification;

    /**
     * Constructs a {@code Name}.
     *
     * @param classification A valid category.
     */
    public Classification(String classification) {
        requireNonNull(classification);
        checkArgument(isValidClassification(classification), MESSAGE_CONSTRAINTS);
        this.classification = classification;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidClassification(String classification) {
        return classification.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return classification;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Classification // instanceof handles nulls
            && classification.equals(((Classification) other).classification)); // state check
    }

    @Override
    public int hashCode() {
        return classification.hashCode();
    }

    public boolean isCharacter() {
        return classification.matches("char|c");
    }

    public boolean isItem() {
        return classification.matches("item|i");
    }

    public boolean isMob() {
        return classification.matches("mob|m");
    }

}
