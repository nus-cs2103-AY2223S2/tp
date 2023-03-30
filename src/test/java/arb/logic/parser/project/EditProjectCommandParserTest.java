package arb.logic.parser.project;

import static arb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static arb.logic.commands.CommandTestUtil.CLIENT_DESC_ALIAS_ALICE;
import static arb.logic.commands.CommandTestUtil.CLIENT_DESC_ALICE;
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
import static arb.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static arb.logic.commands.CommandTestUtil.PRICE_DESC_ALIAS_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.PRICE_DESC_ALIAS_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.PRICE_DESC_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.PRICE_DESC_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.TAG_DESC_ALIAS_PAINTING;
import static arb.logic.commands.CommandTestUtil.TAG_DESC_ALIAS_POTTERY;
import static arb.logic.commands.CommandTestUtil.TAG_DESC_PAINTING;
import static arb.logic.commands.CommandTestUtil.TITLE_DESC_ALIAS_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.TITLE_DESC_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_CLIENT_NAME_ALICE;
import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_DEADLINE_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_PRICE_OIL_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_PRICE_SKY_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_PAINTING;
import static arb.logic.commands.CommandTestUtil.VALID_TAG_POTTERY;
import static arb.logic.commands.CommandTestUtil.VALID_TITLE_SKY_PAINTING;
import static arb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static arb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static arb.testutil.TypicalIndexes.INDEX_FIRST;
import static arb.testutil.TypicalIndexes.INDEX_SECOND;
import static arb.testutil.TypicalIndexes.INDEX_THIRD;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import arb.commons.core.index.Index;
import arb.logic.commands.project.EditProjectCommand;
import arb.logic.commands.project.EditProjectCommand.EditProjectDescriptor;
import arb.model.project.Deadline;
import arb.model.project.Price;
import arb.model.project.Title;
import arb.testutil.EditProjectDescriptorBuilder;

