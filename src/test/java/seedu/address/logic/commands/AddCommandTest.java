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
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
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
    private static class ModelStub implements Model {
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

        /**
         * Returns true if an elderly with the same identity as {@code elderly} exists in the friendly link database.
         *
         * @param elderly
         */
        @Override
        public boolean hasElderly(Person elderly) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Deletes the given elderly.
         * The elderly must exist in the friendly link database.
         *
         * @param target
         */
        @Override
        public void deleteElderly(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Adds the given elderly.
         * {@code elderly} must not already exist in the friendly link database.
         *
         * @param elderly
         */
        @Override
        public void addElderly(Person elderly) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces the given elderly {@code target} with {@code editedPerson}.
         * {@code target} must exist in the friendly link database.
         * The elderly identity of {@code editedPerson} must not be the same as another existing elderly in the
         * friendly link database.
         *
         * @param target
         * @param editedPerson
         */
        @Override
        public void setElderly(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns true if a volunteer with the same identity as {@code volunteer} exists in the friendly link database.
         *
         * @param volunteer
         */
        @Override
        public boolean hasVolunteer(Person volunteer) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Deletes the given volunteer.
         * The volunteer must exist in the friendly link database.
         *
         * @param target
         */
        @Override
        public void deleteVolunteer(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Adds the given volunteer.
         * {@code volunteer} must not already exist in the friendly link database.
         *
         * @param volunteer
         */
        @Override
        public void addVolunteer(Person volunteer) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces the given volunteer {@code target} with {@code editedPerson}.
         * {@code target} must exist in the friendly link database.
         * The volunteer identity of {@code editedPerson} must not be the same as another existing volunteer in the
         * friendly link database.
         *
         * @param target
         * @param editedPerson
         */
        @Override
        public void setVolunteer(Person target, Person editedPerson) {
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

        /**
         * Returns an unmodifiable view of the filtered elderly list
         */
        @Override
        public ObservableList<Person> getFilteredElderlyList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates the filter of the filtered elderly list to filter by the given {@code predicate}.
         *
         * @param predicate
         * @throws NullPointerException if {@code predicate} is null.
         */
        @Override
        public void updateFilteredElderlyList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns an unmodifiable view of the filtered volunteers list
         */
        @Override
        public ObservableList<Person> getFilteredVolunteerList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates the filter of the filtered volunteers list to filter by the given {@code predicate}.
         *
         * @param predicate
         * @throws NullPointerException if {@code predicate} is null.
         */
        @Override
        public void updateFilteredVolunteerList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private static class ModelStubWithPerson extends ModelStub {
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
    private static class ModelStubAcceptingPersonAdded extends ModelStub {
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
