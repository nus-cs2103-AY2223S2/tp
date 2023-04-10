package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GROUP;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTimeCommand}.
 */
public class FindTimeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidGroupIndex_throwsCommandException() {
        Index invalidIndex = Index.fromZeroBased(1000);
        FindTimeCommand command = new FindTimeCommand(invalidIndex, null);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validGroupIndex_success() {
        LocalDate date = LocalDate.now();
        FindTimeCommand command = new FindTimeCommand(INDEX_FIRST_GROUP, date);
        Group group = model.getFilteredGroupList().get(INDEX_FIRST_GROUP.getZeroBased());

        String expectedMessage = String.format(FindTimeCommand.MESSAGE_SUCCESS);

        expectedModel.updateFilteredTimeSlotList(group, date);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
