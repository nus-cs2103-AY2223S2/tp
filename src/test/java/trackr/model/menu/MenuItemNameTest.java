package trackr.model.menu;

import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MenuItemNameTest {

    @Test
    public void constructor_invalidName_throwsException() {
        assertThrows(NullPointerException.class, () -> new ItemName(null));

        assertThrows(IllegalArgumentException.class, () -> new ItemName(""));

        assertThrows(IllegalArgumentException.class, () -> new ItemName("*!@#"));
    }

}
