package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AssignCommand}.
 */
public class AssignCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        //null person index
        assertThrows(NullPointerException.class, () -> new AssignCommand(INDEX_FIRST, null));

        //null task index
        assertThrows(NullPointerException.class, () -> new AssignCommand(INDEX_FIRST, null));

        //all null args
        assertThrows(NullPointerException.class, () -> new AssignCommand(INDEX_FIRST, null));
    }

}
