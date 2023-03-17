package seedu.address.experimental.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.entity.Entity;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Entity> PREDICATE_SHOW_ALL_ENTITIES = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getRerollFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setRerollFilePath(Path rerollFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setReroll(ReadOnlyReroll reroll);

    /**
     * Returns the AddressBook
     */
    ReadOnlyReroll getReroll();

    /**
     * Returns true if a entity with the same identity as {@code entity} exists in the address book.
     */
    boolean hasEntity(Entity entity);

    /**
     * Deletes the given entity. The entity must exist in the address book.
     */
    void deleteEntity(Entity target);

    /**
     * Adds the given entity. {@code entity} must not already exist in the address book.
     */
    void addEntity(Entity entity);

    /**
     * Replaces the given entity {@code target} with {@code editedPerson}. {@code target} must exist in the address
     * book. The entity identity of {@code editedPerson} must not be the same as another existing entity in the address
     * book.
     */
    void setEntity(Entity target, Entity editedEntity);

    // ============== Filtered entity list =================

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
     * Sets the current selected entity
     */
    void setCurrentSelectedEntity(Entity newSelection);

    /**
     * Returns the current selected entity
     */
    Entity getCurrentSelectedEntity();

}
