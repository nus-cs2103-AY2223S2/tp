package seedu.sprint.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.testutil.TypicalApplications.AMAZON;
import static seedu.sprint.testutil.TypicalApplications.APPLE;
import static seedu.sprint.testutil.TypicalApplications.GOOGLE;
import static seedu.sprint.testutil.TypicalApplications.GOVTECH;
import static seedu.sprint.testutil.TypicalApplications.META;
import static seedu.sprint.testutil.TypicalApplications.MICROSOFT;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.parser.SortCommandParser.SortingOrder;
import seedu.sprint.logic.parser.SortCommandParser.SortingSequence;
import seedu.sprint.model.Model;
import seedu.sprint.model.ModelManager;
import seedu.sprint.model.UserPrefs;
import seedu.sprint.model.application.AlphabeticalComparator;
import seedu.sprint.model.application.ApplicationHasTaskPredicate;
import seedu.sprint.model.application.DeadlineComparator;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        SortCommand sortAscAlphabeticalCommand = new SortCommand(SortingOrder.ALPHABETICAL, SortingSequence.ASCENDING);
        SortCommand sortDscAlphabeticalCommand = new SortCommand(SortingOrder.ALPHABETICAL, SortingSequence.DESCENDING);
        SortCommand sortAscDeadlineCommand = new SortCommand(SortingOrder.DEADLINE, SortingSequence.ASCENDING);
        SortCommand sortDscDeadlineCommand = new SortCommand(SortingOrder.DEADLINE, SortingSequence.DESCENDING);

        // same object -> returns true
        assertTrue(sortAscAlphabeticalCommand.equals(sortAscAlphabeticalCommand));

        // same values -> returns true
        SortCommand sortAscAlphabeticalCommandCopy =
                new SortCommand(SortingOrder.ALPHABETICAL, SortingSequence.ASCENDING);
        assertTrue(sortAscAlphabeticalCommand.equals(sortAscAlphabeticalCommandCopy));

        // different types -> returns false
        assertFalse(sortAscAlphabeticalCommand.equals(1));

        // null -> returns false
        assertFalse(sortAscAlphabeticalCommand.equals(null));

        // different sorting order -> returns false
        assertFalse(sortAscAlphabeticalCommand.equals(sortAscDeadlineCommand));
        // different sorting sequence -> returns false
        assertFalse(sortAscAlphabeticalCommand.equals(sortDscAlphabeticalCommand));
        // both sequence and order different -> returns false
        assertFalse(sortAscAlphabeticalCommand.equals(sortDscDeadlineCommand));
    }
    @Test
    public void execute_sortAlphabetical_success() {
        String expectedSuccessMessage = "Sorted all applications by alphabetical order!";

        // Test for sorting alphabetically in ascending order
        SortCommand sortAscAlphabeticalCommand = new SortCommand(SortingOrder.ALPHABETICAL, SortingSequence.ASCENDING);
        AlphabeticalComparator ascAlphabeticalComparator = new AlphabeticalComparator(SortingSequence.ASCENDING);
        expectedModel.updateSortedApplicationList(ascAlphabeticalComparator);
        assertCommandSuccess(sortAscAlphabeticalCommand, model, commandHistory, expectedSuccessMessage, expectedModel);
        assertEquals(Arrays.asList(AMAZON, GOVTECH, APPLE, GOOGLE, MICROSOFT, META),
                model.getSortedApplicationList());

        // Test for sorting alphabetically in descending order
        SortCommand sortDscAlphabeticalCommand = new SortCommand(SortingOrder.ALPHABETICAL, SortingSequence.DESCENDING);
        AlphabeticalComparator dscAlphabeticalComparator = new AlphabeticalComparator(SortingSequence.DESCENDING);
        expectedModel.updateSortedApplicationList(dscAlphabeticalComparator);
        assertCommandSuccess(sortDscAlphabeticalCommand, model, commandHistory, expectedSuccessMessage, expectedModel);
        assertEquals(Arrays.asList(META, MICROSOFT, GOOGLE, APPLE, GOVTECH, AMAZON),
                model.getSortedApplicationList());

    }

    @Test
    public void execute_sortDeadline_success() {
        String expectedSuccessMessage = "Listed all applications with task deadlines and sorted them in order!";

        // Test for sorting by deadline in ascending order
        SortCommand sortAscDeadlineCommand = new SortCommand(SortingOrder.DEADLINE, SortingSequence.ASCENDING);
        DeadlineComparator ascDeadlineComparator = new DeadlineComparator(SortingSequence.ASCENDING);
        expectedModel.updateFilteredApplicationList(new ApplicationHasTaskPredicate());
        expectedModel.updateSortedApplicationList(ascDeadlineComparator);
        assertCommandSuccess(sortAscDeadlineCommand, model, commandHistory, expectedSuccessMessage, expectedModel);
        assertEquals(Arrays.asList(GOVTECH, META, GOOGLE), model.getSortedApplicationList());

        // Test for sorting by deadline in descending order
        SortCommand sortDscDeadlineCommand = new SortCommand(SortingOrder.DEADLINE, SortingSequence.DESCENDING);
        DeadlineComparator dscDeadlineComparator = new DeadlineComparator(SortingSequence.DESCENDING);
        expectedModel.updateFilteredApplicationList(new ApplicationHasTaskPredicate());
        expectedModel.updateSortedApplicationList(dscDeadlineComparator);
        assertCommandSuccess(sortDscDeadlineCommand, model, commandHistory, expectedSuccessMessage, expectedModel);
        assertEquals(Arrays.asList(GOOGLE, META, GOVTECH), model.getSortedApplicationList());
    }

}
