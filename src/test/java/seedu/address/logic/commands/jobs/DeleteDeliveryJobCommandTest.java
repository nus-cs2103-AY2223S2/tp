package seedu.address.logic.commands.jobs;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeleteDeliveryJobCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteDeliveryJobCommand(null));
    }
}
