package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PAIR;
import static seedu.address.commons.core.Messages.MESSAGE_ELDERLY_NOT_FOUND;
import static seedu.address.commons.core.Messages.MESSAGE_VOLUNTEER_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_CHARLIE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPairs.PAIR1;
import static seedu.address.testutil.TypicalPairs.PAIR2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.pair.Pair;
import seedu.address.model.pair.exceptions.DuplicatePairException;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.exceptions.ElderlyNotFoundException;
import seedu.address.model.person.exceptions.VolunteerNotFoundException;
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
        String expectedMessage = String.format(AddPairCommand.MESSAGE_ADD_PAIR_SUCCESS,
                elderly.getNric(), volunteer.getNric());
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(validPair, modelStub.pair);
    }

    @Test
    public void execute_invalidPerson_throwsCommandException() {
        Nric pair1ElderlyNric = PAIR1.getElderly().getNric();
        Nric pair1VolunteerNric = PAIR1.getVolunteer().getNric();
        Nric pair2ElderlyNric = PAIR2.getElderly().getNric();
        Nric pair2VolunteerNric = PAIR2.getVolunteer().getNric();
        ModelStub modelStub = new ModelStubAcceptingPairAdded(PAIR1.getElderly(), PAIR1.getVolunteer());

        // Invalid elderly -> throws exception
        AddPairCommand addPairCommand1 = new AddPairCommand(pair2ElderlyNric, pair1VolunteerNric);
        String expectedMessage = String.format(MESSAGE_ELDERLY_NOT_FOUND, pair2ElderlyNric);
        assertThrows(CommandException.class,
                expectedMessage, () -> addPairCommand1.execute(modelStub));

        // Invalid volunteer -> throws exception
        AddPairCommand addPairCommand2 = new AddPairCommand(pair1ElderlyNric, pair2VolunteerNric);
        expectedMessage = String.format(MESSAGE_VOLUNTEER_NOT_FOUND, pair2VolunteerNric);
        assertThrows(CommandException.class,
                expectedMessage, () -> addPairCommand2.execute(modelStub));
    }

    @Test
    public void execute_duplicatePair_throwsCommandException() {
        Pair validPair = new PairBuilder().build();
        ModelStub modelStub = new ModelStubWithPair(validPair);
        Nric elderlyNric = validPair.getElderly().getNric();
        Nric volunteerNric = validPair.getVolunteer().getNric();
        AddPairCommand addPairCommand = new AddPairCommand(elderlyNric, volunteerNric);
        String expectedMessage = String.format(MESSAGE_DUPLICATE_PAIR, elderlyNric, volunteerNric);
        assertThrows(CommandException.class,
                expectedMessage, () -> addPairCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddPairCommand addPair1Command = new AddPairCommand(validAmyNric, validBobNric);
        AddPairCommand addPair2Command = new AddPairCommand(validAmyNric, validCharlieNric);
        AddPairCommand addPair3Command = new AddPairCommand(validBobNric, validAmyNric);

        // same object -> returns true
        assertEquals(addPair1Command, addPair1Command);

        // same values -> returns true
        AddPairCommand addPair1CommandCopy = new AddPairCommand(validAmyNric, validBobNric);
        assertEquals(addPair1CommandCopy, addPair1CommandCopy);

        // different types -> returns false
        assertNotEquals(1, addPair1Command);

        // null -> returns false
        assertNotEquals(null, addPair1Command);

        // different person -> returns false
        assertNotEquals(addPair1Command, addPair2Command);
        assertNotEquals(addPair1Command, addPair3Command);
    }

    /**
     * A Model stub that contains a single pair.
     */
    private static class ModelStubWithPair extends ModelStub {
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

        @Override
        public boolean addPair(Nric elderlyNric, Nric volunteerNric) {
            if (pair.getElderly().getNric().equals(elderlyNric)
                && pair.getVolunteer().getNric().equals(volunteerNric)) {
                throw new DuplicatePairException();
            }
            return false; // dummy return
        }

    }

    /**
     * A Model stub that accepts the pair being added if elderly and volunteer already exists.
     */
    private static class ModelStubAcceptingPairAdded extends ModelStub {
        private Pair pair;
        private final Elderly elderly;
        private final Volunteer volunteer;

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
        public boolean addPair(Nric elderlyNric, Nric volunteerNric) {
            if (!elderlyNric.equals(elderly.getNric())) {
                throw new ElderlyNotFoundException();
            } else if (!volunteerNric.equals(volunteer.getNric())) {
                throw new VolunteerNotFoundException();
            } else if (pair != null
                    && pair.getElderly().getNric().equals(elderlyNric)
                    && pair.getVolunteer().getNric().equals(volunteerNric)) {
                throw new DuplicatePairException();
            }
            this.pair = new Pair(getElderly(elderlyNric), getVolunteer(volunteerNric));
            return false; // dummy return
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
        public FriendlyLink getFriendlyLink() {
            return new FriendlyLink();
        }
    }

}
