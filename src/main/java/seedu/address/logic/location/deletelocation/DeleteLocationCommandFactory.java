package seedu.address.logic.location.deletelocation;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;

/**
 * The factory that creates a {@code DeleteLocationCommand}.
 */
public class DeleteLocationCommandFactory implements CommandFactory<DeleteLocationCommand> {
    public static final String COMMAND_WORD = "delete";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.empty();
    }

    @Override
    public DeleteLocationCommand createCommand(CommandParam param) throws ParseException {
        final String id = param.getUnnamedValueOrThrow();
        return new DeleteLocationCommand(id);
    }
}
