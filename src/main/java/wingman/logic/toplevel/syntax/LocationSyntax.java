package wingman.logic.toplevel.syntax;

import java.util.Set;

import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.item.exceptions.DuplicateItemException;
import wingman.model.location.Location;

/**
 * The syntax for location.
 */
public abstract class LocationSyntax {
    /**
     * The prefix for name.
     */
    public static final String PREFIX_NAME = "/n";

    /**
     * The prefixes.
     */
    public static final Set<String> PREFIXES = Set.of(PREFIX_NAME);

    /**
     * Creates a location.
     *
     * @param param the command parameter for location.
     * @return the location.
     * @throws ParseException if the command parameter does not fit the
     *                        requirements.
     */
    public static Location factory(CommandParam param) throws ParseException {
        final String name = param.getNamedValuesOrThrow(PREFIX_NAME);
        return new Location(name);
    }

    /**
     * Adds a location to the model.
     *
     * @param model    the model.
     * @param location the location.
     */
    public static void add(Model model, Location location) throws DuplicateItemException {
        model.addLocation(location);
    }

    /**
     * Gets the manager for location.
     *
     * @param model the model.
     * @return the manager for location.
     */
    public static ReadOnlyItemManager<Location> getManager(Model model) {
        return model.getLocationManager();
    }

    /**
     * Deletes the location from the model.
     *
     * @param model    the model.
     * @param location the location to be deleted from the model.
     */
    public static void delete(Model model, Location location) {
        model.deleteLocation(location);
    }
}
