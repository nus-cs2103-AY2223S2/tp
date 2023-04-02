package seedu.address.logic.autocompletion;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICANT_WITH_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLATFORM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.logic.commands.AddApplicantCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteApplicantCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ViewCommand;

/**
 * Autocomplete a given query.
 */
public class Autocompletion {
    private static final List<String> POSSIBLE_COMMAND_WORDS = Arrays.asList(
            AddCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD,
            AddApplicantCommand.COMMAND_WORD,
            DeleteApplicantCommand.COMMAND_WORD,
            FindCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD,
            ViewCommand.COMMAND_WORD
    );

    private static final Map<String, String> COMMAND_WORD_USAGES = new HashMap<>() {{
            put(AddApplicantCommand.COMMAND_WORD, "INDEX " + PREFIX_APPLICANT + "APPLICANT");
            put(AddCommand.COMMAND_WORD, PREFIX_TITLE + "TITLE "
                    + PREFIX_DESCRIPTION + "DESCRIPTION "
                    + PREFIX_APPLICANT + "APPLICANT"
                    + PREFIX_PLATFORM + "PLATFORM");
            put(DeleteApplicantCommand.COMMAND_WORD, "INDEX " + PREFIX_APPLICANT_WITH_ID + "APPLICANT#ID");
            put(DeleteCommand.COMMAND_WORD, "INDEX");
            put(EditCommand.COMMAND_WORD, "INDEX "
                    + PREFIX_TITLE + "TITLE "
                    + PREFIX_DESCRIPTION + "DESCRIPTION "
                    + PREFIX_APPLICANT + "APPLICANT"
                    + PREFIX_PLATFORM + "PLATFORM");
            put(FindCommand.COMMAND_WORD, "QUERY");
        }
    };

    private String query;
    private List<String> results;

    /**
     * Creates an autocompletion service.
     * @param query The input string to complete
     */
    public Autocompletion(String query) {
        this.query = query;
        this.results = new ArrayList<>();
    }

    /**
     * Obtains list of suggestions for the current query.
     * @return A list of possible continuation for the query
     */
    public List<String> getListOfSuggestions() {
        addSuggestionsForCommandWord();
        addSuggestionsForCommandWordUsage();

        results.sort(Comparator.comparingInt(String::length));

        return results;
    }

    private void addSuggestionsForCommandWord() {
        if (query.matches("(?i)^\\s*[a-zA-Z]+$")) {
            String trimmedToLowerQuery = query.trim().toLowerCase();
            for (String command : POSSIBLE_COMMAND_WORDS) {
                if (command.toLowerCase().equals(trimmedToLowerQuery)
                        && COMMAND_WORD_USAGES.containsKey(trimmedToLowerQuery)) {
                    results.add(" " + COMMAND_WORD_USAGES.get(trimmedToLowerQuery));
                } else if (command.toLowerCase().startsWith(trimmedToLowerQuery)) {
                    results.add(command.substring(trimmedToLowerQuery.length()));
                }
            }
        }
    }

    private void addSuggestionsForCommandWordUsage() {
        for (String key : COMMAND_WORD_USAGES.keySet()) {
            if (query.matches("(?i)^\\s*" + key + "\\s+$")) {
                results.add(COMMAND_WORD_USAGES.get(key));
            }
        }
    }
}
