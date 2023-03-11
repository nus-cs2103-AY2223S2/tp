package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MeetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.location.LocationUtil;
import seedu.address.model.location.Location;

/**
 * Parses input arguments and creates a new MeetCommand object
 */
public class MeetCommandParser implements Parser<MeetCommand> {

    private final Collection<Location> locationHashSet;

    /**
     * Constructs a {@code MeetCommandParser} with the correct data loaded.
     * @param meetupType The type of meetup we are interested in.
     */
    public MeetCommandParser(MeetupType meetupType) {
        switch (meetupType) {
        case EAT:
            locationHashSet = LocationUtil.EAT_LOCATIONS;
            break;
        case STUDY:
            locationHashSet = LocationUtil.STUDY_LOCATIONS;
            break;
        case MEET:
            locationHashSet = LocationUtil.MEET_LOCATIONS;
            break;
        default:
            locationHashSet = new HashSet<>();
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the MeetCommand
     * and returns a MeetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MeetCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args);
        Set<Index> indices = ParserUtil.parseIndices(Arrays.asList(argumentMultimap.getPreamble().split(" ")));
        return new MeetCommand(indices, locationHashSet);
    }

    /**
     * The types of meetups possible.
     * EAT: Places to eat.
     * STUDY: Places to study.
     * MEET: Places to either eat or study (combined).
     */
    public enum MeetupType {
        EAT, STUDY, MEET
    }
}
