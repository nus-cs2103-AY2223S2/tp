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
 * Manages adding of customers
 */
public class AddCustomerCommand extends Command {
    public static final String COMMAND_WORD = "addcustomer";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Registers a customer with the shop. "
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
        + PREFIX_TAG + "No insurance "
        + PREFIX_TAG + "owesMoney";
    public static final String MESSAGE_SUCCESS = "New customer added";

    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final Set<Tag> tags;

    /**
     * Constructs command that adds customer to the model
     *
     * @param name    Name of customer
     * @param phone   Phone number of customer
     * @param email   Email of customer
     * @param address Address of customer
     * @param tags    Tags of customer
     */
    public AddCustomerCommand(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
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
        requireNonNull(model);
        try {
            model.getShop().addCustomer(name, phone, email, address, tags);
            model.updateFilteredCustomerList(Model.PREDICATE_SHOW_ALL_CUSTOMERS);
            model.selectCustomer(lst -> lst.get(lst.size() - 1));
            return new CommandResult(MESSAGE_SUCCESS, Tab.CUSTOMERS);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}
