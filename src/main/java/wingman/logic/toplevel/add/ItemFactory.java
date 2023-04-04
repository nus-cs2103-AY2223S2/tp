package wingman.logic.toplevel.add;

import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.crew.Crew;
import wingman.model.flight.Flight;
import wingman.model.item.Item;
import wingman.model.location.Location;
import wingman.model.plane.Plane;

/**
 * The interface responsible for creating an item.
 *
 * @param <T> the type of the item.
 */
@FunctionalInterface
public interface ItemFactory<T extends Item> {
    /**
     * Creates an item based on the {@link CommandParam} supplied.
     *
     * @param param the command parameter after parsing
     * @return the item created
     * @throws ParseException if the command parameter does not fit the
     *                        requirement.
     */
    T create(CommandParam param) throws ParseException;

    /**
     * Creates a plane.
     *
     * @param param the command parameter after parsing.
     * @return a new crew
     * @throws ParseException if the parameter does not fit the requirements.
     */
    static Plane planeFactory(CommandParam param) throws ParseException {
        return null;
    }

    /**
     * Creates a crew.
     *
     * @param param the command parameter after parsing.
     * @return a new crew.
     * @throws ParseException if the parameter does not fit the requirements.
     */
    static Crew crewFactory(CommandParam param) throws ParseException {
        return null;
    }

    /**
     * Creates a location.
     *
     * @param param the command parameter after parsing.
     * @return a new location.
     * @throws ParseException if the parameter does not fit the requirements.
     */
    static Location locationFactory(CommandParam param) throws ParseException {
        return null;
    }

    /**
     * Creates a flight.
     *
     * @param param the command parameter after parsing.
     * @return a new flight.
     * @throws ParseException if the parameter does not fit the requirements.
     */
    static Flight flightFactory(CommandParam param) throws ParseException {
        return null;
    }
}
