package arb.commons.core.sorting;

import static arb.model.Model.PROJECT_DEADLINE_COMPARATOR;
import static arb.model.Model.PROJECT_PRICE_COMPARATOR;
import static arb.model.Model.PROJECT_TITLE_COMPARATOR;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import arb.model.project.Project;

/** Represents a sorting option to sort the project list by. */
public enum ProjectSortingOption {
    DEADLINE("deadline", "d", "Sorted all projects by deadline!", PROJECT_DEADLINE_COMPARATOR),
    NAME("name", "n", "Sorted all projects by name!", PROJECT_TITLE_COMPARATOR),
    PRICE("price", "pr", "Sorted all projects by price!", PROJECT_PRICE_COMPARATOR);

    public static final String MESSAGE_CONSTRAINTS =
            "Sorting options should be either \'deadline\' or \'name\' or \'price\'";
    private static final EnumSet<ProjectSortingOption> sortingOptions = EnumSet.allOf(ProjectSortingOption.class);

    private final Set<String> optionStrings;
    private final String successMessage;
    private final Comparator<Project> comparator;

    /**
     * ProjectSortingOption can only be created by calling {@link ProjectSortingOption#getSortingOption(String)}.
     */
    private ProjectSortingOption(String optionString, String aliasOptionString, String successMessage,
            Comparator<Project> comparator) {
        this.optionStrings = new HashSet<>(Arrays.asList(optionString, aliasOptionString));
        this.successMessage = successMessage;
        this.comparator = comparator;
    }

    private boolean isOptionString(String optionString) {
        return this.optionStrings.contains(optionString);
    }

    /**
     * Returns if a given string is a valid project sorting option.
     */
    public static boolean isValidSortingOption(String optionString) {
        return sortingOptions.stream().anyMatch(e -> e.isOptionString(optionString));
    }

    /**
     * Creates a new {@code {ProjectSortingOption}} using the given option string.
     */
    public static ProjectSortingOption getSortingOption(String optionString) {
        Iterator<ProjectSortingOption> iterator = sortingOptions.iterator();
        while (iterator.hasNext()) {
            ProjectSortingOption currentSortingOption = iterator.next();
            if (currentSortingOption.isOptionString(optionString)) {
                return currentSortingOption;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getSuccessMessage() {
        return this.successMessage;
    }

    public Comparator<Project> getComparator() {
        return this.comparator;
    }

}
