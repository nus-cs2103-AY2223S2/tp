package seedu.address.logic.crew.addcrew;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.crew.Crew;
import seedu.address.model.crew.CrewRank;


/**
 * The factory that creates {@code AddLocationCommand}.
 */
public class AddCrewCommandFactory implements CommandFactory<AddCrewCommand> {
    public static final String COMMAND_WORD = "add";
    public static final String PREFIX_NAME = "/name";
    public static final String PREFIX_RANK = "/rank";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(PREFIX_NAME, PREFIX_RANK));
    }

    @Override
    public AddCrewCommand createCommand(CommandParam param) throws ParseException {
        final String name = param.getNamedValuesOrThrow(PREFIX_NAME);
        final int rankId = param.getNamedIntOrThrow(PREFIX_RANK);
        final CrewRank rank = CrewRank.fromIndex(rankId);
        final Crew crew = new Crew(name, rank);
        return new AddCrewCommand(crew);
    }
}