public class EditProjectCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProjectCommand.MESSAGE_USAGE);

    private EditProjectCommandParser parser = new EditProjectCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE_SKY_PAINTING, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditProjectCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_SKY_PAINTING, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_SKY_PAINTING, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC,
                Title.MESSAGE_CONSTRAINTS); // invalid title
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC,
                Deadline.MESSAGE_CONSTRAINTS); // invalid deadline
        assertParseFailure(parser, "1" + INVALID_PRICE_DESC,
                Price.MESSAGE_CONSTRAINTS); // invalid price

        // valid deadline followed by invalid deadline. The test case for invalid deadline
        // followed by valid deadline
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DEADLINE_DESC_OIL_PAINTING
                + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_DEADLINE_DESC,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;

        // using main prefixes
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_OIL_PAINTING
                + TITLE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING + TAG_DESC_PAINTING
                + CLIENT_DESC_ALICE;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withTitle(VALID_TITLE_SKY_PAINTING)
                .withDeadline(VALID_DEADLINE_OIL_PAINTING)
                .withPrice(VALID_PRICE_SKY_PAINTING)
                .withTags(VALID_TAG_PAINTING)
                .withLinkedClientNameKeywords(Arrays.asList(VALID_CLIENT_NAME_ALICE))
                .build();
        EditProjectCommand expectedCommand = new EditProjectCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // using alias prefixes
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_ALIAS_OIL_PAINTING
                + TITLE_DESC_ALIAS_SKY_PAINTING + PRICE_DESC_ALIAS_SKY_PAINTING
                + TAG_DESC_ALIAS_PAINTING + CLIENT_DESC_ALIAS_ALICE;

        assertParseSuccess(parser, userInput, expectedCommand);

        // using a mix of main and alias prefixes
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_ALIAS_OIL_PAINTING
                + TITLE_DESC_SKY_PAINTING + PRICE_DESC_SKY_PAINTING
                + TAG_DESC_ALIAS_PAINTING + CLIENT_DESC_ALICE;

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;

        // using main prefixes
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_OIL_PAINTING
                + PRICE_DESC_OIL_PAINTING;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withDeadline(VALID_DEADLINE_OIL_PAINTING)
                .withPrice(VALID_PRICE_OIL_PAINTING)
                .build();
        EditProjectCommand expectedCommand = new EditProjectCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // using alias prefixes
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_ALIAS_OIL_PAINTING
                + PRICE_DESC_ALIAS_OIL_PAINTING;

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title, using main prefix
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_SKY_PAINTING;
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withTitle(VALID_TITLE_SKY_PAINTING).build();
        EditProjectCommand expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // title, using alias prefix
        userInput = targetIndex.getOneBased() + TITLE_DESC_ALIAS_SKY_PAINTING;
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline, using main prefix
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_SKY_PAINTING;
        descriptor = new EditProjectDescriptorBuilder().withDeadline(VALID_DEADLINE_SKY_PAINTING).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline, using alias prefix
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_ALIAS_SKY_PAINTING;
        assertParseSuccess(parser, userInput, expectedCommand);

        // price, using main prefix
        userInput = targetIndex.getOneBased() + PRICE_DESC_SKY_PAINTING;
        descriptor = new EditProjectDescriptorBuilder().withPrice(VALID_PRICE_SKY_PAINTING).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // price, using alias prefix
        userInput = targetIndex.getOneBased() + PRICE_DESC_ALIAS_SKY_PAINTING;
        assertParseSuccess(parser, userInput, expectedCommand);

        // client, using main prefix
        userInput = targetIndex.getOneBased() + CLIENT_DESC_ALICE;
        descriptor = new EditProjectDescriptorBuilder().withLinkedClientNameKeywords(Arrays
                .asList(VALID_CLIENT_NAME_ALICE)).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // client, using alias prefix
        userInput = targetIndex.getOneBased() + CLIENT_DESC_ALIAS_ALICE;
        descriptor = new EditProjectDescriptorBuilder().withLinkedClientNameKeywords(Arrays
                .asList(VALID_CLIENT_NAME_ALICE)).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags, using main prefix
        userInput = targetIndex.getOneBased() + TAG_DESC_PAINTING;
        descriptor = new EditProjectDescriptorBuilder().withTags(VALID_TAG_PAINTING).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags, using alias prefix
        userInput = targetIndex.getOneBased() + TAG_DESC_ALIAS_PAINTING;
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC_SKY_PAINTING
                + DEADLINE_DESC_ALIAS_SKY_PAINTING + DEADLINE_DESC_ALIAS_OIL_PAINTING
                + DEADLINE_DESC_OIL_PAINTING + PRICE_DESC_SKY_PAINTING + PRICE_DESC_ALIAS_OIL_PAINTING
                + PRICE_DESC_ALIAS_SKY_PAINTING + PRICE_DESC_OIL_PAINTING
                + TAG_DESC_PAINTING + TAG_DESC_ALIAS_POTTERY;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withDeadline(VALID_DEADLINE_OIL_PAINTING)
                .withPrice(VALID_PRICE_OIL_PAINTING)
                .withTags(VALID_TAG_PAINTING, VALID_TAG_POTTERY)
                .build();
        EditProjectCommand expectedCommand = new EditProjectCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_DEADLINE_DESC + DEADLINE_DESC_OIL_PAINTING;
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder()
                .withDeadline(VALID_DEADLINE_OIL_PAINTING).build();
        EditProjectCommand expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_DEADLINE_DESC
                + DEADLINE_DESC_OIL_PAINTING;
        descriptor = new EditProjectDescriptorBuilder().withDeadline(VALID_DEADLINE_OIL_PAINTING).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetValues_success() {
        Index targetIndex = INDEX_THIRD;

        // empty tag, main prefix
        String userInput = targetIndex.getOneBased() + EMPTY_TAG;
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withTags().build();
        EditProjectCommand expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // empty tag, alias prefix
        userInput = targetIndex.getOneBased() + EMPTY_TAG_ALIAS;
        assertParseSuccess(parser, userInput, expectedCommand);

        // empty deadline, main prefix
        userInput = targetIndex.getOneBased() + EMPTY_DEADLINE;
        descriptor = new EditProjectDescriptorBuilder().withDeadline(null).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // empty deadline, alias prefix
        userInput = targetIndex.getOneBased() + EMPTY_DEADLINE_ALIAS;
        assertParseSuccess(parser, userInput, expectedCommand);

        // empty price, main prefix
        userInput = targetIndex.getOneBased() + EMPTY_PRICE;
        descriptor = new EditProjectDescriptorBuilder().withPrice(null).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // empty price, alias prefix
        userInput = targetIndex.getOneBased() + EMPTY_PRICE_ALIAS;
        assertParseSuccess(parser, userInput, expectedCommand);

        // empty client, main prefix
        userInput = targetIndex.getOneBased() + EMPTY_CLIENT;
        descriptor = new EditProjectDescriptorBuilder().withLinkedClientNameKeywords(Arrays.asList()).build();
        expectedCommand = new EditProjectCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // empty client, alias prefix
        userInput = targetIndex.getOneBased() + EMPTY_CLIENT_ALIAS;
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
