package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentList;
import seedu.address.model.Model;
import seedu.address.ui.CalendarCard;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_ALIAS = "c";
    public static final String MESSAGE_SUCCESS = "Patient and appointment list have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        model.setAppointmentList(new AppointmentList());
        CalendarCard.addAppointmentsToCalendar(model.getFilteredAppointmentList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
