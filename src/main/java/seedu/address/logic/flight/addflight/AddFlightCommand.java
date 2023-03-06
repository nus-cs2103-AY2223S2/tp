package seedu.address.logic.flight.addflight;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flight.Flight;

/**
 * The command that adds a flight to Wingman
 */
public class AddFlightCommand implements Command {
    /**
     * The flight to be added
     */
    private final Flight flight;

    /**
     * Creates a command that, when executed, adds the flight to Wingman
     *
     * @param flight The flight to be added
     */
    public AddFlightCommand(Flight flight) {
        this.flight = flight;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.addFlight(flight);
        // TODO: better message
        return new CommandResult("Added flight: " + flight);
    }
}
