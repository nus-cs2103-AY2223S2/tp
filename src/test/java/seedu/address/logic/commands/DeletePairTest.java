package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalElderly.ALICE;
import static seedu.address.testutil.TypicalElderly.BENSON;
import static seedu.address.testutil.TypicalVolunteers.AMY;
import static seedu.address.testutil.TypicalVolunteers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyFriendlyLink;
import seedu.address.model.pair.Pair;
import seedu.address.model.pair.exceptions.PairNotFoundException;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.exceptions.ElderlyNotFoundException;
import seedu.address.model.person.exceptions.VolunteerNotFoundException;
import seedu.address.model.person.information.Nric;
import seedu.address.testutil.PairBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class DeletePairTest {

    @Test
    public void execute_validPair_success() throws Exception {
        Pair pair = new PairBuilder().build();
        Nric elderlyNric = pair.getElderly().getNric();
        Nric volunteerNric = pair.getVolunteer().getNric();
        DeletePairCommand deletePairCommand = new DeletePairCommand(elderlyNric, volunteerNric);

        Model model = new ModelStubWithDeletablePair(pair);

        CommandResult commandResult = deletePairCommand.execute(model);
        assertEquals(String.format(DeletePairCommand.MESSAGE_DELETE_PAIR_SUCCESS, pair),
                commandResult.getFeedbackToUser());
        assertFalse(model.hasPair(pair));
    }

    @Test
    public void execute_invalidPerson_failure() throws Exception {
        Pair pair = new PairBuilder().withElderly(ALICE).withVolunteer(BOB).build();
        Model model = new ModelStubWithDeletablePair(pair);
        assertTrue(model.hasPair(pair));

        //Invalid Elderly
        final DeletePairCommand deletePairCommand1 = new DeletePairCommand(AMY.getNric(), BOB.getNric());
        assertThrows(CommandException.class,
                DeletePairCommand.MESSAGE_ELDERLY_NOT_FOUND, () -> deletePairCommand1.execute(model));
        assertTrue(model.hasPair(pair));

        //Invalid Volunteer
        final DeletePairCommand deletePairCommand2 = new DeletePairCommand(ALICE.getNric(), BENSON.getNric());
        assertThrows(CommandException.class,
                DeletePairCommand.MESSAGE_VOLUNTEER_NOT_FOUND, () -> deletePairCommand2.execute(model));
        assertTrue(model.hasPair(pair));
    }

    @Test
    public void equals() {
        final DeletePairCommand deletePairCommand = new DeletePairCommand(ALICE.getNric(), BOB.getNric());

        // same object -> returns true
        assertEquals(deletePairCommand, deletePairCommand);

        // same values -> returns true
        DeletePairCommand deletePairCommandCopy = new DeletePairCommand(ALICE.getNric(), BOB.getNric());
        assertEquals(deletePairCommand, deletePairCommandCopy);

        // null -> returns false
        assertNotEquals(null, deletePairCommand);

        // different elderly -> returns false
        assertNotEquals(deletePairCommand, new DeletePairCommand(AMY.getNric(), BOB.getNric()));

        // different volunteer -> returns false
        assertNotEquals(deletePairCommand, new DeletePairCommand(ALICE.getNric(), BENSON.getNric()));
    }

    /**
     * A Model stub that can delete a pair.
     */
    private static class ModelStubWithDeletablePair extends ModelStub {
        private Pair pair;

        ModelStubWithDeletablePair(Pair pair) {
            requireNonNull(pair);
            this.pair = pair;
        }

        @Override
        public boolean hasPair(Pair pair) {
            requireNonNull(pair);
            return pair.isSamePair(this.pair);
        }

        @Override
        public void deletePair(Pair pair) {
            requireNonNull(pair);
            if (pair.equals(this.pair)) {
                this.pair = null;
            } else {
                throw new PairNotFoundException();
            }
        }

        @Override
        public Elderly getElderly(Nric nric) {
            if (pair.getElderly().getNric().equals(nric)) {
                return pair.getElderly();
            } else {
                throw new ElderlyNotFoundException();
            }
        }

        @Override
        public Volunteer getVolunteer(Nric nric) {
            if (pair.getVolunteer().getNric().equals(nric)) {
                return pair.getVolunteer();
            } else {
                throw new VolunteerNotFoundException();
            }
        }

        @Override
        public ReadOnlyFriendlyLink getFriendlyLink() {
            return new FriendlyLink();
        }
    }

}
