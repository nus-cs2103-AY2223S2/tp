package seedu.ultron.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_KEYDATE;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.ultron.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.ultron.logic.commands.exceptions.CommandException;
import seedu.ultron.model.Model;
import seedu.ultron.model.opening.Opening;

/**
 * Adds a opening to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a opening to Ultron. "
            + "Parameters: "
            + PREFIX_POSITION + "POSITION "
            + PREFIX_COMPANY + "COMPANY "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_STATUS + "STATUS "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_KEYDATE + "KEYDATE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_POSITION + "Backend Developer "
            + PREFIX_COMPANY + "Shopee "
            + PREFIX_EMAIL + "hr@shopee.com "
            + PREFIX_STATUS + "applied "
            + PREFIX_REMARK + "3 rounds of interviews "
            + PREFIX_KEYDATE + "Online Assessment@2023-10-10";

    public static final String MESSAGE_SUCCESS = "New opening added: %1$s";
    public static final String MESSAGE_DUPLICATE_OPENING = "This opening already exists in Ultron";

    private final Opening toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Opening}
     */
    public AddCommand(Opening opening) {
        requireNonNull(opening);
        toAdd = opening;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasOpening(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_OPENING);
        }

        model.addOpening(toAdd);
        model.setSelectedIndex(null);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
