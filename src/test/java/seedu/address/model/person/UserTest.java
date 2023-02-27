package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalUser;

public class UserTest {
    @Test
    public void equals_sameObject_true() {
        assertEquals(TypicalUser.LINUS, TypicalUser.LINUS);
    }
}
