package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.listing.Listing;



/**
 * The API of the Model component.
 */
public interface Model2 {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Listing> PREDICATE_SHOW_ALL_LISTINGS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs2 userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs2 getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getListingBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setListingBookFilePath(Path listingBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setListingBook(ReadOnlyListingBook listingBook);

    /** Returns the AddressBook */
    ReadOnlyListingBook getListingBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasListing(Listing listing);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteListing(Listing target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addListing(Listing listing);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setListing(Listing target, Listing editedListing);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Listing> getFilteredListingList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredListingList(Predicate<Listing> predicate);
}
