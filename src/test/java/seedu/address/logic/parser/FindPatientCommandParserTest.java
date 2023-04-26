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

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindPatientCommand.getCommandUsage());

    private FindPatientCommandParser parser = new FindPatientCommandParser();

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
        assertParseFailure(parser, " h/",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " w/",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " d/",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " st/",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " r/",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " t/",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " n/ p/ e/ h/ w/ d/ st/ r/ t/",
                MESSAGE_INVALID_FORMAT);

        //Invalid Prefix Sequence
        assertParseFailure(parser, "n /bob",
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " n /bob",
                MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_validArgs_returnsFindPatientCommand() {

        // Exact Strings
        PatientFilter expectedPatientFilter = new PatientFilter(ZAYDEN.getName().getValue(),
                ZAYDEN.getPhone().getValue(),
                ZAYDEN.getEmail().getValue(),
                ZAYDEN.getHeight().getValue(),
                ZAYDEN.getWeight().getValue(),
                ZAYDEN.getDiagnosis().getValue(),
                ZAYDEN.getStatus().getValue(),
                ZAYDEN.getRemark().getValue(),
                new HashSet<>());

        String userInput = " n/" + ZAYDEN.getName().getValue()
                + " p/" + ZAYDEN.getPhone().getValue()
                + " e/" + ZAYDEN.getEmail().getValue()
                + " h/" + ZAYDEN.getHeight().getValue()
                + " w/" + ZAYDEN.getWeight().getValue()
                + " d/" + ZAYDEN.getDiagnosis().getValue()
                + " st/" + ZAYDEN.getStatus().getValue()
                + " r/" + ZAYDEN.getRemark().getValue();

        FindPatientCommand expectedFindPatientCommand =
                new FindPatientCommand(new PatientContainsKeywordsPredicate(expectedPatientFilter));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, userInput, expectedFindPatientCommand);
    }
}
