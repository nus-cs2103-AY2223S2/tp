package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class CopyCommandTest {

    private final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    private final StringSelection emptyString = new StringSelection("");

    @BeforeEach
    public void setUp() {
        // Clear clipboard contents
        clipboard.setContents(emptyString, null);
    }

    @Test
    public void execute_copyValidIndex_success() throws IOException, UnsupportedFlavorException {
        Person personToCopy = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        CopyCommand copyCommand = new CopyCommand(INDEX_FIRST_PERSON);

        assertCommandSuccess(copyCommand, model, CopyCommand.MESSAGE_COPY_SUCCESS, expectedModel);

        // Check contents of clipboard
        try {
            String expectedInformation = copyCommand.getInformation(personToCopy);
            String actualInformation = (String) clipboard.getData(DataFlavor.stringFlavor);
            assertEquals(actualInformation, expectedInformation);
        } catch (IOException | UnsupportedFlavorException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    @Test
    public void execute_copyInvalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CopyCommand copyCommand = new CopyCommand(outOfBoundIndex);

        assertCommandFailure(copyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
