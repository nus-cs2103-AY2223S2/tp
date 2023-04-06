package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.idgen.IdGenerator;
import seedu.address.model.Model;
import seedu.address.model.entity.shop.exception.CustomerNotFoundException;
import seedu.address.model.service.appointment.Appointment;

/**
 * Manages adding appointments
 */
public class AddAppointmentCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "addappointment";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds appointment with customer. "
            + "Parameters: "
            + PREFIX_CUSTOMER_ID + "CUSTOMER ID "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CUSTOMER_ID + "5 "
            + PREFIX_DATE + "2023-02-03 "
            + PREFIX_TIME + "17:00";

    public static final String MESSAGE_SUCCESS = "New appointment added";
    private final int customerId;
    private final LocalDateTime dateTime;

    /**
     * Constructs command that adds appointment to the model
     * @param customerId ID of customer
     * @param dateTime Date and time of appointment
     */
    public AddAppointmentCommand(int customerId, LocalDateTime dateTime) {
        this.customerId = customerId;
        this.dateTime = dateTime;
    }

    /**
     * Execution of command
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of command execution
     * @throws CommandException If error occurs during command execution
     */
    @Override
    public CommandResult executeUndoableCommand(Model model) throws CommandException {
        try {
            model.getShop().addAppointment(customerId, dateTime);
            model.selectAppointment(lst -> lst.get(lst.size() - 1));
            return new CommandResult(MESSAGE_SUCCESS, Tab.APPOINTMENTS);
        } catch (CustomerNotFoundException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
