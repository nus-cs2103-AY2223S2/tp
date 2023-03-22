package seedu.address.experimental.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;

/***/
public class RerollAllEntities implements ReadOnlyEntities {

    private final UniqueEntityList<Entity> entities;
    {
        entities = new UniqueEntityList<>();
    }

    private final Predicate<Entity> isCharacter = entity -> entity instanceof Character;
    private final Predicate<Entity> isMob = entity -> entity instanceof Mob;
    private final Predicate<Entity> isItem = entity -> entity instanceof Item;

    private final ObservableList<Entity> characters =
            new FilteredList<>(entities.asUnmodifiableObservableList(), isCharacter);
    private final ObservableList<Entity> mobs = new FilteredList<>(entities.asUnmodifiableObservableList(), isMob);
    private final ObservableList<Entity> items = new FilteredList<>(entities.asUnmodifiableObservableList(), isItem);

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
    public String toString() {
        return entities.asUnmodifiableObservableList().size() + " entities";
    }

    /**
     * Returns list of characters
     */
    public ObservableList<Entity> getCharacters() {
        return characters;
    }

    /**
     * Returns list of items
     */
    public ObservableList<Entity> getItems() {
        return items;
    }

    /**
     * Return list of mobs
     */
    public ObservableList<Entity> getMobs() {
        return mobs;
    }
}
