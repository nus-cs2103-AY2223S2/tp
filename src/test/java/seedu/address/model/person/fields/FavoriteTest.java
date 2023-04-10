package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class FavoriteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Favorite(null));
    }

    @Test
    public void constructorString_invalidFav_throwsIllegalArgumentException() {
        String invalidFav = "123";
        assertThrows(IllegalArgumentException.class, () -> new Favorite(invalidFav));
    }

    @Test
    public void isValidFavorite() {
        // null favorite
        assertThrows(NullPointerException.class, () -> Favorite.isValidFavorite(null));

        // invalid favorite
        assertFalse(Favorite.isValidFavorite("FAV")); // string fav
        assertFalse(Favorite.isValidFavorite("t")); // lowercase t
        assertFalse(Favorite.isValidFavorite("f")); // lowercase f

        // valid favorite
        assertTrue(Favorite.isValidFavorite("T")); // uppercase T
        assertTrue(Favorite.isValidFavorite("F")); // uppercase F
    }
}
