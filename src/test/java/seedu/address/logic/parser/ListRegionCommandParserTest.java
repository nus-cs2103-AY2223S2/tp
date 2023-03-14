package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListRegionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Region;
import seedu.address.model.person.Region.Regions;

public class ListRegionCommandParserTest {

    private ListRegionCommandParser parser = new ListRegionCommandParser();

    @Test
    public void parse_emptyString_throwsParseException() {
        assertParseFailure(parser, "", Region.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "                                 ", Region.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " ", Region.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_validRegion_returnsCorrectRegion() throws ParseException {
        assertEquals(parser.parse(" north "), new ListRegionCommand(Regions.NORTH));
        assertEquals(parser.parse(" WEST     "), new ListRegionCommand(Regions.WEST));
        assertEquals(parser.parse("east"), new ListRegionCommand(Regions.EAST));
        assertEquals(parser.parse("sOuTh"), new ListRegionCommand(Regions.SOUTH));
        assertEquals(parser.parse("South"), new ListRegionCommand(Regions.SOUTH));
    }

    @Test
    public void parse_invalidRegion_throwsParseException() throws ParseException {
        assertParseFailure(parser, "north south east west", Region.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "Noth", Region.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "wdawsdwadsdawd", Region.MESSAGE_CONSTRAINTS);
    }
}
