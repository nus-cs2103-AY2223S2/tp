package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.person.Elderly;
import seedu.address.testutil.ElderlyBuilder;


public class AddElderlyCommandTest {

    @Test
    public void constructor_nullElderly_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddElderlyCommand(null));
    }

    @Test
    public void execute_elderlyAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingElderlyAdded modelStub = new ModelStubAcceptingElderlyAdded();
        Elderly validElderly = new ElderlyBuilder().build();

        CommandResult commandResult = new AddElderlyCommand(validElderly).execute(modelStub);

        assertEquals(String.format(AddElderlyCommand.MESSAGE_SUCCESS, validElderly),
                commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validElderly), modelStub.elderlyAdded);
    }

    @Test
    public void execute_duplicateElderly_throwsCommandException() {
        Elderly validElderly = new ElderlyBuilder().build();
        AddElderlyCommand addElderlyCommand = new AddElderlyCommand(validElderly);
        ModelStub modelStub = new ModelStubWithElderly(validElderly);

        assertThrows(CommandException.class, Messages.MESSAGE_DUPLICATE_ELDERLY, () ->
                        addElderlyCommand.execute(modelStub));
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
     * A Model stub that contains a single elderly.
     */
    private static class ModelStubWithElderly extends ModelStub {
        private final Elderly elderly;

        ModelStubWithElderly(Elderly elderly) {
            requireNonNull(elderly);
            this.elderly = elderly;
        }

        @Override
        public boolean hasElderly(Elderly elderly) {
            requireNonNull(elderly);
            return this.elderly.isSamePerson(elderly);
        }
    }

    /**
     * A Model stub that always accept the elderly being added.
     */
    private static class ModelStubAcceptingElderlyAdded extends ModelStub {
        final ArrayList<Elderly> elderlyAdded = new ArrayList<>();

        @Override
        public boolean hasElderly(Elderly elderly) {
            requireNonNull(elderly);
            return elderlyAdded.stream().anyMatch(elderly::isSamePerson);
        }

        @Override
        public void addElderly(Elderly elderly) {
            requireNonNull(elderly);
            elderlyAdded.add(elderly);
        }

        @Override
        public FriendlyLink getFriendlyLink() {
            return new FriendlyLink();
        }
    }
}
