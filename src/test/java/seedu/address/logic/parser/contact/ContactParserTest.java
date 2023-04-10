package seedu.address.logic.parser.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.contact.AddContactCommand;
import seedu.address.logic.commands.contact.DeleteContactCommand;
import seedu.address.logic.commands.contact.EditContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.ContactUtil;
import seedu.address.testutil.EditContactDescriptorBuilder;

public class ContactParserTest {

    private final ContactParser parser = new ContactParser();

    @Test
    public void parseCommand_add_contact() throws Exception {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        Contact contact = new ContactBuilder().build();
        AddContactCommand command = (AddContactCommand) parser.parseContactCommand(
                AddContactCommand.COMMAND_WORD,
                " " + targetIndex.getOneBased() + " "
                        + ContactUtil.getAddContactCommandsArguments(contact));
        assertEquals(new AddContactCommand(targetIndex, contact), command);
    }

    @Test
    public void parseCommand_delete_contact() throws Exception {
        DeleteContactCommand command = (DeleteContactCommand) parser.parseContactCommand(
                DeleteContactCommand.COMMAND_WORD, " " + INDEX_FIRST_APPLICATION.getOneBased());
        assertEquals(new DeleteContactCommand(INDEX_FIRST_APPLICATION), command);
    }

    @Test
    public void parseCommand_edit_contact() throws Exception {
        Contact contact = new ContactBuilder().build();
        EditContactCommand.EditContactDescriptor descriptor = new EditContactDescriptorBuilder(contact).build();
        EditContactCommand command = (EditContactCommand) parser.parseContactCommand(
                EditContactCommand.COMMAND_WORD,
                " " + INDEX_FIRST_APPLICATION.getOneBased() + " "
                        + ContactUtil.getEditContactDescriptorDetails(descriptor));
        assertEquals(new EditContactCommand(INDEX_FIRST_APPLICATION, descriptor), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseContactCommand("", ""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseContactCommand("unknownCommand", "unknownArguments"));
    }
}
