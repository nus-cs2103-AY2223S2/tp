package trackr.logic.parser;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.commands.CommandTestUtil.COST_PRICE_DESC_LARGE;
import static trackr.logic.commands.CommandTestUtil.INVALID_COST_PRICE_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_ITEM_NAME_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_SELLING_PRICE_DESC;
import static trackr.logic.commands.CommandTestUtil.ITEM_NAME_DESC_NIKECAP;
import static trackr.logic.commands.CommandTestUtil.SELLING_PRICE_DESC_LARGE;
import static trackr.logic.commands.CommandTestUtil.VALID_COST_PRICE_LARGE;
import static trackr.logic.commands.CommandTestUtil.VALID_COST_PRICE_SMALL;
import static trackr.logic.commands.CommandTestUtil.VALID_ITEM_NAME_NIKECAP;
import static trackr.logic.commands.CommandTestUtil.VALID_SELLING_PRICE_LARGE;
import static trackr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static trackr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static trackr.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static trackr.testutil.TypicalIndexes.INDEX_THIRD_OBJECT;

import org.junit.jupiter.api.Test;

import trackr.commons.core.index.Index;
import trackr.logic.commands.menu.EditMenuItemCommand;
import trackr.logic.parser.menu.EditMenuItemCommandParser;
import trackr.model.menu.ItemCost;
import trackr.model.menu.ItemName;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItemDescriptor;
import trackr.testutil.MenuItemDescriptorBuilder;

