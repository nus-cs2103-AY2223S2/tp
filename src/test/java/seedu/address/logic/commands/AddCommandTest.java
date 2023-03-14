package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyTuteeManagingSystem;
import seedu.address.model.TuteeManagingSystem;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.tutee.Tutee;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Tutee validTutee = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validTutee).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validTutee), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTutee), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Tutee validTutee = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validTutee);
        ModelStub modelStub = new ModelStubWithPerson(validTutee);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tutee alice = new PersonBuilder().withName("Alice").build();
        Tutee bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different tutee -> returns false
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
        public Path getTuteeManagingSystemPath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTuteeManagingSystemPath(Path tuteeManagingSystemPath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTutee(Tutee tutee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTuteeManagingSystem(ReadOnlyTuteeManagingSystem tuteeManagingSystem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTuteeManagingSystem getTuteeManagingSystem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutee(Tutee tutee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTutee(Tutee target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutee(Tutee target, Tutee editedTutee) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tutee> getFilteredTuteeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTuteeList(Predicate<Tutee> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single tutee.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Tutee tutee;

        ModelStubWithPerson(Tutee tutee) {
            requireNonNull(tutee);
            this.tutee = tutee;
        }

        @Override
        public boolean hasTutee(Tutee tutee) {
            requireNonNull(tutee);
            return this.tutee.isSamePerson(tutee);
        }
    }

    /**
     * A Model stub that always accept the tutee being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Tutee> personsAdded = new ArrayList<>();

        @Override
        public boolean hasTutee(Tutee tutee) {
            requireNonNull(tutee);
            return personsAdded.stream().anyMatch(tutee::isSamePerson);
        }

        @Override
        public void addTutee(Tutee tutee) {
            requireNonNull(tutee);
            personsAdded.add(tutee);
        }

        @Override
        public ReadOnlyTuteeManagingSystem getTuteeManagingSystem() {
            return new TuteeManagingSystem();
        }
    }

}
