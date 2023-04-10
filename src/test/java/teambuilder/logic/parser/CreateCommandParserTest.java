package teambuilder.logic.parser;

import static teambuilder.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static teambuilder.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static teambuilder.logic.commands.CommandTestUtil.TAG_DESC_A;
import static teambuilder.logic.commands.CommandTestUtil.TEAMDESC_DESC_A;
import static teambuilder.logic.commands.CommandTestUtil.TEAMNAME_DESC_A;
import static teambuilder.logic.commands.CommandTestUtil.VALID_SKILLTAG_TEAM;
import static teambuilder.logic.commands.CommandTestUtil.VALID_TEAMDESC_A;
import static teambuilder.logic.commands.CommandTestUtil.VALID_TEAMNAME_A;
import static teambuilder.logic.parser.CommandParserTestUtil.assertParseFailure;
import static teambuilder.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import teambuilder.logic.commands.CreateCommand;
import teambuilder.model.tag.Tag;
import teambuilder.model.team.Desc;
import teambuilder.model.team.Team;
import teambuilder.model.team.TeamName;

public class CreateCommandParserTest {

    private CreateCommandParser parser = new CreateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Team expectedTeam = new Team(new TeamName(VALID_TEAMNAME_A), new Desc(VALID_TEAMDESC_A),
                new HashSet<>(Arrays.asList(new Tag(VALID_SKILLTAG_TEAM))));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TEAMNAME_DESC_A + TEAMDESC_DESC_A + TAG_DESC_A,
                new CreateCommand(expectedTeam));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE);

        // missing team name prefix
        assertParseFailure(parser, TEAMDESC_DESC_A + TAG_DESC_A, expectedMessage);

        // missing team desc prefix
        assertParseFailure(parser, TEAMNAME_DESC_A + TAG_DESC_A, expectedMessage);
    }

}
