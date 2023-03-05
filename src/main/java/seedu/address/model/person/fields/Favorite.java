package seedu.address.model.person.fields;

import java.util.Objects;

/**
 * Represents a person's favorite status in the address book.
 */
public class Favorite {

    public static final String MESSAGE_CONSTRAINTS = "Fav";
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
        if (favoriteMessage.equals("T")) {
            this.isFavorite = true;
        } else {
            this.isFavorite = false;
        }
    }

    public boolean getFavoriteStatus() {
        return isFavorite;
    }

    /**
     * Checks if a string is a valid favorite tag
     */
    public static boolean isValidFavorite(String trimmedFavorite) {
        if (Objects.equals(trimmedFavorite, "")) {
            return true;
        }
        return Objects.equals(trimmedFavorite, "T") || Objects.equals(trimmedFavorite, "F");
    }

    /**
     * Returns a String Representation of Favorite.
     */
    public String toString() {
        if (isFavorite) {
            return "T";
        } else {
            return "F";
        }
    }
}
