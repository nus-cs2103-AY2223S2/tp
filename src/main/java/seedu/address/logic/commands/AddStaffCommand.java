package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_UNSUPPORTED_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.person.Address;
import seedu.address.model.entity.person.Email;
import seedu.address.model.entity.person.Name;
import seedu.address.model.entity.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Manages adding of Staff
 */
public class AddStaffCommand extends Command {
    public static final String COMMAND_WORD = "addstaff";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a staff to the shop. "
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_PHONE + "PHONE "
        + PREFIX_EMAIL + "EMAIL "
        + PREFIX_ADDRESS + "ADDRESS "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_PHONE + "98765432 "
        + PREFIX_EMAIL + "johnd@example.com "
        + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
        + PREFIX_TAG + "temp intern "
        + PREFIX_TAG + "leaving 2nd march";
    public static final String MESSAGE_SUCCESS = "New staff added";

    protected final Name name;
    protected final Phone phone;
    protected final Email email;
    protected final Address address;
    protected final Set<Tag> tags;

    /**
     * Constructs command that adds staff to the model
     *
     * @param name    Name of staff
     * @param phone   Phone number of staff
     * @param email   Email of staff
     * @param address Address of staff
     * @param tags    Tags of staff
     */
    public AddStaffCommand(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags = tags;
    }

    /**
     * Execution of command
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of command execution
     * @throws CommandException If error occurs during command execution
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_UNSUPPORTED_COMMAND);
    }
}
