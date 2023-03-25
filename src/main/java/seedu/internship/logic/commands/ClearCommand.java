package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.Model;

/**
 * Clears the internship catalogue.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Internship Catalogue has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInternshipCatalogue(new InternshipCatalogue());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
