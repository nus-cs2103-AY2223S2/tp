package seedu.address.logic.parser;



import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalDoctors.ALICE;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindDoctorCommand;
import seedu.address.model.person.doctor.DoctorContainsKeywordsPredicate;
import seedu.address.model.person.doctor.DoctorFilter;

public class FindDoctorCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindDoctorCommand.MESSAGE_USAGE);

    private FindDoctorCommandParser parser = new FindDoctorCommandParser();


    @Test
    public void parse_emptyArg_throwsParseException() {

        //Empty String
        assertParseFailure(parser, "",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " ",
                MESSAGE_INVALID_FORMAT);

        //Whitespace
        assertParseFailure(parser, " \t\r\n",
                MESSAGE_INVALID_FORMAT);

        //Missing Prefix
        assertParseFailure(parser, " bob",
                MESSAGE_INVALID_FORMAT);

        //Missing Values
        assertParseFailure(parser, " n/",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " p/",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " e/",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " s/",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " y/",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " t/",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " n/ p/ e/ s/ y/ t/",
                MESSAGE_INVALID_FORMAT);

        //Invalid Prefix Sequence
        assertParseFailure(parser, "n /bob",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " n /bob",
                MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_validArgs_returnsFindDoctorCommand() {

        // Exact Strings
        DoctorFilter expectedDoctorFilter = new DoctorFilter(ALICE.getName().fullName,
                ALICE.getPhone().value,
                ALICE.getEmail().value,
                ALICE.getSpecialty().specialty,
                ALICE.getYoe().value,
                new HashSet<>());

        String userInput = " n/" + ALICE.getName().fullName
                + " p/" + ALICE.getPhone().value
                + " e/" + ALICE.getEmail().value
                + " s/" + ALICE.getSpecialty().specialty
                + " y/" + ALICE.getYoe().value;

        FindDoctorCommand expectedFindDoctorCommand =
                new FindDoctorCommand(new DoctorContainsKeywordsPredicate(expectedDoctorFilter));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, userInput, expectedFindDoctorCommand);
    }
}
