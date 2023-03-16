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
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;

/**
 * Represents the in-memory model of the Reroll data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Reroll reroll;
    private final UserPrefs userPrefs;
    private final FilteredList<Entity> filteredAllEntities;
    private final FilteredList<Entity> filteredItems;
    private final FilteredList<Entity> filteredMobs;
    private final FilteredList<Entity> filteredCharacters;
    // Placeholder for the active list.
    private FilteredList<Entity> filteredActive;
    private Entity active = null;

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
        this.filteredCharacters = (FilteredList<Entity>) new FilteredList<>(this.reroll.getCharacterList());
        this.filteredItems = (FilteredList<Entity>) new FilteredList<>(this.reroll.getItemList());
        this.filteredMobs = (FilteredList<Entity>) new FilteredList<>(this.reroll.getMobList());
        this.filteredAllEntities = new FilteredList<>(this.reroll.getAllList());
        // By default, the active list is all entities.
        this.filteredActive = this.filteredAllEntities;
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
        if (entity instanceof Item) {
            reroll.addItem((Item) entity);
            filteredActive = filteredItems;
        } else if (entity instanceof Character) {
            reroll.addCharacter((Character) entity);
            filteredActive = filteredCharacters;
        } else if (entity instanceof Mob) {
            reroll.addMob((Mob) entity);
            filteredActive = filteredMobs;
        }
        updateFilteredEntityList(PREDICATE_SHOW_ALL_ENTITIES);
    }

    @Override
    public void deleteEntity(Entity key) {
        requireNonNull(key);
        if (key instanceof Item) {
            reroll.deleteItem((Item) key);
        } else if (key instanceof Character) {
            reroll.deleteCharacter((Character) key);
        } else if (key instanceof Mob) {
            reroll.deleteMob((Mob) key);
        } else {

        }
    }

    @Override
    public void setEntity(Entity target, Entity edited) {
        requireAllNonNull(target, edited);
        if (!target.getClass().equals(edited.getClass())) {
            return; // throw error.
        }
        if (target instanceof Item) {
            reroll.setItem((Item) target, (Item) edited);
        } else if (target instanceof Character) {
            reroll.setCharacter((Character) target, (Character) edited);
        } else if (target instanceof Mob) {
            reroll.setMob((Mob) target, (Mob) edited);
        } else {
        }
    }

    //=========== Filtered Entity List Accessors ==========================================================//

    @Override
    public ObservableList<Entity> getFilteredEntityList() {
        return filteredActive;
    }

    @Override
    public void updateFilteredEntityList(Predicate<Entity> predicate) {
        requireNonNull(predicate);
        filteredActive.setPredicate(predicate);
    }
    // Reset
    @Override
    public void resetFilteredEntityList() {
        filteredActive = filteredAllEntities;
    }

    // ============= Edit Mode ==================

    @Override
    public void setCurrentSelectedEntity(Entity newSelection) {
        this.active = newSelection;
    }

    @Override
    public Entity getCurrentSelectedEntity() {
        if (active == null) {
            // Should throw some error.
        }
        return active;
    }

}
