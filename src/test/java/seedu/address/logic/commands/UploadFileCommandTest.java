package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    void equals_sameIndex_true() {
        Index index1 = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(1);
        UploadFileCommand command1 = new UploadFileCommand(index1);
        UploadFileCommand command2 = new UploadFileCommand(index2);

        assertTrue(command1.equals(command2));
    }

    @Test
    void equals_differentIndex_false() {
        Index index1 = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(2);
        UploadFileCommand command1 = new UploadFileCommand(index1);
        UploadFileCommand command2 = new UploadFileCommand(index2);

        assertFalse(command1.equals(command2));
    }
}
