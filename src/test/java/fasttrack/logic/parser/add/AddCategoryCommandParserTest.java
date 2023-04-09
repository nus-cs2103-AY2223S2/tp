package fasttrack.logic.parser.add;

import static fasttrack.logic.commands.CommandTestUtil.CAT_APPLE;
import static fasttrack.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static fasttrack.logic.commands.CommandTestUtil.SUM_APPLE;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fasttrack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static fasttrack.testutil.TypicalCategories.FOOD;
import static fasttrack.testutil.TypicalCategories.FOOD_NO_SUMMARY;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.add.AddCategoryCommand;
import fasttrack.model.category.Category;

class AddCategoryCommandParserTest {
    private final AddCategoryCommandParser parser = new AddCategoryCommandParser();

    @Test
    void parse_validArgs_returnsAddCategoryCommand() {
        String input1 = CAT_APPLE;
        AddCategoryCommand expectedCommand1 = new AddCategoryCommand(FOOD_NO_SUMMARY);
        assertParseSuccess(parser, input1, expectedCommand1);

        String input2 = CAT_APPLE + SUM_APPLE;
        AddCategoryCommand expectedCommand2 = new AddCategoryCommand(FOOD);
        assertParseSuccess(parser, input2, expectedCommand2);
    }

    @Test
    void parse_invalidValue_throwsParseException() {
        String input1 = INVALID_CATEGORY_DESC;
        assertParseFailure(parser, input1, Category.MESSAGE_CONSTRAINTS);
    }
}
