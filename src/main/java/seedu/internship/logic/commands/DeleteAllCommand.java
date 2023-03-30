package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.internship.model.EventCatalogue;
import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.Model;

/**
 * Clears the internship catalogue.
 */
public class DeleteAllCommand extends Command {

    public static final String COMMAND_WORD = "deleteall";

    public static final String CONFIRMATION_CODE = "confirm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes all internships and events.\n"
            + "Enter \"" + CONFIRMATION_CODE + "\" after to verify deleting all data\n"
            + "Example: " + COMMAND_WORD + " " + CONFIRMATION_CODE;

    public static final String MESSAGE_SUCCESS = "All data deleted!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInternshipCatalogue(new InternshipCatalogue());
        model.setEventCatalogue(new EventCatalogue());
        return new CommandResult(MESSAGE_SUCCESS, ResultType.HOME);
    }
}
