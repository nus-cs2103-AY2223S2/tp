package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.testutil.ModelStub;

public class AutocompleteEngineTest {

    private final Model model = new ModelStubExistingFieldValues();
    private final AutocompleteEngine autocomplete = new AutocompleteEngine(model);

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
