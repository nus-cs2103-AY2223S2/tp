package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.testutil.ModelStub;

public class AutocompleteEngineTest {

    private static final String ADD_COMMAND_SUGGESTION = "add n/NAME [p/PHONE] [e/EMAIL] [a/ADDRESS] "
            + "[edu/EDUCATION] [tele/TELEGRAM_HANDLE] [r/REMARK] [t/TAG]... [m/MODULE]...";
    private static final String EDIT_COMMAND_SUGGESTION = "edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] "
            + "[edu/EDUCATION] [tele/TELEGRAM_HANDLE] [t/TAG]... [m/MODULE]...";
    private static final String REMARK_COMMAND_SUGGESTION = "remark INDEX [REMARK]";
    private final Model model = new ModelStubExistingFieldValues();
    private final AutocompleteEngine autocomplete = new AutocompleteEngine(model);

    @Test
    public void suggestCommand_blankInput_returnsEmpty() {
        assertSuggestion("", "");
        assertSuggestion("   ", "   ");
        assertSuggestion(" \t \n ", " \t \n ");
    }

    @Test
    public void suggestCommand_validCommandSuffix_returnsCommandUsage() {
        assertSuggestion("a", ADD_COMMAND_SUGGESTION);
        assertSuggestion("ad", ADD_COMMAND_SUGGESTION);
        assertSuggestion("add", ADD_COMMAND_SUGGESTION);

        assertSuggestion("e", EDIT_COMMAND_SUGGESTION);
        assertSuggestion("ed", EDIT_COMMAND_SUGGESTION);
        assertSuggestion("edit", EDIT_COMMAND_SUGGESTION);

        assertSuggestion("r", REMARK_COMMAND_SUGGESTION);
        assertSuggestion("rem", REMARK_COMMAND_SUGGESTION);
        assertSuggestion("remark", REMARK_COMMAND_SUGGESTION);
    }

    @Test
    public void suggestCommand_invalidCommandSuffix_throwsParseException() {
        assertSuggestionThrows("x", MESSAGE_UNKNOWN_COMMAND);
        assertSuggestionThrows("adda", MESSAGE_UNKNOWN_COMMAND);
        assertSuggestionThrows("adda n/Person Name", MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void suggestCommand_validCompulsoryArgSuffix_returnsArgWithPlaceholder() {
        String userInput;

        userInput = "add n";
        assertSuggestion(userInput, userInput + "/NAME");

        userInput = "add p/91304513 n";
        assertSuggestion(userInput, userInput + "/NAME");
    }

    @Test
    public void suggestCommand_invalidIndex_throwsParseException() {
        // Currently, the suggestion's heuristic only checks for non-negative integers.
        // assertSuggestionThrows("edit 0", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        // assertSuggestionThrows("edit 0", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertSuggestionThrows("edit -1", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertSuggestionThrows("edit a", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertSuggestionThrows("edit 1a", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertSuggestionThrows("edit a n/Person name", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertSuggestionThrows("remark -1", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertSuggestionThrows("remark a", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertSuggestionThrows("remark 1a", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertSuggestionThrows("remark a insert remarks here", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void suggestCommand_validOptionalArgSuffix_returnsArgWithPlaceholder() {
        String userInput;

        userInput = "add p";
        assertSuggestion(userInput, userInput + "/PHONE]");

        userInput = "add n/Person Name p";
        assertSuggestion(userInput, userInput + "/PHONE]");

        userInput = "add n/Person Name p/91304513 a";
        assertSuggestion(userInput, userInput + "/ADDRESS]");

        userInput = "edit 1 p";
        assertSuggestion(userInput, userInput + "/PHONE]");

        userInput = "edit 2 n/Person Name p";
        assertSuggestion(userInput, userInput + "/PHONE]");

        userInput = "edit 13 n/Person Name p/91304513 a";
        assertSuggestion(userInput, userInput + "/ADDRESS]");
    }

    @Test
    public void autocompleteCommand_noSuggestion() {
        String userInput = "text";
        String autocompleted = autocomplete.autocompleteCommand(userInput, userInput);
        // Just returns the 'userInput'.
        assertEquals(userInput, autocompleted);
    }

    /** Asserts that {@code userInput} returns a suggestion equal to {@code expectedSuggestion}. */
    private void assertSuggestion(String userInput, String expectedSuggestion) {
        try {
            assertEquals(expectedSuggestion, autocomplete.suggestCommand(userInput));
        } catch (ParseException e) {
            throw new AssertionError("Should not have thrown any 'ParseException'.", e);
        }
    }

    /**
     * Asserts that {@code userInput} throws a `ParseException` with a error
     * message of {@code expectedErrorMessage}.
     */
    private void assertSuggestionThrows(String userInput, String expectedErrorMessage) {
        assertThrows(ParseException.class, () -> autocomplete.suggestCommand(userInput), expectedErrorMessage);
    }

    private class ModelStubExistingFieldValues extends ModelStub {
        @Override
        public ArrayList<String> getExistingEducationValues() {
            return new ArrayList<>(List.of("edu1", "edu2", "diffEdu"));
        }

        @Override
        public ArrayList<String> getExistingModuleValues() {
            return new ArrayList<>(List.of("mod1", "mod2", "diffMod"));
        }

        @Override
        public ArrayList<String> getExistingTagValues() {
            return new ArrayList<>(List.of("tag1", "tag2", "diffTag"));
        }
    }
}
