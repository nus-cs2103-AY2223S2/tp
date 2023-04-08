package seedu.address.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AutoM8CommandTestUtil.assertFailure;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.entity.shop.exception.CustomerNotFoundException;
import seedu.address.testutil.TypicalShop;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class AutoM8DeleteCommandTest {
    @Test
    public void execute_invalidValues_failure() {
        Model model = TypicalShop.getTypicalModel();
        assertFailure(new DeleteCustomerCommand(-1), model, new CustomerNotFoundException(-1));
        assertFailure(new DeleteCustomerCommand(9), model, new CustomerNotFoundException(9));
    }
}
