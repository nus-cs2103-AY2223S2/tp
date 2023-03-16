package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.MeetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.location.Location;
import seedu.address.model.location.LocationUtil;
import seedu.address.model.person.ContactIndex;

/**
 * Parses input arguments and creates a new MeetCommand object
 */
public class MeetCommandParser implements Parser<MeetCommand> {

    private final Collection<Location> locationHashSet;

    /**
     * Constructs a {@code MeetCommandParser} with the correct data loaded.
     * @param meetType The type of meetup we are interested in.
     */
    public MeetCommandParser(MeetType meetType) {
        switch (meetType) {
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
        List<String> indexArray = Arrays.stream(argumentMultimap.getPreamble().split(" "))
                .filter(x -> !x.isEmpty())
                .collect(Collectors.toList());

        Set<ContactIndex> indices =
                ParserUtil.parseIndices(indexArray);
        return new MeetCommand(indices, locationHashSet);
    }
}
