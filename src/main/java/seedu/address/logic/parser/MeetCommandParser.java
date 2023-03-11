package seedu.address.logic.parser;

import java.util.HashMap;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MeetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.location.LocationUtil;
import seedu.address.model.location.Location;

public class MeetCommandParser implements Parser<MeetCommand> {

    private final HashMap<String, Location> locationHashMap;

    public MeetCommandParser(MeetupType meetupType) {
        switch (meetupType) {
        case EAT:
            locationHashMap = LocationUtil.EAT_LOCATIONS;
            break;
        case STUDY:
            locationHashMap = LocationUtil.STUDY_LOCATIONS;
            break;
        case MEET:
            locationHashMap = LocationUtil.MEET_LOCATIONS;
            break;
        default:
            locationHashMap = new HashMap<>();
        }
    }

    public MeetCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args);
        Set<Index> indices = ParserUtil.parseIndices(argumentMultimap.getAllValues(Prefix.BLANK));
        return new MeetCommand(indices, locationHashMap);
    }

    public enum MeetupType {
        EAT, STUDY, MEET
    }
}
