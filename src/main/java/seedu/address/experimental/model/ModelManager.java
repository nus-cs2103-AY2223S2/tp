package seedu.address.experimental.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.entity.Entity;

/**
 * Represents the in-memory model of the Reroll data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Reroll reroll;
    private final UserPrefs userPrefs;
    private final FilteredList<Entity> filteredEntities;

    /**
     * Initializes a ModelManager with the given reroll and userPrefs.
     */

    public ModelManager(ReadOnlyReroll reroll, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(reroll, userPrefs);

        logger.fine("Initializing with Reroll: " + reroll + " and user prefs " + userPrefs);

        this.reroll = new Reroll(reroll);
        this.userPrefs = new UserPrefs(userPrefs);
        // Meaningless to maintain generics at this point.
        // Don't want any generics outside of this model.
        @SuppressWarnings("unchecked")
        FilteredList<Entity> temp = (FilteredList<Entity>) new FilteredList<>(this.reroll.getList());
        this.filteredEntities = temp;
    }

    public ModelManager() {
        this(new Reroll(), new UserPrefs());
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
    public Path getRerollFilePath() {
        return userPrefs.getRerollFilePath();
    }

    @Override
    public void setRerollFilePath(Path rerollFilePath) {
        requireNonNull(rerollFilePath);
        userPrefs.setRerollFilePath(rerollFilePath);
    }

    //=====================================Rerollllll==============================

    @Override
    public void setReroll(ReadOnlyReroll reroll) {
        this.reroll.resetData(reroll);
    }

    @Override
    public ReadOnlyReroll getReroll() {
        return reroll;
    }

    @Override
    public boolean hasEntity(Entity entity) {
        requireNonNull(entity);
        return reroll.hasEntity(entity);
    }

    @Override
    public void addEntity(Entity entity) {
        requireNonNull(entity);
        reroll.addEntity(entity);
        updateFilteredEntityList(PREDICATE_SHOW_ALL_ENTITIES);
    }

    @Override
    public void deleteEntity(Entity entity) {
        reroll.deleteEntity(entity);
    }

    @Override
    public void setEntity(Entity target, Entity edited) {
        reroll.setEntity(target, edited);
    }

    //=========== Filtered Entity List Accessors =============================================================

    @Override
    public ObservableList<Entity> getFilteredEntityList() {
        return filteredEntities;
    }

    @Override
    public void updateFilteredEntityList(Predicate<Entity> predicate) {
        requireNonNull(predicate);
        filteredEntities.setPredicate(predicate);
    }
}
