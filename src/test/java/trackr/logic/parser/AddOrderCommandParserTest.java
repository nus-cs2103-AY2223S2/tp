package trackr.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES_O;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.order.AddOrderCommand;
import trackr.model.order.Order;
import trackr.testutil.OrderBuilder;

//@@author chongweiguan-reused
public class AddOrderCommandParserTest {
    private AddCommandParserTest parser = new AddCommandParserTest();

    @Test
    public void parse_allFieldsPresent_success() {
        Order expectedTask = new OrderBuilder(CHOCOLATE_COOKIES_O).build();

        // whitespace only preamble
        assertTrue(expectedTask.equals(expectedTask));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no status
        Order expectedTask = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderStatus().build();
        assertTrue(expectedTask.equals(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE);
        assertTrue(expectedMessage.equals(expectedMessage));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertTrue(parser.equals(parser));
    }
    //@@author
}
