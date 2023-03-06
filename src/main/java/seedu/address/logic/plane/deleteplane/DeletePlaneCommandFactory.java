package seedu.address.logic.plane.deleteplane;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;

/**
 * The factory that's responsible for creating a {@code DeletePlaneCommand}.
 */
public class DeletePlaneCommandFactory implements CommandFactory<DeletePlaneCommand> {
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
    public DeletePlaneCommand createCommand(CommandParam param) throws ParseException {
        final String id = param.getUnnamedValueOrThrow();
        return new DeletePlaneCommand(id);
    }
}
