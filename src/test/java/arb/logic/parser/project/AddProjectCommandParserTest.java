package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.commands.CommandTestUtil.CLIENT_DESC_ALIAS_ALICE;
import static arb.logic.commands.CommandTestUtil.CLIENT_DESC_ALIAS_BOB;
import static arb.logic.commands.CommandTestUtil.CLIENT_DESC_ALICE;
import static arb.logic.commands.CommandTestUtil.CLIENT_DESC_BOB;
import static arb.logic.commands.CommandTestUtil.DEADLINE_DESC_ALIAS_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.DEADLINE_DESC_ALIAS_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.DEADLINE_DESC_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.DEADLINE_DESC_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.EMPTY_CLIENT;
import static arb.logic.commands.CommandTestUtil.EMPTY_CLIENT_ALIAS;
import static arb.logic.commands.CommandTestUtil.EMPTY_DEADLINE;
import static arb.logic.commands.CommandTestUtil.EMPTY_DEADLINE_ALIAS;
import static arb.logic.commands.CommandTestUtil.EMPTY_PRICE;
import static arb.logic.commands.CommandTestUtil.EMPTY_PRICE_ALIAS;
import static arb.logic.commands.CommandTestUtil.EMPTY_TAG;
import static arb.logic.commands.CommandTestUtil.EMPTY_TAG_ALIAS;
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
import static arb.logic.commands.CommandTestUtil.VALID_CLIENT_NAME_ALICE;
import static arb.logic.commands.CommandTestUtil.VALID_CLIENT_NAME_BOB;
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
import arb.model.client.predicates.NameContainsKeywordsPredicate;
import arb.model.project.Deadline;
import arb.model.project.Price;
import arb.model.project.Project;
import arb.model.project.Title;
import arb.model.tag.Tag;
import arb.testutil.PredicateUtil;
import arb.testutil.ProjectBuilder;

