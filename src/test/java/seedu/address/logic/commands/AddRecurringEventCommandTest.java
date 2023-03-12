package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddRecurringEventCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs());

    @Test
    public void execute_success() throws CommandException {
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);

        RecurringEvent recurringEvent = new RecurringEvent("biking", DayOfWeek.valueOf("MONDAY"),
                LocalTime.parse("12:00"), LocalTime.parse("14:00"));

        AddReccuringEventCommand command = new AddReccuringEventCommand(Index.fromOneBased(1),
                recurringEvent);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addRecurringEvent(editedPerson, recurringEvent);

        String expectedMessage = String.format(AddReccuringEventCommand.MESSAGE_SUCCESS, recurringEvent) + " to "
                + editedPerson.getName();

        assertEquals(expectedMessage, command.execute(model).getFeedbackToUser());

        String expectedList = editedPerson.getRecurringEventList().toString();

        System.out.println(expectedList);

        assertEquals(expectedList, "biking\n");

    }
}
