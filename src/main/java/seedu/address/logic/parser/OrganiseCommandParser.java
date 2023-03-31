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
import seedu.address.model.meetup.exceptions.InvalidMeetUpIndexException;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.exceptions.InvalidContactIndexException;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.TimePeriod;

/**
 * Parses input arguments and creates a new OrganiseCommand object
 */
public class OrganiseCommandParser implements Parser<OrganiseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the OrganiseCommand
     * and returns a OrganiseCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */

    private static final String MESSAGE_NO_PARTICIPANTS_GIVEN = "Participants should not be empty";
    private static final String MESSAGE_NO_DATE_GIVEN = "Date should not be empty";
    private static final String MESSAGE_NO_LOCATION_GIVEN = "Location should not be empty";
    private static final String MESSAGE_WRONG_TIME_FORMAT = "Time should have start hour and end hour";
    private static final String MESSAGE_WRONG_DATE_FORMAT = "Date should be MONDAY to FRIDAY";
    private static final String MESSAGE_INVALID_RECOMMENDATION_INDEX = "Recommendation index "
            + "should be a positive number";

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
            return parseRecommendation(args);
        }

        //for customised meetings
        Participants participants = parseParticipants(argumentMultimap);
        TimePeriod timePeriod = parseTimePeriod(argumentMultimap);
        Location location = parseLocation(argumentMultimap);
        return new OrganiseCommand(timePeriod, location, participants);
    }

    /**
     * Parses user input for organising recommendation.
     * @param args The user input.
     * @return OrganiseCommand for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    private OrganiseCommand parseRecommendation(String args) throws ParseException {
        try {
            ContactIndex contactIndex = new ContactIndex(Integer.parseInt(args.trim()));
            return new OrganiseCommand(contactIndex);
        } catch (NumberFormatException | InvalidMeetUpIndexException | InvalidContactIndexException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_INVALID_RECOMMENDATION_INDEX));
        }
    }

    /**
     * Parses user input to get meeting participants.
     * @param argumentMultimap Mappings of prefixes to their respective arguments.
     * @return Participants of the meet up.
     * @throws ParseException if the user input does not conform the expected format.
     */
    private Participants parseParticipants(ArgumentMultimap argumentMultimap) throws ParseException {
        List<String> indexArray = Arrays.stream(argumentMultimap.getPreamble().split(" "))
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());

        Set<ContactIndex> indices = ParserUtil.parseIndices(indexArray);
        if (indices.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NO_PARTICIPANTS_GIVEN));
        }

        Participants participants = new Participants(indices);
        return participants;
    }

    /**
     * Parses user input to get meeting day.
     * @param argumentMultimap Mappings of prefixes to their respective arguments.
     * @return Day of the meet up.
     * @throws ParseException if the user input does not conform the expected format.
     */
    private Day parseDay(ArgumentMultimap argumentMultimap) throws ParseException {
        if (!argumentMultimap.getValue(Prefix.DAY).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NO_DATE_GIVEN));
        }
        Day day;
        try {
            day = Day.valueOf(argumentMultimap.getValue(Prefix.DAY).get());
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_WRONG_DATE_FORMAT));
        }
        return day;
    }

    /**
     * Parses user input to get time period of the meeting.
     * @param argumentMultimap Mappings of prefixes to their respective arguments.
     * @return Time period of the meet up.
     * @throws ParseException if the user input does not conform the expected format.
     */
    private TimePeriod parseTimePeriod(ArgumentMultimap argumentMultimap) throws ParseException {
        List<String> time = List.of(argumentMultimap.getValue(Prefix.TIME).get().split(" "));
        LocalTime startHour;
        LocalTime endHour;
        if (time.size() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_WRONG_TIME_FORMAT));
        }

        try {
            startHour = ParserUtil.parseStartHour(Integer.parseInt(time.get(0)));
            endHour = ParserUtil.parseEndHour(Integer.parseInt(time.get(1)));
        } catch (NumberFormatException | IllegalFieldValueException | AssertionError e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_WRONG_TIME_FORMAT));
        }

        Day day = parseDay(argumentMultimap);
        TimePeriod timePeriod = new TimeBlock(startHour, endHour, day);
        return timePeriod;
    }

    /**
     * Parses user input to get meeting location.
     * @param argumentMultimap Mappings of prefixes to their respective arguments.
     * @return Location of the meet up.
     * @throws ParseException if the user input does not conform the expected format.
     */
    private Location parseLocation(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getValue(Prefix.LOCATION).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NO_LOCATION_GIVEN));
        }
        if (argumentMultimap.getValue(Prefix.LOCATION).get().trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_NO_LOCATION_GIVEN));
        }

        Location location = new Location(argumentMultimap.getValue(Prefix.LOCATION).get(),
                1.3, 103.7); //using a dummy location
        return location;
    }
}
