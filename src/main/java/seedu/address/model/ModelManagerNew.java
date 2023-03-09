package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.opening.Opening;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManagerNew implements ModelNew {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Ultron ultron;
    private final UserPrefsNew userPrefs;
    private final FilteredList<Opening> filteredOpenings;

    /**
     * Initializes a ModelManager with the given ultron and userPrefs.
     */
    public ModelManagerNew(ReadOnlyUltron ultron, ReadOnlyUserPrefsNew userPrefs) {
        requireAllNonNull(ultron, userPrefs);

        logger.fine("Initializing with ultron: " + ultron + " and user prefs " + userPrefs);

        this.ultron = new Ultron(ultron);
        this.userPrefs = new UserPrefsNew(userPrefs);
        filteredOpenings = new FilteredList<>(this.ultron.getOpeningList());
    }

    public ModelManagerNew() {
        this(new Ultron(), new UserPrefsNew());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefsNew userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefsNew getUserPrefs() {
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
        updateFilteredOpeningList(PREDICATE_SHOW_ALL_PERSONS);
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
        filteredOpenings.setPredicate(predicate);
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
        ModelManagerNew other = (ModelManagerNew) obj;
        return ultron.equals(other.ultron)
                && userPrefs.equals(other.userPrefs)
                && filteredOpenings.equals(other.filteredOpenings);
    }

}
