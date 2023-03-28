package seedu.address.logic.pilot.linklocation;

import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.link.exceptions.LinkException;
import seedu.address.model.location.Location;
import seedu.address.model.location.PilotLocationType;
import seedu.address.model.pilot.Pilot;

/**
 * The command that links a pilot to a flight
 */
public class LinkPilotToLocationCommand implements Command {
    private static final String DISPLAY_MESSAGE =
            "Linked pilot [%s] to location [%s].";

    /**
     * The id of the location
     */
    private final Location location;

    /**
     * The id of the pilot
     */
    private final Map<PilotLocationType, Pilot> pilot;

    /**
     * Creates a new link command.
     *
     * @param location the id of the location.
     * @param pilot the id of the pilot.
     */
    public LinkPilotToLocationCommand(Location location, Map<PilotLocationType, Pilot> pilot) {
        this.location = location;
        this.pilot = pilot;
    }

    @Override
    public String toString() {
        String result = pilot.entrySet()
                .stream()
                .map((entry) -> String.format(
                        "%s: %s",
                        entry.getKey(),
                        entry.getValue().getName()))
                .collect(Collectors.joining(","));
        return String.format(DISPLAY_MESSAGE, result, location.getName());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            for (Map.Entry<PilotLocationType, Pilot> entry : pilot.entrySet()) {
                location.getPilotLink().putRevolve(entry.getKey(), entry.getValue());
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(this.toString());
    }
}
