package seedu.techtrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.techtrack.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.techtrack.commons.core.GuiSettings;
import seedu.techtrack.logic.commands.exceptions.CommandException;
import seedu.techtrack.logic.parser.OrderParser;
import seedu.techtrack.model.Model;
import seedu.techtrack.model.ReadOnlyRoleBook;
import seedu.techtrack.model.ReadOnlyUserPrefs;
import seedu.techtrack.model.RoleBook;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.testutil.RoleBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_roleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingRoleAdded modelStub = new ModelStubAcceptingRoleAdded();
        Role validRole = new RoleBuilder().build();

        CommandResult<String> commandResult = new AddCommand(validRole).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validRole), commandResult.getOutput());
        assertEquals(Arrays.asList(validRole), modelStub.rolesAdded);
    }

    @Test
    public void execute_duplicateRole_throwsCommandException() {
        Role validRole = new RoleBuilder().build();
        AddCommand addCommand = new AddCommand(validRole);
        ModelStub modelStub = new ModelStubWithRole(validRole);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Role alice = new RoleBuilder().withName("Alice").build();
        Role bob = new RoleBuilder().withName("Bob").build();
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

        // different role -> returns false
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
        public Path getRoleBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRoleBookFilePath(Path roleBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRole(Role role) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRoleBook(ReadOnlyRoleBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyRoleBook getRoleBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRole(Role role) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRole(Role target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRole(Role target, Role editedRole) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Role> getFilteredRoleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRoleList(Predicate<Role> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void displaySortedSalaryList(OrderParser orderParser) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void displaySortedDeadlineList(OrderParser orderParser) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single role.
     */
    private class ModelStubWithRole extends ModelStub {
        private final Role role;

        ModelStubWithRole(Role role) {
            requireNonNull(role);
            this.role = role;
        }

        @Override
        public boolean hasRole(Role role) {
            requireNonNull(role);
            return this.role.isSameRole(role);
        }
    }

    /**
     * A Model stub that always accept the role being added.
     */
    private class ModelStubAcceptingRoleAdded extends ModelStub {
        final ArrayList<Role> rolesAdded = new ArrayList<>();

        @Override
        public boolean hasRole(Role role) {
            requireNonNull(role);
            return rolesAdded.stream().anyMatch(role::isSameRole);
        }

        @Override
        public void addRole(Role role) {
            requireNonNull(role);
            rolesAdded.add(role);
        }

        @Override
        public ReadOnlyRoleBook getRoleBook() {
            return new RoleBook();
        }
    }

}
