package seedu.modtrek.logic.parser;

import static seedu.modtrek.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modtrek.logic.commands.CommandTestUtil.CODE_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.CODE_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.CREDIT_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.CREDIT_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.GRADE_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.GRADE_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_CODE_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_CREDIT_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_GRADE_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_SEMYEAR_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.modtrek.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.modtrek.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.modtrek.logic.commands.CommandTestUtil.SEMYEAR_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.SEMYEAR_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.TAG_DESC_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.TAG_DESC_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CODE_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CODE_MA2002;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CREDIT_CS1101S;
import static seedu.modtrek.logic.commands.CommandTestUtil.VALID_CREDIT_MA2002;
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
import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.MA2002;

import org.junit.jupiter.api.Test;

import seedu.modtrek.logic.commands.AddCommand;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.model.module.SemYear;
import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.testutil.ModuleBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Module expectedModule = new ModuleBuilder(MA2002).withTags(VALID_TAG_MA2002).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002
                + GRADE_DESC_MA2002 + TAG_DESC_MA2002, new AddCommand(expectedModule));

        // multiple names - last name accepted
        assertParseSuccess(parser, CODE_DESC_CS1101S + CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002
                + GRADE_DESC_MA2002 + TAG_DESC_MA2002, new AddCommand(expectedModule));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, CODE_DESC_MA2002 + CREDIT_DESC_CS1101S + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002
                + GRADE_DESC_MA2002 + TAG_DESC_MA2002, new AddCommand(expectedModule));

        // multiple emails - last email accepted
        assertParseSuccess(parser, CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + SEMYEAR_DESC_CS1101S + SEMYEAR_DESC_MA2002
                + GRADE_DESC_MA2002 + TAG_DESC_MA2002, new AddCommand(expectedModule));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002 + GRADE_DESC_CS1101S
                + GRADE_DESC_MA2002 + TAG_DESC_MA2002, new AddCommand(expectedModule));

        // multiple tags - all accepted
        Module expectedModuleMultipleTags = new ModuleBuilder(MA2002).withTags(VALID_TAG_MA2002, VALID_TAG_CS1101S)
                .build();
        assertParseSuccess(parser, CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002 + GRADE_DESC_MA2002
                + TAG_DESC_CS1101S + TAG_DESC_MA2002, new AddCommand(expectedModuleMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Module expectedModule = new ModuleBuilder(CS1101S).withTags().build();
        assertParseSuccess(parser, CODE_DESC_CS1101S + CREDIT_DESC_CS1101S + SEMYEAR_DESC_CS1101S + GRADE_DESC_CS1101S,
                new AddCommand(expectedModule));

        //no grade specified
        expectedModule = new ModuleBuilder().withCode(VALID_CODE_CS1101S)
                .withCredit(VALID_CREDIT_CS1101S).withSemYear(VALID_SEMYEAR_CS1101S).build();
        assertParseSuccess(parser, CODE_DESC_CS1101S + CREDIT_DESC_CS1101S + SEMYEAR_DESC_CS1101S,
                new AddCommand(expectedModule));
    }

    @Test
    public void parse_noArguments_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_preamblePresent_failure() {
        assertParseFailure(parser, " module" + CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {

        // missing code prefix
        assertParseFailure(parser, VALID_CODE_MA2002 + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002 + GRADE_DESC_MA2002,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        // missing credit prefix
        assertParseFailure(parser, CODE_DESC_MA2002 + VALID_CREDIT_MA2002 + SEMYEAR_DESC_MA2002 + GRADE_DESC_MA2002,
                AddCommand.MESSAGE_MISSING_PREFIXES);

        // missing sem-year prefix
        assertParseFailure(parser, CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + VALID_SEMYEAR_MA2002 + GRADE_DESC_MA2002,
                AddCommand.MESSAGE_MISSING_PREFIXES);

        // all prefixes missing
        assertParseFailure(parser, VALID_CODE_MA2002 + VALID_CREDIT_MA2002 + VALID_SEMYEAR_MA2002 + VALID_GRADE_MA2002,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_detailMissing_failure() {

        // missing code detail
        assertParseFailure(parser, " " + PREFIX_CODE + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002
                        + GRADE_DESC_MA2002, Code.MESSAGE_MISSING_DETAIL);

        // missing credit detail
        assertParseFailure(parser, CODE_DESC_MA2002 + " " + PREFIX_CREDIT + SEMYEAR_DESC_MA2002
                        + GRADE_DESC_MA2002, Credit.MESSAGE_MISSING_DETAIL);

        // missing sem-year detail
        assertParseFailure(parser, CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + " " + PREFIX_SEMYEAR
                        + GRADE_DESC_MA2002, SemYear.MESSAGE_MISSING_DETAIL);

        // missing tag detail
        assertParseFailure(parser, CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002
                        + " " + PREFIX_TAG, Tag.MESSAGE_MISSING_DETAIL);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_CODE_DESC + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002 + GRADE_DESC_MA2002
                + TAG_DESC_CS1101S + TAG_DESC_MA2002, Code.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, CODE_DESC_MA2002 + INVALID_CREDIT_DESC + SEMYEAR_DESC_MA2002 + GRADE_DESC_MA2002
                + TAG_DESC_CS1101S + TAG_DESC_MA2002, Credit.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + INVALID_SEMYEAR_DESC + GRADE_DESC_MA2002
                + TAG_DESC_CS1101S + TAG_DESC_MA2002, SemYear.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002 + INVALID_GRADE_DESC
                + TAG_DESC_CS1101S + TAG_DESC_MA2002, Grade.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002 + GRADE_DESC_MA2002
                + INVALID_TAG_DESC + VALID_TAG_MA2002, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_CODE_DESC + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002 + INVALID_GRADE_DESC,
                Code.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CODE_DESC_MA2002 + CREDIT_DESC_MA2002 + SEMYEAR_DESC_MA2002
                + GRADE_DESC_MA2002 + TAG_DESC_CS1101S + TAG_DESC_MA2002,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
