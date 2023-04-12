package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PersonLivesInRegionPredicate;
import seedu.address.model.person.Region.Regions;

/**
 * Lists all persons residing in a region
 */
public class ListRegionCommand extends Command {
    public static final String COMMAND_WORD = "listRegion";
    public static final String MESSAGE_SUCCESS = "Listed all persons living in given region";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all persons living in given region.\n"
            + "Available regions are North, South, East, West, Central, Unknown\n"
            + "Parameters: REGION\n"
            + "Example: " + COMMAND_WORD + " north";

    private final Regions targetRegion;

    public ListRegionCommand(Regions targetRegion) {
        this.targetRegion = targetRegion;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(new PersonLivesInRegionPredicate(targetRegion));
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    /**
     * Two commands are considered equal (or equivalent)
     * if executing both yields the exact same results for all model states <p>
     * For the list region command, having the same target region will guarantee the same results.
     */
    @Override
    public boolean equals(Object other) {
        // Safe typecast. Shortcircuit will disallow type mismatch at runtime
        return other == this
                || (other instanceof ListRegionCommand
                && targetRegion.equals(((ListRegionCommand) other).targetRegion));
    }
}
