package seedu.address.logic.toplevel.syntax;

import java.util.Set;

import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.crew.Crew;
import seedu.address.model.crew.CrewRank;

/**
 * The class that stores the syntax for a crew.
 */
public abstract class CrewSyntax {
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

    /**
     * The factory that creates a crew.
     *
     * @param param the parameter.
     * @return a new crew.
     * @throws ParseException if the param does not meet the expectations.
     */
    public static Crew factory(CommandParam param) throws ParseException {
        final String name = param.getNamedValuesOrThrow(PREFIX_NAME);
        final int rankId = param.getNamedIntOrThrow(PREFIX_RANK);
        final CrewRank rank = CrewRank.fromIndex(rankId);
        return new Crew(name, rank);
    }

    /**
     * Adds the given crew to the model.
     *
     * @param model the model to which the crew shall be added.
     * @param crew  the crew that which will be added to the model.
     */
    public static void add(Model model, Crew crew) {
        model.addCrew(crew);
    }
}
