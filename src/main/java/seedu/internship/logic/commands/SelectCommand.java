package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.internship.commons.core.Messages;
import seedu.internship.commons.core.index.Index;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.model.Model;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventByInternship;
import seedu.internship.model.internship.Internship;

/**
 * View the details selected internship in the internship catalogue.
 */
public class SelectCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the internship identified by the index number used in the displayed internship list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_INTERNSHIP_SUCCESS = "Selecting Internship %1$s at %2$s";

    private final Index targetIndex;

    public SelectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToView = lastShownList.get(targetIndex.getZeroBased());
        model.updateSelectedInternship(internshipToView);


        model.updateFilteredEventList(new EventByInternship(model.getSelectedInternship()));
        ObservableList<Event> events = model.getFilteredEventList();

        return new CommandResult(String.format(MESSAGE_VIEW_INTERNSHIP_SUCCESS, internshipToView.getPosition(),
                internshipToView.getCompany()), ResultType.SHOW_INFO, internshipToView, events);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectCommand // instanceof handles nulls
                && targetIndex.equals(((SelectCommand) other).targetIndex)); // state check
    }
}
