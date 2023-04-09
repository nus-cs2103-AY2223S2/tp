package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Index validIndex;
    private Appointment validAppointment;

    @BeforeEach
    public void setUp() {
        validIndex = Index.fromOneBased(1);
        validAppointment = new AppointmentBuilder().build();
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null, validAppointment));
    }

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(validIndex, null));
    }

    @Test
    public void execute_clientIndexOutOfBounds_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(outOfBoundsIndex, validAppointment);
        assertThrows(CommandException.class, () -> addAppointmentCommand.execute(model));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validIndex, validAppointment);
        assertEquals(addAppointmentCommand, addAppointmentCommand);
    }

    @Test
    public void equals_differentTypes_returnsFalse() {
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validIndex, validAppointment);
        assertNotEquals(addAppointmentCommand, new ClearCommand());
    }

    @Test
    public void equals_differentIndex_returnsFalse() {
        AddAppointmentCommand addAppointmentCommandOne = new AddAppointmentCommand(validIndex, validAppointment);
        AddAppointmentCommand addAppointmentCommandTwo = new AddAppointmentCommand(Index.fromOneBased(2),
            validAppointment);
        assertNotEquals(addAppointmentCommandOne, addAppointmentCommandTwo);
    }

    @Test
    public void equals_sameValue_returnsTrue() {
        AddAppointmentCommand addAppointmentCommandOne = new AddAppointmentCommand(validIndex, validAppointment);
        AddAppointmentCommand addAppointmentCommandTwo = new AddAppointmentCommand(validIndex, validAppointment);
        assertEquals(addAppointmentCommandTwo, addAppointmentCommandTwo);
    }

}
