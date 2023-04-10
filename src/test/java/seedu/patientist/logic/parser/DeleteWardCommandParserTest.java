package seedu.patientist.logic.parser;

import static seedu.patientist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.patientist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.patientist.testutil.WardBuilder.DEFAULT_NAME;

import org.junit.jupiter.api.Test;

import seedu.patientist.logic.commands.DeleteWardCommand;
import seedu.patientist.model.ward.Ward;
import seedu.patientist.testutil.WardBuilder;

public class DeleteWardCommandParserTest {
    private DeleteWardCommandParser parser = new DeleteWardCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Ward expectedWard = new WardBuilder().build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " " + PREFIX_NAME + DEFAULT_NAME,
                new DeleteWardCommand(expectedWard.getWardName()));

        // multiple names - last name accepted
        assertParseSuccess(parser, " " + PREFIX_NAME + DEFAULT_NAME + " " + PREFIX_NAME + DEFAULT_NAME,
                new DeleteWardCommand(expectedWard.getWardName()));
    }
}
