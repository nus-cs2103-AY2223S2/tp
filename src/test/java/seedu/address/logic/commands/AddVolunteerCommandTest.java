package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Volunteer;
import seedu.address.testutil.VolunteerBuilder;

public class AddVolunteerCommandTest {

    @Test
    public void constructor_nullVolunteer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddVolunteerCommand(null));
    }

    @Test
    public void execute_volunteerAcceptedByModel_addSuccessful() throws Exception {
        CommandTestUtil.ModelStubAcceptingPersonAdded modelStub = new CommandTestUtil.ModelStubAcceptingPersonAdded();
        Volunteer validVolunteer = new VolunteerBuilder().build();

        CommandResult commandResult = new AddVolunteerCommand(validVolunteer).execute(modelStub);

        assertEquals(String.format(AddVolunteerCommand.MESSAGE_SUCCESS, validVolunteer),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validVolunteer), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Volunteer validVolunteer = new VolunteerBuilder().build();
        AddVolunteerCommand addCommand = new AddVolunteerCommand(validVolunteer);
        CommandTestUtil.ModelStub modelStub = new CommandTestUtil.ModelStubWithPerson(validVolunteer);

        assertThrows(CommandException.class,
                AddVolunteerCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
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
}
