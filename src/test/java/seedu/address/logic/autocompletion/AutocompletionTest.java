package seedu.address.logic.autocompletion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT_WITH_ID;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddApplicantCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteCommand;

public class AutocompletionTest {
    @Test
    public void testEmptyString() {
        Autocompletion autocompletion = new Autocompletion("");
        List<String> suggestions = autocompletion.getListOfSuggestions();
        assertTrue(suggestions.isEmpty());
    }

    @Test
    public void testSingleLetter() {
        Autocompletion autocompletion = new Autocompletion("a");
        List<String> suggestions = autocompletion.getListOfSuggestions();
        assertFalse(suggestions.isEmpty());
        assertTrue(suggestions.contains(AddCommand.COMMAND_WORD.substring(1)));
        assertTrue(suggestions.contains(AddApplicantCommand.COMMAND_WORD.substring(1)));
    }

    @Test
    public void testFullCommandWord() {
        Autocompletion autocompletion = new Autocompletion(DeleteCommand.COMMAND_WORD);
        List<String> suggestions = autocompletion.getListOfSuggestions();
        assertFalse(suggestions.isEmpty());
        assertTrue(suggestions.contains(" INDEX"));
    }

    @Test
    public void testQueryWithSpaces() {
        Autocompletion autocompletion = new Autocompletion("   " + DeleteCommand.COMMAND_WORD + " ");
        List<String> suggestions = autocompletion.getListOfSuggestions();
        assertFalse(suggestions.isEmpty());
        assertTrue(suggestions.contains("INDEX"));
    }
}
