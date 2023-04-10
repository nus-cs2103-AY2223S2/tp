package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MeetCommand;
import seedu.address.model.location.util.LocationDataUtil;
import seedu.address.model.person.ContactIndex;

public class MeetCommandParserTest {

    private static final MeetCommandParser MEET_COMMAND_PARSER =
            new MeetCommandParser(MeetType.MEET);
    private static final MeetCommandParser EAT_COMMAND_PARSER =
            new MeetCommandParser(MeetType.EAT);
    private static final MeetCommandParser STUDY_COMMAND_PARSER =
            new MeetCommandParser(MeetType.STUDY);
    private static final List<MeetCommandParser> COMMAND_PARSER_LIST =
            List.of(MEET_COMMAND_PARSER, EAT_COMMAND_PARSER, STUDY_COMMAND_PARSER);

    @Test
    public void parse_invalidIndex_failure() {
        // negative
        COMMAND_PARSER_LIST.forEach(p -> assertParseFailure(p, "-1", MESSAGE_INVALID_INDEX));
        COMMAND_PARSER_LIST.forEach(p -> assertParseFailure(p, "2 -1", MESSAGE_INVALID_INDEX));
        COMMAND_PARSER_LIST.forEach(p -> assertParseFailure(p, "a 2", MESSAGE_INVALID_INDEX));
    }

    @Test
    public void parse_validIndex_success() {
        // standard
        assertParseSuccess(MEET_COMMAND_PARSER, "1",
                new MeetCommand(createContactIndicesWithUser(1), LocationDataUtil.MEET_LOCATIONS));
        assertParseSuccess(EAT_COMMAND_PARSER, "1",
                new MeetCommand(createContactIndicesWithUser(1), LocationDataUtil.EAT_LOCATIONS));
        assertParseSuccess(STUDY_COMMAND_PARSER, "1",
                new MeetCommand(createContactIndicesWithUser(1), LocationDataUtil.STUDY_LOCATIONS));

        assertParseSuccess(MEET_COMMAND_PARSER, "2 7",
                new MeetCommand(createContactIndicesWithUser(2, 7),
                        LocationDataUtil.MEET_LOCATIONS));
        assertParseSuccess(EAT_COMMAND_PARSER, "2 7",
                new MeetCommand(createContactIndicesWithUser(7, 2),
                        LocationDataUtil.EAT_LOCATIONS));
        assertParseSuccess(STUDY_COMMAND_PARSER, "2 7",
                new MeetCommand(createContactIndicesWithUser(2, 7),
                        LocationDataUtil.STUDY_LOCATIONS));

        // untrimmed
        assertParseSuccess(MEET_COMMAND_PARSER, "   1   ",
                new MeetCommand(createContactIndicesWithUser(1), LocationDataUtil.MEET_LOCATIONS));
        assertParseSuccess(EAT_COMMAND_PARSER, "2 7",
                new MeetCommand(createContactIndicesWithUser(7, 2),
                        LocationDataUtil.EAT_LOCATIONS));
        assertParseSuccess(STUDY_COMMAND_PARSER, "   6     ",
                new MeetCommand(createContactIndicesWithUser(6), LocationDataUtil.STUDY_LOCATIONS));
    }

    private Set<ContactIndex> createContactIndicesWithUser(int... indices) {
        Set<ContactIndex> contactIndices = new HashSet<>();
        contactIndices.add(new ContactIndex(0));

        Arrays.stream(indices)
                .mapToObj(ContactIndex::new)
                .forEach(contactIndices::add);

        return contactIndices;
    }
}
