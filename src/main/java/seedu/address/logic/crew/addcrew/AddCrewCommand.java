package seedu.address.logic.crew.addcrew;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.crew.Crew;

/**
 * The command that adds a crew to Wingman.
 */
public class AddCrewCommand implements Command {
    private final Crew crew;

    /**
     * Creates a executable command that add a crew to the list.
     * @param crew the location to add.
     */
    public AddCrewCommand(Crew crew) {
        this.crew = crew;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.addCrew(crew);
        return new CommandResult("Added Crew: " + crew.getName());
    }

}

