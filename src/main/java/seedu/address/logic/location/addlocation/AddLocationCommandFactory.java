package seedu.address.logic.location.addlocation;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.location.Location;

/**
 * The factory that creates {@code AddLocationCommand}.
 */
public class AddLocationCommandFactory implements CommandFactory<AddLocationCommand> {
    public static final String COMMAND_WORD = "add";
    public static final String PREFIX_NAME = "/name";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(PREFIX_NAME));
    }

    @Override
    public AddLocationCommand createCommand(CommandParam param) throws ParseException {
        final String name = param.getNamedValuesOrThrow(PREFIX_NAME);
        final Location location = new Location(name);
        return new AddLocationCommand(location);
    }
}
