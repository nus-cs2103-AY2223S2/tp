package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.EventCatalogue;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.Model;

/**
 * Deletes all data in the internship and event catalogue.
 */
public class DeleteAllCommand extends Command {

    public static final String COMMAND_WORD = "deleteall";

    public static final String CONFIRMATION_CODE = "confirm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes all internships and events.\n"
            + "Enter \"" + CONFIRMATION_CODE + "\" after to verify deleting all data\n"
            + "Example: " + COMMAND_WORD + " " + CONFIRMATION_CODE;

    public static final String MESSAGE_SUCCESS = "All data deleted!";

    public static final String MESSAGE_INCORRECT_CODE = "Incorrect code.\n"
            + "Confirmation code: " + CONFIRMATION_CODE;

    private final String code;

    /**
     * Creates a DeleteALlCommand with the given code {@code String}
     */
    public DeleteAllCommand(String str) {
        this.code = str;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!code.equals(CONFIRMATION_CODE)) {
            throw new CommandException(MESSAGE_INCORRECT_CODE);
        }

        model.setInternshipCatalogue(new InternshipCatalogue());
        model.setEventCatalogue(new EventCatalogue());
        model.updateSelectedInternship(null);
        return new CommandResult(MESSAGE_SUCCESS, ResultType.HOME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAllCommand // instanceof handles nulls
                && code.equals(((DeleteAllCommand) other).code));
    }
}
