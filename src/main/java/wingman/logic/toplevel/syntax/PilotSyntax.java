package wingman.logic.toplevel.syntax;

import java.util.Set;
import java.util.stream.Stream;

import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.item.exceptions.DuplicateItemException;
import wingman.model.pilot.Gender;
import wingman.model.pilot.Pilot;
import wingman.model.pilot.PilotRank;
import wingman.model.pilot.exceptions.InvalidGenderException;

/**
 * The syntax related to a pilot.
 */
public abstract class PilotSyntax extends ModelSyntax {
    /**
     * The type name.
     */
    public static final String TYPE_NAME = "pilot";

    /**
     * The prefix for getting the name of the pilot.
     */
    public static final String PREFIX_NAME = "/n";

    /**
     * The prefix for getting the rank of the pilot.
     */
    public static final String PREFIX_RANK = "/r";

    /**
     * The prefix for getting the age of the pilot.
     */
    public static final String PREFIX_AGE = "/a";

    /**
     * The prefix for getting the gender of the pilot.
     */
    public static final String PREFIX_GENDER = "/g";

    /**
     * The prefix for getting the flight hour of the pilot.
     */
    public static final String PREFIX_FLIGHT_HOUR = "/fh";

    private static final String INVALID_PILOT_RANK_MESSAGE =
            "%s is an invalid pilot rank.\n"
                    + "Please try 0 for a Training Captain, "
                    + "1 for a Captain, "
                    + "2 for a Senior First Officer,\n"
                    + "3 for a First Officer, "
                    + "4 for a Second Officer, "
                    + "or 5 for a Cadet.";

    private static final String INVALID_GENDER_MESSAGE =
            "Input gender %s is invalid. \n"
                    + "Please try 0 for male, 1 for female, or 2 for others.";

    /**
     * The set of all prefixes of a pilot.
     */
    public static final Set<String> PREFIXES = Set.of(
            PREFIX_NAME,
            PREFIX_AGE,
            PREFIX_RANK,
            PREFIX_GENDER,
            PREFIX_FLIGHT_HOUR
    );

    /**
     * Parses the gender from a number.
     *
     * @param genderIdx User-input number indicating gender
     * @return the Gender value
     * @throws ParseException when the input number is not a valid gender type
     */
    private static Gender parseGenderFromNumber(int genderIdx) throws ParseException {
        final Gender gender;
        try {
            gender = Gender.fromIndex(genderIdx);
        } catch (InvalidGenderException e) {
            throw new ParseException(
                    String.format(
                            INVALID_GENDER_MESSAGE,
                            genderIdx
                    )
            );
        }
        return gender;
    }

    /**
     * Parses the rank from a number.
     *
     * @param rankIdx User-input number indicating gender
     * @return the rank value
     * @throws ParseException when the input number is not a valid gender type
     */
    private static PilotRank parseRankFromNumber(int rankIdx) throws ParseException {
        if (!(Stream.of(0, 1, 2, 3, 4, 5)
                .anyMatch(validRank -> validRank.equals(rankIdx)))) {
            throw new ParseException(String.format(
                    INVALID_PILOT_RANK_MESSAGE,
                    rankIdx
            ));
        }

        return PilotRank.fromIndex(rankIdx);
    }

    /**
     * Creates a pilot.
     *
     * @param param the command parameter after parsing.
     * @return a new pilot
     * @throws ParseException if the parameter does not fit the requirements, or
     *                          the gender is invalid
     */
    public static Pilot factory(CommandParam param) throws ParseException {
        final String name = param.getNamedValuesOrThrow(PREFIX_NAME);
        final int rankId = param.getNamedIntOrThrow(PREFIX_RANK);
        final PilotRank rank = parseRankFromNumber(rankId);
        final int age = param.getNamedIntOrThrow(PREFIX_AGE);
        final int genderId = param.getNamedIntOrThrow(PREFIX_GENDER);
        final Gender gender = parseGenderFromNumber(genderId);
        final int flightHour = param.getNamedIntOrThrow(PREFIX_FLIGHT_HOUR);

        requireAllNonNegative(age, flightHour);
        requireAllAlphanumericOrSpace(name);

        return new Pilot(name, age, gender, rank, flightHour);
    }

    /**
     * Adds the given pilot to the model.
     *
     * @param model the model to which the pilot is added.
     * @param pilot the pilot that which is added to the model.
     */
    public static void add(Model model, Pilot pilot) throws DuplicateItemException {
        model.addPilot(pilot);
    }

    /**
     * Gets the manager for pilot.
     *
     * @param model the model.
     * @return the manager for pilot.
     */
    public static ReadOnlyItemManager<Pilot> getManager(Model model) {
        return model.getPilotManager();
    }

    /**
     * Deletes the pilot from the model.
     *
     * @param model the model.
     * @param pilot the pilot.
     */
    public static void delete(Model model, Pilot pilot) {
        model.deletePilot(pilot);
    }
}
