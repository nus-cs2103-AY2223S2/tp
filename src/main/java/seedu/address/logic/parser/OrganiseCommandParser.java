package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalTime;

import seedu.address.logic.commands.OrganiseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.location.Location;
import seedu.address.model.meetup.Participants;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.time.Day;

/**
 * Parses input arguments and creates a new OrganiseCommand object
 */
public class OrganiseCommandParser implements Parser<OrganiseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the OrganiseCommand
     * and returns a OrganiseCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */

    private static final String MESSAGE_NO_PARTICIPANTS_GIVEN = "No participants were supplied";
    private static final String MESSAGE_NO_DATE_GIVEN = "No dates were supplied";
    private static final String MESSAGE_NO_LOCATION_GIVEN = "No location was supplied";
    private static final String MESSAGE_NO_TIME_GIVEN = "No time was supplied";
    private static final String MESSAGE_WRONG_TIME_FORMAT = "Time not in correct format";
    private static final String MESSAGE_WRONG_DATE_FORMAT = "Date not in correct format";

    /**
     * Parses the given {@code String} of arguments in the context of the OrganiseCommand
     * and returns a OrganiseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public OrganiseCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, Prefix.DAY, Prefix.TIME, Prefix.LOCATION);

        //for recommendations
        if (!argumentMultimap.getPreamble().isEmpty() && !argumentMultimap.getValue(Prefix.DAY).isPresent()) {
            try {
                ContactIndex contactIndex = new ContactIndex(Integer.parseInt(args.trim()));
                return new OrganiseCommand(contactIndex);
            } catch (NumberFormatException nfe) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }
        }

        //for participants
        List<String> indexArray = Arrays.stream(argumentMultimap.getPreamble()
                .split(" "))
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());
        Set<ContactIndex> indices = ParserUtil.parseIndices(indexArray);
        if (indices.isEmpty()) {
            throw new ParseException(MESSAGE_NO_PARTICIPANTS_GIVEN);
        }
        Participants participants = new Participants(indices);

        //for day
        if (!argumentMultimap.getValue(Prefix.DAY).isPresent()) {
            throw new ParseException(MESSAGE_NO_DATE_GIVEN);
        }
        Day day;
        try {
            day = Day.valueOf(argumentMultimap.getValue(Prefix.DAY).get());
        } catch (IllegalArgumentException e) {
            throw new ParseException(MESSAGE_WRONG_DATE_FORMAT);
        }

        //for time
        if (!argumentMultimap.getValue(Prefix.TIME).isPresent()) {
            throw new ParseException(MESSAGE_NO_TIME_GIVEN);
        }
        List<String> time = List.of(argumentMultimap.getValue(Prefix.TIME).get().split(" "));
        LocalTime startHour;
        LocalTime endHour;
        try {
            startHour = new LocalTime(Integer.parseInt(time.get(0)), 0);
            endHour = new LocalTime(Integer.parseInt(time.get(1)), 0);
        } catch (NumberFormatException | IllegalFieldValueException nfe) {
            throw new ParseException(MESSAGE_WRONG_TIME_FORMAT);
        }

        //for location
        if (argumentMultimap.getValue(Prefix.LOCATION).isEmpty()) {
            throw new ParseException(MESSAGE_NO_LOCATION_GIVEN);
        }
        if (argumentMultimap.getValue(Prefix.LOCATION).get().trim().isEmpty()) {
            throw new ParseException(MESSAGE_NO_LOCATION_GIVEN);
        }

        Location location = new Location(argumentMultimap.getValue(Prefix.LOCATION).get(),
                1.3, 103.7); //using a dummy location

        return new OrganiseCommand(day, startHour, endHour, location, participants);

    }
}
