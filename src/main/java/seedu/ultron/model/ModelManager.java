package seedu.ultron.model;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.ultron.commons.core.GuiSettings;
import seedu.ultron.commons.core.LogsCenter;
import seedu.ultron.commons.core.index.Index;
import seedu.ultron.model.opening.KeydateSort;
import seedu.ultron.model.opening.Opening;
import seedu.ultron.model.opening.OpeningByDateComparator;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Ultron ultron;
    private final UserPrefs userPrefs;
    private ObservableList<Opening> filteredOpenings;
    private Index selectedIndex;

    /**
     * Initializes a ModelManager with the given ultron and userPrefs.
     */
    public ModelManager(ReadOnlyUltron ultron, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(ultron, userPrefs);

        logger.fine("Initializing with ultron: " + ultron + " and user prefs " + userPrefs);

        this.ultron = new Ultron(ultron);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredOpenings = FXCollections.observableArrayList(this.ultron.getOpeningList());
    }

    public ModelManager() {
        this(new Ultron(), new UserPrefs());
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
    public Path getUltronFilePath() {
        return userPrefs.getUltronFilePath();
    }

    @Override
    public void setUltronFilePath(Path ultronFilePath) {
        requireNonNull(ultronFilePath);
        userPrefs.setUltronFilePath(ultronFilePath);
    }

    //=========== Ultron ================================================================================

    @Override
    public void setUltron(ReadOnlyUltron ultron) {
        this.ultron.resetData(ultron);
    }

    @Override
    public ReadOnlyUltron getUltron() {
        return ultron;
    }

    @Override
    public boolean hasOpening(Opening person) {
        requireNonNull(person);
        return ultron.hasOpening(person);
    }

    @Override
    public void deleteOpening(Opening target) {
        ultron.removeOpening(target);
    }

    @Override
    public void addOpening(Opening person) {
        ultron.addOpening(person);
        updateFilteredOpeningList(PREDICATE_SHOW_ALL_OPENINGS);
    }

    @Override
    public void setOpening(Opening target, Opening editedOpening) {
        requireAllNonNull(target, editedOpening);

        ultron.setOpening(target, editedOpening);
    }

    //=========== Filtered Opening List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Opening} backed by the internal list of
     * {@code versionedUltron}
     */
    @Override
    public ObservableList<Opening> getFilteredOpeningList() {
        return filteredOpenings;
    }

    @Override
    public void updateFilteredOpeningList(Predicate<Opening> predicate) {
        requireNonNull(predicate);
        filteredOpenings.clear();
        filteredOpenings.addAll(this.ultron.getOpeningList());
        for (Iterator<Opening> iterator = filteredOpenings.iterator(); iterator.hasNext();) {
            Opening opening = iterator.next();
            if (!predicate.test(opening)) {
                iterator.remove();
            }
        }
    }

    @Override
    public void sortFilteredOpeningList(KeydateSort direction) {
        if (direction.getDirection().equals("ASC")) {
            FXCollections.sort(filteredOpenings, new OpeningByDateComparator());
        } else if (direction.getDirection().equals("DESC")) {
            FXCollections.sort(filteredOpenings, new OpeningByDateComparator().reversed());
        }
    }

    //=========== Selected Opening Accessors=======================================================================

    @Override
    public Opening getSelectedOpening() {
        if (selectedIndex == null) {
            return null;
        }
        return filteredOpenings.get(selectedIndex.getZeroBased());
    }

    public boolean hasSelectedIndex() {
        return selectedIndex != null;
    }

    @Override
    public Index getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public void setSelectedIndex(Index index) {
        selectedIndex = index;
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
        return ultron.equals(other.ultron)
                && userPrefs.equals(other.userPrefs)
                && filteredOpenings.equals(other.filteredOpenings);
    }

}
