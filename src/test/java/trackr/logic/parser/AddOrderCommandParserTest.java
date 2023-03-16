package trackr.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.AddOrderCommand;
import trackr.model.order.Order;
import trackr.testutil.OrderBuilder;

public class AddOrderCommandParserTest {
    private AddCommandParserTest parser = new AddCommandParserTest();

    @Test
    public void parse_allFieldsPresent_success() {
        Order expectedTask = new OrderBuilder(CHOCOLATE_COOKIES).build();

        // whitespace only preamble
        assertTrue(expectedTask.equals(expectedTask));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no status
        Order expectedTask = new OrderBuilder(CHOCOLATE_COOKIES).withOrderStatus().build();
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
}
