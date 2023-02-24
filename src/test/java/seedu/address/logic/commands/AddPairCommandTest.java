package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyFriendlyLink;
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
        assertEquals(Collections.singletonList(validPair), modelStub.pairsAdded);
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
        assertEquals(addPair1Command, addPair1Command);

        // same values -> returns true
        AddPairCommand addPair1CommandCopy = new AddPairCommand(pair1);
        assertEquals(addPair1CommandCopy, addPair1CommandCopy);

        // different types -> returns false
        assertNotEquals(1, addPair1Command);

        // null -> returns false
        assertNotEquals(null, addPair1Command);

        // different person -> returns false
        assertNotEquals(addPair1Command, addPair2Command);
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
        public boolean hasPair(Pair pair) {
            requireNonNull(pair);
            return this.pair.isSamePair(pair);
        }
    }

    /**
     * A Model stub that always accept the pair being added.
     */
    private static class ModelStubAcceptingPairAdded extends ModelStub {
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
