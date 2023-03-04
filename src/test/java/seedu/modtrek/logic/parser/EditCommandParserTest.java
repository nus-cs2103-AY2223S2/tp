package seedu.modtrek.logic.parser;

import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.commands.CommandTestUtil.GRADE_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.GRADE_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.SEMYEAR_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.SEMYEAR_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_SEMYEAR_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_CODE_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_CREDIT_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.CODE_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.CREDIT_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.CREDIT_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.TAG_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.TAG_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_GRADE_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_GRADE_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_SEMYEAR_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_SEMYEAR_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CODE_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CREDIT_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CREDIT_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_CS1101S;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.modtrek.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.modtrek.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.modtrek.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.modtrek.commons.core.index.Index;
import seedu.modtrek.logic.commands.EditCommand;
import seedu.modtrek.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.testutil.EditModuleDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_CODE_CS1101S, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CODE_DESC_CS1101S, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + CODE_DESC_CS1101S, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_CODE_DESC, Code.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_CREDIT_DESC, Credit.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_SEMYEAR_DESC, SemYear.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_GRADE_DESC, Grade.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_CREDIT_DESC + SEMYEAR_DESC_CS1101S, Credit.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + CREDIT_DESC_MA2002 + INVALID_CREDIT_DESC, Credit.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Module} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_MA2002 + TAG_DESC_CS1101S + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_MA2002 + TAG_EMPTY + TAG_DESC_CS1101S, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_MA2002 + TAG_DESC_CS1101S, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_CODE_DESC + INVALID_SEMYEAR_DESC + VALID_GRADE_CS1101S + VALID_CREDIT_CS1101S,
                Code.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + CREDIT_DESC_MA2002 + TAG_DESC_CS1101S
                + SEMYEAR_DESC_CS1101S + GRADE_DESC_CS1101S + CODE_DESC_CS1101S + TAG_DESC_MA2002;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_CODE_CS1101S)
                .withCredit(VALID_CREDIT_MA2002).withSemYear(VALID_SEMYEAR_CS1101S).withGrade(VALID_GRADE_CS1101S)
                .withTags(VALID_TAG_CS1101S, VALID_TAG_MA2002).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + CREDIT_DESC_MA2002 + SEMYEAR_DESC_CS1101S;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCredit(VALID_CREDIT_MA2002)
                .withSemYear(VALID_SEMYEAR_CS1101S).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + CODE_DESC_CS1101S;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_CODE_CS1101S).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + CREDIT_DESC_CS1101S;
        descriptor = new EditModuleDescriptorBuilder().withCredit(VALID_CREDIT_CS1101S).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + SEMYEAR_DESC_CS1101S;
        descriptor = new EditModuleDescriptorBuilder().withSemYear(VALID_SEMYEAR_CS1101S).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + GRADE_DESC_CS1101S;
        descriptor = new EditModuleDescriptorBuilder().withGrade(VALID_GRADE_CS1101S).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_MA2002;
        descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_MA2002).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + CREDIT_DESC_CS1101S + GRADE_DESC_CS1101S + SEMYEAR_DESC_CS1101S
                + TAG_DESC_MA2002 + CREDIT_DESC_CS1101S + GRADE_DESC_CS1101S + SEMYEAR_DESC_CS1101S + TAG_DESC_MA2002
                + CREDIT_DESC_MA2002 + GRADE_DESC_MA2002 + SEMYEAR_DESC_MA2002 + TAG_DESC_CS1101S;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCredit(VALID_CREDIT_MA2002)
                .withSemYear(VALID_SEMYEAR_MA2002).withGrade(VALID_GRADE_MA2002).withTags(VALID_TAG_MA2002, VALID_TAG_CS1101S)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_CREDIT_DESC + CREDIT_DESC_MA2002;
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCredit(VALID_CREDIT_MA2002).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + SEMYEAR_DESC_MA2002 + INVALID_CREDIT_DESC + GRADE_DESC_MA2002
                + CREDIT_DESC_MA2002;
        descriptor = new EditModuleDescriptorBuilder().withCredit(VALID_CREDIT_MA2002).withSemYear(VALID_SEMYEAR_MA2002)
                .withGrade(VALID_GRADE_MA2002).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
