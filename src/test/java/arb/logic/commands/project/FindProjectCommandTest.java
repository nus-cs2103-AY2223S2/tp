package arb.logic.commands.project;

import static arb.commons.core.Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW;
import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.testutil.TypicalAddressBook.getTypicalAddressBook;
import static arb.testutil.TypicalProjects.CRAYON_PROJECT;
import static arb.testutil.TypicalProjects.DIGITAL_PROJECT;
import static arb.testutil.TypicalProjects.SCULPTURE_PROJECT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import arb.commons.core.predicate.CombinedPredicate;
import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;
import arb.model.project.Project;
import arb.model.project.predicates.TitleContainsKeywordsPredicate;
import arb.testutil.PredicateUtil;

/**
 * Contains integration tests (interaction with the Model) for {@code FindProjectCommand}.
 */
public class FindProjectCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TitleContainsKeywordsPredicate firstPredicate =
                PredicateUtil.getTitleContainsKeywordsPredicate("first");
        TitleContainsKeywordsPredicate secondPredicate =
                PredicateUtil.getTitleContainsKeywordsPredicate("second");

        FindProjectCommand findFirstCommand = new FindProjectCommand(firstPredicate);
        FindProjectCommand findSecondCommand = new FindProjectCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindProjectCommand findFirstCommandCopy = new FindProjectCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void execute_zeroKeywords_noProjectFound() {
        TitleContainsKeywordsPredicate predicate = PredicateUtil.getTitleContainsKeywordsPredicate();
        CombinedPredicate<Project> finalPredicate = PredicateUtil.getCombinedPredicate(predicate);
        FindProjectCommand command = new FindProjectCommand(finalPredicate);
        expectedModel.updateFilteredProjectList(finalPredicate);
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 0) + "\n" + finalPredicate;
        assertCommandSuccess(command, ListType.PROJECT, ListType.PROJECT, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredProjectList());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void execute_multipleKeywords_multipleProjectsFound() {
        TitleContainsKeywordsPredicate predicate =
                PredicateUtil.getTitleContainsKeywordsPredicate("Crayon", "Digital", "Sculpture");
        CombinedPredicate<Project> finalPredicate = PredicateUtil.getCombinedPredicate(predicate);
        FindProjectCommand command = new FindProjectCommand(finalPredicate);
        expectedModel.updateFilteredProjectList(finalPredicate);
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 3) + "\n" + finalPredicate;
        assertCommandSuccess(command, ListType.PROJECT, ListType.PROJECT, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CRAYON_PROJECT, DIGITAL_PROJECT, SCULPTURE_PROJECT), model.getFilteredProjectList());
    }

}
