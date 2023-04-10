package seedu.address.model.person.fields;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.List;

/**
 * Represents a person's favorite status in the address book.
 */
public class Favorite {

    public static final String MESSAGE_CONSTRAINTS = "Favorite can only be T or F";

    private static final String VALUE_TRUE = "T";
    private static final String VALUE_FALSE = "F";

    public final boolean isFavorite;

    /**
     * Constructs a {@code Favorite}.
     *
     * @param isFavorite If favorite is true or not.
     */
    public Favorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    /**
     * Constructs a {@code Favorite} based on message input
     *
     * @param favoriteMessage If message is "T" or "F"
     */
    public Favorite(String favoriteMessage) {
        requireNonNull(favoriteMessage);
        checkArgument(isValidFavorite(favoriteMessage), MESSAGE_CONSTRAINTS);
        this.isFavorite = VALUE_TRUE.equals(favoriteMessage);
    }

    public boolean getFavoriteStatus() {
        return isFavorite;
    }

    /**
     * Checks if a string is a valid favorite tag
     */
    public static boolean isValidFavorite(String trimmedFavorite) {
        return List.of("", VALUE_TRUE, VALUE_FALSE).contains(trimmedFavorite);
    }

    /**
     * Returns a String Representation of Favorite.
     */
    @Override
    public String toString() {
        if (isFavorite) {
            return VALUE_TRUE;
        } else {
            return VALUE_FALSE;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Favorite // instanceof handles nulls
                && this.isFavorite == (((Favorite) other).isFavorite)); // state check
    }
}
