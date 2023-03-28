package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PERSON_IN_ELDERLY;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PERSON_IN_VOLUNTEERS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddVolunteerCommandTest.ModelStubWithVolunteer;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Nric;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;


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
    public void execute_sameNricInElderlyList_throwsCommandException() {
        Elderly validElderly = new ElderlyBuilder().build();
        AddElderlyCommand addElderlyCommand = new AddElderlyCommand(validElderly);
        ModelStub modelStub = new ModelStubWithElderly(validElderly);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_PERSON_IN_ELDERLY, () ->
                        addElderlyCommand.execute(modelStub));
    }

    @Test
    public void execute_sameNricInVolunteersList_throwsCommandException() {
        Volunteer validVolunteer = new VolunteerBuilder().build();
        Elderly validElderly = new ElderlyBuilder().build();
        AddElderlyCommand addElderlyCommand = new AddElderlyCommand(validElderly);
        ModelStub modelStub = new ModelStubWithVolunteer(validVolunteer);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_PERSON_IN_VOLUNTEERS, () ->
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
    public static class ModelStubWithElderly extends ModelStub {
        private final Elderly elderly;

        ModelStubWithElderly(Elderly elderly) {
            requireNonNull(elderly);
            this.elderly = elderly;
        }

        @Override
        public boolean hasElderly(Nric nric) {
            requireNonNull(nric);
            return this.elderly.getNric().equals((nric));
        }

        @Override
        public boolean hasVolunteer(Nric nric) {
            return false;
        }
    }

    /**
     * A Model stub that always accept the elderly being added.
     */
    private static class ModelStubAcceptingElderlyAdded extends ModelStub {
        final ArrayList<Elderly> elderlyAdded = new ArrayList<>();

        @Override
        public boolean hasElderly(Nric nric) {
            return false;
        }

        @Override
        public boolean hasVolunteer(Nric nric) {
            return false;
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
