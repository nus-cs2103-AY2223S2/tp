package seedu.address.logic.toplevel.add;

import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.crew.Crew;
import seedu.address.model.flight.Flight;
import seedu.address.model.item.Item;
import seedu.address.model.location.Location;
import seedu.address.model.plane.Plane;

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
