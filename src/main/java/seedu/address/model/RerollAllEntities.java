package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;

/**
 * Wrap all data at the entity level.
 * Package-private utility class of Reroll.
 */
public class RerollAllEntities implements ReadOnlyEntities {

    private final Predicate<Entity> isCharacter = entity -> entity instanceof Character;
    private final Predicate<Entity> isMob = entity -> entity instanceof Mob;
    private final Predicate<Entity> isItem = entity -> entity instanceof Item;

    private final UniqueEntityList entities;
    private final ObservableList<Entity> characters;
    private final ObservableList<Entity> items;
    private final ObservableList<Entity> mobs;

    {
        entities = new UniqueEntityList();
        characters = new FilteredList<>(getEntityList(), isCharacter);
        items = new FilteredList<>(getEntityList(), isItem);
        mobs = new FilteredList<>(getEntityList(), isMob);
    }

    // List level operations ==============================

    void setEntities(List<Entity> entities) {
        this.entities.setEntities(entities);
    }

    void resetData(ReadOnlyEntities newData) {
        requireNonNull(newData);

        setEntities(newData.getEntityList());
    }

    // Entity level operations ===============================
    boolean hasEntity(Entity entity) {
        requireNonNull(entity);
        return entities.contains(entity);
    }

    void addEntity(Entity entity) {
        requireNonNull(entity);
        entities.add(entity);
    }

    void setEntity(Entity target, Entity edited) {
        requireNonNull(edited);

        entities.setEntity(target, edited);
    }

    void deleteEntity(Entity entity) {
        entities.remove(entity);
    }

    @Override
    public ObservableList<Entity> getEntityList() {
        return entities.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Entity> getCharList() {
        return characters;
    }

    @Override
    public ObservableList<Entity> getItemList() {
        return items;
    }

    @Override
    public ObservableList<Entity> getMobList() {
        return mobs;
    }

    @Override
    public String toString() {
        return entities.asUnmodifiableObservableList().size() + " entities";
    }

    /**
     * Returns true if the relative ordering within each classification is the same.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RerollAllEntities)) {
            return false;
        }

        RerollAllEntities otherReroll = (RerollAllEntities) other;
        return otherReroll.items.equals(this.items)
                && otherReroll.characters.equals(this.characters)
                && otherReroll.mobs.equals(this.mobs);
    }
}
