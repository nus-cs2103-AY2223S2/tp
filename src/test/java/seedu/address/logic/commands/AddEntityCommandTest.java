package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.experimental.model.Reroll;
import seedu.address.experimental.model.Model;
import seedu.address.experimental.model.ReadOnlyReroll;
import seedu.address.experimental.model.ReadOnlyUserPrefs;
import seedu.address.model.entity.Entity;
import seedu.address.testutil.EntityBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

public class AddEntityCommandTest {

    @Test
    public void constructor_nullEntity_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEntityCommand(null));
    }

    @Test
    public void execute_characterAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEntityAdded modelStub = new ModelStubAcceptingEntityAdded();
        Entity validEntity = new EntityBuilder().buildChar();

        CommandResult commandResult = new AddEntityCommand(validEntity).execute(modelStub);

        assertEquals(String.format(AddEntityCommand.MESSAGE_SUCCESS, validEntity), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEntity), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicateEntity_throwsCommandException() {
        Entity validEntity = new EntityBuilder().buildChar();
        AddEntityCommand addEntityCommand = new AddEntityCommand(validEntity);
        ModelStub modelStub = new ModelStubWithEntity(validEntity);

        assertThrows(CommandException.class, AddEntityCommand.MESSAGE_DUPLICATE_ENTITY,
                () -> addEntityCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Entity alice = new EntityBuilder().withName("Alice").buildChar();
        Entity bob = new EntityBuilder().withName("Bob").buildChar();
        AddEntityCommand addAliceCommand = new AddEntityCommand(alice);
        AddEntityCommand addBobCommand = new AddEntityCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddEntityCommand addAliceCommandCopy = new AddEntityCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
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
        public void addEntity(Entity entity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setReroll(ReadOnlyReroll newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyReroll getReroll() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEntity(Entity entity) {
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
        public ObservableList<Entity> getFilteredEntityList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEntityList(Predicate<Entity> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addFilteredEntityList(Predicate<Entity> predicate) {
            throw new AssertionError("This method should not be called.");
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
        public void setCurrentSelectedEntity(Entity newSelection) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Entity getCurrentSelectedEntity() {
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
