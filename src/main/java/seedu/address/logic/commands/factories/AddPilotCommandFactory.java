package seedu.address.logic.commands.factories;

import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.CommandFactory;
import seedu.address.logic.commands.CommandParam;
import seedu.address.logic.commands.commands.AddPilotCommand;
import seedu.address.logic.commands.exceptions.ParseException;
import seedu.address.model.pilot.Gender;
import seedu.address.model.pilot.Pilot;
import seedu.address.model.pilot.PilotRank;

/**
 * The factory that's responsible for creating a {@code AddPilotCommand}.
 */
public class AddPilotCommandFactory implements CommandFactory<AddPilotCommand> {
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
        final String name = param.getValueOrThrow(PREFIX_NAME);
        final int rankId = param.getIntOrThrow(PREFIX_RANK);
        final PilotRank rank = PilotRank.fromIndex(rankId);
        final int age = param.getIntOrThrow(PREFIX_AGE);
        final int genderIdx = param.getIntOrThrow(PREFIX_GENDER);
        final Gender gender = Gender.fromIndex(genderIdx);
        final int flightHour = param.getIntOrThrow(PREFIX_FLIGHT_HOUR);

        final Pilot pilot = new Pilot(name, age, gender, rank, flightHour);

        return new AddPilotCommand(pilot);
    }
}
