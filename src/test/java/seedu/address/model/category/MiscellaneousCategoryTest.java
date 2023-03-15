package seedu.address.model.category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MiscellaneousCategoryTest {
    private final MiscellaneousCategory miscellaneousCategory = new MiscellaneousCategory();

    @Test
    public void toStringTest() {
        assertEquals("Miscellaneous", miscellaneousCategory.toString());
    }
}
