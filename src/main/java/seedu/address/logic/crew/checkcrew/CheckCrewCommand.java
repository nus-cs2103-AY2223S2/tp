package seedu.address.logic.crew.checkcrew;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.crew.Crew;

/**
 * The command that checks a crew's availability in the Wingman app.
 */
public class CheckCrewCommand implements Command {
    /**
     * The UUID of the crew whose availability is to be checked.
     */
    private final String id;

    /**
     * Creates a command that, when executed, checks the availability of the crew with the given UUID.
     *
     * @param id the UUID of the crew to be checked.
     */
    public CheckCrewCommand(String id) {
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        int index = Integer.parseInt(id);
        if (index >= model.getCrewManager().size() || index < 0) {
            throw new CommandException(String.format(
                    "Index %s is out of bounds."
                            + "Please enter a valid index.",
                    index
            ));
        } else {
            boolean isAvailable = model.checkCrewByIndex(index);
            Crew crew = model.getCrewManager().getItem(index);

            if (isAvailable) {
                return new CommandResult(String.format(
                        "%s is available.",
                        crew.toString()
                ));
            } else {
                return new CommandResult(String.format(
                        "%s is unavailable.",
                        crew.toString()
                ));
            }
        }
    }
}
