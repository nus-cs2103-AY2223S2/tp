package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Name;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Entity> PREDICATE_SHOW_ALL_ENTITIES = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' Reroll file path.
     */
    Path getRerollFilePath();

    /**
     * Sets the user prefs' Reroll file path.
     */
    void setRerollFilePath(Path rerollFilePath);

    Predicate<Entity> getClassificationPredicate(Classification classification);

    /**
     * Returns Reroll
     */
    ReadOnlyReroll getReroll();

    /**
     * Replaces address book data with the data in {@code Reroll}.
     */
    void setReroll(ReadOnlyReroll reroll);

    /**
     * Returns true if a entity with the same identity as {@code entity} exists in the address book.
     */
    boolean hasEntity(Entity entity);


    void deleteEntities(List<Entity> entities);

    /**
     * Deletes the given entity. The entity must exist in Reroll.
     */
    void deleteEntity(Entity target);

    /**
     * Adds the given entity. {@code entity} must not already exist in Reroll.
     */
    void addEntity(Entity entity);

    /**
     * Replaces the given entity {@code target} with {@code editedEntity}. {@code target} must exist in Reroll.
     * The entity identity of {@code editedEntity} must not be the same as another existing entity in Reroll.
     */
    void setEntity(Entity target, Entity editedEntity);

    // ============== Filtered entity list =================
    /**
     * Returns the entities listed by the given predicate
     * without modifying the selection
     *
     * @param predicate
     */
    List<Entity> getSnapshotEntities(Predicate<? super Entity> predicate);

    /**
     * Returns an unmodifiable view of the filtered entity list
     */
    ObservableList<Entity> getFilteredEntityList();

    /**
     * Updates the filter of the filtered entity list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEntityList(Predicate<Entity> predicate);

    /**
     * Returns the current Predicate of the filtered list.
     */
    Predicate<? super Entity> getCurrentPredicate();

    /**
     * Adds the predicate of the filtered entity list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void addPredicate(Predicate<Entity> predicate);

    /**
     * Resets filtered entity list back to all entities Can be used before tag/name find function
     */
    void resetFilteredEntityList();

    /**
     * Set filtered list to items only
     */
    void listItems();

    /**
     * Set filtered list to characters only
     */
    void listCharacters();

    /**
     * Set filtered list to mobs only
     */
    void listMobs();


    // =============== Edit mode ===================

    /**
     * Returns the current selected entity
     */
    Entity getCurrentSelectedEntity();

    /**
     * Sets the current selected entity
     */
    void setCurrentSelectedEntity(Entity newSelection);

    /**
     * Get list of entities by classification
     */
    ObservableList<Entity> getListByClassification(Classification classification);

    /**
     * Get new character using template
     */
    Character createFromTemplate(Name entityName, Name templateName) throws NoSuchElementException;

    List<String> getTemplates();
}
