package seedu.address.model.person.fields;

import java.util.Objects;

public class Favorite {

    public static final String MESSAGE_CONSTRAINTS = "Fav";
    public final boolean isFavorite;

    public Favorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }


    public static boolean isValidFavorite(String trimmedFavorite) {
        if (Objects.equals(trimmedFavorite, "")) {
            return true;
        }
        return Objects.equals(trimmedFavorite, "T") || Objects.equals(trimmedFavorite, "F");
    }
}
