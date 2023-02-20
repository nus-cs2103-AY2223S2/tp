package seedu.address.model.person.fields;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Favorite {

    public static final String MESSAGE_CONSTRAINTS = "Stonks";
    public final boolean isFavorite;

    public Favorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }


    public static boolean isValidFavorite(String trimmedFavorite) {
        return Objects.equals(trimmedFavorite, "yes") || Objects.equals(trimmedFavorite, "no");
    }
}
