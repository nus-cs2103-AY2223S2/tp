package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Name;
import seedu.address.model.entity.Template;

/**
 * Wraps all data at Reroll level.
 */
public class Reroll implements ReadOnlyReroll {

    // Abstracting the entity list gives more flexibility for further functionality of Reroll.
    private final RerollAllEntities entities;
    private final Template templates;

    // Initializer
    {
        entities = new RerollAllEntities();
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

    public Character createFromTemplate(Name newEntity, String templateName) throws NoSuchElementException {
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