public class AddProjectCommandParserTest {
    private AddProjectCommandParser parser = new AddProjectCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Project expectedProject = new ProjectBuilder(SKY_PAINTING).withTags().build();
        NameContainsKeywordsPredicate expectedPredicate =
                PredicateUtil.getNameContainsKeywordsPredicate(VALID_CLIENT_NAME_ALICE);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_SKY_PAINTING + CLIENT_DESC_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(expectedPredicate)));

        // multiple names, main prefix only - last name accepted
        assertParseSuccess(parser, TITLE_DESC_OIL_PAINTING + TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_SKY_PAINTING + CLIENT_DESC_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(expectedPredicate)));

        // multiple names, alias prefix only - last name accepted
        assertParseSuccess(parser, TITLE_DESC_ALIAS_OIL_PAINTING + TITLE_DESC_ALIAS_SKY_PAINTING
                + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING + CLIENT_DESC_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(expectedPredicate)));

        // multiple names, mix of main and alias prefix - last name accepted
        assertParseSuccess(parser, TITLE_DESC_OIL_PAINTING + TITLE_DESC_OIL_PAINTING + TITLE_DESC_SKY_PAINTING
                + TITLE_DESC_ALIAS_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + CLIENT_DESC_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(expectedPredicate)));

        // multiple deadlines, main prefix only - last deadline accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_OIL_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_SKY_PAINTING + CLIENT_DESC_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(expectedPredicate)));

        // multiple deadlines, alias prefix only - last deadline accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_ALIAS_OIL_PAINTING
                + DEADLINE_DESC_ALIAS_SKY_PAINTING + PRICE_DESC_SKY_PAINTING + CLIENT_DESC_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(expectedPredicate)));

        // multiple deadlines, mix of main and alias prefix - last deadline accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_OIL_PAINTING
                + DEADLINE_DESC_ALIAS_OIL_PAINTING + DEADLINE_DESC_ALIAS_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_SKY_PAINTING + CLIENT_DESC_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(expectedPredicate)));

        // multiple prices, main prefix only - last price accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_OIL_PAINTING + PRICE_DESC_SKY_PAINTING + CLIENT_DESC_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(expectedPredicate)));

        // multiple prices, alias prefix only - last price accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_ALIAS_OIL_PAINTING
                + PRICE_DESC_ALIAS_SKY_PAINTING + CLIENT_DESC_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(expectedPredicate)));

        // multiple prices, mix of main and alias prefix - last price accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING
                + PRICE_DESC_ALIAS_SKY_PAINTING + PRICE_DESC_ALIAS_OIL_PAINTING + PRICE_DESC_OIL_PAINTING
                + PRICE_DESC_SKY_PAINTING + CLIENT_DESC_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(expectedPredicate)));

        NameContainsKeywordsPredicate expectedMultipleKeywordPredicate =
                PredicateUtil.getNameContainsKeywordsPredicate(VALID_CLIENT_NAME_ALICE, VALID_CLIENT_NAME_BOB);
        // multiple clients, main prefix only - last client accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + CLIENT_DESC_BOB + CLIENT_DESC_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(expectedMultipleKeywordPredicate)));

        // multiple clients, alias prefix only - last client accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + CLIENT_DESC_ALIAS_BOB + CLIENT_DESC_ALIAS_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(expectedMultipleKeywordPredicate)));

        // multiple tags, main prefix only - all accepted
        Project expectedProjectMultipleTags = new ProjectBuilder(SKY_PAINTING)
                .withTags(VALID_TAG_PAINTING, VALID_TAG_POTTERY)
                .build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + CLIENT_DESC_ALICE + TAG_DESC_POTTERY + TAG_DESC_PAINTING,
                new AddProjectCommand(expectedProjectMultipleTags, Optional.of(expectedPredicate)));

        // multiple tags, alias prefix only - all accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + CLIENT_DESC_ALICE + TAG_DESC_ALIAS_POTTERY + TAG_DESC_ALIAS_PAINTING,
                new AddProjectCommand(expectedProjectMultipleTags, Optional.of(expectedPredicate)));

        // multiple tags, mix of main and alias prefix - all accepted
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + CLIENT_DESC_ALICE + TAG_DESC_ALIAS_POTTERY + TAG_DESC_PAINTING,
                new AddProjectCommand(expectedProjectMultipleTags, Optional.of(expectedPredicate)));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        NameContainsKeywordsPredicate predicate =
                PredicateUtil.getNameContainsKeywordsPredicate(VALID_CLIENT_NAME_ALICE);

        // zero tags
        Project expectedProject = new ProjectBuilder(SKY_PAINTING).withTags().build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + CLIENT_DESC_ALICE, new AddProjectCommand(expectedProject, Optional.of(predicate)));

        // no deadline
        expectedProject = new ProjectBuilder(SKY_PAINTING).withDeadline(null).build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + TAG_DESC_PAINTING + TAG_DESC_POTTERY
                + PRICE_DESC_SKY_PAINTING + CLIENT_DESC_ALICE,
                new AddProjectCommand(expectedProject, Optional.of(predicate)));

        // no price
        expectedProject = new ProjectBuilder(SKY_PAINTING).withPrice(null).build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + CLIENT_DESC_ALICE
                + TAG_DESC_PAINTING + TAG_DESC_POTTERY,
                new AddProjectCommand(expectedProject, Optional.of(predicate)));

        // no linked client
        expectedProject = new ProjectBuilder(SKY_PAINTING).build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + TAG_DESC_PAINTING + TAG_DESC_POTTERY,
                new AddProjectCommand(expectedProject, Optional.empty()));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TITLE_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_SKY_PAINTING + VALID_DEADLINE_SKY_PAINTING + VALID_PRICE_SKY_PAINTING
                + VALID_CLIENT_NAME_ALICE + VALID_TAG_PAINTING, expectedMessage);
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

    @Test
    public void parse_emptyValue_success() {
        // empty deadline, main prefix
        Project expectedProject = new ProjectBuilder(SKY_PAINTING).withDeadline(null).build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + TAG_DESC_PAINTING + TAG_DESC_POTTERY
                + PRICE_DESC_SKY_PAINTING + EMPTY_DEADLINE,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // empty deadline, alias prefix
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + TAG_DESC_PAINTING + TAG_DESC_POTTERY
                + PRICE_DESC_SKY_PAINTING + EMPTY_DEADLINE_ALIAS,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // empty price, main prefix
        expectedProject = new ProjectBuilder(SKY_PAINTING).withPrice(null).build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + TAG_DESC_PAINTING
                + TAG_DESC_POTTERY + EMPTY_PRICE,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // empty price, alias prefix
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + TAG_DESC_PAINTING
                + TAG_DESC_POTTERY + EMPTY_PRICE_ALIAS,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // empty client, main prefix
        expectedProject = new ProjectBuilder(SKY_PAINTING).build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + TAG_DESC_PAINTING
                + TAG_DESC_POTTERY + PRICE_DESC_SKY_PAINTING + EMPTY_CLIENT,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // empty client, alias prefix
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + TAG_DESC_PAINTING
                + TAG_DESC_POTTERY + PRICE_DESC_SKY_PAINTING + EMPTY_CLIENT_ALIAS,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // empty tag, main prefix
        expectedProject = new ProjectBuilder(SKY_PAINTING).withTags().build();
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + EMPTY_TAG
                + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));

        // empty tag, alias prefix
        assertParseSuccess(parser, TITLE_DESC_SKY_PAINTING + DEADLINE_DESC_SKY_PAINTING + EMPTY_TAG_ALIAS
                + PRICE_DESC_SKY_PAINTING,
                new AddProjectCommand(expectedProject, Optional.empty()));
    }
}
