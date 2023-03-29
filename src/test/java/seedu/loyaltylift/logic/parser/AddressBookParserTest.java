package seedu.loyaltylift.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.loyaltylift.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_FILTER;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_POINTS;
import static seedu.loyaltylift.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;
import static seedu.loyaltylift.model.Model.PREDICATE_SHOW_ALL_ORDERS;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.logic.commands.AddCustomerCommand;
import seedu.loyaltylift.logic.commands.AddPointsCommand;
import seedu.loyaltylift.logic.commands.AppendCustomerNoteCommand;
import seedu.loyaltylift.logic.commands.AppendOrderNoteCommand;
import seedu.loyaltylift.logic.commands.ClearCommand;
import seedu.loyaltylift.logic.commands.DeleteCustomerCommand;
import seedu.loyaltylift.logic.commands.EditCustomerCommand;
import seedu.loyaltylift.logic.commands.EditCustomerCommand.EditCustomerDescriptor;
import seedu.loyaltylift.logic.commands.ExitCommand;
import seedu.loyaltylift.logic.commands.FindCustomerCommand;
import seedu.loyaltylift.logic.commands.FindOrderCommand;
import seedu.loyaltylift.logic.commands.HelpCommand;
import seedu.loyaltylift.logic.commands.ListCustomerCommand;
import seedu.loyaltylift.logic.commands.ListOrderCommand;
import seedu.loyaltylift.logic.commands.SetCustomerNoteCommand;
import seedu.loyaltylift.logic.commands.SetOrderNoteCommand;
import seedu.loyaltylift.logic.commands.SetPointsCommand;
import seedu.loyaltylift.logic.commands.ViewCustomerCommand;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.customer.CustomerNameContainsKeywordsPredicate;
import seedu.loyaltylift.model.customer.Points;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.OrderNameContainsKeywordsPredicate;
import seedu.loyaltylift.model.order.OrderStatusPredicate;
import seedu.loyaltylift.model.order.StatusValue;
import seedu.loyaltylift.testutil.CustomerBuilder;
import seedu.loyaltylift.testutil.CustomerUtil;
import seedu.loyaltylift.testutil.EditCustomerDescriptorBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addc() throws Exception {
        Customer customer = new CustomerBuilder().build();
        AddCustomerCommand command =
                (AddCustomerCommand) parser.parseCommand(CustomerUtil.getAddCustomerCommand(customer));
        assertEquals(new AddCustomerCommand(customer), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deletec() throws Exception {
        DeleteCustomerCommand command = (DeleteCustomerCommand) parser.parseCommand(
                DeleteCustomerCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteCustomerCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editc() throws Exception {
        Customer customer = new CustomerBuilder().build();
        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCustomerCommand command = (EditCustomerCommand) parser.parseCommand(EditCustomerCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + CustomerUtil.getEditCustomerDescriptorDetails(descriptor));
        assertEquals(new EditCustomerCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findc() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCustomerCommand command = (FindCustomerCommand) parser.parseCommand(
                FindCustomerCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCustomerCommand(new CustomerNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listc() throws Exception {
        ListCustomerCommand parsedCommand;

        // no args
        parsedCommand = (ListCustomerCommand) parser.parseCommand(ListCustomerCommand.COMMAND_WORD);
        assertEquals(new ListCustomerCommand(), parsedCommand);

        // sort points
        parsedCommand = (ListCustomerCommand) parser.parseCommand(
                ListCustomerCommand.COMMAND_WORD + " " + PREFIX_SORT + "points");
        assertEquals(new ListCustomerCommand(Customer.SORT_POINTS, PREDICATE_SHOW_ALL_CUSTOMERS), parsedCommand);

        // filter marked
        parsedCommand = (ListCustomerCommand) parser.parseCommand(
                ListCustomerCommand.COMMAND_WORD + " " + PREFIX_FILTER + "marked");
        assertEquals(new ListCustomerCommand(Customer.SORT_NAME, Customer.FILTER_SHOW_MARKED), parsedCommand);

        // sort points and filter marked
        parsedCommand = (ListCustomerCommand) parser.parseCommand(ListCustomerCommand.COMMAND_WORD + " "
                + PREFIX_SORT + "points" + " " + PREFIX_FILTER + "marked");
        assertEquals(new ListCustomerCommand(Customer.SORT_POINTS, Customer.FILTER_SHOW_MARKED), parsedCommand);
    }

    @Test
    public void parseCommand_viewc() throws Exception {
        ViewCustomerCommand command = (ViewCustomerCommand) parser.parseCommand(
                ViewCustomerCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new ViewCustomerCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_setpoints() throws Exception {
        final Points points = new Points(100, 100);
        SetPointsCommand command = (SetPointsCommand) parser.parseCommand(SetPointsCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PREFIX_POINTS + points.value);
        assertEquals(new SetPointsCommand(INDEX_FIRST, points), command);
    }

    @Test
    public void parseCommand_addpoints() throws Exception {
        final Integer addPoints = 100;
        AddPointsCommand command = (AddPointsCommand) parser.parseCommand(AddPointsCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PREFIX_POINTS + addPoints);
        assertEquals(new AddPointsCommand(INDEX_FIRST, addPoints), command);
    }

    public void parseCommand_setnotec() throws Exception {
        final Note note = new Note("Test Note");
        SetCustomerNoteCommand command = (SetCustomerNoteCommand) parser.parseCommand(
                SetCustomerNoteCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " "
                + PREFIX_NOTE + note.value);
        assertEquals(new SetCustomerNoteCommand(INDEX_FIRST, note), command);
    }

    @Test
    public void parseCommand_appendnotec() throws Exception {
        final String noteToAppend = "Extra note";
        AppendCustomerNoteCommand command = (AppendCustomerNoteCommand) parser.parseCommand(
                AppendCustomerNoteCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " "
                + PREFIX_NOTE + noteToAppend);
        assertEquals(new AppendCustomerNoteCommand(INDEX_FIRST, noteToAppend), command);
    }

    @Test
    public void parseCommand_setnoteo() throws Exception {
        final Note note = new Note("Test Note");
        SetOrderNoteCommand command = (SetOrderNoteCommand) parser.parseCommand(
                SetOrderNoteCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " "
                + PREFIX_NOTE + note.value);
        assertEquals(new SetOrderNoteCommand(INDEX_FIRST, note), command);
    }

    @Test
    public void parseCommand_appendnoteo() throws Exception {
        final String noteToAppend = "Extra note";
        AppendOrderNoteCommand command = (AppendOrderNoteCommand) parser.parseCommand(
                AppendOrderNoteCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " "
                + PREFIX_NOTE + noteToAppend);
        assertEquals(new AppendOrderNoteCommand(INDEX_FIRST, noteToAppend), command);
    }

    @Test
    public void parseCommand_findo() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindOrderCommand command = (FindOrderCommand) parser.parseCommand(
                FindOrderCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindOrderCommand(new OrderNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_listo() throws Exception {
        ListOrderCommand parsedCommand;
        OrderStatusPredicate pendingPredicate = new OrderStatusPredicate(StatusValue.PENDING);

        // no args
        parsedCommand = (ListOrderCommand) parser.parseCommand(ListOrderCommand.COMMAND_WORD);
        assertEquals(new ListOrderCommand(), parsedCommand);

        // sort status
        parsedCommand = (ListOrderCommand) parser.parseCommand(
                ListOrderCommand.COMMAND_WORD + " " + PREFIX_SORT + "status");
        assertEquals(new ListOrderCommand(Order.SORT_STATUS, PREDICATE_SHOW_ALL_ORDERS), parsedCommand);

        // filter pending
        parsedCommand = (ListOrderCommand) parser.parseCommand(
                ListOrderCommand.COMMAND_WORD + " " + PREFIX_FILTER + "pending");
        assertEquals(new ListOrderCommand(Order.SORT_CREATED_DATE, pendingPredicate), parsedCommand);

        // sort name and filter pending
        parsedCommand = (ListOrderCommand) parser.parseCommand(
                ListOrderCommand.COMMAND_WORD + " " + PREFIX_SORT + "name " + PREFIX_FILTER + "pending");
        assertEquals(new ListOrderCommand(Order.SORT_NAME, pendingPredicate), parsedCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
