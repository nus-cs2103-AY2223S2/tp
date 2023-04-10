package wingman.logic.toplevel.exit;

import java.util.Optional;
import java.util.Set;

import wingman.logic.core.CommandFactory;
import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;

/**
 * The factory that's responsible for creating a {@code ExitCommand}.
 */
public class ExitCommandFactory implements CommandFactory<ExitCommand> {
    public static final String COMMAND_WORD = "exit";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.empty();
    }

    @Override
    public ExitCommand createCommand(CommandParam param) throws ParseException {
        return new ExitCommand();
    }
}
