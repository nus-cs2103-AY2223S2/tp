package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyReroll;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Reroll;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Name;
import seedu.address.testutil.EntityBuilder;

public class MakeCommandTest {

    @Test
    public void constructor_nullEntity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MakeCommand(null));
    }

    @Test
    public void execute_characterAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEntityAdded modelStub = new ModelStubAcceptingEntityAdded();
        Entity validEntity = new EntityBuilder().buildChar();

        CommandResult commandResult = new MakeCommand(validEntity).execute(modelStub);

        assertEquals(String.format(MakeCommand.MESSAGE_SUCCESS, validEntity), commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validEntity), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicateEntity_throwsCommandException() {
        Entity validEntity = new EntityBuilder().buildChar();
        MakeCommand makeCommand = new MakeCommand(validEntity);
        ModelStub modelStub = new ModelStubWithEntity(validEntity);

        assertThrows(CommandException.class, MakeCommand.MESSAGE_DUPLICATE_ENTITY, (
        )-> makeCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Entity alice = new EntityBuilder().withName("Alice").buildChar();
        Entity bob = new EntityBuilder().withName("Bob").buildChar();
        MakeCommand addAliceCommand = new MakeCommand(alice);
        MakeCommand addBobCommand = new MakeCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        MakeCommand addAliceCommandCopy = new MakeCommand(alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different person -> returns false
        assertNotEquals(addAliceCommand, addBobCommand);
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getRerollFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRerollFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Entity> getClassificationPredicate(Classification classification) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEntity(Entity entity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyReroll getReroll() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setReroll(ReadOnlyReroll newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEntity(Entity entity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEntities(List<Entity> entities) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEntity(Entity target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEntity(Entity target, Entity editedEntity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Entity> getSnapshotEntities(Predicate<? super Entity> predicate) {
            return null;
        }

        @Override
        public ObservableList<Entity> getFilteredEntityList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEntityList(Predicate<Entity> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<? super Entity> getCurrentPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Adds the predicate of the filtered entity list to filter by the given
         * {@code predicate}.
         *
         * @param predicate
         * @throws NullPointerException if {@code predicate} is null.
         */
        @Override
        public void addPredicate(Predicate<Entity> predicate) {

        }

        @Override
        public void resetFilteredEntityList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void listItems() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void listCharacters() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void listMobs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Entity getCurrentSelectedEntity() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentSelectedEntity(Entity newSelection) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Entity> getListByClassification(Classification classification) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Character createFromTemplate(Name entityName, Name templateName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<String> getTemplates() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithEntity extends ModelStub {
        private final Entity entity;

        ModelStubWithEntity(Entity entity) {
            requireNonNull(entity);
            this.entity = entity;
        }

        @Override
        public boolean hasEntity(Entity entity) {
            requireNonNull(entity);
            return this.entity.isSameEntity(entity);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingEntityAdded extends ModelStub {
        final ArrayList<Entity> personsAdded = new ArrayList<>();

        @Override
        public boolean hasEntity(Entity entity) {
            requireNonNull(entity);
            return personsAdded.stream().anyMatch(entity::isSameEntity);
        }

        @Override
        public void addEntity(Entity entity) {
            requireNonNull(entity);
            personsAdded.add(entity);
        }

        @Override
        public ReadOnlyReroll getReroll() {
            return new Reroll();
        }
    }

}
