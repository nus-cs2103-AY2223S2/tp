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
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyFriendlyLink;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Person;
import seedu.address.testutil.PairBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddPairCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPairCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPairAdded modelStub = new ModelStubAcceptingPairAdded();
        Pair validPair = new PairBuilder().build();

        CommandResult commandResult = new AddPairCommand(validPair).execute(modelStub);

        assertEquals(String.format(AddPairCommand.MESSAGE_SUCCESS, validPair), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPair), modelStub.pairsAdded);
    }

    @Test
    public void execute_duplicatePair_throwsCommandException() {
        Pair validPair = new PairBuilder().build();
        AddPairCommand addPairCommand = new AddPairCommand(validPair);
        ModelStub modelStub = new ModelStubWithPair(validPair);

        assertThrows(CommandException.class,
                AddPairCommand.MESSAGE_DUPLICATE_PAIR, () -> addPairCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        Pair pair1 = new PairBuilder().withElderly(alice).build();
        Pair pair2 = new PairBuilder().withElderly(bob).build();
        AddPairCommand addPair1Command = new AddPairCommand(pair1);
        AddPairCommand addPair2Command = new AddPairCommand(pair2);

        // same object -> returns true
        assertTrue(addPair1Command.equals(addPair1Command));

        // same values -> returns true
        AddPairCommand addPair1CommandCopy = new AddPairCommand(pair1);
        assertTrue(addPair1CommandCopy.equals(addPair1CommandCopy));

        // different types -> returns false
        assertFalse(addPair1Command.equals(1));

        // null -> returns false
        assertFalse(addPair1Command.equals(null));

        // different person -> returns false
        assertFalse(addPair1Command.equals(addPair2Command));
    }

    /**
     * A default model stub that have all the methods failing.
     */
    public static class ModelStub implements Model {
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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
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
        public ObservableList<Pair> getFilteredPairList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPairList(Predicate<Pair> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single pair.
     */
    private class ModelStubWithPair extends ModelStub {
        private final Pair pair;

        ModelStubWithPair(Pair pair) {
            requireNonNull(pair);
            this.pair = pair;
        }

        @Override
        public boolean hasPair(Pair pair) {
            requireNonNull(pair);
            return this.pair.isSamePair(pair);
        }
    }

    /**
     * A Model stub that always accept the pair being added.
     */
    private class ModelStubAcceptingPairAdded extends ModelStub {
        final ArrayList<Pair> pairsAdded = new ArrayList<>();

        @Override
        public boolean hasPair(Pair pair) {
            requireNonNull(pair);
            return pairsAdded.stream().anyMatch(pair::isSamePair);
        }

        @Override
        public void addPair(Pair pair) {
            requireNonNull(pair);
            pairsAdded.add(pair);
        }

        @Override
        public ReadOnlyFriendlyLink getFriendlyLink() {
            return new FriendlyLink();
        }
    }
}
