package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class DeleteTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_validStudentIndexInvalidTaskIndexUnfilteredList_failure() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }
}
