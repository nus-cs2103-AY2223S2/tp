package seedu.dengue.model.variant;

import static seedu.dengue.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VariantTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Variant(null));
    }

    @Test
    public void constructor_invalidVariantName_throwsIllegalArgumentException() {
        String invalidVariantName = "";
        assertThrows(IllegalArgumentException.class, () -> new Variant(invalidVariantName));
    }

    @Test
    public void isValidVariantName() {
        // null variant name
        assertThrows(NullPointerException.class, () -> Variant.isValidVariantName(null));
    }

}
