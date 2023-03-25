package seedu.address.logic.toplevel.syntax;

import java.util.Set;

import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.flight.Flight;

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
}
