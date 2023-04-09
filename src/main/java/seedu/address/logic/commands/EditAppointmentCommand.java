package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Edits the details of an existing appointment in the shop.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "editappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the id number displayed by listappointments. "
            + "Existing values will be overwritten by the input values.\n"
            + "Note that if " + PREFIX_DATE + " is used, then " + PREFIX_TIME + " must accompany it, and vice versa."
            + "Parameters: "
            + PREFIX_INTERNAL_ID + "APPOINTMENT_ID "
            + "[" + PREFIX_CUSTOMER_ID + "CUSTOMER ID] "
            + "[" + PREFIX_DATE + "DATE  "
            + PREFIX_TIME + "TIME]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CUSTOMER_ID + "5 "
            + PREFIX_DATE + "2023-02-03 "
            + PREFIX_TIME + "17:00";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment %d";

    private final int id;
    private final Optional<Integer> customerId;
    private final Optional<LocalDateTime> dateTime;

    /**
     * @param id ID of appointment
     * @param customerId ID of customer
     * @param dateTime Date and time of appointment
     */
    public EditAppointmentCommand(int id, Optional<Integer> customerId, Optional<LocalDateTime> dateTime) {
        requireNonNull(customerId);
        requireNonNull(dateTime);
        this.id = id;
        this.customerId = customerId;
        this.dateTime = dateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            model.getShop().editAppointment(id, customerId, dateTime);
            model.resetSelected();
            model.selectAppointment(lst -> lst.stream().filter(a -> a.getId() == id).findFirst().get());
            return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, id), Tab.APPOINTMENTS);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}
