package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;


import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Comment;

public class CommentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Comment com = new Comment("Well Done");
        CommentCommand commentCommand = new CommentCommand(outOfBoundIndex, com);

        assertCommandFailure(commentCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }



    @Test
    public void constructor_nullComment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CommentCommand(null, null));
    }


    @Test
    public void equals() {
        Comment first = new Comment("Well done");
        Comment second = new Comment("Not done well");
        Index firstTaskIndex = Index.fromZeroBased(1);
        Index secondTaskIndex = Index.fromZeroBased(2);
        CommentCommand addFirstCommand = new CommentCommand(firstTaskIndex,first);
        CommentCommand addSecondCommand = new CommentCommand(secondTaskIndex,second);

        // same object -> returns true
        assertTrue(addFirstCommand.equals(addFirstCommand));

        // same values -> returns true
        CommentCommand addFirstCommandCopy = new CommentCommand(firstTaskIndex,first);
        assertTrue(addFirstCommand.equals(addFirstCommandCopy));

        // different types -> returns false
        assertFalse(addFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstCommand.equals(null));

        // different comments -> returns false
        assertFalse(addFirstCommand.equals(addSecondCommand));

        // different task index -> returns false
        CommentCommand addSecondCommandCopyDifferentIndex = new CommentCommand(secondTaskIndex,first);
        assertFalse(addSecondCommand.equals(addSecondCommandCopyDifferentIndex));
    }
}



