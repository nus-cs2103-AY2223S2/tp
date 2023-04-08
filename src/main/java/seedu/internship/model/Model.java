package seedu.internship.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.internship.commons.core.GuiSettings;
import seedu.internship.model.internship.Internship;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Internship> PREDICATE_SHOW_ALL_INTERNSHIPS = unused -> true;

    /** {@code Predicate} that shows upcoming internships */
    Predicate<Internship> PREDICATE_SHOW_UPCOMING_INTERNSHIPS = internship -> {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        LocalDate nextWeek = now.plusWeeks(1);
        LocalDate deadline = LocalDate.parse(internship.getDate().toString(), formatter);
        String status = internship.getStatus().toString();
        List<String> acceptedStatuses = new ArrayList<>(Arrays.asList(
                "new", "offered", "assessment", "interview"));
        return !deadline.isBefore(now) && deadline.isBefore(nextWeek) && acceptedStatuses.contains(status);
    };

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' InternBuddy file path.
     */
    Path getInternBuddyFilePath();

    /**
     * Sets the user prefs' InternBuddy book file path.
     */
    void setInternBuddyFilePath(Path InternBuddyFilePath);

    /**
     * Replaces InternBuddy data with the data in {@code internbuddy}.
     */
    void setInternBuddy(ReadOnlyInternBuddy internBuddy);

    /** Returns the InternBuddy */
    ReadOnlyInternBuddy getInternBuddy();

    /**
     * Returns true if an internship with the same identity as {@code Internship} exists in InternBuddy.
     */
    boolean hasInternship(Internship internship);

    /**
     * Deletes the given internship.
     * The internship must exist in InternBuddy.
     */
    void deleteInternship(Internship target);

    /**
     * Views the given internship.
     * The {@code internship} must exist in InternBuddy.
     */
    void viewInternship(Internship target);

    /**
     * Adds the given internship.
     * {@code internship} must not already exist in InternBuddy.
     */
    void addInternship(Internship internship);

    /**
     * Replaces the given internship {@code target} with {@code editedInternship}.
     * {@code target} must exist in InternBuddy.
     * The internship identity of {@code editedInternship} must not be the same as another existing internship in
     * InternBuddy.
     *
     * @param target The given internship to be replaced.
     * @param editedInternship The internship to replace the original one.
     */
    void setInternship(Internship target, Internship editedInternship);

    /** Returns an unmodifiable view of the filtered internship list */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Updates the filter of the filtered internship list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipList(Predicate<Internship> predicate);

    /**
     * Gets the internship that is currently being selected for viewing
     *
     * @return the currently selected internship
     */
    Internship getSelectedInternship();


    /**
     * Updates the internship that is currently being selected for viewing
     *
     * @param target The internship that is selected for viewing
     */
    void updateSelectedInternship(Internship target);

    /**
     * Copies the content of the internship onto the clipboard of the computer
     * {@code internship} must not already exist in InternBuddy.
     */

    void copyInternship(Internship target);
}
