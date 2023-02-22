package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_CHARLIE;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
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
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Nric;
import seedu.address.testutil.PairBuilder;

public class AddPairCommandTest {

    private final Nric validAmyNric = new Nric(VALID_NRIC_AMY);
    private final Nric validBobNric = new Nric(VALID_NRIC_BOB);
    private final Nric validCharlieNric = new Nric(VALID_NRIC_CHARLIE);

    @Test
    public void constructor_nullElderly_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPairCommand(null, validAmyNric));
    }

    @Test
    public void constructor_nullVolunteer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPairCommand(validAmyNric, null));
    }

    @Test
    public void execute_pairAcceptedByModel_addSuccessful() throws Exception {
        Pair validPair = new PairBuilder().build();
        Elderly elderly = validPair.getElderly();
        Volunteer volunteer = validPair.getVolunteer();
        ModelStubAcceptingPairAdded modelStub = new ModelStubAcceptingPairAdded(elderly, volunteer);
        CommandResult commandResult = new AddPairCommand(elderly.getNric(), volunteer.getNric()).execute(modelStub);
        assertEquals(String.format(AddPairCommand.MESSAGE_SUCCESS, validPair), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicatePair_throwsCommandException() {
        Pair validPair = new PairBuilder().build();
        ModelStub modelStub = new ModelStubWithPair(validPair);
        Nric elderlyNric = validPair.getElderly().getNric();
        Nric volunteerNric = validPair.getVolunteer().getNric();
        AddPairCommand addPairCommand = new AddPairCommand(elderlyNric, volunteerNric);

        assertThrows(CommandException.class,
                AddPairCommand.MESSAGE_DUPLICATE_PAIR, () -> addPairCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddPairCommand addPair1Command = new AddPairCommand(validAmyNric, validBobNric);
        AddPairCommand addPair2Command = new AddPairCommand(validAmyNric, validCharlieNric);

        // same object -> returns true
        assertTrue(addPair1Command.equals(addPair1Command));

        // same values -> returns true
        AddPairCommand addPair1CommandCopy = new AddPairCommand(validAmyNric, validBobNric);
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
        public Elderly getElderly(Nric nric) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Volunteer getVolunteer(Nric nric) {
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
     * A Model stub that contains a single pair.
     */
    private class ModelStubWithPair extends ModelStub {
        private final Pair pair;

        ModelStubWithPair(Pair pair) {
            requireNonNull(pair);
            this.pair = pair;
        }

        @Override
        public Elderly getElderly(Nric nric) {
            return pair.getElderly();
        }

        @Override
        public Volunteer getVolunteer(Nric nric) {
            return pair.getVolunteer();
        }

        @Override
        public boolean hasPair(Pair pair) {
            requireNonNull(pair);
            return this.pair.isSamePair(pair);
        }
    }

    /**
     * A Model stub that accepts the pair being added if elderly and volunteer already exists.
     */
    private class ModelStubAcceptingPairAdded extends ModelStub {
        private Pair pair;
        private Elderly elderly;
        private Volunteer volunteer;

        ModelStubAcceptingPairAdded(Elderly elderly, Volunteer volunteer) {
            requireAllNonNull(elderly, volunteer);
            this.elderly = elderly;
            this.volunteer = volunteer;
        }

        @Override
        public boolean hasPair(Pair pair) {
            requireNonNull(pair);
            return pair.isSamePair(this.pair);
        }

        @Override
        public void addPair(Pair pair) {
            requireNonNull(pair);
            this.pair = pair;
        }

        @Override
        public Elderly getElderly(Nric nric) {
            return elderly;
        }

        @Override
        public Volunteer getVolunteer(Nric nric) {
            return volunteer;
        }

        @Override
        public ReadOnlyFriendlyLink getFriendlyLink() {
            return new FriendlyLink();
        }
    }

}
