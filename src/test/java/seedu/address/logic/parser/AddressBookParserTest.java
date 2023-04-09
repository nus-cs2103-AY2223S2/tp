package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_FREQUENCY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_PREMIUM_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_PREMIUM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.COFFEE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POLICY;
import static seedu.address.testutil.TypicalPolicies.AMY_POLICY;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddPolicyCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeletePolicyCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditPolicyCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.sortcommand.SortByClientEmailCommand;
import seedu.address.logic.commands.sortcommand.SortByClientNameCommand;
import seedu.address.logic.commands.sortcommand.SortByClientPhoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.model.client.policy.Premium;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.ClientUtil;
import seedu.address.testutil.EditClientDescriptorBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Client client = new ClientBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ClientUtil.getAddCommand(client));
        assertEquals(new AddCommand(client), command);
    }
    @Test
    public void parseCommand_addPolicy() throws Exception {
        final String policyStub = POLICY_NAME_AMY + POLICY_DATE_AMY + POLICY_PREMIUM_AMY + POLICY_FREQUENCY_AMY;
        AddPolicyCommand command = (AddPolicyCommand) parser.parseCommand(
                AddPolicyCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased() + " " + policyStub);
        assertEquals(new AddPolicyCommand(INDEX_FIRST_CLIENT, AMY_POLICY), command);
    }

    @Test
    public void parseCommand_deletePolicy() throws Exception {
        DeletePolicyCommand command = (DeletePolicyCommand) parser.parseCommand(
                DeletePolicyCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased() + " "
                + PREFIX_POLICY_INDEX + "1");
        assertEquals(new DeletePolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY), command);
    }

    @Test
    public void parseCommand_editPolicy() throws Exception {
        EditPolicyCommand command = (EditPolicyCommand) parser.parseCommand(
                EditPolicyCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased() + " "
                + PREFIX_POLICY_INDEX + "1" + " " + PREFIX_POLICY_PREMIUM + "5000");
        EditPolicyCommand.EditPolicyDescriptor editedPolicy = new EditPolicyCommand.EditPolicyDescriptor();
        editedPolicy.setPremium(new Premium("5000"));
        assertEquals(new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_POLICY, editedPolicy), command);
    }

    @Test
    public void parseCommand_addAppointment() throws Exception {
        AddAppointmentCommand command = (AddAppointmentCommand) parser.parseCommand(
                AddAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased() + " "
                + PREFIX_APPOINTMENT_NAME + "Coffee chat" + " " + PREFIX_APPOINTMENT_DATE + "01.01.2024");
        assertEquals(new AddAppointmentCommand(INDEX_FIRST_CLIENT, COFFEE), command);
    }

    @Test
    public void parseCommand_deleteAppointment() throws Exception {
        DeleteAppointmentCommand command = (DeleteAppointmentCommand) parser.parseCommand(
                DeleteAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased());
        assertEquals(new DeleteAppointmentCommand(INDEX_FIRST_CLIENT), command);
    }
    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_CLIENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Client client = new ClientBuilder().build();
        EditCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(client).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CLIENT.getOneBased() + " " + ClientUtil.getEditClientDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_CLIENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_select() throws Exception {
        SelectCommand command = (SelectCommand) parser.parseCommand(
                SelectCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased());
        assertEquals(new SelectCommand(INDEX_FIRST_CLIENT), command);
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

    @Test
    public void parseCommand_sortByClientName() throws Exception {
        assertTrue(parser.parseCommand(SortByClientNameCommand.COMMAND_WORD + "  1")
                instanceof SortByClientNameCommand);
        assertTrue(parser.parseCommand(SortByClientNameCommand.COMMAND_WORD + "  0")
                instanceof SortByClientNameCommand);
        assertTrue(parser.parseCommand(SortByClientNameCommand.COMMAND_WORD + "  -1")
                instanceof SortByClientNameCommand);
    }

    @Test
    public void parseCommand_sortByClientPhone() throws Exception {
        assertTrue(parser.parseCommand(SortByClientPhoneCommand.COMMAND_WORD + "  1")
                instanceof SortByClientPhoneCommand);
        assertTrue(parser.parseCommand(SortByClientPhoneCommand.COMMAND_WORD + "  0")
                instanceof SortByClientPhoneCommand);
        assertTrue(parser.parseCommand(SortByClientPhoneCommand.COMMAND_WORD + "  -1")
                instanceof SortByClientPhoneCommand);
    }

    @Test
    public void parseCommand_sortByClientEmail() throws Exception {
        assertTrue(parser.parseCommand(SortByClientEmailCommand.COMMAND_WORD + "  1")
                instanceof SortByClientEmailCommand);
        assertTrue(parser.parseCommand(SortByClientEmailCommand.COMMAND_WORD + "  0")
                instanceof SortByClientEmailCommand);
        assertTrue(parser.parseCommand(SortByClientEmailCommand.COMMAND_WORD + "  -1")
                instanceof SortByClientEmailCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + " 3") instanceof RedoCommand);
    }
}
