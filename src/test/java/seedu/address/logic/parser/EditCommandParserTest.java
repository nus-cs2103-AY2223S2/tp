package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FISH;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_FISH;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_FISH;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditFishDescriptor;
import seedu.address.model.fish.FeedingInterval;
import seedu.address.model.fish.LastFedDate;
import seedu.address.model.fish.Name;
import seedu.address.model.fish.Species;
import seedu.address.model.tag.Tag;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.TankName;
import seedu.address.testutil.EditFishDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_LAST_FED_DATE_DESC,
                LastFedDate.MESSAGE_CONSTRAINTS); // invalid lastFedDate
        assertParseFailure(parser, "1" + INVALID_SPECIES_DESC, Species.MESSAGE_CONSTRAINTS); // invalid species
        // invalid feeding interval
        assertParseFailure(parser, "1" + INVALID_FEEDING_INTERVAL_DESC, FeedingInterval.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TANK_DESC, TankName.MESSAGE_CONSTRAINTS);//invalid tank
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid lastFedDate followed by valid species
        assertParseFailure(parser, "1" + INVALID_LAST_FED_DATE_DESC + SPECIES_DESC_AMY,
                LastFedDate.MESSAGE_CONSTRAINTS);

        // valid lastFedDate followed by invalid lastFedDate. The test case for invalid lastFedDate followed by valid
        // lastFedDate
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + LAST_FED_DATE_DESC_BOB + INVALID_LAST_FED_DATE_DESC,
                LastFedDate.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Fish} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_SPECIES_DESC + VALID_FEEDING_INTERVAL_AMY
                        + VALID_LAST_FED_DATE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_FISH;
        String userInput = targetIndex.getOneBased() + LAST_FED_DATE_DESC_BOB + TAG_DESC_HUSBAND
                + SPECIES_DESC_AMY + FEEDING_INTERVAL_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND
                + TANK_DESC_BOB;

        EditFishDescriptor descriptor = new EditFishDescriptorBuilder().withName(VALID_NAME_AMY)
                .withLastFedDate(VALID_LAST_FED_DATE_BOB).withSpecies(VALID_SPECIES_AMY).withFeedingInterval(VALID_FEEDING_INTERVAL_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withTank(VALID_TANK_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_FISH;
        String userInput = targetIndex.getOneBased() + LAST_FED_DATE_DESC_BOB + SPECIES_DESC_AMY;

        EditFishDescriptor descriptor = new EditFishDescriptorBuilder().withLastFedDate(VALID_LAST_FED_DATE_BOB)
                .withSpecies(VALID_SPECIES_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_FISH;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditFishDescriptor descriptor = new EditFishDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // lastFedDate
        userInput = targetIndex.getOneBased() + LAST_FED_DATE_DESC_AMY;
        descriptor = new EditFishDescriptorBuilder().withLastFedDate(VALID_LAST_FED_DATE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // species
        userInput = targetIndex.getOneBased() + SPECIES_DESC_AMY;
        descriptor = new EditFishDescriptorBuilder().withSpecies(VALID_SPECIES_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // feeding interval
        userInput = targetIndex.getOneBased() + FEEDING_INTERVAL_DESC_AMY;
        descriptor = new EditFishDescriptorBuilder().withFeedingInterval(VALID_FEEDING_INTERVAL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tank
        userInput = targetIndex.getOneBased() + TANK_DESC_AMY;
        descriptor = new EditFishDescriptorBuilder().withTank(VALID_TANK_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditFishDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_FISH;
        String userInput = targetIndex.getOneBased() + LAST_FED_DATE_DESC_AMY + FEEDING_INTERVAL_DESC_AMY
                + SPECIES_DESC_AMY
                + TAG_DESC_FRIEND + LAST_FED_DATE_DESC_AMY + FEEDING_INTERVAL_DESC_AMY + SPECIES_DESC_AMY
                + TAG_DESC_FRIEND + TANK_DESC_BOB
                + LAST_FED_DATE_DESC_BOB + FEEDING_INTERVAL_DESC_BOB + SPECIES_DESC_BOB + TAG_DESC_HUSBAND;

        EditFishDescriptor descriptor = new EditFishDescriptorBuilder().withLastFedDate(VALID_LAST_FED_DATE_BOB)
                .withSpecies(VALID_SPECIES_BOB).withFeedingInterval(VALID_FEEDING_INTERVAL_BOB)
                .withTank(VALID_TANK_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_FISH;
        String userInput = targetIndex.getOneBased() + INVALID_LAST_FED_DATE_DESC + LAST_FED_DATE_DESC_BOB;
        EditFishDescriptor descriptor = new EditFishDescriptorBuilder().withLastFedDate(VALID_LAST_FED_DATE_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + SPECIES_DESC_BOB + INVALID_LAST_FED_DATE_DESC + FEEDING_INTERVAL_DESC_BOB
                + LAST_FED_DATE_DESC_BOB;
        descriptor = new EditFishDescriptorBuilder().withLastFedDate(VALID_LAST_FED_DATE_BOB)
                .withSpecies(VALID_SPECIES_BOB).withFeedingInterval(VALID_FEEDING_INTERVAL_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_FISH;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditFishDescriptor descriptor = new EditFishDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
