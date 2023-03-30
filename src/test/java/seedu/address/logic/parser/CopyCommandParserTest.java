package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATOON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.commands.CopyCommand.CopyInformationSelector;
import seedu.address.testutil.CopyInformationSelectorBuilder;

public class CopyCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE);

    private final CopyCommandParser parser = new CopyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

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
    public void parse_allFieldsSelected_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NAME + " " + PREFIX_PHONE + " " + PREFIX_EMAIL
                + " " + PREFIX_ADDRESS + " " + PREFIX_RANK + " " + PREFIX_UNIT + " " + PREFIX_COMPANY
                + " " + PREFIX_PLATOON + " " + PREFIX_TAG;

        CopyInformationSelector informationSelector = new CopyInformationSelector();
        informationSelector.copyAllInformation(true);
        CopyCommand expectedCommand = new CopyCommand(targetIndex, informationSelector);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSelected_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NAME + " " + PREFIX_EMAIL
                + " " + PREFIX_TAG;

        CopyInformationSelector informationSelector = new CopyInformationSelectorBuilder().selectName().selectEmail()
                .selectTags().build();
        CopyCommand expectedCommand = new CopyCommand(targetIndex, informationSelector);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_THIRD_PERSON;

        // name
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NAME;
        CopyInformationSelector informationSelector = new CopyInformationSelectorBuilder().selectName().build();
        CopyCommand expectedCommand = new CopyCommand(targetIndex, informationSelector);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + " " + PREFIX_PHONE;
        informationSelector = new CopyInformationSelectorBuilder().selectPhone().build();
        expectedCommand = new CopyCommand(targetIndex, informationSelector);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + " " + PREFIX_EMAIL;
        informationSelector = new CopyInformationSelectorBuilder().selectEmail().build();
        expectedCommand = new CopyCommand(targetIndex, informationSelector);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + " " + PREFIX_ADDRESS;
        informationSelector = new CopyInformationSelectorBuilder().selectAddress().build();
        expectedCommand = new CopyCommand(targetIndex, informationSelector);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rank
        userInput = targetIndex.getOneBased() + " " + PREFIX_RANK;
        informationSelector = new CopyInformationSelectorBuilder().selectRank().build();
        expectedCommand = new CopyCommand(targetIndex, informationSelector);
        assertParseSuccess(parser, userInput, expectedCommand);

        // unit
        userInput = targetIndex.getOneBased() + " " + PREFIX_UNIT;
        informationSelector = new CopyInformationSelectorBuilder().selectUnit().build();
        expectedCommand = new CopyCommand(targetIndex, informationSelector);
        assertParseSuccess(parser, userInput, expectedCommand);

        // company
        userInput = targetIndex.getOneBased() + " " + PREFIX_COMPANY;
        informationSelector = new CopyInformationSelectorBuilder().selectCompany().build();
        expectedCommand = new CopyCommand(targetIndex, informationSelector);
        assertParseSuccess(parser, userInput, expectedCommand);

        // platoon
        userInput = targetIndex.getOneBased() + " " + PREFIX_PLATOON;
        informationSelector = new CopyInformationSelectorBuilder().selectPlatoon().build();
        expectedCommand = new CopyCommand(targetIndex, informationSelector);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tag
        userInput = targetIndex.getOneBased() + " " + PREFIX_TAG;
        informationSelector = new CopyInformationSelectorBuilder().selectTags().build();
        expectedCommand = new CopyCommand(targetIndex, informationSelector);
        assertParseSuccess(parser, userInput, expectedCommand);

        // all
        userInput = targetIndex.getOneBased() + " ";
        informationSelector = new CopyInformationSelectorBuilder().selectAll().build();
        expectedCommand = new CopyCommand(targetIndex, informationSelector);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
