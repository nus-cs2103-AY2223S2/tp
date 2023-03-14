package arb.commons.core.sorting;

import static arb.logic.commands.CommandTestUtil.VALID_SORTING_OPTION_DEADLINE;
import static arb.logic.commands.CommandTestUtil.VALID_SORTING_OPTION_TITLE;
import static arb.model.Model.PROJECT_DEADLINE_COMPARATOR;
import static arb.model.Model.PROJECT_TITLE_COMPARATOR;
import static arb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ProjectSortingOptionTest {

    @Test
    public void createProjectSortingOption() {
        // invalid sorting option
        assertThrows(IllegalArgumentException.class, () -> ProjectSortingOption.getSortingOption("invalid"));

        // check equality
        assertEquals(PROJECT_DEADLINE_COMPARATOR, ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_DEADLINE)
                .getComparator());
        assertEquals(PROJECT_TITLE_COMPARATOR, ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_TITLE)
                .getComparator());
    }

    @Test
    public void equals() {
        final ProjectSortingOption byDeadline = ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_DEADLINE);

        // same sorting option -> returns true
        assertTrue(byDeadline.equals(ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_DEADLINE)));

        // same object -> returns true
        assertTrue(byDeadline.equals(byDeadline));

        // null -> returns false
        assertFalse(byDeadline.equals(null));

        // different types -> returns false
        assertFalse(byDeadline.equals(5.0f));

        // different sorting option -> returns false
        assertFalse(byDeadline.equals(ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_TITLE)));
    }
}
