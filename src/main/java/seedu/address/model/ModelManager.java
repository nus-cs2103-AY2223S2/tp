package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Name;
import seedu.address.model.util.ComposedPredicate;

/**
 * Represents the in-memory model of the Reroll data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private static final Predicate<Entity> PREDICATE_IS_CHARACTER = entity -> entity instanceof Character;
    private static final Predicate<Entity> PREDICATE_IS_MOB = entity -> entity instanceof Mob;
    private static final Predicate<Entity> PREDICATE_IS_ITEM = entity -> entity instanceof Item;

    private final Reroll reroll;
    private final UserPrefs userPrefs;
    private final FilteredList<Entity> filteredActive;
    private Entity currentSelectedEntity;

    /**
     * Initializes a ModelManager with the given Reroll and userPrefs.
     */
    public ModelManager(ReadOnlyReroll reroll, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(reroll, userPrefs);

        logger.fine("Initializing with Reroll: " + reroll + " and user prefs " + userPrefs);

        this.reroll = new Reroll(reroll);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredActive = new FilteredList<>(this.reroll.getEntities().getEntityList());
    }

    /**
     * Initializes a ModelManager with empty Reroll and userPrefs.
     */
    public ModelManager() {
        this(new Reroll(), new UserPrefs());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ModelManager) {
            ModelManager modelManager = (ModelManager) other;
            return (modelManager.reroll).equals(this.reroll) && (modelManager.userPrefs).equals(this.userPrefs);
        } else {
            return false;
        }
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
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

    @Override
    public Predicate<Entity> getClassificationPredicate(Classification classification) {
        return null;
    }

    //=====================================Rerollllll==============================

    @Override
    public ReadOnlyReroll getReroll() {
        return reroll;
    }

    @Override
    public void setReroll(ReadOnlyReroll reroll) {
        this.reroll.resetData(reroll);
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
    public void updateFilteredEntityList(Predicate<Entity> predicate) {
        requireNonNull(predicate);
        filteredActive.setPredicate(predicate);
    }


    @Override
    public void deleteEntities(List<Entity> entities) {
        for (int i = entities.size() - 1; i >= 0; i--) {
            deleteEntity(entities.get(i));
        }

    }

    @Override
    public void deleteEntity(Entity key) {
        requireNonNull(key);
        reroll.deleteEntity(key);
    }

    // =========== List operations

    @Override
    public void setEntity(Entity target, Entity edited) {
        requireAllNonNull(target, edited);

        reroll.setEntity(target, edited);
    }

    @Override
    public void listItems() {
        updateFilteredEntityList(PREDICATE_IS_ITEM);
    }

    @Override
    public void listCharacters() {
        updateFilteredEntityList(PREDICATE_IS_CHARACTER);
    }

    //=========== Filtered Entity List Accessors =============================================================

    @Override
    public void listMobs() {
        updateFilteredEntityList(PREDICATE_IS_MOB);
    }

    /**
     * Returns the entities listed by the given predicate
     * without modifying the selection
     *
     * @param predicate
     */
    @Override
    public List<Entity> getSnapshotEntities(Predicate<? super Entity> predicate) {
        Predicate<? super Entity> previousPredicate = filteredActive.getPredicate();
        filteredActive.setPredicate(predicate);
        List<Entity> result = new ArrayList<>();

        for (Entity e : getFilteredEntityList()) {
            result.add(e);
        }
        filteredActive.setPredicate(previousPredicate);
        return result;
    }

    //=========== Filtered Entity List Accessors =============================================================
    @Override
    public ObservableList<Entity> getFilteredEntityList() {
        return filteredActive;
    }

    @Override
    public Predicate<? super Entity> getCurrentPredicate() {
        return filteredActive.getPredicate();
    }

    @Override
    public void addPredicate(Predicate<Entity> predicate) {
        requireNonNull(predicate);
        filteredActive.setPredicate(new ComposedPredicate<>(filteredActive.getPredicate(), predicate));
    }

    // Reset
    @Override
    public void resetFilteredEntityList() {
        updateFilteredEntityList(PREDICATE_SHOW_ALL_ENTITIES);
    }

    //=========== Edit Mode =============================================================

    @Override
    public Entity getCurrentSelectedEntity() {
        return currentSelectedEntity;
    }

    @Override
    public void setCurrentSelectedEntity(Entity newSelection) {
        currentSelectedEntity = newSelection;
    }

    @Override
    public ObservableList<Entity> getListByClassification(String classification) {
        requireNonNull(classification);
        ObservableList<Entity> entities = null;
        switch (classification) {
        case "char":
            entities = this.reroll.getEntities().getCharList();
            break;
        case "mob":
            entities = this.reroll.getEntities().getMobList();
            break;
        case "item":
            entities = this.reroll.getEntities().getItemList();
            break;
        default:
            logger.info("What have you done");
        }
        return entities;
    }

    @Override
    public Character createFromTemplate(Name entityName, String templateName) throws NoSuchElementException {
        return this.reroll.createFromTemplate(entityName, templateName);
    }

    @Override
    public List<String> getTemplates() {
        return this.reroll.getTemplates();
    }
}
