package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.commands.CommandTestUtil.DEADLINE_DESC_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.DEADLINE_DESC_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static arb.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static arb.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static arb.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static arb.logic.commands.CommandTestUtil.TITLE_DESC_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.TITLE_DESC_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TITLE_SKY_PAINTING;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static arb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static arb.testutil.TypicalProjects.SKY_PAINTING;

import org.junit.jupiter.api.Test;

import arb.logic.commands.project.AddProjectCommand;
import arb.model.project.Deadline;
import arb.model.project.Project;
import arb.model.project.Title;
import arb.testutil.ProjectBuilder;

public class AddProjectCommandParserTest {
    private AddProjectCommandParser parser = new AddProjectCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Project expectedProject = new ProjectBuilder(SKY_PAINTING).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject));

        // multiple names - last name accepted
        assertParseSuccess(parser, TITLE_DESC_OIL_PAINTING + TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_OIL_PAINTING + DEADLINE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no deadline
        Project expectedProject = new ProjectBuilder(SKY_PAINTING).withDeadline(null).build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TITLE_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_SKY_PAINTING + VALID_DEADLINE_SKY_PAINTING,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TITLE_DESC + DEADLINE_DESC_SKY_PAINTING, Title.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, TITLE_DESC_SKY_PAINTING + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + INVALID_DEADLINE_DESC,
                Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE));
    }
}
