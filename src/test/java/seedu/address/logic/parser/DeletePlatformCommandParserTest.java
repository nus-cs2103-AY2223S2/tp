package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLATFORM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLATFORM_NAME_LINKEDIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLATFORM_NAME_LINKEDIN_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LISTING;
import static seedu.address.testutil.TypicalListings.SOFTWARE_DEVELOPER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePlatformCommand;
import seedu.address.model.listing.Listing;
import seedu.address.model.platform.PlatformName;
import seedu.address.testutil.ListingBuilder;

public class DeletePlatformCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePlatformCommand.MESSAGE_USAGE);

    private DeletePlatformCommandParser parser = new DeletePlatformCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Listing expectedListing =
                new ListingBuilder(SOFTWARE_DEVELOPER).build();

        assertParseSuccess(parser, "1 " + VALID_PLATFORM_NAME_LINKEDIN_DESC,
                new DeletePlatformCommand(INDEX_FIRST_LISTING, VALID_PLATFORM_NAME_LINKEDIN));
    }

    @Test
    public void parse_invalidPlatform_failure() {
        assertParseFailure(parser, "1 " + INVALID_PLATFORM_DESC, PlatformName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, VALID_PLATFORM_NAME_LINKEDIN_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "a" + VALID_PLATFORM_NAME_LINKEDIN_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0" + VALID_PLATFORM_NAME_LINKEDIN_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-1" + VALID_PLATFORM_NAME_LINKEDIN_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingPrefix_failure() {
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

}
