package wingman.logic.toplevel.changemode;

import java.util.Optional;
import java.util.Set;

import wingman.logic.core.CommandFactory;
import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.OperationMode;

/**
 * The factory that's responsible for creating a {@code ChangeModeCommand}.
 */
public class ChangeModeCommandFactory implements CommandFactory<ChangeModeCommand> {
    public static final String COMMAND_WORD = "mode";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.empty();
    }

    @Override
    public ChangeModeCommand createCommand(CommandParam param) throws ParseException {
        final String modeStr = param.getUnnamedValueOrThrow();
        final OperationMode mode;

        try {
            mode = OperationMode.fromString(modeStr);
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(
                    "%s is an invalid mode.\n"
                            + "Please try crew, flight, location, pilot, or plane.",
                    modeStr));
        }
        return new ChangeModeCommand(mode);
    }
}
