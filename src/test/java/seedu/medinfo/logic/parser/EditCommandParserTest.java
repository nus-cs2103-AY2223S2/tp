package seedu.medinfo.logic.parser;

import static seedu.medinfo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medinfo.logic.commands.CommandTestUtil.DISCHARGE_DESC_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.medinfo.logic.commands.CommandTestUtil.INVALID_STATUS;
import static seedu.medinfo.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.medinfo.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_DISCHARGE_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_DISCHARGE_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_WARD_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_WARD_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.WARD_DESC_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.WARD_DESC_BOB;
import static seedu.medinfo.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.medinfo.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.medinfo.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.medinfo.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.medinfo.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.medinfo.commons.core.index.Index;
import seedu.medinfo.logic.commands.EditCommand;
import seedu.medinfo.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.medinfo.model.patient.Status;
import seedu.medinfo.testutil.EditPatientDescriptorBuilder;

public class EditCommandParserTest {


    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + STATUS_DESC_AMY + WARD_DESC_AMY + DISCHARGE_DESC_AMY;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withStatus(VALID_STATUS_AMY).withWard(VALID_WARD_AMY).withDischarge(VALID_DISCHARGE_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + STATUS_DESC_BOB + WARD_DESC_BOB;

        EditCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withStatus(VALID_STATUS_BOB).withWard(VALID_WARD_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // status
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + STATUS_DESC_AMY;
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withStatus(VALID_STATUS_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // discharge
        userInput = targetIndex.getOneBased() + DISCHARGE_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withDischarge(VALID_DISCHARGE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + STATUS_DESC_AMY + STATUS_DESC_AMY;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withStatus(VALID_STATUS_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_STATUS_DESC;
        EditCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DISCHARGE_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withDischarge(VALID_DISCHARGE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
