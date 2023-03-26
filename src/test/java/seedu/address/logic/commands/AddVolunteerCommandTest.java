package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PERSON_IN_ELDERLY;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PERSON_IN_VOLUNTEERS;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddElderlyCommandTest.ModelStubWithElderly;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Nric;
import seedu.address.testutil.ElderlyBuilder;
import seedu.address.testutil.VolunteerBuilder;

public class AddVolunteerCommandTest {

    @Test
    public void constructor_nullVolunteer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddVolunteerCommand(null));
    }

    @Test
    public void execute_volunteerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingVolunteerAdded modelStub = new ModelStubAcceptingVolunteerAdded();
        Volunteer validVolunteer = new VolunteerBuilder().build();

        CommandResult commandResult = new AddVolunteerCommand(validVolunteer).execute(modelStub);

        assertEquals(String.format(AddVolunteerCommand.MESSAGE_SUCCESS, validVolunteer),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validVolunteer), modelStub.volunteersAdded);
    }

    @Test
    public void execute_sameNricInVolunteersList_throwsCommandException() {
        Volunteer validVolunteer = new VolunteerBuilder().build();
        AddVolunteerCommand addVolunteerCommand = new AddVolunteerCommand(validVolunteer);
        ModelStub modelStub = new ModelStubWithVolunteer(validVolunteer);

        assertThrows(CommandException.class,
                MESSAGE_DUPLICATE_PERSON_IN_VOLUNTEERS, () -> addVolunteerCommand.execute(modelStub));
    }

    @Test
    public void execute_sameNricInElderlyList_throwsCommandException() {
        Volunteer validVolunteer = new VolunteerBuilder().build();
        Elderly validElderly = new ElderlyBuilder().build();
        AddVolunteerCommand addVolunteerCommand = new AddVolunteerCommand(validVolunteer);
        ModelStub modelStub = new ModelStubWithElderly(validElderly);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_PERSON_IN_ELDERLY, () ->
                addVolunteerCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Volunteer alice = new VolunteerBuilder().withName("Alice").build();
        Volunteer bob = new VolunteerBuilder().withName("Bob").build();
        AddVolunteerCommand addAliceCommand = new AddVolunteerCommand(alice);
        AddVolunteerCommand addBobCommand = new AddVolunteerCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddVolunteerCommand addAliceCommandCopy = new AddVolunteerCommand(alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different person -> returns false
        assertNotEquals(addAliceCommand, addBobCommand);
    }

    /**
     * A Model stub that contains a single volunteer.
     */
    public static class ModelStubWithVolunteer extends ModelStub {
        private final Volunteer volunteer;

        ModelStubWithVolunteer(Volunteer volunteer) {
            requireNonNull(volunteer);
            this.volunteer = volunteer;
        }

        @Override
        public boolean hasVolunteer(Nric nric) {
            requireNonNull(nric);
            return this.volunteer.getNric().equals((nric));
        }

        @Override
        public boolean hasElderly(Nric nric) {
            return false;
        }
    }

    /**
     * A Model stub that always accept the volunteer being added.
     */
    private static class ModelStubAcceptingVolunteerAdded extends ModelStub {
        final ArrayList<Volunteer> volunteersAdded = new ArrayList<>();

        @Override
        public boolean hasVolunteer(Nric nric) {
            return false;
        }

        @Override
        public boolean hasElderly(Nric nric) {
            return false;
        }

        @Override
        public void addVolunteer(Volunteer volunteer) {
            requireNonNull(volunteer);
            volunteersAdded.add(volunteer);
        }

        @Override
        public FriendlyLink getFriendlyLink() {
            return new FriendlyLink();
        }
    }
}
