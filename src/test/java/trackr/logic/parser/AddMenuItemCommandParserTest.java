package trackr.logic.parser;

import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.logic.commands.CommandTestUtil.COST_PRICE_DESC_LARGE;
import static trackr.logic.commands.CommandTestUtil.COST_PRICE_DESC_SMALL;
import static trackr.logic.commands.CommandTestUtil.INVALID_COST_PRICE_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_ITEM_NAME_DESC;
import static trackr.logic.commands.CommandTestUtil.INVALID_SELLING_PRICE_DESC;
import static trackr.logic.commands.CommandTestUtil.ITEM_NAME_DESC_CUPCAKE;
import static trackr.logic.commands.CommandTestUtil.ITEM_NAME_DESC_NIKECAP;
import static trackr.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static trackr.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static trackr.logic.commands.CommandTestUtil.SELLING_PRICE_DESC_LARGE;
import static trackr.logic.commands.CommandTestUtil.SELLING_PRICE_DESC_SMALL;
import static trackr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static trackr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static trackr.testutil.TypicalMenuItems.NIKE_CAP;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.menu.AddMenuItemCommand;
import trackr.logic.parser.menu.AddMenuItemCommandParser;
import trackr.model.menu.ItemCost;
import trackr.model.menu.ItemName;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;
import trackr.testutil.MenuItemBuilder;

public class AddMenuItemCommandParserTest {
    private AddMenuItemCommandParser parser = new AddMenuItemCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        MenuItem expectedMenuItem = new MenuItemBuilder(NIKE_CAP).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ITEM_NAME_DESC_NIKECAP
                + SELLING_PRICE_DESC_SMALL + COST_PRICE_DESC_SMALL, new AddMenuItemCommand(expectedMenuItem));

        // multiple names - last name accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ITEM_NAME_DESC_CUPCAKE + ITEM_NAME_DESC_NIKECAP
                + SELLING_PRICE_DESC_SMALL + COST_PRICE_DESC_SMALL, new AddMenuItemCommand(expectedMenuItem));


        // multiple selling price - last selling price accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ITEM_NAME_DESC_NIKECAP + SELLING_PRICE_DESC_LARGE
                + SELLING_PRICE_DESC_SMALL + COST_PRICE_DESC_SMALL, new AddMenuItemCommand(expectedMenuItem));

        // multiple cost price - last cost price accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ITEM_NAME_DESC_NIKECAP + SELLING_PRICE_DESC_SMALL
                + COST_PRICE_DESC_LARGE + COST_PRICE_DESC_SMALL, new AddMenuItemCommand(expectedMenuItem));
    }


    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_ITEM_NAME_DESC + SELLING_PRICE_DESC_LARGE
                + COST_PRICE_DESC_LARGE, ItemName.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, ITEM_NAME_DESC_CUPCAKE + INVALID_SELLING_PRICE_DESC
                + COST_PRICE_DESC_LARGE, String.format(ItemSellingPrice.MESSAGE_CONSTRAINTS));

        // invalid status
        assertParseFailure(parser, ITEM_NAME_DESC_CUPCAKE + SELLING_PRICE_DESC_LARGE
                + INVALID_COST_PRICE_DESC, String.format(ItemCost.MESSAGE_CONSTRAINTS));

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_ITEM_NAME_DESC + SELLING_PRICE_DESC_LARGE + INVALID_COST_PRICE_DESC,
                ItemName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ITEM_NAME_DESC_CUPCAKE
                        + SELLING_PRICE_DESC_LARGE + COST_PRICE_DESC_LARGE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMenuItemCommand.MESSAGE_USAGE));
    }
}
