package seedu.address.logic.pilot.linkpilot;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.ParseException;

/**
 * The factory that's responsible for creating a new
 * <code>CommandFactory</code>.
 */
public class LinkPilotCommandFactory implements CommandFactory<LinkPilotCommand> {
    private static final String COMMAND_WORD = "link";
    private static final String PILOT_PREFIX = "/pilot";
    private static final String FLIGHT_PREFIX = "/flight";
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(PILOT_PREFIX, FLIGHT_PREFIX));
    }

    @Override
    public LinkPilotCommand createCommand(CommandParam param) throws ParseException {
        return null;
    }
}
