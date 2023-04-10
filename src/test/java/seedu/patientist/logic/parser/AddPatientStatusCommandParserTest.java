package seedu.patientist.logic.parser;

import static seedu.patientist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.patientist.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.patientist.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.patientist.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.AddPatientStatusCommand;
import seedu.patientist.model.person.patient.PatientStatusDetails;

public class AddPatientStatusCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientStatusCommand.MESSAGE_USAGE);

    private AddPatientStatusCommandParser parser = new AddPatientStatusCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, STATUS_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + STATUS_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + STATUS_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1" + " some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1" + " i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + STATUS_DESC_BOB;

        AddPatientStatusCommand expectedCommand =
                new AddPatientStatusCommand(targetIndex, List.of(new PatientStatusDetails(VALID_STATUS_BOB)));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsAll() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + STATUS_DESC_BOB + STATUS_DESC_AMY;

        AddPatientStatusCommand expectedCommand = new AddPatientStatusCommand(targetIndex,
                List.of(new PatientStatusDetails(VALID_STATUS_BOB), new PatientStatusDetails(VALID_STATUS_AMY)));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
