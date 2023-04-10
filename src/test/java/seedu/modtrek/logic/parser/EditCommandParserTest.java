package seedu.modtrek.logic.parser;

import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.commands.CommandTestUtil.CODE_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.CREDIT_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.CREDIT_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.GRADE_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.GRADE_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_CODE_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_CREDIT_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_SEMYEAR_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.SEMYEAR_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.SEMYEAR_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.TAG_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.TAG_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CODE_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CREDIT_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CREDIT_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_GRADE_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_GRADE_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_SEMYEAR_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_SEMYEAR_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_TAG_MA2002;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_CREDIT;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_SEMYEAR;
import static seedu.modtrek.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modtrek.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.EditCommand;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.testutil.EditModuleDescriptorBuilder;

class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no module specified
        assertParseFailure(parser, " " + PREFIX_CODE + " " + VALID_CODE_CS1101S, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, " " + VALID_CODE_CS1101S,
                String.format(EditCommand.MESSAGE_NOT_EDITED, EditCommand.MESSAGE_USAGE));

        // no module and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, " " + VALID_CODE_CS1101S + VALID_CREDIT_CS1101S,
                Code.MESSAGE_CONSTRAINTS);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, " " + VALID_CODE_CS1101S + "/i " + VALID_CREDIT_CS1101S,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, VALID_CODE_CS1101S + INVALID_CODE_DESC,
                Code.MESSAGE_CONSTRAINTS); // invalid code
        assertParseFailure(parser, VALID_CODE_CS1101S + INVALID_CREDIT_DESC,
                Credit.MESSAGE_CONSTRAINTS); // invalid credit
        assertParseFailure(parser, VALID_CODE_CS1101S + INVALID_SEMYEAR_DESC,
                SemYear.MESSAGE_CONSTRAINTS); // invalid sem-year
        assertParseFailure(parser, VALID_CODE_CS1101S + INVALID_GRADE_DESC,
                Grade.MESSAGE_CONSTRAINTS); // invalid grade
        assertParseFailure(parser, VALID_CODE_CS1101S + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid credit followed by valid sem-year
        assertParseFailure(parser, VALID_CODE_CS1101S + INVALID_CREDIT_DESC
                + SEMYEAR_DESC_CS1101S, Credit.MESSAGE_CONSTRAINTS);

        // valid credit followed by invalid credit. The test case for invalid credit followed by valid credit
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, VALID_CODE_CS1101S + CREDIT_DESC_MA2002
                + INVALID_CREDIT_DESC, Credit.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Module} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, VALID_CODE_CS1101S + TAG_DESC_MA2002
                + TAG_DESC_CS1101S + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_CODE_CS1101S + TAG_DESC_MA2002
                + TAG_EMPTY + TAG_DESC_CS1101S, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_CODE_CS1101S + TAG_EMPTY
                + TAG_DESC_MA2002 + TAG_DESC_CS1101S, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, VALID_CODE_CS1101S + INVALID_CODE_DESC
                        + INVALID_SEMYEAR_DESC + VALID_GRADE_CS1101S + VALID_CREDIT_CS1101S,
                Code.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingValue_failure() {
        assertParseFailure(parser, VALID_CODE_CS1101S + " " + PREFIX_CODE,
                Code.MESSAGE_MISSING_DETAIL);
        assertParseFailure(parser, VALID_CODE_CS1101S + " " + PREFIX_CREDIT,
                Credit.MESSAGE_MISSING_DETAIL);
        assertParseFailure(parser, VALID_CODE_CS1101S + " " + PREFIX_SEMYEAR,
                SemYear.MESSAGE_MISSING_DETAIL);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = VALID_CODE_CS1101S + CREDIT_DESC_MA2002 + TAG_DESC_CS1101S
                + SEMYEAR_DESC_CS1101S + GRADE_DESC_CS1101S + CODE_DESC_CS1101S + TAG_DESC_MA2002;

        EditCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCode(VALID_CODE_CS1101S)
                .withCredit(VALID_CREDIT_MA2002).withSemYear(VALID_SEMYEAR_CS1101S)
                .withGrade(VALID_GRADE_CS1101S)
                .withTags(VALID_TAG_CS1101S, VALID_TAG_MA2002).build();
        EditCommand expectedCommand = new EditCommand(new Code(VALID_CODE_CS1101S), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = VALID_CODE_CS1101S + CREDIT_DESC_MA2002 + SEMYEAR_DESC_CS1101S;

        EditCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCredit(VALID_CREDIT_MA2002)
                .withSemYear(VALID_SEMYEAR_CS1101S).build();
        EditCommand expectedCommand = new EditCommand(new Code(VALID_CODE_CS1101S), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // code
        String userInput = VALID_CODE_CS1101S + CODE_DESC_CS1101S;
        EditCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCode(VALID_CODE_CS1101S).build();
        EditCommand expectedCommand = new EditCommand(new Code(VALID_CODE_CS1101S), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // credit
        userInput = VALID_CODE_CS1101S + CREDIT_DESC_CS1101S;
        descriptor = new EditModuleDescriptorBuilder().withCredit(VALID_CREDIT_CS1101S).build();
        expectedCommand = new EditCommand(new Code(VALID_CODE_CS1101S), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // sem-year
        userInput = VALID_CODE_CS1101S + SEMYEAR_DESC_CS1101S;
        descriptor = new EditModuleDescriptorBuilder().withSemYear(VALID_SEMYEAR_CS1101S).build();
        expectedCommand = new EditCommand(new Code(VALID_CODE_CS1101S), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // grade
        userInput = VALID_CODE_CS1101S + GRADE_DESC_CS1101S;
        descriptor = new EditModuleDescriptorBuilder().withGrade(VALID_GRADE_CS1101S).build();
        expectedCommand = new EditCommand(new Code(VALID_CODE_CS1101S), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = VALID_CODE_CS1101S + TAG_DESC_MA2002;
        descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_MA2002).build();
        expectedCommand = new EditCommand(new Code(VALID_CODE_CS1101S), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = VALID_CODE_CS1101S + CREDIT_DESC_CS1101S + GRADE_DESC_CS1101S
                + SEMYEAR_DESC_CS1101S
                + TAG_DESC_MA2002 + CREDIT_DESC_CS1101S + GRADE_DESC_CS1101S + SEMYEAR_DESC_CS1101S
                + TAG_DESC_MA2002
                + CREDIT_DESC_MA2002 + GRADE_DESC_MA2002 + SEMYEAR_DESC_MA2002 + TAG_DESC_CS1101S;

        EditCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCredit(VALID_CREDIT_MA2002)
                .withSemYear(VALID_SEMYEAR_MA2002).withGrade(VALID_GRADE_MA2002)
                .withTags(VALID_TAG_MA2002, VALID_TAG_CS1101S)
                .build();
        EditCommand expectedCommand = new EditCommand(new Code(VALID_CODE_CS1101S), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = VALID_CODE_CS1101S + INVALID_CREDIT_DESC + CREDIT_DESC_MA2002;
        EditCommand.EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder()
                .withCredit(VALID_CREDIT_MA2002).build();
        EditCommand expectedCommand = new EditCommand(new Code(VALID_CODE_CS1101S), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = VALID_CODE_CS1101S + SEMYEAR_DESC_MA2002 + INVALID_CREDIT_DESC
                + GRADE_DESC_MA2002
                + CREDIT_DESC_MA2002;
        descriptor = new EditModuleDescriptorBuilder().withCredit(VALID_CREDIT_MA2002)
                .withSemYear(VALID_SEMYEAR_MA2002)
                .withGrade(VALID_GRADE_MA2002).build();
        expectedCommand = new EditCommand(new Code(VALID_CODE_CS1101S), descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
