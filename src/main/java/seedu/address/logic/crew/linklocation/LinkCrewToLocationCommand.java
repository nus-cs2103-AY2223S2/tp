package seedu.address.logic.crew.linklocation;


import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.crew.Crew;
import seedu.address.model.link.exceptions.LinkException;
import seedu.address.model.location.CrewLocationType;
import seedu.address.model.location.Location;

/**
 * The command that links a crew to a flight
 */
public class LinkCrewToLocationCommand implements Command {
    private static final String DISPLAY_MESSAGE =
            "Linked %s to flight %s.";

    /**
     * The id of the crews
     */
    private final Map<CrewLocationType, Crew> crews;

    /**
     * The id of the location
     */
    private final Location location;

    /**
     * Creates a new link command.
     *
     * @param crews the id of the crews.
     * @param location the id of the location.
     */
    public LinkCrewToLocationCommand(Map<CrewLocationType, Crew> crews, Location location) {
        this.crews = crews;
        this.location = location;
    }

    @Override
    public String toString() {
        String result = crews.entrySet()
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
            for (Map.Entry<CrewLocationType, Crew> entry : crews.entrySet()) {
                location.getCrewLink().putRevolve(entry.getKey(), entry.getValue());
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(this.toString());
    }

}
