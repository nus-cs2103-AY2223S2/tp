package seedu.address.logic.parser.documents;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.documents.AddDocumentsCommand;
import seedu.address.logic.commands.documents.DeleteDocumentsCommand;
import seedu.address.logic.commands.documents.EditDocumentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.documents.Documents;
import seedu.address.testutil.DocumentsBuilder;
import seedu.address.testutil.DocumentsUtil;
import seedu.address.testutil.EditDocumentsDescriptorBuilder;

public class DocumentsParserTest {

    private final DocumentsParser parser = new DocumentsParser();

    @Test
    public void parseCommand_add_docs() throws Exception {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        Documents documents = new DocumentsBuilder().build();
        AddDocumentsCommand command = (AddDocumentsCommand) parser.parseDocumentsCommand(
                AddDocumentsCommand.COMMAND_WORD,
                " " + targetIndex.getOneBased() + " "
                        + DocumentsUtil.getAddDocumentsCommandArguments(documents));
        assertEquals(new AddDocumentsCommand(targetIndex, documents), command);
    }

    @Test
    public void parseCommand_delete_docs() throws Exception {
        DeleteDocumentsCommand command = (DeleteDocumentsCommand) parser.parseDocumentsCommand(
                DeleteDocumentsCommand.COMMAND_WORD, " " + INDEX_FIFTH_APPLICATION.getOneBased());
        assertEquals(new DeleteDocumentsCommand(INDEX_FIFTH_APPLICATION), command);
    }

    @Test
    public void parseCommand_edit_docs() throws Exception {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        Documents documents = new DocumentsBuilder().build();
        EditDocumentsCommand.EditDocumentsDescriptor descriptor = new EditDocumentsDescriptorBuilder(documents).build();
        EditDocumentsCommand command = (EditDocumentsCommand) parser.parseDocumentsCommand(
                EditDocumentsCommand.COMMAND_WORD,
                " " + INDEX_FIFTH_APPLICATION.getOneBased() + " "
                        + DocumentsUtil.getEditDocumentsDescriptorDetails(descriptor));
        assertEquals(new EditDocumentsCommand(INDEX_FIFTH_APPLICATION, descriptor), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseDocumentsCommand("", ""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseDocumentsCommand("unknownCommand", "unknownArguments"));
    }
}
