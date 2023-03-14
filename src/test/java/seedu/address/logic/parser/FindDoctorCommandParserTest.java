package seedu.address.logic.parser;



import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalDoctors.ALICE;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindDoctorCommand;
import seedu.address.model.person.DoctorContainsKeywordsPredicate;
import seedu.address.model.person.DoctorFilter;

public class FindDoctorCommandParserTest {

    private FindDoctorCommandParser parser = new FindDoctorCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {

        //Empty String
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));

        //Whitespace
        assertParseFailure(parser, " \t\r\n",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));

        //Missing Prefix
        assertParseFailure(parser, " bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));

        //Missing Values
        assertParseFailure(parser, " n/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " p/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " e/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " s/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " y/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " t/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n/ p/ e/ s/ y/ t/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));

        //Invalid Prefix Sequence
        assertParseFailure(parser, "n /bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n /bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));

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
