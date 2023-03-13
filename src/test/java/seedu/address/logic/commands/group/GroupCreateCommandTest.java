package seedu.address.logic.commands.group;

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
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for GroupListCommand.
 */
class GroupCreateCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupCreateCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        GroupCreateCommandTest.ModelStubAcceptingGroupCreated modelStub =
                new GroupCreateCommandTest.ModelStubAcceptingGroupCreated();
        Group validGroup = new Group("CS2103");

        CommandResult commandResult = new GroupCreateCommand(validGroup).execute(modelStub);

        assertEquals(String.format(GroupCreateCommand.MESSAGE_SUCCESS, validGroup), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validGroup), modelStub.groupsCreated);
    }

    @Test
    public void execute_duplicateGroup_throwsCommandException() {
        Group validGroup = new Group("CS2103");
        GroupCreateCommand groupCreateCommand = new GroupCreateCommand(validGroup);
        GroupCreateCommandTest.ModelStub modelStub = new GroupCreateCommandTest.ModelStubWithGroup(validGroup);

        assertThrows(CommandException.class,
                GroupCreateCommand.MESSAGE_DUPLICATE_GROUP, () -> groupCreateCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Group friendsGroup = new Group("Friends");
        Group courseGroup = new Group("CS2103");
        GroupCreateCommand createFriendsGroupCommand = new GroupCreateCommand(friendsGroup);
        GroupCreateCommand createCourseGroupCommand = new GroupCreateCommand(courseGroup);

        // same object -> returns true
        assertTrue(createFriendsGroupCommand.equals(createFriendsGroupCommand));

        // same values -> returns true
        GroupCreateCommand createFriendsGroupCommandCopy = new GroupCreateCommand(friendsGroup);
        assertTrue(createFriendsGroupCommand.equals(createFriendsGroupCommandCopy));

        // different types -> returns false
        assertFalse(createFriendsGroupCommand.equals(1));

        // null -> returns false
        assertFalse(createFriendsGroupCommand.equals(null));

        // different person -> returns false
        assertFalse(createFriendsGroupCommand.equals(createCourseGroupCommand));
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPersonInGroup(Person person, Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removePersonFromGroup(Person person, Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGroup(Group group) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Group> getFilteredGroupList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredGroupList(Predicate<Group> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addIsolatedEvent(Person index, IsolatedEvent eventToAdd) {
            throw new AssertionError("This method should not be called.");
        }

        public void deleteIsolatedEvent(Person personToEdit, IsolatedEvent event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIsolatedEvent(Person personToEdit, IsolatedEvent originalEvent,
                                     IsolatedEvent editedIsolatedEvent) {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRecurringEvent(Person personToEdit, RecurringEvent eventToAdd) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single group.
     */
    private class ModelStubWithGroup extends GroupCreateCommandTest.ModelStub {
        private final Group group;

        ModelStubWithGroup(Group group) {
            requireNonNull(group);
            this.group = group;
        }

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.isSameGroup(group);
        }
    }

    /**
     * A Model stub that always accept the group being added.
     */
    private class ModelStubAcceptingGroupCreated extends GroupCreateCommandTest.ModelStub {
        final ArrayList<Group> groupsCreated = new ArrayList<>();

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return groupsCreated.stream().anyMatch(group::isSameGroup);
        }

        @Override
        public void addGroup(Group group) {
            requireNonNull(group);
            groupsCreated.add(group);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
