package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_KEYDATE;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntaxNew.PREFIX_STATUS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelNew;
import seedu.address.model.opening.Opening;

/**
 * Adds a opening to the address book.
 */
public class AddCommandNew extends CommandNew {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a opening to Ultron. "
            + "Parameters: "
            + PREFIX_POSITION + "POSITION "
            + PREFIX_COMPANY + "COMPANY "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_REMARK + "REMARK "
            + "[" + PREFIX_KEYDATE + "DATE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_POSITION + "Backend Developer "
            + PREFIX_COMPANY + "Shopee "
            + PREFIX_EMAIL + "hr@shopee.com "
            + PREFIX_STATUS + "applied "
            + PREFIX_REMARK + "3 rounds of interviews ";

    public static final String MESSAGE_SUCCESS = "New opening added: %1$s";
    public static final String MESSAGE_DUPLICATE_OPENING = "This opening already exists in Ultron";

    private final Opening toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Opening}
     */
    public AddCommandNew(Opening opening) {
        requireNonNull(opening);
        toAdd = opening;
    }

    @Override
    public CommandResultNew execute(ModelNew model) throws CommandException {
        requireNonNull(model);

        if (model.hasOpening(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_OPENING);
        }

        model.addOpening(toAdd);
        return new CommandResultNew(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommandNew // instanceof handles nulls
                && toAdd.equals(((AddCommandNew) other).toAdd));
    }
}
