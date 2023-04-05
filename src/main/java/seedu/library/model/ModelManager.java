package seedu.library.model;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.library.commons.core.GuiSettings;
import seedu.library.commons.core.LogsCenter;
import seedu.library.model.bookmark.Bookmark;
import seedu.library.model.tag.Tag;


/**
 * Represents the in-memory model of the library data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Library library;
    private final Tags tagList;
    private final UserPrefs userPrefs;
    private final FilteredList<Bookmark> filteredBookmarks;
    private int selectedIndex;
    private Bookmark selectedBookmark;


    /**
     * Initializes a ModelManager with the given library and userPrefs, tagList.
     */
    public ModelManager(ReadOnlyLibrary library, ReadOnlyUserPrefs userPrefs, ReadOnlyTags tagList) {
        requireAllNonNull(library, userPrefs);

        logger.fine("Initializing with library: " + library
                + " and user prefs " + userPrefs
                + " and tag list " + tagList);

        this.library = new Library(library);
        this.userPrefs = new UserPrefs(userPrefs);
        this.tagList = new Tags(tagList);
        filteredBookmarks = new FilteredList<>(this.library.getBookmarkList());
        selectedBookmark = null;
        selectedIndex = -1;
    }

    public ModelManager() {
        this(new Library(), new UserPrefs(), new Tags());
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
    public Path getLibraryFilePath() {
        return userPrefs.getLibraryFilePath();
    }

    @Override
    public void setLibraryFilePath(Path libraryFilePath) {
        requireNonNull(libraryFilePath);
        userPrefs.setLibraryFilePath(libraryFilePath);
    }

    //=========== Library ================================================================================

    @Override
    public void setLibrary(ReadOnlyLibrary library) {
        this.library.resetData(library);
    }

    @Override
    public ReadOnlyLibrary getLibrary() {
        return library;
    }

    @Override
    public boolean hasBookmark(Bookmark bookmark) {
        requireNonNull(bookmark);
        return library.hasBookmark(bookmark);
    }

    @Override
    public void deleteBookmark(Bookmark target) {
        library.removeBookmark(target);
    }

    @Override
    public void addBookmark(Bookmark bookmark) {
        library.addBookmark(bookmark);
        updateFilteredBookmarkList(PREDICATE_SHOW_ALL_BOOKMARKS);
    }

    @Override
    public void setBookmark(Bookmark target, Bookmark editedBookmark) {
        requireAllNonNull(target, editedBookmark);

        library.setBookmark(target, editedBookmark);
    }
    public void viewBookmark(Bookmark target) {
        library.viewBookmark(target);
    }

    //=========== tagList ================================================================================

    @Override
    public ReadOnlyTags getTags() {
        return tagList;
    }

    @Override
    public void addTags(Set<Tag> toAdd) {
        for (Tag tagToAdd : toAdd) {
            tagList.addTag(tagToAdd);
        }
    }

    @Override
    public boolean hasTag(Set<Tag> tags) {
        return tagList.containsAll(tags);
    }

    @Override
    public boolean hasTag(Tag tag) {
        return tagList.contains(tag);
    }

    @Override
    public String tagListToString() {
        return tagList.tagListToString();
    }

    @Override
    public boolean tagInUse(Tag target) {
        Set<Tag> allTags = library.getAllTags();
        return allTags.contains(target);
    }

    @Override
    public void deleteTag(Tag target) {
        tagList.removeTag(target);
    }

    //=========== Filtered Bookmark List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Bookmark} backed by the internal list of
     * {@code versionedLibrary}
     */
    @Override
    public ObservableList<Bookmark> getFilteredBookmarkList() {
        return filteredBookmarks;
    }

    @Override
    public void updateFilteredBookmarkList(Predicate<Bookmark> predicate) {
        requireNonNull(predicate);
        filteredBookmarks.setPredicate(predicate);
    }

    @Override
    public void updateSortedBookmarkList(String order) {
        requireNonNull(order);
        library.sortBookmarks(order);
    }

    @Override
    public Bookmark getSelectedBookmark() {
        return this.selectedBookmark;
    }
    @Override
    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @Override
    public void updateSelectedBookmark(Bookmark target) {
        this.selectedBookmark = target;
    }
    @Override
    public void updateSelectedIndex(int index) {
        this.selectedIndex = index;
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
        return library.equals(other.library)
                && userPrefs.equals(other.userPrefs)
                && filteredBookmarks.equals(other.filteredBookmarks);
    }

}
