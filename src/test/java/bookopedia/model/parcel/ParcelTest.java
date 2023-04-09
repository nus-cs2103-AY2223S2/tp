package bookopedia.model.parcel;

import static bookopedia.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ParcelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Parcel(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidParcelName = "";
        assertThrows(IllegalArgumentException.class, () -> new Parcel(invalidParcelName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Parcel.isValidParcelName(null));
    }

}
