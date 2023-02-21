package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyFriendlyLink;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Person;
import seedu.address.testutil.ElderlyBuilder;


public class AddElderlyCommandTest {

    @Test
    public void constructor_nullVolunteer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddElderlyCommand(null));
    }

    @Test
    public void execute_volunteerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Elderly validElderly = new ElderlyBuilder().build();

        CommandResult commandResult = new AddElderlyCommand(validElderly).execute(modelStub);

        assertEquals(String.format(AddElderlyCommand.MESSAGE_SUCCESS, validElderly),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validElderly), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Elderly validElderly = new ElderlyBuilder().build();
        AddElderlyCommand addCommand = new AddElderlyCommand(validElderly);
        ModelStub modelStub = new ModelStubWithPerson(validElderly);

        assertThrows(CommandException.class,
                AddElderlyCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Elderly alice = new ElderlyBuilder().withName("Alice").build();
        Elderly bob = new ElderlyBuilder().withName("Bob").build();
        AddElderlyCommand addAliceCommand = new AddElderlyCommand(alice);
        AddElderlyCommand addBobCommand = new AddElderlyCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddElderlyCommand addAliceCommandCopy = new AddElderlyCommand(alice);
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
        public Path getFriendlyLinkFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFriendlyLinkFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFriendlyLink(ReadOnlyFriendlyLink newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFriendlyLink getFriendlyLink() {
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
        public void addPair(Pair pair) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPair(Pair pair) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePair(Pair target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPair(Pair target, Pair editedPair) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Pair> getFilteredPairList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPairList(Predicate<Pair> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends AddElderlyCommandTest.ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends AddElderlyCommandTest.ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyFriendlyLink getFriendlyLink() {
            return new FriendlyLink();
        }
    }
}
