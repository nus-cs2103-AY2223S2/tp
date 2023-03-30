package seedu.address.experimental.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Template;

/**
 * Wraps all data at Reroll level.
 */
public class Reroll implements ReadOnlyReroll {

    private final Predicate<Entity> isCharacter = entity -> entity instanceof Character;
    private final Predicate<Entity> isMob = entity -> entity instanceof Mob;
    private final Predicate<Entity> isItem = entity -> entity instanceof Item;

    // Abstracting the entity list gives more flexibility for further functionality of Reroll.
    private final RerollAllEntities entities;
    private final ReadOnlyEntities characters;
    private final ReadOnlyEntities items;
    private final ReadOnlyEntities mobs;
    private final Template templates;

    // Initializer
    {
        entities = new RerollAllEntities();
        characters = new RerollCharacters(new FilteredList<>(entities.getEntityList(), isCharacter));
        items = new RerollItems(new FilteredList<>(entities.getEntityList(), isItem));
        mobs = new RerollMobs(new FilteredList<>(entities.getEntityList(), isMob));
        templates = Template.getPresetTemplates();
    }

    public Reroll() {}

    /**
     * Create Reroll from existing copy.
     * @param toBeCopied Unmodifiable view of a Reroll.
     */
    public Reroll(ReadOnlyReroll toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Reset data to new Reroll's data.
     * @param newData Unmodifiable view of another Reroll.
     */
    public void resetData(ReadOnlyReroll newData) {
        requireNonNull(newData);
        // Initialize all entities
        entities.resetData(newData.getEntities());
    }

    @Override
    public ReadOnlyEntities getEntities() {
        return entities;
    }

    @Override
    public ReadOnlyEntities getItems() {
        return items;
    }

    @Override
    public ReadOnlyEntities getCharacters() {
        return characters;
    }

    @Override
    public ReadOnlyEntities getMobs() {
        return mobs;
    }

    @Override
    public List<String> getTemplates() {
        return templates.list();
    }

    // Entity level operations ===============

    public boolean hasEntity(Entity e) {
        return entities.hasEntity(e);
    }

    public void addEntity(Entity entity) {
        entities.addEntity(entity);
    }

    public void setEntity(Entity target, Entity edited) {
        entities.setEntity(target, edited);
    }

    public void deleteEntity(Entity entity) {
        entities.deleteEntity(entity);
    }


    // Misc ====================

    /**
     * Return list for the all entities in Reroll.
     */
    public ObservableList<Entity> getAllList() {
        return entities.getEntityList();
    }

    public ObservableList<Entity> getCharList() {
        return characters.getEntityList();
    }

    public ObservableList<Entity> getItemList() {
        return items.getEntityList();
    }

    public ObservableList<Entity> getMobList() {
        return mobs.getEntityList();
    }

    public Character createFromTemplate(Name newEntity, Name templateName) throws NoSuchElementException {
        return this.templates.generateCharacter(newEntity, templateName);
    }

    @Override
    public String toString() {
        return "Many entities...";
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Reroll
                && entities.equals(((Reroll) other).entities));
    }
}
