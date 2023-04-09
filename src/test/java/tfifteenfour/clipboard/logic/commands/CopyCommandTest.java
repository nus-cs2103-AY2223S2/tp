package tfifteenfour.clipboard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.testutil.TypicalModel;

class CopyCommandTest {
    private Model model;
    private CurrentSelection actualSelection;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.STUDENT_PAGE);
    }

    /*
    @Test
    public void execute_copyCommand_success() throws CommandException {
        CopyCommand copyCommand = new CopyCommand(INDEX_FIRST);
        String expectedMessage = CopyCommand.MESSAGE_SUCCESS;

        assertCommandSuccess(copyCommand, model, expectedMessage, expectedModel);
        copyCommand.execute(model);
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        CopyCommand copyCommand = new CopyCommand(INDEX_OUT_OF_BOUND);
        assertThrows(CommandException.class, () -> copyCommand.execute(model));
    }

    @Test
    public void execute_wrongPage_throwsCommandException() {
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
        CopyCommand copyCommand = new CopyCommand(INDEX_FIRST);
        assertThrows(CommandException.class, () -> copyCommand.execute(model));
    }
     */

    @Test
    public void equals() {
        CopyCommand copyCommand1 = new CopyCommand(INDEX_FIRST);
        CopyCommand copyCommand2 = new CopyCommand(INDEX_FIRST);

        // same object -> returns true
        assertEquals(copyCommand1, copyCommand1);

        // same values -> returns true
        assertEquals(copyCommand1, copyCommand2);

        // different types -> returns false
        assertNotEquals(1, copyCommand1);

        // null -> returns false
        assertNotEquals(null, copyCommand1);

        // different index -> returns false
        CopyCommand copyCommand3 = new CopyCommand(INDEX_SECOND);
        assertNotEquals(copyCommand1, copyCommand3);
    }

}
