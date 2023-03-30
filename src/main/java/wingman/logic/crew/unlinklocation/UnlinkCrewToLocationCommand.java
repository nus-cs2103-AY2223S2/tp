package wingman.logic.crew.unlinklocation;

import java.util.Map;
import java.util.stream.Collectors;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.crew.Crew;
import wingman.model.link.exceptions.LinkException;
import wingman.model.location.CrewLocationType;
import wingman.model.location.Location;

/**
 * The command class that unlinks crews to
 * locations, where they reside.
 */
public class UnlinkCrewToLocationCommand implements Command {
    private static final String LOCATION_NOT_FOUND_EXCEPTION =
            "Location with ID %s can't be found.";
    private static final String CREW_NOT_FOUND_EXCEPTION =
            "Crew with ID %s can't be found.";
    private static final String DISPLAY_MESSAGE =
            "Unlinked %s from %s.";

    /**
     * The location to be linked to.
     */
    private final Location location;

    /**
     * The id of the crews
     */
    private final Map<CrewLocationType, Crew> crews;

    /**
     * Creates a new link command.
     *
     * @param crews the id of the crews.
     * @param location the id of the location.
     */
    public UnlinkCrewToLocationCommand(Location location, Map<CrewLocationType, Crew> crews) {
        this.location = location;
        this.crews = crews;
    }

    @Override
    public String toString() {
        String result = crews.entrySet()
                .stream()
                .map((entry) -> String.format(
                        "%s",
                        entry.getValue().toString()))
                .collect(Collectors.joining(","));
        return String.format(DISPLAY_MESSAGE, result, location.getName());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            for (Map.Entry<CrewLocationType, Crew> entry : crews.entrySet()) {
                location.getCrewLink().delete(entry.getKey(), entry.getValue());
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(this.toString());
    }
}
