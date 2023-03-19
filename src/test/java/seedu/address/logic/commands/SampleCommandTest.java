package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SampleCommand.
 */
public class SampleCommandTest {
    private static final int SAMPLE_SIZE = 25;
    private static final SampleCommand SAMPLE_COMMAND = new SampleCommand(SAMPLE_SIZE);

    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs());

    @Test
    public void execute_validSize_success() {
        SAMPLE_COMMAND.execute(model);
        assertEquals(SAMPLE_SIZE,
                model.getEduMate().getPersonList().size());
    }

    @Test
    public void equals_sameSize_success() {
        SampleCommand otherCommand = new SampleCommand(SAMPLE_SIZE);
        assertEquals(SAMPLE_COMMAND, otherCommand);
    }
}
