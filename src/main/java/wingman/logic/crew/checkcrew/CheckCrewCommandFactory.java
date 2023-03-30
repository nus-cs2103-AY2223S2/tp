package wingman.logic.crew.checkcrew;

import java.util.Optional;
import java.util.Set;

import wingman.logic.core.CommandFactory;
import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;

/**
 * The factory that's responsible for creating a {@code CheckCrewCommand}.
 */
public class CheckCrewCommandFactory implements CommandFactory<CheckCrewCommand> {
    public static final String COMMAND_WORD = "check";
    public static final String PREFIX_ID = "/id";

    private static final String NO_CREW_MESSAGE =
            "No crew has been entered. "
                    + "Please enter /id followed by the crew ID.";
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(PREFIX_ID));
    }

    @Override
    public CheckCrewCommand createCommand(CommandParam param) throws ParseException, CommandException {
        String index = param.getNamedValuesOrThrow(PREFIX_ID);

        return new CheckCrewCommand(index);
    }
}
