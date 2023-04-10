package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntities.CARL;
import static seedu.address.testutil.TypicalEntities.LEEROY;
import static seedu.address.testutil.TypicalEntities.RIZZ;
import static seedu.address.testutil.TypicalEntities.SPOON;
import static seedu.address.testutil.TypicalEntities.getTypicalReroll;
import static seedu.address.testutil.TypicalFields.GARB_TAG;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.exceptions.DuplicateEntityException;
import seedu.address.testutil.EntityBuilder;

public class RerollAllEntitiesTest {

    private final RerollAllEntities allEntities = new RerollAllEntities();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), allEntities.getEntityList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> allEntities.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyReroll_replacesData() {
        RerollAllEntities newData = (RerollAllEntities) getTypicalReroll().getEntities();
        allEntities.resetData(newData);
        assertEquals(newData, allEntities);
    }

    @Test
    public void resetData_withDuplicateEntities_throwsDuplicateEntityException() {
        Entity fakeSpoon = new EntityBuilder(SPOON).withTags(new HashSet<>(Arrays.asList(GARB_TAG))).buildItem();
        List<Entity> newEntities = Arrays.asList(SPOON, fakeSpoon);
        RerollAllEntitiesStub newData = new RerollAllEntitiesStub(newEntities);

        assertThrows(DuplicateEntityException.class, () -> allEntities.resetData(newData));
    }

    @Test
    public void hasEntity_nullEntity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> allEntities.hasEntity(null));
    }

    @Test
    public void hasEntity_entityNotInReroll_returnsFalse() {
        assertFalse(allEntities.hasEntity(LEEROY));
    }

    @Test
    public void hasEntity_entityWithSameIdentity_returnsTrue() {
        // Rizz is an item
        allEntities.addEntity(RIZZ);
        Entity editedRizz = new EntityBuilder(RIZZ).withTags(new HashSet<>(Arrays.asList(GARB_TAG))).buildItem();
        assertTrue(allEntities.hasEntity(editedRizz));
    }

    @Test
    public void hasEntity_entityWithSameNameDifferentClassification_returnsFalse() {
        allEntities.addEntity(RIZZ);
        Entity editedRizz = new EntityBuilder(RIZZ).withTags(new HashSet<>(Arrays.asList(GARB_TAG))).buildMob();
        assertFalse(allEntities.hasEntity(editedRizz));
    }

    @Test
    public void getEntityList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> allEntities.getEntityList().remove(0));
    }

    @Test
    public void getCharList_correctResult() {
        // Results can be extrapolated to other classification.
        allEntities.resetData(getTypicalReroll().getEntities());
        RerollAllEntities otherData = new RerollAllEntities();
        List<Entity> characterOnly = Arrays.asList(LEEROY, CARL);
        otherData.setEntities(characterOnly);
        assertTrue(otherData.getCharList().equals(otherData.getEntityList()));
    }

    /**
     * A stub RerollAllEntities whose entity list can violate interface constraints
     */
    public static class RerollAllEntitiesStub implements ReadOnlyEntities {

        private final Predicate<Entity> isCharacter = entity -> entity instanceof Character;
        private final Predicate<Entity> isMob = entity -> entity instanceof Mob;
        private final Predicate<Entity> isItem = entity -> entity instanceof Item;

        private final ObservableList<Entity> entities = FXCollections.observableArrayList();
        private final ObservableList<Entity> items;
        private final ObservableList<Entity> mobs;
        private final ObservableList<Entity> characters;

        RerollAllEntitiesStub(Collection<Entity> entities) {
            this.entities.setAll(entities);
            this.items = new FilteredList<>(this.entities, isItem);
            this.mobs = new FilteredList<>(this.entities, isMob);
            this.characters = new FilteredList<>(this.entities, isCharacter);
        }

        @Override
        public ObservableList<Entity> getEntityList() {
            return entities;
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
    }
}
