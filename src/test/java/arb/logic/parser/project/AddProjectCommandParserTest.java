package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.commands.CommandTestUtil.DEADLINE_DESC_ALIAS_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.DEADLINE_DESC_ALIAS_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.DEADLINE_DESC_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.DEADLINE_DESC_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static arb.logic.commands.CommandTestUtil.INVALID_PRICE_DESC;
import static arb.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static arb.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static arb.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static arb.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static arb.logic.commands.CommandTestUtil.PRICE_DESC_ALIAS_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.PRICE_DESC_ALIAS_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.PRICE_DESC_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.PRICE_DESC_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.TAG_DESC_ALIAS_PAINTING;
import static arb.logic.commands.CommandTestUtil.TAG_DESC_ALIAS_POTTERY;
import static arb.logic.commands.CommandTestUtil.TAG_DESC_PAINTING;
import static arb.logic.commands.CommandTestUtil.TAG_DESC_POTTERY;
import static arb.logic.commands.CommandTestUtil.TITLE_DESC_ALIAS_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.TITLE_DESC_ALIAS_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.TITLE_DESC_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.TITLE_DESC_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_PRICE_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_POTTERY;
import static arb.logic.commands.CommandTestUtil.VALID_TITLE_SKY_PAINTING;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static arb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static arb.testutil.TypicalProjects.SKY_PAINTING;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import arb.logic.commands.project.AddProjectCommand;
import arb.model.project.Deadline;
import arb.model.project.Price;
import arb.model.project.Project;
import arb.model.project.Title;
import arb.model.tag.Tag;
import arb.testutil.ProjectBuilder;

public class AddProjectCommandParserTest {
    private AddProjectCommandParser parser = new AddProjectCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Project expectedProject = new ProjectBuilder(SKY_PAINTING).withTags().build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // multiple names, main prefix only - last name accepted
        assertParseSuccess(parser, TITLE_DESC_OIL_PAINTING + TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // multiple names, alias prefix only - last name accepted
        assertParseSuccess(parser, TITLE_DESC_ALIAS_OIL_PAINTING + TITLE_DESC_ALIAS_SKY_PAINTING
                + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // multiple names, mix of main and alias prefix - last name accepted
        assertParseSuccess(parser, TITLE_DESC_OIL_PAINTING + TITLE_DESC_OIL_PAINTING + TITLE_DESC_SKY_PAINTING
                + TITLE_DESC_ALIAS_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // multiple deadlines, main prefix only - last deadline accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_OIL_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // multiple deadlines, alias prefix only - last deadline accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_ALIAS_OIL_PAINTING
                + DEADLINE_DESC_ALIAS_SKY_PAINTING + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // multiple deadlines, mix of main and alias prefix - last deadline accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_OIL_PAINTING
                + DEADLINE_DESC_ALIAS_OIL_PAINTING + DEADLINE_DESC_ALIAS_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // multiple prices, main prefix only - last price accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_OIL_PAINTING + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // multiple prices, alias prefix only - last price accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_ALIAS_OIL_PAINTING
                + PRICE_DESC_ALIAS_SKY_PAINTING, new AddProjectCommand(expectedProject, Optional.empty()));

        // multiple prices, mix of main and alias prefix - last price accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_ALIAS_SKY_PAINTING + PRICE_DESC_ALIAS_OIL_PAINTING + PRICE_DESC_OIL_PAINTING
                + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // multiple tags, main prefix only - all accepted
        Project expectedProjectMultipleTags = new ProjectBuilder(SKY_PAINTING)
                .withTags(VALID_TAG_PAINTING, VALID_TAG_POTTERY)
                .build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + TAG_DESC_POTTERY + TAG_DESC_PAINTING,
                new AddProjectCommand(expectedProjectMultipleTags, Optional.empty()));

        // multiple tags, alias prefix only - all accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + TAG_DESC_ALIAS_POTTERY + TAG_DESC_ALIAS_PAINTING,
                new AddProjectCommand(expectedProjectMultipleTags, Optional.empty()));

        // multiple tags, mix of main and alias prefix - all accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + TAG_DESC_ALIAS_POTTERY + TAG_DESC_PAINTING,
                new AddProjectCommand(expectedProjectMultipleTags, Optional.empty()));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Project expectedProject = new ProjectBuilder(SKY_PAINTING).withTags().build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // no deadline
        expectedProject = new ProjectBuilder(SKY_PAINTING).withDeadline(null).build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + TAG_DESC_PAINTING + TAG_DESC_POTTERY
                + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // no price
        expectedProject = new ProjectBuilder(SKY_PAINTING).withPrice(null).build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + TAG_DESC_PAINTING
                + TAG_DESC_POTTERY,
                new AddProjectCommand(expectedProject, Optional.empty()));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TITLE_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_SKY_PAINTING + VALID_DEADLINE_SKY_PAINTING + VALID_PRICE_SKY_PAINTING,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TITLE_DESC + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_SKY_PAINTING, Title.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, TITLE_DESC_SKY_PAINTING + INVALID_DEADLINE_DESC
                + PRICE_DESC_SKY_PAINTING, Deadline.MESSAGE_CONSTRAINTS);

        // invalid price
        assertParseFailure(parser, TITLE_DESC_ALIAS_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + INVALID_PRICE_DESC, Price.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + INVALID_TAG_DESC + VALID_TAG_PAINTING, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + INVALID_DEADLINE_DESC,
                Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_SKY_PAINTING
                + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE));
    }
}
