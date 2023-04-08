package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
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
 * Manages adding of technicians
 */
public class AddTechnicianCommand extends AddStaffCommand {
    public static final String COMMAND_WORD = "addtechnician";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a technician to the shop. "
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
            + PREFIX_TAG + "leader "
            + PREFIX_TAG + "leaving 2nd march";
    public static final String MESSAGE_SUCCESS = "New technician added";

    /**
     * Constructs command that adds technician to the model
     *
     * @param name    Name of staff
     * @param phone   Phone number of staff
     * @param email   Email of staff
     * @param address Address of staff
     * @param tags    Tags of staff
     */
    public AddTechnicianCommand(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
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
        requireNonNull(model);
        try {
            model.getShop().addTechnician(this.name, this.phone, this.email, this.address, this.tags);
            model.selectTechnician(lst -> lst.get(lst.size() - 1));
            return new CommandResult(MESSAGE_SUCCESS, Tab.TECHNICIANS);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}
