package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPatients.ZAYDEN;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.model.person.patient.PatientContainsKeywordsPredicate;
import seedu.address.model.person.patient.PatientFilter;

public class FindPatientCommandParserTest {

    private FindPatientCommandParser parser = new FindPatientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {

        //Empty String
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));

        //Whitespace
        assertParseFailure(parser, " \t\r\n",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));

        //Missing Prefix
        assertParseFailure(parser, " bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));

        //Missing Values
        assertParseFailure(parser, " n/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " p/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " e/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " h/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " w/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " d/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " st/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " r/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " t/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n/ p/ e/ h/ w/ d/ st/ r/ t/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));

        //Invalid Prefix Sequence
        assertParseFailure(parser, "n /bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n /bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_validArgs_returnsFindPatientCommand() {

        // Exact Strings
        PatientFilter expectedPatientFilter = new PatientFilter(ZAYDEN.getName().fullName,
                ZAYDEN.getPhone().value,
                ZAYDEN.getEmail().value,
                ZAYDEN.getHeight().value,
                ZAYDEN.getWeight().value,
                ZAYDEN.getDiagnosis().diagnosis,
                ZAYDEN.getStatus().status,
                ZAYDEN.getRemark().remark,
                new HashSet<>());

        String userInput = " n/" + ZAYDEN.getName().fullName
                + " p/" + ZAYDEN.getPhone().value
                + " e/" + ZAYDEN.getEmail().value
                + " h/" + ZAYDEN.getHeight().value
                + " w/" + ZAYDEN.getWeight().value
                + " d/" + ZAYDEN.getDiagnosis().diagnosis
                + " st/" + ZAYDEN.getStatus().status
                + " r/" + ZAYDEN.getRemark().remark;

        FindPatientCommand expectedFindPatientCommand =
                new FindPatientCommand(new PatientContainsKeywordsPredicate(expectedPatientFilter));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, userInput, expectedFindPatientCommand);
    }
}
