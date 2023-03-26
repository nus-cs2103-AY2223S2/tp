package seedu.wife.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.wife.testutil.Assert.assertThrows;
import static seedu.wife.testutil.TypicalWife.getTypicalWife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Model;
import seedu.wife.model.ModelManager;
import seedu.wife.model.UserPrefs;
import seedu.wife.model.tag.Tag;
import seedu.wife.testutil.TypicalTag;

/**
 * A class to test the DeleteTagCommand.
 */
public class DeleteTagCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWife(), new UserPrefs());
    }

    @Test
    public void execute_deleteTagNotInPredefined_showError() throws Exception {
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(new Tag("Test"));
        assertThrows(CommandException.class, () -> deleteTagCommand.execute(model));
    }

    @Test
    public void execute_deleteTagInPredefined_showSuccess() throws Exception {
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(TypicalTag.CHOCOLATE_TAG);

        CommandResult commandResult = new CommandResult(
            DeleteTagCommand.MESSAGE_TAG_DELETE_SUCCESS
            + "\n"
            + TypicalTag.CHOCOLATE_TAG
        );

        assertEquals(commandResult, deleteTagCommand.execute(model));
    }

}
