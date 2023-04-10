package wingman.logic.pilot.checkpilot;

import java.util.Optional;
import java.util.Set;

import wingman.logic.core.CommandFactory;
import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;

/**
 * The factory that's responsible for creating a {@code CheckPilotCommand}.
 */
public class CheckPilotCommandFactory implements CommandFactory<CheckPilotCommand> {
    public static final String COMMAND_WORD = "check";
    public static final String PREFIX_ID = "/id";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(PREFIX_ID));
    }

    @Override
    public CheckPilotCommand createCommand(CommandParam param) throws ParseException {
        String id = param.getNamedValuesOrThrow(PREFIX_ID);

        return new CheckPilotCommand(id);
    }
}
