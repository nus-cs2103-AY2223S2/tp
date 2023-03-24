package seedu.address.logic.plane.unlinklocation;

import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandResult;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.link.exceptions.LinkException;
import seedu.address.model.location.Location;
import seedu.address.model.location.PlaneLocationType;
import seedu.address.model.plane.Plane;

/**
 * The command class that unlinks plane to
 * locations, where they reside.
 */
public class UnlinkPlaneToLocationCommand implements Command {
    private static final String PILOT_NOT_FOUND_EXCEPTION =
            "plane with id [%s] is not found.";
    private static final String LOCATION_NOT_FOUND_EXCEPTION =
            "Location with id [%s] is not found.";
    private static final String DISPLAY_MESSAGE =
            "Unlinked plane [%s] from location [%s].";

    /**
     * The id of the plane
     */
    private final Map<PlaneLocationType, Plane> plane;

    /**
     * The id of the location
     */
    private final Location location;

    /**
     * Creates a new link command.
     *
     * @param plane the id of the plane.
     * @param location the id of the location.
     */
    public UnlinkPlaneToLocationCommand(Map<PlaneLocationType, Plane> plane, Location location) {
        this.plane = plane;
        this.location = location;
    }

    @Override
    public String toString() {
        String result = plane.entrySet()
                .stream()
                .map((entry) -> String.format(
                        "%s: %s",
                        entry.getKey(),
                        entry.getValue().getModel()))
                .collect(Collectors.joining(","));
        return String.format(DISPLAY_MESSAGE, result, location.getName());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            for (Map.Entry<PlaneLocationType, Plane> entry : plane.entrySet()) {
                location.planeLink.delete(entry.getKey(), entry.getValue());
            }
        } catch (LinkException e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(this.toString());
    }
}
