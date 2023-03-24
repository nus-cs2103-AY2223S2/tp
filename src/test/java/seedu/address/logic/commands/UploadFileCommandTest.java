package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


class UploadFileCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() throws CommandException {
        UploadFileCommand uploadFileCommand = new UploadFileCommand(INDEX_FIRST_PERSON);
        CommandResult result = uploadFileCommand.execute(model);
        assertEquals(String.format(UploadFileCommand.MESSAGE_UPLOAD_SUCCESS,
                        model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased())),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UploadFileCommand command = new UploadFileCommand(outOfBoundIndex);
        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
