package arb.commons.core.sorting;

import java.util.Comparator;

import arb.logic.parser.exceptions.ParseException;
import arb.model.Model;
import arb.model.project.Project;

/** Represents an option to sort the project list by. */
public class SortingOption {

    public static final String MESSAGE_CONSTRAINTS =
            "Sorting options should be either \'deadline\' or \'name\'";

    public final static String deadlineSortingOption = "deadline";
    public final static String titleSortingOption = "name";

    public static final String MESSAGE_SUCCESS_TITLE = "Sorted all projects by name!";
    public static final String MESSAGE_SUCCESS_DEADLINE = "Sorted all projects by deadline!";

    private final Comparator<Project> comparator;
    private final String successMessage;

    public SortingOption(String option) throws ParseException {
        switch (option) {
        case deadlineSortingOption:
            comparator = Model.PROJECT_DEADLINE_COMPARATOR;
            successMessage = MESSAGE_SUCCESS_DEADLINE;
            break;
        case titleSortingOption:
            comparator = Model.PROJECT_TITLE_COMPARATOR;
            successMessage = MESSAGE_SUCCESS_TITLE;
            break;
        default:
            throw new ParseException("Invalid comparator");
        }
    }

    public Comparator<Project> getComparator() {
        return this.comparator;
    }

    public String getSuccessMessage() {
        return this.successMessage;
    }

    public static boolean isValidSortingOption(String sortingOption) {
        return (sortingOption.equals(deadlineSortingOption) || sortingOption.equals(titleSortingOption));
    }

}
