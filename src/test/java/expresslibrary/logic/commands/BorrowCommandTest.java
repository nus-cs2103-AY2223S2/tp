package expresslibrary.logic.commands;

import static expresslibrary.logic.commands.CommandTestUtil.assertCommandFailure;
import static expresslibrary.testutil.TypicalExpressLibrary.getTypicalExpressLibrary;
import static expresslibrary.testutil.TypicalIndexes.INDEX_FIRST;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import expresslibrary.commons.core.Messages;
import expresslibrary.commons.core.index.Index;
import expresslibrary.model.Model;
import expresslibrary.model.ModelManager;
import expresslibrary.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code BorrowCommand}.
 */
public class BorrowCommandTest {

    private Model model = new ModelManager(getTypicalExpressLibrary(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        BorrowCommand borrowCommand = new BorrowCommand(outOfBoundIndex, INDEX_FIRST, LocalDate.now().plusDays(1));

        assertCommandFailure(borrowCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

}
