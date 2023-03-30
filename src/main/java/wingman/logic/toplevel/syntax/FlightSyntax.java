package wingman.logic.toplevel.syntax;

import java.util.Set;

import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.flight.Flight;

/**
 * The syntax for flight.
 */
public abstract class FlightSyntax {
    /**
     * The prefix for code.
     */
    public static final String PREFIX_CODE = "/c";

    /**
     * The prefixes.
     */
    public static final Set<String> PREFIXES = Set.of(PREFIX_CODE);

    /**
     * Creates a flights.
     *
     * @param param the parameter.
     * @return the flight created from param.
     * @throws ParseException if the param does not fit the requirement.
     */
    public static Flight factory(CommandParam param) throws ParseException {
        final String code = param.getNamedValuesOrThrow(PREFIX_CODE);
        return new Flight(code);
    }

    /**
     * Adds a flight to the given model.
     *
     * @param model  the model to which the flight shall be added.
     * @param flight the flight that which will be added to the model.
     */
    public static void add(Model model, Flight flight) {
        model.addFlight(flight);
    }

    /**
     * Gets the flight manager.
     *
     * @param model the model.
     * @return the flight manager.
     */
    public static ReadOnlyItemManager<Flight> getManager(Model model) {
        return model.getFlightManager();
    }

    /**
     * Deletes the flight from the model.
     *
     * @param model  the model from which the flight is deleted.
     * @param flight the flight to be deleted.
     */
    public static void delete(Model model, Flight flight) {
        model.deleteFlight(flight);
    }
}
