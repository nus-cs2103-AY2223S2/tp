package seedu.address.logic.toplevel.syntax;

import java.util.Set;

import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.location.Location;

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
    public static final Set<String> prefixes = Set.of(PREFIX_NAME);

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
    public static void add(Model model, Location location) {
        model.addLocation(location);
    }
}