public class EditMenuItemCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMenuItemCommand.MESSAGE_USAGE);

    private EditMenuItemCommandParser parser = new EditMenuItemCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_ITEM_NAME_NIKECAP, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditMenuItemCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + ITEM_NAME_DESC_NIKECAP, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + ITEM_NAME_DESC_NIKECAP, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_ITEM_NAME_DESC,
                ItemName.MESSAGE_CONSTRAINTS); // invalid menu item name
        assertParseFailure(parser, "1" + INVALID_SELLING_PRICE_DESC,
                String.format(ItemSellingPrice.MESSAGE_CONSTRAINTS)); // invalid menu item selling price
        assertParseFailure(parser, "1" + INVALID_COST_PRICE_DESC,
                ItemCost.MESSAGE_CONSTRAINTS); // invalid menu item cost price

        // invalid menu item name followed by valid menu item selling price
        assertParseFailure(parser, "1" + INVALID_ITEM_NAME_DESC
                + VALID_SELLING_PRICE_LARGE, ItemName.MESSAGE_CONSTRAINTS);

        // valid value followed by invalid value.
        // The test case for invalid value followed by valid value
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + ITEM_NAME_DESC_NIKECAP + INVALID_ITEM_NAME_DESC,
                ItemName.MESSAGE_CONSTRAINTS); //menu item name

        assertParseFailure(parser, "1" + SELLING_PRICE_DESC_LARGE + INVALID_SELLING_PRICE_DESC,
                String.format(ItemSellingPrice.MESSAGE_CONSTRAINTS)); //menu item selling price

        assertParseFailure(parser, "1" + COST_PRICE_DESC_LARGE + INVALID_COST_PRICE_DESC,
                ItemCost.MESSAGE_CONSTRAINTS); //menu item cost price

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_ITEM_NAME_DESC + INVALID_SELLING_PRICE_DESC + VALID_COST_PRICE_SMALL,
                ItemName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_OBJECT;
        String userInput = targetIndex.getOneBased() + ITEM_NAME_DESC_NIKECAP
                + SELLING_PRICE_DESC_LARGE + COST_PRICE_DESC_LARGE;


        MenuItemDescriptor descriptor =
                new MenuItemDescriptorBuilder()
                        .withName(VALID_ITEM_NAME_NIKECAP)
                        .withPrice(VALID_SELLING_PRICE_LARGE)
                        .withCost(VALID_COST_PRICE_LARGE).build();
        EditMenuItemCommand expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);


        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        //name and desc
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased()
                + ITEM_NAME_DESC_NIKECAP + SELLING_PRICE_DESC_LARGE;

        MenuItemDescriptor descriptor =
                new MenuItemDescriptorBuilder()
                        .withName(VALID_ITEM_NAME_NIKECAP)
                        .withPrice(VALID_SELLING_PRICE_LARGE).build();
        EditMenuItemCommand expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //name and cost price
        userInput = targetIndex.getOneBased() + ITEM_NAME_DESC_NIKECAP
                + COST_PRICE_DESC_LARGE;
        descriptor = new MenuItemDescriptorBuilder().withName(VALID_ITEM_NAME_NIKECAP)
                .withCost(VALID_COST_PRICE_LARGE).build();
        expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // menu item name
        Index targetIndex = INDEX_THIRD_OBJECT;
        String userInput = targetIndex.getOneBased() + ITEM_NAME_DESC_NIKECAP;
        MenuItemDescriptor descriptor = new MenuItemDescriptorBuilder()
                .withName(VALID_ITEM_NAME_NIKECAP).build();
        EditMenuItemCommand expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // menu item selling price
        userInput = targetIndex.getOneBased() + SELLING_PRICE_DESC_LARGE;
        descriptor = new MenuItemDescriptorBuilder()
                .withPrice(VALID_SELLING_PRICE_LARGE).build();
        expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // menu item cost price
        userInput = targetIndex.getOneBased() + COST_PRICE_DESC_LARGE;
        descriptor = new MenuItemDescriptorBuilder()
                .withCost(VALID_COST_PRICE_LARGE).build();
        expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased() + ITEM_NAME_DESC_NIKECAP
                + SELLING_PRICE_DESC_LARGE + COST_PRICE_DESC_LARGE
                + ITEM_NAME_DESC_NIKECAP + SELLING_PRICE_DESC_LARGE
                + COST_PRICE_DESC_LARGE + ITEM_NAME_DESC_NIKECAP
                + SELLING_PRICE_DESC_LARGE + COST_PRICE_DESC_LARGE;

        MenuItemDescriptor descriptor =
                new MenuItemDescriptorBuilder()
                        .withName(VALID_ITEM_NAME_NIKECAP)
                        .withPrice(VALID_SELLING_PRICE_LARGE)
                        .withCost(VALID_COST_PRICE_LARGE).build();
        EditMenuItemCommand expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        //no other valid values specified
        // invalid menu item name followed by valid menu item name
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased() + INVALID_ITEM_NAME_DESC + ITEM_NAME_DESC_NIKECAP;
        MenuItemDescriptor descriptor =
                new MenuItemDescriptorBuilder().withName(VALID_ITEM_NAME_NIKECAP).build();
        EditMenuItemCommand expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid menu item selling price followed by valid menu item selling price
        userInput = targetIndex.getOneBased() + INVALID_SELLING_PRICE_DESC + SELLING_PRICE_DESC_LARGE;
        descriptor = new MenuItemDescriptorBuilder().withPrice(VALID_SELLING_PRICE_LARGE).build();
        expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid menu item cost price
        //followed by valid menu item cost price
        userInput = targetIndex.getOneBased() + INVALID_COST_PRICE_DESC + COST_PRICE_DESC_LARGE;
        descriptor = new MenuItemDescriptorBuilder().withCost(VALID_COST_PRICE_LARGE).build();
        expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        // invalid menu item name followed by valid menu item name
        userInput = targetIndex.getOneBased()
                + INVALID_ITEM_NAME_DESC + COST_PRICE_DESC_LARGE
                + SELLING_PRICE_DESC_LARGE + ITEM_NAME_DESC_NIKECAP;
        descriptor = new MenuItemDescriptorBuilder()
                .withName(VALID_ITEM_NAME_NIKECAP)
                .withPrice(VALID_SELLING_PRICE_LARGE)
                .withCost(VALID_COST_PRICE_LARGE).build();
        expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid menu item selling price followed by valid menu item selling price
        userInput = targetIndex.getOneBased()
                + ITEM_NAME_DESC_NIKECAP + INVALID_SELLING_PRICE_DESC
                + COST_PRICE_DESC_LARGE + SELLING_PRICE_DESC_LARGE;
        descriptor = new MenuItemDescriptorBuilder()
                .withName(VALID_ITEM_NAME_NIKECAP)
                .withPrice(VALID_SELLING_PRICE_LARGE)
                .withCost(VALID_COST_PRICE_LARGE).build();
        expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // invalid menu item cost price
        //followed by valid menu item cost price
        userInput = targetIndex.getOneBased()
                + INVALID_COST_PRICE_DESC + ITEM_NAME_DESC_NIKECAP
                + COST_PRICE_DESC_LARGE + SELLING_PRICE_DESC_LARGE;
        descriptor = new MenuItemDescriptorBuilder()
                .withName(VALID_ITEM_NAME_NIKECAP)
                .withPrice(VALID_SELLING_PRICE_LARGE)
                .withCost(VALID_COST_PRICE_LARGE).build();
        expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_differentInputOrder_success() {
        //menu item cost price
        //, menu item selling price, menu item name
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased() + COST_PRICE_DESC_LARGE
                + SELLING_PRICE_DESC_LARGE + ITEM_NAME_DESC_NIKECAP;
        MenuItemDescriptor descriptor = new MenuItemDescriptorBuilder()
                .withCost(VALID_COST_PRICE_LARGE)
                .withPrice(VALID_SELLING_PRICE_LARGE)
                .withName(VALID_ITEM_NAME_NIKECAP).build();
        EditMenuItemCommand expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //menu item selling price, menu item cost price
        //, menu item name
        userInput = targetIndex.getOneBased() + SELLING_PRICE_DESC_LARGE
                + COST_PRICE_DESC_LARGE + ITEM_NAME_DESC_NIKECAP;
        descriptor = new MenuItemDescriptorBuilder()
                .withPrice(VALID_SELLING_PRICE_LARGE)
                .withCost(VALID_COST_PRICE_LARGE)
                .withName(VALID_ITEM_NAME_NIKECAP).build();
        expectedCommand = new EditMenuItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
