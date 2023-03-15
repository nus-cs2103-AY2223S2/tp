package seedu.address.model.category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserDefinedCategoryTest {
    private final UserDefinedCategory userDefinedCategory = new UserDefinedCategory("test", "description");

    @Test
    public void toStringTest() {
        assertEquals("test", userDefinedCategory.toString());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("description", userDefinedCategory.getSummary());
    }

    @Test
    public void equalsTest() {
        assertEquals(userDefinedCategory, new UserDefinedCategory("test", "description"));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(userDefinedCategory.hashCode(), new UserDefinedCategory("test", "description").hashCode());
    }
}
