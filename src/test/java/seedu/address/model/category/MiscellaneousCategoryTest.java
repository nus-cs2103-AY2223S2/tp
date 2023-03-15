package seedu.address.model.category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MiscellaneousCategoryTest {
    private final MiscellaneousCategory miscellaneousCategory = new MiscellaneousCategory();

    @Test
    public void toStringTest() {
        assertEquals("Miscellaneous", miscellaneousCategory.toString());
    }

    @Test
    public void getCategoryNameTest() {
        assertEquals("Miscellaneous", miscellaneousCategory.getCategoryName());
    }

    @Test
    public void getSummaryTest() {
        assertEquals("Placeholder Description", miscellaneousCategory.getSummary());
    }

    @Test
    public void equalsTest() {
        assertEquals(miscellaneousCategory, miscellaneousCategory);
    }

    @Test
    public void hashCodeTest() {
        assertEquals(miscellaneousCategory.hashCode(), miscellaneousCategory.hashCode());
    }
}
