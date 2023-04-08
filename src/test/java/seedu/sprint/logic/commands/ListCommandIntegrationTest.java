package seedu.sprint.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;
import static seedu.sprint.testutil.TypicalApplications.AMAZON;
import static seedu.sprint.testutil.TypicalApplications.APPLE;
import static seedu.sprint.testutil.TypicalApplications.GOOGLE;
import static seedu.sprint.testutil.TypicalApplications.GOVTECH;
import static seedu.sprint.testutil.TypicalApplications.META;
import static seedu.sprint.testutil.TypicalApplications.MICROSOFT;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.parser.SortCommandParser.SortingSequence;
import seedu.sprint.model.Model;
import seedu.sprint.model.ModelManager;
import seedu.sprint.model.UserPrefs;
import seedu.sprint.model.application.AlphabeticalComparator;
import seedu.sprint.model.application.ApplicationContainsKeywordsPredicate;
import seedu.sprint.model.application.ApplicationHasTaskPredicate;
import seedu.sprint.model.application.DeadlineComparator;
import seedu.sprint.model.application.DefaultComparator;

/**
 * Contains integration tests (interaction with other commands like Sort and Find) for ListCommand.
 */
public class ListCommandIntegrationTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
        commandHistory = new CommandHistory();
    }

    /**
     * Integration test between Sort and List commands.
     * This test executes a list command after sorting alphabetically.
     */
    @Test
    public void execute_listAfterSortAlphabetical() {
        AlphabeticalComparator ascAlphabeticalComparator = new AlphabeticalComparator(SortingSequence.ASCENDING);
        model.updateSortedApplicationList(ascAlphabeticalComparator);
        expectedModel.updateSortedApplicationList(ascAlphabeticalComparator);
        assertEquals(Arrays.asList(AMAZON, GOVTECH, APPLE, GOOGLE, MICROSOFT, META),
                model.getSortedApplicationList());

        ListCommand listCommand = new ListCommand();
        expectedModel.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        expectedModel.updateSortedApplicationList(new DefaultComparator(expectedModel.getFilteredApplicationList()));
        assertCommandSuccess(listCommand, model, commandHistory, ListCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(Arrays.asList(GOVTECH, META, MICROSOFT, APPLE, AMAZON, GOOGLE),
                model.getSortedApplicationList());
    }

    /**
     * Integration test between Sort and List commands.
     * This test executes a list command after sorting by deadline.
     */
    public void execute_listAfterSortDeadline() {
        DeadlineComparator ascDeadlineComparator = new DeadlineComparator(SortingSequence.ASCENDING);
        model.updateFilteredApplicationList(new ApplicationHasTaskPredicate());
        model.updateSortedApplicationList(ascDeadlineComparator);
        expectedModel.updateFilteredApplicationList(new ApplicationHasTaskPredicate());
        expectedModel.updateSortedApplicationList(ascDeadlineComparator);
        assertEquals(Arrays.asList(GOVTECH, META, GOOGLE), model.getSortedApplicationList());


        ListCommand listCommand = new ListCommand();
        expectedModel.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        expectedModel.updateSortedApplicationList(new DefaultComparator(expectedModel.getFilteredApplicationList()));
        assertCommandSuccess(listCommand, model, commandHistory, ListCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(Arrays.asList(GOVTECH, META, MICROSOFT, APPLE, AMAZON, GOOGLE),
                model.getSortedApplicationList());
    }

    /**
     * Integration test between Sort and Find commands.
     * This test executes a list command after finding with a specific keyword.
     */
    public void execute_listAfterFindKeyword() {
        ApplicationContainsKeywordsPredicate predicate = preparePredicate("Google");
        model.updateFilteredApplicationList(predicate);
        expectedModel.updateFilteredApplicationList(predicate);
        assertEquals(Arrays.asList(GOOGLE), model.getSortedApplicationList());

        ListCommand listCommand = new ListCommand();
        expectedModel.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        assertCommandSuccess(listCommand, model, commandHistory, ListCommand.MESSAGE_SUCCESS, expectedModel);
        assertEquals(Arrays.asList(GOVTECH, META, MICROSOFT, APPLE, AMAZON, GOOGLE),
                model.getSortedApplicationList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ApplicationContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ApplicationContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
