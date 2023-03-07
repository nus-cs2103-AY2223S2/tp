package seedu.address.logic.flight.deleteflight;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;


/**
 * The factory that's responsible for creating a {@code DeleteFlightCommand}.
 */
public class DeleteFlightCommandFactory implements CommandFactory<DeleteFlightCommand> {
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
    public DeleteFlightCommand createCommand(CommandParam param) throws ParseException {
        final String id = param.getUnnamedValueOrThrow();
        return new DeleteFlightCommand(id);
    }
}
