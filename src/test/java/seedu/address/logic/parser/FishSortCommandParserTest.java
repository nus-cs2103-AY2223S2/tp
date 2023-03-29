package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.SORT_FEEDING;
import static seedu.address.logic.commands.CommandTestUtil.SORT_LAST_FED_DATE;
import static seedu.address.logic.commands.CommandTestUtil.SORT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.SORT_SPECIES;
import static seedu.address.logic.commands.CommandTestUtil.SORT_TANK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.fish.FishSortCommand;
import seedu.address.logic.parser.fish.FishSortCommandParser;


public class FishSortCommandParserTest {
    private final FishSortCommandParser parser = new FishSortCommandParser();
    @Test
    public void parse_allScenarios_success() {
        assertParseSuccess(parser, SORT_NAME,
                new FishSortCommand(FishSortCommandParser.NAME_COMPARATOR, null));
        assertParseSuccess(parser, SORT_LAST_FED_DATE,
                new FishSortCommand(FishSortCommandParser.LAST_FED_COMPARATOR, null));
        assertParseSuccess(parser, SORT_SPECIES,
                new FishSortCommand(FishSortCommandParser.SPECIES_COMPARATOR, null));
        assertParseSuccess(parser, SORT_FEEDING,
                new FishSortCommand(FishSortCommandParser.FEEDING_COMPARATOR, null));
        assertParseSuccess(parser, SORT_TANK,
                new FishSortCommand(FishSortCommandParser.TANK_COMPARATOR, null));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FishSortCommand.MESSAGE_USAGE);

        // missing input
        assertParseFailure(parser, " " + PREFIX_SORT_BY + " ", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FishSortCommand.MESSAGE_USAGE);

        // extra character
        assertParseFailure(parser, " " + PREFIX_SORT_BY + "lfdd", expectedMessage);

        // missing character
        assertParseFailure(parser, " " + PREFIX_SORT_BY + "lf", expectedMessage);

        // invalid character
        assertParseFailure(parser, " " + PREFIX_SORT_BY + "@", expectedMessage);

        // integer character
        assertParseFailure(parser, " " + PREFIX_SORT_BY + "-1", expectedMessage);
    }
}
