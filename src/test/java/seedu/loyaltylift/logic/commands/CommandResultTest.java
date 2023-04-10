package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_AND_SHOW_CUSTOMER;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_AND_SHOW_ORDER;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_CUSTOMERS_ONLY;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.LIST_ORDERS_ONLY;
import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.REMOVE_INFO_FROM_VIEW;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));

        // different ListViewGuiAction value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", LIST_AND_SHOW_CUSTOMER)));

        // different ListViewGuiAction value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", LIST_AND_SHOW_ORDER)));

        // different ListViewGuiAction value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", LIST_CUSTOMERS_ONLY)));

        // different ListViewGuiAction value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", LIST_ORDERS_ONLY)));

        // different ListViewGuiAction value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", REMOVE_INFO_FROM_VIEW)));
    }

    @Test
    public void isShowCustomerList() {
        CommandResult falseCommandResult = new CommandResult("feedback");
        assertFalse(falseCommandResult.isShowCustomerList());

        falseCommandResult = new CommandResult("feedback", LIST_AND_SHOW_ORDER);
        assertFalse(falseCommandResult.isShowCustomerList());

        falseCommandResult = new CommandResult("feedback", LIST_ORDERS_ONLY);
        assertFalse(falseCommandResult.isShowCustomerList());

        CommandResult trueCommandResult = new CommandResult("feedback", LIST_AND_SHOW_CUSTOMER);
        assertTrue(trueCommandResult.isShowCustomerList());

        trueCommandResult = new CommandResult("feedback", LIST_CUSTOMERS_ONLY);
        assertTrue(trueCommandResult.isShowCustomerList());

        falseCommandResult = new CommandResult("feedback", REMOVE_INFO_FROM_VIEW);
        assertFalse(falseCommandResult.isShowCustomerList());
    }

    @Test
    public void isShowOrderList() {
        CommandResult falseCommandResult = new CommandResult("feedback");
        assertFalse(falseCommandResult.isShowOrderList());

        falseCommandResult = new CommandResult("feedback", LIST_AND_SHOW_CUSTOMER);
        assertFalse(falseCommandResult.isShowOrderList());

        falseCommandResult = new CommandResult("feedback", LIST_CUSTOMERS_ONLY);
        assertFalse(falseCommandResult.isShowOrderList());

        CommandResult trueCommandResult = new CommandResult("feedback", LIST_AND_SHOW_ORDER);
        assertTrue(trueCommandResult.isShowOrderList());

        trueCommandResult = new CommandResult("feedback", LIST_ORDERS_ONLY);
        assertTrue(trueCommandResult.isShowOrderList());

        falseCommandResult = new CommandResult("feedback", REMOVE_INFO_FROM_VIEW);
        assertFalse(falseCommandResult.isShowOrderList());
    }

    @Test
    public void isClearInfoView() {
        CommandResult falseCommandResult = new CommandResult("feedback");
        assertFalse(falseCommandResult.isClearInfoView());

        falseCommandResult = new CommandResult("feedback", LIST_AND_SHOW_CUSTOMER);
        assertFalse(falseCommandResult.isClearInfoView());

        falseCommandResult = new CommandResult("feedback", LIST_CUSTOMERS_ONLY);
        assertFalse(falseCommandResult.isClearInfoView());

        falseCommandResult = new CommandResult("feedback", LIST_AND_SHOW_ORDER);
        assertFalse(falseCommandResult.isClearInfoView());

        falseCommandResult = new CommandResult("feedback", LIST_ORDERS_ONLY);
        assertFalse(falseCommandResult.isClearInfoView());

        CommandResult trueCommandResult = new CommandResult("feedback", REMOVE_INFO_FROM_VIEW);
        assertTrue(trueCommandResult.isClearInfoView());
    }

    @Test
    public void showHelp() {
        CommandResult trueCommandResult = new CommandResult("feedback", true, false);
        assertTrue(trueCommandResult.isShowHelp());

        CommandResult falseCommandResult = new CommandResult("feedback", false, false);
        assertFalse(falseCommandResult.isShowHelp());
    }

    @Test
    public void exit() {
        CommandResult trueCommandResult = new CommandResult("feedback", false, true);
        assertTrue(trueCommandResult.isExit());

        CommandResult falseCommandResult = new CommandResult("feedback", false, false);
        assertFalse(falseCommandResult.isExit());
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());

        // different ListViewGuiAction value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", LIST_AND_SHOW_CUSTOMER).hashCode());

        // different ListViewGuiAction value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", LIST_AND_SHOW_ORDER).hashCode());

        // different ListViewGuiAction value-> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", LIST_CUSTOMERS_ONLY).hashCode());

        // different ListViewGuiAction value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", LIST_ORDERS_ONLY).hashCode());

        // different ListViewGuiAction value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", REMOVE_INFO_FROM_VIEW).hashCode());
    }
}
