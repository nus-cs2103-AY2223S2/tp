package seedu.address.logic.commands.factories;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.CommandFactory;
import seedu.address.logic.commands.CommandParam;
import seedu.address.logic.commands.commands.AddPilotCommand;
import seedu.address.logic.commands.exceptions.ParseException;

/**
 * The factory that's responsible for creating a {@code AddPilotCommand}.
 */
public class PilotFactory implements CommandFactory<AddPilotCommand> {
    public static final String COMMAND_WORD = "add";
    public static final String PREFIX_NAME = "/name";
    public static final String PREFIX_RANK = "/rank";
    public static final String PREFIX_AGE = "/age";
    public static final String PREFIX_GENDER = "/gender";
    public static final String PREFIX_FLIGHT_HOUR = "/fh";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(PREFIX_NAME, PREFIX_RANK, PREFIX_AGE,
            PREFIX_GENDER, PREFIX_FLIGHT_HOUR));
    }

    @Override
    public AddPilotCommand createCommand(CommandParam param) throws ParseException {
        final String name = param.getValue(PREFIX_NAME)
                                .orElseThrow(() -> new ParseException("Name is required"));
        final String rank = param.getValue(PREFIX_RANK)
                                .orElseThrow(() -> new ParseException("Rank is required"));
        final String ageStr =
            param.getValue(PREFIX_AGE).orElseThrow(() -> new ParseException("Age is required"));
        final int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            throw new ParseException("Age must be a number: " + e.getMessage());
        }

        return null;
    }
}
