package wingman.logic.pilot.addpilot;

import java.util.Optional;
import java.util.Set;

import wingman.logic.core.CommandFactory;
import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.pilot.Gender;
import wingman.model.pilot.Pilot;
import wingman.model.pilot.PilotRank;

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
        final String name = param.getNamedValuesOrThrow(PREFIX_NAME);
        final int rankId = param.getNamedIntOrThrow(PREFIX_RANK);
        final PilotRank rank = PilotRank.fromIndex(rankId);
        final int age = param.getNamedIntOrThrow(PREFIX_AGE);
        final int genderIdx = param.getNamedIntOrThrow(PREFIX_GENDER);
        final Gender gender = Gender.fromIndex(genderIdx);
        final int flightHour = param.getNamedIntOrThrow(PREFIX_FLIGHT_HOUR);

        final Pilot pilot = new Pilot(name, age, gender, rank, flightHour);

        return new AddPilotCommand(pilot);
    }
}
