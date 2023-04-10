package arb.commons.core.sorting;

import static arb.logic.commands.CommandTestUtil.VALID_SORTING_OPTION_DEADLINE;
import static arb.logic.commands.CommandTestUtil.VALID_SORTING_OPTION_DEADLINE_ALIAS;
import static arb.logic.commands.CommandTestUtil.VALID_SORTING_OPTION_PRICE;
import static arb.logic.commands.CommandTestUtil.VALID_SORTING_OPTION_PRICE_ALIAS;
import static arb.logic.commands.CommandTestUtil.VALID_SORTING_OPTION_TITLE;
import static arb.logic.commands.CommandTestUtil.VALID_SORTING_OPTION_TITLE_ALIAS;
import static arb.model.Model.PROJECT_DEADLINE_COMPARATOR;
import static arb.model.Model.PROJECT_PRICE_COMPARATOR;
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
        assertEquals(PROJECT_PRICE_COMPARATOR, ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_PRICE)
                .getComparator());

        // check equality when using aliases
        assertEquals(PROJECT_DEADLINE_COMPARATOR, ProjectSortingOption
                .getSortingOption(VALID_SORTING_OPTION_DEADLINE_ALIAS)
                .getComparator());
        assertEquals(PROJECT_TITLE_COMPARATOR, ProjectSortingOption
                .getSortingOption(VALID_SORTING_OPTION_TITLE_ALIAS)
                .getComparator());
        assertEquals(PROJECT_PRICE_COMPARATOR, ProjectSortingOption
                .getSortingOption(VALID_SORTING_OPTION_PRICE_ALIAS)
                .getComparator());
    }

    @Test
    public void equals() {
        final ProjectSortingOption byDeadline = ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_DEADLINE);
        final ProjectSortingOption byPrice = ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_PRICE);

        // same sorting option -> returns true
        assertTrue(byDeadline.equals(ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_DEADLINE)));
        assertTrue(byPrice.equals(ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_PRICE)));

        // same sorting option from alias -> returns true
        assertTrue(byDeadline.equals(ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_DEADLINE_ALIAS)));
        assertTrue(byPrice.equals(ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_PRICE_ALIAS)));

        // same object -> returns true
        assertTrue(byDeadline.equals(byDeadline));
        assertTrue(byPrice.equals(byPrice));

        // null -> returns false
        assertFalse(byDeadline.equals(null));
        assertFalse(byPrice.equals(null));

        // different types -> returns false
        assertFalse(byDeadline.equals(5.0f));
        assertFalse(byPrice.equals("abcd"));

        // different sorting option -> returns false
        assertFalse(byDeadline.equals(ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_TITLE)));
        assertFalse(byPrice.equals(ProjectSortingOption.getSortingOption(VALID_SORTING_OPTION_TITLE)));
    }
}
