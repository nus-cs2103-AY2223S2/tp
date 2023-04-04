package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLATFORM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLATFORM_NAME_GLINTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLATFORM_NAME_GLINTS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLATFORM_NAME_INDEED_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATFORM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LISTING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPlatformCommand;
import seedu.address.model.platform.PlatformName;
import seedu.address.testutil.PlatformBuilder;

public class AddPlatformCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPlatformCommand.MESSAGE_USAGE);

    private AddPlatformCommandParser parser = new AddPlatformCommandParser();

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, VALID_PLATFORM_NAME_INDEED_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "a" + VALID_PLATFORM_NAME_INDEED_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0" + VALID_PLATFORM_NAME_INDEED_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-1" + VALID_PLATFORM_NAME_INDEED_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 i/ string" + VALID_PLATFORM_NAME_INDEED_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        // only one platform
        assertParseSuccess(parser, "1 " + VALID_PLATFORM_NAME_GLINTS_DESC,
                new AddPlatformCommand(
                        INDEX_FIRST_LISTING,
                        new PlatformBuilder().withName(VALID_PLATFORM_NAME_GLINTS).build()));

        // multiple platform - only accept the last one
        assertParseSuccess(parser, "1 " + VALID_PLATFORM_NAME_INDEED_DESC + VALID_PLATFORM_NAME_GLINTS_DESC,
                new AddPlatformCommand(
                        INDEX_FIRST_LISTING,
                        new PlatformBuilder().withName(VALID_PLATFORM_NAME_GLINTS).build()));

    }

    @Test
    public void parse_missingPlatform_failure() {
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidId_failure() {
        assertParseFailure(parser, "1" + INVALID_PLATFORM_DESC, PlatformName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 " + PREFIX_PLATFORM, PlatformName.MESSAGE_CONSTRAINTS);
    }
}
