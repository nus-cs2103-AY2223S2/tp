package wingman.logic.toplevel.syntax;

import java.util.Set;
import java.util.stream.Stream;

import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.crew.Crew;
import wingman.model.crew.CrewRank;
import wingman.model.item.exceptions.DuplicateItemException;

/**
 * The class that stores the syntax for a crew.
 */
public abstract class CrewSyntax extends ModelSyntax {
    /**
     * The prefix for name.
     */
    public static final String PREFIX_NAME = "/n";

    /**
     * The prefix for rank.
     */
    public static final String PREFIX_RANK = "/r";

    /**
     * The prefixes of a crew.
     */
    public static final Set<String> PREFIXES = Set.of(
            PREFIX_NAME,
            PREFIX_RANK
    );

    private static final String INVALID_CREW_RANK_MESSAGE =
            "%s is an invalid crew rank.\n"
                    + "Please try 0 for a Cabin Service Director, "
                    + "1 for a Senior Flight Attendant,\n"
                    + "2 for a Flight Attendant, "
                    + "or 3 for a Trainee.";

    /**
     * The factory that creates a crew.
     *
     * @param param the parameter.
     * @return a new crew.
     * @throws ParseException if the param does not meet the expectations.
     */
    public static Crew factory(CommandParam param) throws ParseException {
        final String name = param.getNamedValuesOrThrow(PREFIX_NAME);
        requireAllAlphanumericOrSpace(name);

        final int rankId = param.getNamedIntOrThrow(PREFIX_RANK);

        if (!(Stream.of(0, 1, 2, 3)
                .anyMatch(validRank -> validRank.equals(rankId)))) {
            throw new ParseException(String.format(
                    INVALID_CREW_RANK_MESSAGE,
                    rankId
            ));
        }

        final CrewRank rank = CrewRank.fromIndex(rankId);
        return new Crew(name, rank);
    }

    /**
     * Adds the given crew to the model.
     *
     * @param model the model to which the crew shall be added.
     * @param crew  the crew that which will be added to the model.
     */
    public static void add(Model model, Crew crew) throws DuplicateItemException {
        model.addCrew(crew);
    }

    /**
     * Gets the manager for the crew.
     *
     * @param model the model.
     * @return the manager for the crew.
     */
    public static ReadOnlyItemManager<Crew> getManager(Model model) {
        return model.getCrewManager();
    }

    /**
     * Deletes the crew from the model.
     *
     * @param model the model.
     * @param crew  the crew to be deleted from the model.
     */
    public static void delete(Model model, Crew crew) {
        model.deleteCrew(crew);
    }
}
