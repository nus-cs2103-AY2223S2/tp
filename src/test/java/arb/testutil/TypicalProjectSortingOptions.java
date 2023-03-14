package arb.testutil;

import arb.commons.core.sorting.ProjectSortingOption;

/**
 * A utility class containing a list of {@code ProjectSortingOption} objects to be used in tests.
 */
public class TypicalProjectSortingOptions {
    public static final ProjectSortingOption BY_DEADLINE = ProjectSortingOption.getSortingOption("deadline");
    public static final ProjectSortingOption BY_TITLE = ProjectSortingOption.getSortingOption("name");
}
