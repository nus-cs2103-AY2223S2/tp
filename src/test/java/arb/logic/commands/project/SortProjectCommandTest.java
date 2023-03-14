package arb.logic.commands.project;

import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.model.Model.PROJECT_DEADLINE_COMPARATOR;
import static arb.model.Model.PROJECT_TITLE_COMPARATOR;
import static arb.testutil.TypicalAddressBook.getTypicalAddressBook;
import static arb.testutil.TypicalProjectSortingOptions.BY_DEADLINE;
import static arb.testutil.TypicalProjectSortingOptions.BY_TITLE;
import static arb.testutil.TypicalProjects.getTypicalProjects;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;
import arb.model.project.Project;

/**
 * Contains integration tests (interaction with the Model) for {@code SortProjectCommand}.
 */
public class SortProjectCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        SortProjectCommand sortByDeadlineCommand = new SortProjectCommand(BY_DEADLINE);
        SortProjectCommand sortByTitleCommand = new SortProjectCommand(BY_TITLE);

        // same object -> returns true
        assertTrue(sortByDeadlineCommand.equals(sortByDeadlineCommand));

        // same values -> returns true
        SortProjectCommand sortByDeadlineCommandCopy = new SortProjectCommand(BY_DEADLINE);
        assertTrue(sortByDeadlineCommand.equals(sortByDeadlineCommandCopy));

        // different types -> returns false
        assertFalse(sortByDeadlineCommand.equals(1));

        // null -> returns false
        assertFalse(sortByDeadlineCommand.equals(null));

        // different sorting option -> returns false
        assertFalse(sortByDeadlineCommand.equals(sortByTitleCommand));
    }

    @Test
    public void execute_sortByTitle_success() {
        SortProjectCommand command = new SortProjectCommand(BY_TITLE);
        expectedModel.updateSortedProjectList(PROJECT_TITLE_COMPARATOR);
        assertCommandSuccess(command, ListType.PROJECT, ListType.PROJECT, model, BY_TITLE.getSuccessMessage(),
                expectedModel);
        List<Project> expectedList = getTypicalProjects();
        Collections.sort(expectedList, PROJECT_TITLE_COMPARATOR);
        assertEquals(expectedList, model.getSortedProjectList());
    }

    @Test
    public void execute_sortByDeadline_success() {
        SortProjectCommand command = new SortProjectCommand(BY_DEADLINE);
        expectedModel.updateSortedProjectList(PROJECT_DEADLINE_COMPARATOR);
        assertCommandSuccess(command, ListType.PROJECT, ListType.PROJECT, model, BY_DEADLINE.getSuccessMessage(),
                expectedModel);
        List<Project> expectedList = getTypicalProjects();
        Collections.sort(expectedList, PROJECT_DEADLINE_COMPARATOR);
        assertEquals(expectedList, model.getSortedProjectList());
    }
}
