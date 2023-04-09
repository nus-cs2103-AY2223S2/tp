package wingman.logic.plane.linklocation;

import java.util.Map;
import java.util.stream.Collectors;

import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.model.Model;
import wingman.model.link.exceptions.LinkException;
import wingman.model.location.Location;
import wingman.model.location.PlaneLocationType;
import wingman.model.plane.Plane;

/**
 * The command class that unlinks plane to
 * locations, where they reside.
 */
public class UnlinkPlaneToLocationCommand implements Command {
    private static final String DISPLAY_MESSAGE =
            "Unlinked %s from %s.";

    /**
     * The id of the location
     */
    private final Location location;

    /**
     * The id of the plane
     */
    private final Map<PlaneLocationType, Plane> plane;

    /**
     * Creates a new link command.
     *
     * @param location the id of the location.
     * @param plane    the id of the plane.
     */
    public UnlinkPlaneToLocationCommand(
            Location location,
            Map<PlaneLocationType, Plane> plane
    ) {
        this.location = location;
        this.plane = plane;
    }

    @Override
    public String toString() {
        String result = plane.values()
                             .stream()
                             .map(v -> String.format(
                                     "%s",
                                     v.toString()
                             ))
                             .collect(Collectors.joining(","));
        return String.format(DISPLAY_MESSAGE, result, location.getName());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            for (Map.Entry<PlaneLocationType, Plane> entry : plane.entrySet()) {
                location
                        .getPlaneLink()
                        .delete(entry.getKey(), entry.getValue());
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(this.toString());
    }
}
