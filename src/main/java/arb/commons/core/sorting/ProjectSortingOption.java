package arb.commons.core.sorting;

import static arb.model.Model.PROJECT_DEADLINE_COMPARATOR;
import static arb.model.Model.PROJECT_TITLE_COMPARATOR;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;

import arb.model.project.Project;

/** Represents a sorting option to sort the project list by. */
public enum ProjectSortingOption {
    DEADLINE("deadline", "Sorted all projects by deadline!", PROJECT_DEADLINE_COMPARATOR),
    NAME("name", "Sorted all projects by name!", PROJECT_TITLE_COMPARATOR);

    public static final String MESSAGE_CONSTRAINTS =
            "Sorting options should be either \'deadline\' or \'name\'";
    private static final EnumSet<ProjectSortingOption> sortingOptions = EnumSet.allOf(ProjectSortingOption.class);

    private final String optionString;
    private final String successMessage;
    private final Comparator<Project> comparator;

    /**
     * ProjectSortingOption can only be created by calling {@link ProjectSortingOption#getSortingOption(String)}.
     */
    private ProjectSortingOption(String optionString, String successMessage, Comparator<Project> comparator) {
        this.optionString = optionString;
        this.successMessage = successMessage;
        this.comparator = comparator;
    }

    /**
     * Returns if a given string is a valid project sorting option.
     */
    public static boolean isValidSortingOption(String optionString) {
        return sortingOptions.stream().anyMatch(e -> e.optionString.equals(optionString));
    }

    /**
     * Creates a new {@code {ProjectSortingOption}} using the given option string.
     */
    public static ProjectSortingOption getSortingOption(String optionString) {
        Iterator<ProjectSortingOption> iterator = sortingOptions.iterator();
        while (iterator.hasNext()) {
            ProjectSortingOption currentSortingOption = iterator.next();
            if (currentSortingOption.optionString.equals(optionString)) {
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
