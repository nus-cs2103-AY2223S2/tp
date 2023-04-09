package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;

import javafx.collections.ObservableList;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventByInternship;
import seedu.internship.model.internship.Internship;


/**
 * Adds an internship to the internship catalogue.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an internship to the internship catalogue.\n"
            + "Parameters: "
            + PREFIX_POSITION + "POSITION "
            + PREFIX_COMPANY + "COMPANY "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_POSITION + "Software Engineer "
            + PREFIX_COMPANY + "Grab "
            + PREFIX_STATUS + "1 "
            + PREFIX_DESCRIPTION + "Need SQL and Django "
            + PREFIX_TAG + "important "
            + PREFIX_TAG + "django ";

    public static final String MESSAGE_SUCCESS = "New internship added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in the catalogue";

    private final Internship toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Internship}
     */
    public AddCommand(Internship internship) {
        requireNonNull(internship);
        toAdd = internship;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInternship(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.addInternship(toAdd);
        model.updateSelectedInternship(toAdd);

        model.updateFilteredEventList(new EventByInternship(model.getSelectedInternship()));
        ObservableList<Event> events = model.getFilteredEventList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), ResultType.SHOW_INFO, toAdd, events);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
