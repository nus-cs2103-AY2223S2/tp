package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.comparator.ListingComparator;
import seedu.address.model.listing.Listing;

/**
 * Represents the in-memory model of the listing book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final ListingBook listingBook;
    private List<ListingBook> prevListingBookStates;
    private final UserPrefs userPrefs;
    private final FilteredList<Listing> filteredListings;
    private final SortedList<Listing> displayedListings;

    /**
     * Initializes a ModelManager with the given listingBook and userPrefs.
     */
    public ModelManager(ReadOnlyListingBook listingBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(listingBook, userPrefs);

        logger.fine("Initializing with listing book: " + listingBook + " and user prefs " + userPrefs);

        this.listingBook = new ListingBook(listingBook);
        this.prevListingBookStates = new ArrayList<>();
        this.userPrefs = new UserPrefs(userPrefs);
        filteredListings = new FilteredList<>(this.listingBook.getListingList());
        displayedListings = new SortedList<>(this.filteredListings);
    }

    public ModelManager() {
        this(new ListingBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getListingBookFilePath() {
        return userPrefs.getListingBookFilePath();
    }

    @Override
    public void setListingBookFilePath(Path listingBookFilePath) {
        requireNonNull(listingBookFilePath);
        userPrefs.setListingBookFilePath(listingBookFilePath);
    }

    //=========== ListingBook ================================================================================

    @Override
    public void setListingBook(ReadOnlyListingBook listingBook) {
        this.listingBook.resetData(listingBook);
    }

    @Override
    public ReadOnlyListingBook getListingBook() {
        return listingBook;
    }

    @Override
    public boolean hasListing(Listing listing) {
        requireNonNull(listing);
        return listingBook.hasListing(listing);
    }

    @Override
    public void deleteListing(Listing target) {
        commitListingBook();
        listingBook.removeListing(target);
    }

    @Override
    public void addListing(Listing listing) {
        commitListingBook();
        listingBook.addListing(listing);
        updateFilteredListingBook(PREDICATE_SHOW_ALL_LISTINGS);
    }

    @Override
    public void setListing(Listing target, Listing editedListing) {
        requireAllNonNull(target, editedListing);
        commitListingBook();

        listingBook.setListing(target, editedListing);
    }

    @Override
    public void undo() {
        // Calculate index of last state
        int index = prevListingBookStates.size() - 1;

        // Revert back to the 2nd last state
        this.listingBook.setListings(prevListingBookStates.get(index).getListingList());

        // Delete last element by passing index
        prevListingBookStates.remove(index);
    }

    @Override
    public boolean hasPreviousState() {
        return !this.prevListingBookStates.isEmpty();
    }

    @Override
    public void commitListingBook() {
        ListingBook currState = new ListingBook();
        currState.setListings(listingBook.getListingList());
        this.prevListingBookStates.add(currState);
    }

    //=========== Filtered Listing List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Listing> getDisplayedListingBook() {
        return displayedListings;
    }

    @Override
    public void updateFilteredListingBook(Predicate<Listing> predicate) {
        requireNonNull(predicate);
        filteredListings.setPredicate(predicate);
    }

    @Override
    public void updateSortedListingBook(ListingComparator comparator) {
        requireNonNull(comparator);
        displayedListings.setComparator(comparator);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return listingBook.equals(other.listingBook)
                && userPrefs.equals(other.userPrefs)
                && filteredListings.equals(other.filteredListings)
                && displayedListings.equals(other.displayedListings);
    }
}
