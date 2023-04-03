package arb.logic.commands.project;

import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.testutil.TypicalAddressBook.getTypicalAddressBook;
import static arb.testutil.TypicalIndexes.INDEX_FIRST;
import static arb.testutil.TypicalIndexes.INDEX_SECOND;
import static arb.testutil.TypicalIndexes.INDEX_ZERO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;
import arb.model.project.Project;
import arb.testutil.PredicateUtil;

public class LinkProjectToClientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        LinkProjectToClientCommand linkFirstCommand = new LinkProjectToClientCommand(INDEX_FIRST);
        LinkProjectToClientCommand linkSecondCommand = new LinkProjectToClientCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(linkFirstCommand.equals(linkFirstCommand));

        // same values -> returns true
        LinkProjectToClientCommand linkFirstCommandCopy = new LinkProjectToClientCommand(INDEX_FIRST);
        assertTrue(linkFirstCommand.equals(linkFirstCommandCopy));

        // different types -> returns false
        assertFalse(linkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(linkFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(linkFirstCommand.equals(linkSecondCommand));
    }

    @Test
    public void execute_cancelLinking_success() {
        Project firstProject = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        model.updateFilteredClientList(PredicateUtil.getNameContainsKeywordsPredicate("benson"));
        model.setProjectToLink(firstProject);
        LinkProjectToClientCommand linkProjectToClientCommand = new LinkProjectToClientCommand(INDEX_ZERO);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(linkProjectToClientCommand, ListType.CLIENT, ListType.PROJECT, model,
                LinkProjectToClientCommand.CANCEL_MESSAGE,
                expectedModel);
    }
}
